package ru.matveycock.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import ru.matveycock.filmorate.exception.ValidationException;
import ru.matveycock.filmorate.model.Film;
import ru.matveycock.filmorate.model.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Slf4j
public class ValidationService {

    private static final LocalDate DATE = LocalDate.of(1895,12,28);
    private static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static void validateFilm(Film film) throws ValidationException {
        if(film.getId() == null){
            log.error("Фильм был введён без id");
            throw new ValidationException("Не введён id фильма");
        }else if(film.getName() == null){
            log.error("Name of film is null");
            throw new ValidationException("Name of film is null");
        }else if(film.getDuration() > 200){
            log.error("Description more than 200");
            throw new ValidationException("Description more than 200");
        }else if(film.getReleaseDate().isBefore(DATE)){
            log.error("RealeaseDate is before 28.12.1895");
            throw new ValidationException("RealeaseDate is before 28.12.1895");
        }else if(film.getDuration() < 0){
            log.error("Duration is negative");
            throw new ValidationException("Duration is negative");
        }
    }

    public static void validateUser(User user) throws ValidationException {
        if(user.getEmail() == null){
            log.error("Электронная почта is null");
            throw new ValidationException("Email is null");
        }else if(user.getLogin() == null){
            log.error("Login is null");
            throw new ValidationException("Login is null");
        }else if(user.getLogin().contains(" ")){
            log.error("Login contains \" \" " );
            throw new ValidationException("Login contains \" \" ");
        } else if (!Pattern
                .compile(REGEX_PATTERN)
                .matcher(user.getEmail())
                .matches()) {
            log.error("Электронная почта невалидна");
            throw new ValidationException("Электронная почта невалидна");
        }else if(user.getName() == null){
            log.error("Name is null" );
            setName(user);

        }else if (user.getBirthday().isAfter(LocalDate.now())) {
            log.error("День рождения должен быть в прошлом");
            throw new ValidationException("День рождения должен быть в прошлом");
        }
    }

    private static void setName(User user){
        user.setName(user.getLogin());
    }

}
