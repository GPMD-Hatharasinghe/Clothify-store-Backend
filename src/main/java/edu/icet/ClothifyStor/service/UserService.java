package edu.icet.ClothifyStor.service;

import edu.icet.ClothifyStor.model.User;

import java.util.List;

public interface UserService {

    boolean updateUser(User user);
    User save(User user);
    List<User> read();
    String auth(User loginRequest);
    String checkToken(String token);
}
