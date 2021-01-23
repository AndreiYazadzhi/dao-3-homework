package com.dao.homework.dao;

import com.dao.homework.model.Driver;
import java.util.Optional;

public interface DriverDao extends GenericDao<Driver, Long> {
    Optional<Driver> getByLogin(String login);
}
