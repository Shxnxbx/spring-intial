package restservice.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class Review {

    private LocalDateTime date;

    @Schema(description = "Range: 0-5", example = "3")
    private int score;

    @Schema(example = "foo")
    private String comment;


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }



    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}