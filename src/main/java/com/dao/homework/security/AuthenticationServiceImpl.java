package com.dao.homework.security;

import com.dao.homework.exceptions.AuthenticationException;
import com.dao.homework.lib.Inject;
import com.dao.homework.lib.Service;
import com.dao.homework.model.Driver;
import com.dao.homework.service.DriverService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) {
        Driver driverFromDb = driverService.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("Incorrect username or password"));
        if (driverFromDb.getPassword().equals(password)
                && driverFromDb.getLogin().equals(login)) {
            return driverFromDb;
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
