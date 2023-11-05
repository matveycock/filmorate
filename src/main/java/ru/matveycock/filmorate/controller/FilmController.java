package ru.matveycock.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.matveycock.filmorate.exception.ValidationException;
import ru.matveycock.filmorate.model.Film;
import ru.matveycock.filmorate.service.FilmService;
import ru.matveycock.filmorate.storage.FilmStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.matveycock.filmorate.service.ValidationService.validateFilm;

@Slf4j
@RestController
@Validated
public class FilmController {

    private final FilmService filmService;
    private final FilmStorage filmStorage;
    private final Map<Integer, Film> films = new HashMap();

    @Autowired
    public FilmController(@Qualifier("FilmDbStorage") FilmStorage filmStorage, FilmService filmService) {
        this.filmStorage = filmStorage;
        this.filmService = filmService;
    }


    @PutMapping("/films")
    public void updateFilm(@Valid @RequestBody Film film){
        //filmService.updateFilm(film);
        try{
            validateFilm(film);
            films.remove(film.getId());
            films.put(film.getId(), film);
            log.info("Обновили фильм с id = '{}'", film.getId());
        }catch (ValidationException exception){
            log.error("Film is not validated");
        }
    }


    @PostMapping("/films")
    public void addFilm(@Valid @RequestBody Film film){
        //filmService.addFilm(film);
        try{
            if(films.containsKey(film.getId())){
                log.error("Полученный фильм уже существует");
                throw new ValidationException("Данный фильм уже существует");
                }
            validateFilm(film);
            films.put(film.getId(), film);
            log.info("Добавили новый фильм с id = '{}'", film.getId());
        }catch (ValidationException exception){
            log.error("Film is not validated");
        }
    }

    @GetMapping("/films")
    public List<Film> getAllFilms(){
        //filmService.getAllFilm(film);
        log.info("Был выполнен запрос всех фильмов");
        return new ArrayList<Film>(films.values());
    }
}
