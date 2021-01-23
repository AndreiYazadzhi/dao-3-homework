package com.dao.homework.security;

import com.dao.homework.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password);
}
