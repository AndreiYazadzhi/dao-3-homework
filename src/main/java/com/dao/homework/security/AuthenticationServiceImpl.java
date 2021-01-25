package com.dao.homework.security;

import com.dao.homework.exceptions.AuthenticationException;
import com.dao.homework.lib.Inject;
import com.dao.homework.lib.Service;
import com.dao.homework.model.Driver;
import com.dao.homework.service.DriverService;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) {
        Optional<Driver> driverFromDb = driverService.findByLogin(login);
        if (driverFromDb.isPresent() && driverFromDb.get()
                .getPassword().equals(password)
                && driverFromDb.get().getLogin().equals(login)) {
            return driverFromDb.get();
        }
        throw new AuthenticationException("Incorrect username or password");
    }
}
