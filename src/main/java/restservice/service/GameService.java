package restservice.service;

import org.springframework.stereotype.Service;
import restservice.models.Game;
import restservice.models.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing games
 */
@Service
public class GameService {

    private final List<Game> games = new ArrayList<>();

    /**
     * Updates an existing game
     * @param game the game to update
     * @return updated game
     */
    public Game updateGame(Game game) {
        deleteGame(game.getId());
        games.add(game);
        return game;
    }

    /**
     * Add new game
     * @param game the game to add
     * @return added game
     */
    public Game addGame(Game game) {
        games.add(game);
        return game;
    }

    /**
     *  Finds games by tittle
     * @param title the tittle to search for
     * @return list of game matching tittle
     */
    public List<Game> findGamesByTitle(String title) {
        return games.stream()
                .filter(game -> game.getTitle().toLowerCase().contains(title.toLowerCase().replace("*", "")))
                .collect(Collectors.toList());
    }

    /**
     *  Finds games by genre
     * @param genre the genre to search for
     * @return list of game matching genre
     */
    public List<Game> findGamesByTags(Genre genre) {
        return games.stream()
                .filter(game -> game.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    /**
     * Gets a game by its ID.
     *
     * @param id the ID of the game
     * @return game with same ID, or null if not found
     */
    public Game getGameById(String id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Deletes a game by its ID.
     *
     * @param id the ID of the game to delete
     */
    public void deleteGame(String id) {
        games.removeIf(game -> game.getId().equals(id));
    }
}