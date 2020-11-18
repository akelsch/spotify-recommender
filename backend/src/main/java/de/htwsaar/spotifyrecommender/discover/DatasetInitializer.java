package de.htwsaar.spotifyrecommender.discover;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
@Profile("init")
@RequiredArgsConstructor
public class DatasetInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final ObjectMapper objectMapper;
    private final PlaylistService playlistService;
    private final TrackRepository trackRepository;

    @Value("${de.htwsaar.spotifyrecommender.datasetPath}")
    private String datasetPath;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Flux.fromStream(findAllJsonFiles())
                .log()
                .map(this::deserializeFile)
                .flatMap(this::saveToDatabase)
                .subscribe();
    }

    @SneakyThrows
    private Stream<File> findAllJsonFiles() {
        Path dir = Paths.get(datasetPath);
        return Files.find(dir, 1, (path, attr) -> path.toString().endsWith(".json")).map(Path::toFile);
    }

    @SneakyThrows
    private Tuple2<List<Playlist>, List<Track>> deserializeFile(File file) {
        JsonNode jsonNode = objectMapper.readTree(file);

        List<Playlist> playlistList = new ArrayList<>();
        List<Track> trackList = new ArrayList<>();

        ArrayNode playlists = jsonNode.withArray("playlists");
        for (JsonNode p : playlists) {
            Playlist playlist = objectMapper.treeToValue(p, Playlist.class);
            playlistList.add(playlist);

            ArrayNode tracks = p.withArray("tracks");
            for (JsonNode t : tracks) {
                Track track = objectMapper.treeToValue(t, Track.class);
                track.setPid(playlist.getPid());
                trackList.add(track);
            }
        }

        return Tuples.of(playlistList, trackList);
    }

    private Mono<Void> saveToDatabase(Tuple2<List<Playlist>, List<Track>> data) {
        return playlistService.insertAll(data.getT1())
                .thenMany(trackRepository.saveAll(data.getT2()))
                .then();
    }
}
