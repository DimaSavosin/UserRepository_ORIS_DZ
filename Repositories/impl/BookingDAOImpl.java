package ru.kpfu.servlets.Repositories.impl;

import ru.kpfu.servlets.Repositories.BookingDAO;
import ru.kpfu.servlets.models.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/ORIS";
    private static final String USER = "postgres";
    private static final String PASSWORD = "vimer_401";


    private static final String INSERT_BOOKING_SQL = "insert into bookings (user_id, table_id, booking_date, booking_time, status) values (?,?,?,?,?)";
    private static final String DELETE_BOOKING_SQL = "delete from bookings where id =?";
    private static final String UPDATE_BOOKING_SQL = "update bookings set user_id = ?, table_id = ?, booking_date = ?, booking_time = ?, status = ? where id = ? ";
    private static final String SELECT_BOOKING_SQL = "select * from bookings";

    public void downloadingDrivers() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void save(Booking booking){
        downloadingDrivers();
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING_SQL)){
            preparedStatement.setInt(1,booking.getUser_id());
            preparedStatement.setInt(2,booking.getTableId());
            preparedStatement.setObject(3,booking.getBookingDate());
            preparedStatement.setObject(4,booking.getBookingTime());
            preparedStatement.setString(5, booking.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Booking booking) {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING_SQL);
            preparedStatement.setInt(1,booking.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Booking booking) {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING_SQL);
            preparedStatement.setInt(1,booking.getUser_id());
            preparedStatement.setInt(2,booking.getTableId());
            preparedStatement.setObject(3,booking.getBookingDate());
            preparedStatement.setObject(4,booking.getBookingTime());
            preparedStatement.setString(5,booking.getStatus());
            preparedStatement.setInt(6,booking.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getAll() {
        List<Booking> bookings = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int tableId = resultSet.getInt("table_id");
                LocalDate bookingDate = resultSet.getObject("booking_date", LocalDate.class);
                LocalTime bookingTime = resultSet.getObject("booking_time", LocalTime.class);
                String status = resultSet.getString("status");
                bookings.add(Booking.builder()
                        .id(id)
                        .user_id(userId)
                        .tableId(tableId)
                        .bookingDate(bookingDate)
                        .bookingTime(bookingTime)
                        .status(status)
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }
}
