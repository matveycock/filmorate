package ru.matveycock.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    private final Map<Long, FriendshipStatus> friends = new HashMap<>();

    public void addFriend(long userId, FriendshipStatus status) {
        friends.put(userId, status);
    }

    public void deleteFriend(long userId) {
        friends.remove(userId);
    }

    public Set<Long> getFriends() {
        return new HashSet<>(friends.keySet());
    }
}
