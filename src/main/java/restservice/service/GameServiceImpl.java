package restservice.service;

import org.springframework.stereotype.Service;
import restservice.models.Game;
import restservice.models.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final List<Game> games = new ArrayList<>();

    @Override
    public Game updateGame(Game game) {
        deleteGame(game.getId());
        games.add(game);
        return game;
    }

    @Override
    public Game addGame(Game game) {
        games.add(game);
        return game;
    }

    @Override
    public List<Game> findGamesByTitle(String title) {
        return games.stream()
                .filter(game -> game.getTitle().toLowerCase().contains(title.toLowerCase().replace("*", "")))
                .collect(Collectors.toList());
    }
    @Override
    public List<Game> findGamesByTags(Genre genre) {
        return games.stream()
                .filter(game -> game.getGenre().equals(genre))
                .collect(Collectors.toList());
    }
    @Override
    public Game getGameById(String id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteGame(String id) {
        games.removeIf(game -> game.getId().equals(id));
    }
}
