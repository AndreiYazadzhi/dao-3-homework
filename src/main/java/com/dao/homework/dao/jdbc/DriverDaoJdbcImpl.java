package com.dao.homework.dao.jdbc;

import com.dao.homework.dao.DriverDao;
import com.dao.homework.exceptions.DataProcessingException;
import com.dao.homework.lib.Dao;
import com.dao.homework.model.Driver;
import com.dao.homework.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class DriverDaoJdbcImpl implements DriverDao {
    private static final String TABLE_NAME = "drivers";
    private static final String DRIVERS_NAME = "drivers_name";
    private static final String LICENSE_NUMBER = "license_number";
    private static final String DRIVERS_ID = "drivers_id";
    private static final String DELETED = "deleted";

    @Override
    public Driver create(Driver driver) {
        String query = "INSERT INTO " + TABLE_NAME
                + " (" + DRIVERS_NAME + "," + LICENSE_NUMBER + ") VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getObject(1, Long.class));
            }
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("This data:" + driver
                    + "can't be added to table ", e);
        }
    }

    @Override
    public Optional<Driver> getById(Long driverId) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + DRIVERS_ID
                + "=? AND " + DELETED + " = false";
        Driver driver = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                driver = getDriver(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get data with id:"
                    + driverId + " from DB ", e);
        }
        return Optional.ofNullable(driver);
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE " + TABLE_NAME + " SET " + DRIVERS_NAME
                + "= ?, " + LICENSE_NUMBER + "= ? "
                + " WHERE " + DRIVERS_ID
                + "= ? AND " + DELETED + " = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.setLong(3, driver.getId());
            statement.executeUpdate();
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t update this data: " + driver, e);
        }
    }

    @Override
    public boolean delete(Long driverId) {
        String query = "UPDATE " + TABLE_NAME + " SET " + DELETED
                + "= ?  WHERE " + DRIVERS_ID
                + "= ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            statement.setLong(2, driverId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t delete data with id:"
                    + driverId + " from DB ", e);
        }
    }

    @Override
    public List<Driver> getAll() {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + DELETED + " = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Driver> allDrivers = new ArrayList<>();
            while (resultSet.next()) {
                allDrivers.add(getDriver(resultSet));
            }
            return allDrivers;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t get all data from DB ", e);
        }
    }

    private Driver getDriver(ResultSet resultSet) throws SQLException {
        Driver drivers = new Driver(resultSet
                .getObject(DRIVERS_NAME, String.class),
                resultSet.getObject(LICENSE_NUMBER, String.class));
        drivers.setId(resultSet.getObject(DRIVERS_ID, Long.class));
        return drivers;
    }

}
