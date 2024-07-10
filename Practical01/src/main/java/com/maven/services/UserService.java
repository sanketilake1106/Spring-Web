package com.maven.services;

import com.maven.entities.User;
import org.springframework.stereotype.Component;

import javax.swing.plaf.LabelUI;
import java.io.Serializable;
import java.util.List;

@Component
public interface UserService {

    Serializable addUser(User user);

    User loginUser(User user);

    User getUser(Long id);

    List<User> getAllUser();

    void updateUser(User user);

    void update1User(User user);

    void deleteUser(User user);

}
