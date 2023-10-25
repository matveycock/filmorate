package ru.matveycock.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class Film {

    private Integer id;

    @NotNull
    @NotBlank(message = "Название фильма пустое")
    private String name;

    @Size(max = 200, message = "Описание не более 200 символов")
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;

    @NotNull
    @Positive(message = "Продолжительность фильма отрицательна")
    private Integer duration;

    private Set<Genre> genres;

    @NotNull
    @JsonProperty("mpa")
    private MpaRating mpa;
}
