package restservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restservice.models.Game;
import restservice.models.Genre;
import restservice.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // UPDATE GAME
    @PutMapping
    @Operation(summary = "Update an existing game", description = "Update an existing game by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Game.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation exception", content = @Content)
    })
    public ResponseEntity<Game> updateGame(@RequestBody @Valid Game game) {
        Game updatedGame = gameService.updateGame(game);
        return ResponseEntity.ok(updatedGame);
    }

    // ADD GAME
    @PostMapping
    @Operation(summary = "Add a new game to the store", description = "Add a new game to the store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Game.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "422", description = "Validation exception", content = @Content)
    })
    public ResponseEntity<Game> addGame(@RequestBody @Valid Game game) {
        Game newGame = gameService.addGame(game);
        return ResponseEntity.ok(newGame);
    }

    // FIND GAME BY TITLE
    @GetMapping("/findByTitle")
    @Operation(summary = "Find games by status", description = "Search games by title")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Game.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid status value", content = @Content)
    })
    public ResponseEntity<List<Game>> findGamesByTitle(
            @RequestParam
            @Parameter(description = "Write title in lower case and with wildcard (*)", example = "*dog*") String title) {
        List<Game> games = gameService.findGamesByTitle(title);
        return ResponseEntity.ok(games);
    }

    // FIND GAME BY GENRE
    @GetMapping("/findByGenre")
    @Operation(summary = "Find games by tags", description = "Search games by genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Game.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid tag value", content = @Content)
    })
    public ResponseEntity<List<Game>> findGamesByTags(
            @RequestParam @Parameter(description = "Genre of games") Genre tags) {
        List<Game> games = gameService.findGamesByTags(tags);
        return ResponseEntity.ok(games);
    }

    // FIND GAME BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Find game by ID", description = "Returns a single game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Game.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content)
    })
    public ResponseEntity<Game> getGameById(
            @PathVariable @Parameter(description = "ID of game to return") String id) {
        Game game = gameService.getGameById(id);
        return ResponseEntity.ok(game);
    }

    // UPDATE GAME BY ID
    @PostMapping("/{id}")
    @Operation(summary = "Update game in the store with form data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Void> updateGameWithForm(
            @PathVariable @Parameter(description = "ID of game that needs to be updated") String id) {
        return ResponseEntity.ok().build();
    }

    // DELETE GAME BY ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a game", description = "Deletes a game from the store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid game value")
    })
    public ResponseEntity<Void> deleteGame(
            @PathVariable @Parameter(description = "Game id to delete") String id) {
        gameService.deleteGame(id);
        return ResponseEntity.badRequest().build();

    }
}