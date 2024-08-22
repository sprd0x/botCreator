package com.azizsaparniyazov.services;


import com.azizsaparniyazov.models.Transaction;
import com.azizsaparniyazov.models.User;
import com.azizsaparniyazov.services.utilServices.JsonReader;
import com.azizsaparniyazov.services.utilServices.JsonWriter;
import com.fasterxml.jackson.core.type.TypeReference;

import java.math.BigDecimal;
import java.util.List;

//TODO Xurshid
public class UserService {

    private static final String PATH = "src/main/java/com/azizsaparniyazov/services/Users.json";

    public User register(String userId, String name, String botName, String botToken){
        List<User> users = read();
        if (!hasUser(users, userId)){
            User user = new User();

            user.setId(userId);
            user.setName(name);
            user.setBotName(botName);
            user.setBotToken(botToken);
            users.add(user);
            write(users);
            return user;

        }

        throw new RuntimeException("User already exists!");
    }

    private boolean hasUser(List<User> users, String userId) {
        return users.stream()
                .anyMatch(user -> user.getId().equals(userId));
    }

    private void getUser(String userId){
        List<User> users = read();
        users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(()->new RuntimeException(""));
    }

    public void deleteUser(String userId){
        List<User> users = read();
        users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .ifPresent(users::remove);
        write(users);
    }

    public User addBalance(double amount, String userId){
        List<User> users = read();
        User user = users.stream()
                .filter(user1 -> user1.getId().equals(userId))
                .findFirst()
                .orElseThrow(()->new RuntimeException());
        user.setBalance(user.getBalance().add(new BigDecimal(amount)));
        return user;
    }

    public User reduceBalance(double amount, String userId){
        List<User> users = read();
        User user = users.stream()
                .filter(user1 -> user1.getId().equals(userId))
                .findFirst()
                .orElseThrow(()->new RuntimeException());
        user.setBalance(user.getBalance().subtract(new BigDecimal(amount)));
        return user;
    }

    public List<User> showUser(){
        return read();
    }

    private List<User> read() {
        return JsonReader.readGson(PATH, new TypeReference<List<User>>() {});
    }

    private void write(List<User> users) {
        JsonWriter.writeGson(users, PATH);
    }
}
