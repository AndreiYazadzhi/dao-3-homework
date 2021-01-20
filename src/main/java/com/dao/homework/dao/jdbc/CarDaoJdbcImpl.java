package com.dao.homework.dao.jdbc;

import com.dao.homework.dao.CarDao;
import com.dao.homework.exceptions.DataProcessingException;
import com.dao.homework.lib.Dao;
import com.dao.homework.model.Car;
import com.dao.homework.model.Driver;
import com.dao.homework.model.Manufacturer;
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
public class CarDaoJdbcImpl implements CarDao {

    @Override
    public Car create(Car car) {
        String query = "INSERT INTO cars(cars_model,manufacturer_id) "
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, car.getModel());
            statement.setLong(2, car.getManufacturer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                car.setId(resultSet.getObject(1, Long.class));
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t insert this car:" + car + "into DB ", e);
        }

    }

    @Override
    public Optional<Car> getById(Long carId) {
        String query = "SELECT d.drivers_id, d.drivers_name, d.license_number,\n"
                + "c.cars_id,c.cars_model,drivers_id,m.manufacturer_id,"
                + "m.manufacturer_name,m.manufacturer_country\n"
                + "FROM cars c\n"
                + "LEFT JOIN drivers_cars dc\n"
                + "ON c.cars_id = dc.car_id\n"
                + "LEFT JOIN drivers d\n"
                + "ON c.cars_id = dc.car_id\n"
                + "LEFT JOIN manufacturer m\n"
                + "ON c.manufacturer_id = m.manufacturer_id\n"
                + " WHERE c.cars_id = ? AND c.deleted = false"
                + " AND d.deleted = false";
        Car car = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, carId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                car = getCarInstance(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get data with id:"
                    + carId + " from DB ", e);
        }
        return Optional.ofNullable(car);
    }

    @Override
    public Car update(Car car) {
        String updateQuery = "UPDATE cars SET manufacturer_id = ?, cars_model = ? "
                + "WHERE cars_id = ? AND deleted = false";
        String deleteDriversQuery = "DELETE FROM drivers_cars WHERE car_id = ?";
        String insertDriversQuery = "INSERT INTO drivers_cars(driver_id, car_id) VALUES (?, ?)";
        Long carId = car.getId();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement updateCarStmt = connection.prepareStatement(updateQuery);
                PreparedStatement deleteDriversStmt =
                          connection.prepareStatement(deleteDriversQuery);
                PreparedStatement insertDriversStmt =
                          connection.prepareStatement(insertDriversQuery)) {
            updateCarStmt.setLong(1, car.getManufacturer().getId());
            updateCarStmt.setString(2, car.getModel());
            updateCarStmt.setLong(3, carId);
            updateCarStmt.executeUpdate();
            deleteDriversStmt.setLong(1, carId);
            deleteDriversStmt.executeUpdate();
            for (Driver driver : car.getDrivers()) {
                insertDriversStmt.setLong(1, driver.getId());
                insertDriversStmt.setLong(2, carId);
                insertDriversStmt.executeUpdate();
            }
            return car;
        } catch (SQLException e) {
            throw new DataProcessingException("We can`t update this car:" + car, e);
        }
    }

    @Override
    public boolean delete(Long carsId) {
        String query = "UPDATE cars SET deleted"
                + "= ?  WHERE cars_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            statement.setLong(2, carsId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t delete data with id:"
                    + carsId + " from DB ", e);
        }
    }

    @Override
    public List<Car> getAll() {
        String query = "SELECT d.drivers_id, d.drivers_name, d.license_number,\n"
                + "c.cars_id,c.cars_model,drivers_id,m.manufacturer_id,"
                + "m.manufacturer_name,m.manufacturer_country\n"
                + "FROM cars c\n"
                + "LEFT JOIN drivers_cars dc\n"
                + "ON c.cars_id = dc.car_id\n"
                + "LEFT JOIN drivers d\n"
                + "ON c.cars_id = dc.car_id AND d.deleted = false\n"
                + "LEFT JOIN manufacturer m\n"
                + "ON c.manufacturer_id = m.manufacturer_id "
                + "WHERE c.deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<Car> allCars = new ArrayList<>();
            while (resultSet.next()) {
                allCars.add(getCarInstance(resultSet));
            }
            return allCars;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t get all data from DB ", e);
        }
    }

    public List<Car> getAllByDriver(Long driverId) {
        String query = "SELECT d.drivers_id, d.drivers_name, d.license_number,\n"
                + "c.cars_id,c.cars_model,drivers_id,m.manufacturer_id,"
                + "m.manufacturer_name,m.manufacturer_country\n"
                + "FROM cars c\n"
                + "LEFT JOIN drivers_cars dc\n"
                + "ON c.cars_id = dc.car_id\n"
                + "LEFT JOIN drivers d\n"
                + "ON c.cars_id = dc.car_id\n"
                + "LEFT JOIN manufacturer m\n"
                + "ON c.manufacturer_id = m.manufacturer_id\n"
                + "WHERE d.drivers_id = ?"
                + " AND d.deleted = false"
                + " AND c.deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, driverId);
            ResultSet resultSet = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                cars.add(getCarInstance(resultSet));
            }
            return cars;
        } catch (SQLException e) {
            throw new DataProcessingException("We can't get data for this driver:" + driverId, e);
        }
    }

    public Manufacturer getManufacturerInstance(ResultSet resultSet) throws SQLException {
        Manufacturer manufacturer = new Manufacturer(resultSet
                .getObject("manufacturer_name", String.class),
                resultSet.getObject("manufacturer_country", String.class));
        manufacturer.setId(resultSet.getObject("manufacturer_id", Long.class));
        return manufacturer;
    }

    public Driver getDriverInstance(ResultSet resultSet) throws SQLException {
        Driver driver = new Driver(resultSet
                .getObject("drivers_name", String.class),
                resultSet.getObject("license_number", String.class));
        driver.setId(resultSet.getObject("drivers_id", Long.class));
        return driver;
    }

    public Car getCarInstance(ResultSet resultSet) throws SQLException {
        Car car = new Car(resultSet.getObject("cars_model", String.class),
                getManufacturerInstance(resultSet));
        car.setId(resultSet.getObject("cars_id", Long.class));
        List<Driver> drivers = getDrivers(resultSet, car.getId());
        car.setDrivers(drivers);
        return car;
    }

    private List<Driver> getDrivers(ResultSet resultSet, Long carId) throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        do {
            Driver driver = getDriverInstance(resultSet);
            drivers.add(driver);
        } while (resultSet.next() && resultSet.getObject("cars_id", Long.class).equals(carId));
        resultSet.previous();
        return drivers;
    }
}
