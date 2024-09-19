package restservice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public class Game {


    @NotNull
    @Schema(description = "Unique identifier. Always in lower case and snake case", example = "watch_dogs")
    private String id;
    @NotNull
    @Schema(description = "Title of the game", example = "Watch Dogs")
    private String title;

    @NotNull
    @Schema(description = "Iso format")
    private LocalDateTime launchDate;


    @NotNull
    @Schema()
    private Genre genre;

    @Max(5)
    @Schema(description = "Range: 0-5", example = "3")
    private int score;

    private List<Review> reviews;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}