package ru.kpfu.servlets.Repositories.impl;

import ru.kpfu.servlets.Repositories.TableDAO;
import ru.kpfu.servlets.models.Tables;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TableDAOImpl implements TableDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/ORIS";
    private static final String USER = "postgres";
    private static final String PASSWORD = "vimer_401";
    public void downloadingDrivers() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Tables> getAllTables(){
        downloadingDrivers();
        List<Tables> tables = new ArrayList();
        String sql = "select * from tables";
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int tableNumber = resultSet.getInt("table_number");
                int seatingCapacity = resultSet.getInt("seating_capacity");
                String location = resultSet.getString("location");
                tables.add(Tables.builder()
                        .id(id)
                        .tableNumber(tableNumber)
                        .seatingCapacity(seatingCapacity)
                        .location(location)
                        .build());
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tables;
    }
    public boolean isTableAvailable(int tableId, LocalDate bookingDate, LocalTime bookingTime){
        String sql = "select * from bookings where table_id=? and booking_date=? and booking_time=?";
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tableId);
            preparedStatement.setObject(2, bookingDate);  // LocalDate автоматически преобразуется в java.sql.Date
            preparedStatement.setObject(3, bookingTime);  // LocalTime автоматически преобразуется в java.sql.Time
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
