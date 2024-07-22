package restservice.service;

import restservice.models.Game;
import restservice.models.Genre;

import java.util.List;

public interface GameService {
    Game updateGame(Game game);
    Game addGame(Game game);
    List<Game> findGamesByTitle(String title);
    List<Game> findGamesByTags(Genre genre);
    Game getGameById(String id);
    void deleteGame(String id);
}
