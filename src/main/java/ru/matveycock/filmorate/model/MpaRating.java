package ru.matveycock.filmorate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MpaRating {

    @NotNull
    @NotBlank
    @Positive
    @JsonProperty("id")
    private final int id;

    @NotNull
    @NotBlank
    private String name;
}

