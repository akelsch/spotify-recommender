package de.htwsaar.spotifyrecommender.discover;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("init")
@RequiredArgsConstructor
public class DatasetInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final ObjectMapper objectMapper;
    private final PlaylistService playlistService;
    private final TrackRepository trackRepository;

    @Override
    @SneakyThrows
    public void onApplicationEvent(ApplicationReadyEvent event) {
        JsonParser parser = objectMapper.createParser(new File("C:\\Users\\Arthur\\Downloads\\spotify\\test.json"));
        JsonNode jsonNode = parser.readValueAsTree();

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

        playlistService.insertAll(playlistList)
                .thenMany(trackRepository.saveAll(trackList))
                .subscribe();
    }
}
