package ru.matveycock.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {

    private Integer id;

    @Email(message = "Электронная почта невалидна")
    @NotNull
    @NotBlank(message = "Адрес почты пустой")
    private String email;

    @NotNull
    @NotBlank(message = "Логин пустой")
    private String login;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Past(message = "День рождения должен быть в прошлом")
    private LocalDate birthday;

}
