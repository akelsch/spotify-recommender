package de.htwsaar.spotifyrecommender.dataset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Converts the Spotify Million Playlist Dataset from JSON to CSV so it can be imported into Postgres.
 */
@Component
@Profile("init")
public class DatasetConverter implements ApplicationListener<ApplicationReadyEvent> {

    private final ObjectMapper objectMapper;
    private final CsvMapper csvMapper;

    @Value("${de.htwsaar.spotifyrecommender.datasetPath}")
    private String datasetPath;

    @Autowired
    public DatasetConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.csvMapper = new CsvMapper();
        csvMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        csvMapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        csvMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initDirs();
        Flux.fromStream(findAllJsonFiles())
                .log()
                .map(this::deserializeFile)
                .subscribe(this::writeCsvFiles);
    }

    private void initDirs() {
        File dir = Paths.get(datasetPath, "csv").toFile();
        FileSystemUtils.deleteRecursively(dir);
        dir.mkdir();
    }

    @SneakyThrows
    private Stream<File> findAllJsonFiles() {
        Path dir = Paths.get(datasetPath);
        return Files.find(dir, 1, (path, attr) -> path.toString().endsWith(".json")).map(Path::toFile);
    }

    @SneakyThrows
    private Tuple2<List<PlaylistEntity>, List<TrackEntity>> deserializeFile(File file) {
        JsonNode jsonNode = objectMapper.readTree(file);

        List<PlaylistEntity> playlistList = new ArrayList<>();
        List<TrackEntity> trackList = new ArrayList<>();

        ArrayNode playlists = jsonNode.withArray("playlists");
        for (JsonNode p : playlists) {
            PlaylistEntity playlist = objectMapper.treeToValue(p, PlaylistEntity.class);
            playlistList.add(playlist);

            ArrayNode tracks = p.withArray("tracks");
            for (JsonNode t : tracks) {
                TrackEntity track = objectMapper.treeToValue(t, TrackEntity.class);
                track.setPid(playlist.getPid());
                trackList.add(track);
            }
        }

        return Tuples.of(playlistList, trackList);
    }

    @SneakyThrows
    private void writeCsvFiles(Tuple2<List<PlaylistEntity>, List<TrackEntity>> objects) {
        File playlistsFile = Paths.get(datasetPath, "csv", "playlists.csv").toFile();
        String tracksFilename = "tracks%d.csv".formatted(objects.getT1().get(0).getPid());
        File tracksFile = Paths.get(datasetPath, "csv", tracksFilename).toFile();

        CsvSchema playlistSchema;
        if (playlistsFile.exists()) {
            playlistSchema = csvMapper.schemaFor(PlaylistEntity.class).withoutHeader();
        } else {
            playlistSchema = csvMapper.schemaFor(PlaylistEntity.class).withHeader();
        }

        csvMapper.writer()
                .with(playlistSchema)
                .writeValue(new FileOutputStream(playlistsFile, true), objects.getT1());

        CsvSchema trackSchemaWithoutId = CsvSchema.builder()
                .addColumn("pid")
                .addColumn("track_uri")
                .addColumn("artist_uri")
                .addColumn("album_uri")
                .addColumn("pos")
                .build();

        csvMapper.writer()
                .with(trackSchemaWithoutId.withHeader())
                .writeValue(tracksFile, objects.getT2());
    }
}
