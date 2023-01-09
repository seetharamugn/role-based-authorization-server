package com.seetharamu.authserver.service;



import com.seetharamu.authserver.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
}
