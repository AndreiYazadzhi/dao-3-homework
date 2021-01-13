package com.dao.homework.dao.jdbc;

import com.dao.homework.dao.ManufacturerDao;
import com.dao.homework.exceptions.DataProcessingException;
import com.dao.homework.lib.Dao;
import com.dao.homework.model.Manufacturer;
import com.dao.homework.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ManufacturerJdbcDaoImpl implements ManufacturerDao {
    private static final String TABLE_NAME = "manufacturer";
    private static final String MANUFACTURER_NAME = "manufacturer_name";
    private static final String MANUFACTURER_COUNTRY = "manufacturer_country";
    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String DELETED = "deleted";

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String query = "INSERT INTO manufacturers "
                + "(" + MANUFACTURER_NAME + "," + MANUFACTURER_COUNTRY + ") VALUES(?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("This data can't be added to table ", e);
        }
    }

    @Override
    public Optional<Manufacturer> getById(Long manufacturerId) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + MANUFACTURER_ID
                + "= ? AND " + DELETED + " = false";
        Manufacturer manufacturer = new Manufacturer();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                manufacturer = new Manufacturer(resultSet.getObject(MANUFACTURER_ID, String.class),
                        resultSet.getObject(MANUFACTURER_NAME, String.class));
                manufacturer.setId(manufacturerId);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get data with id:"
                    + manufacturerId + "from DB ", e);
        }
        return Optional.ofNullable(manufacturer);
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE " + TABLE_NAME + " SET " + MANUFACTURER_NAME
                + "= ?, " + MANUFACTURER_COUNTRY + "= ?, "
                + " WHERE " + MANUFACTURER_ID
                + "= ? AND " + DELETED + " = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setLong(3, manufacturer.getId());
            statement.executeUpdate();
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t update this data ", e);
        }
    }

    @Override
    public boolean delete(Long manufacturerId) {
        String query = "UPDATE " + TABLE_NAME + " SET " + DELETED
                + "= ?,  WHERE " + MANUFACTURER_ID
                + "= ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            statement.setLong(2, manufacturerId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t delete data with id:"
                    + manufacturerId + " from DB ", e);
        }
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + DELETED + " = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Manufacturer> allManufacturers = new ArrayList<>();
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer(resultSet.getObject(MANUFACTURER_ID, String.class),
                        resultSet.getObject(MANUFACTURER_NAME, String.class));
                allManufacturers.add(manufacturer);
            }
            return allManufacturers;
        } catch (SQLException e) {
            throw new RuntimeException("Can`t established connection to DB ", e);
        }
    }
}
