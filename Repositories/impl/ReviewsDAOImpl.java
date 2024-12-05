package ru.kpfu.servlets.Repositories.impl;

import ru.kpfu.servlets.Repositories.ReviewsDAO;
import ru.kpfu.servlets.models.Reviews;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDAOImpl implements ReviewsDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/ORIS";
    private static final String USER = "postgres";
    private static final String PASSWORD = "vimer_401";

    private static final String INSERT_REVIEW_SQL = "INSERT INTO reviews (rating, comment, created) VALUES (?, ?, ?)";
    private static final String DELETE_REVIEW_SQL = "DELETE FROM reviews WHERE id = ?";
    private static final String UPDATE_REVIEW_SQL = "UPDATE reviews SET rating = ?, comment = ?, created = ? WHERE id = ?";
    private static final String SELECT_REVIEWS_SQL = "SELECT id, rating, comment, created FROM reviews";

    @Override
    public void save(Reviews review) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REVIEW_SQL)) {

            preparedStatement.setInt(1, review.getRating());
            preparedStatement.setString(2, review.getComment());
            preparedStatement.setObject(3, review.getCreatedAt());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save review", e);
        }
    }

    @Override
    public void update(Reviews review) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REVIEW_SQL)) {

            preparedStatement.setInt(1, review.getRating());
            preparedStatement.setString(2, review.getComment());

            // setObject для LocalDateTime
            preparedStatement.setObject(3, review.getCreatedAt());
            preparedStatement.setInt(4, review.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update review", e);
        }
    }

    @Override
    public void delete(Reviews review) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW_SQL)) {

            preparedStatement.setInt(1, review.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete review", e);
        }
    }


    @Override
    public List<Reviews> getAll() {
        List<Reviews> reviews = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEWS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Reviews review = Reviews.builder()
                        .id(resultSet.getInt("id"))
                        .rating(resultSet.getInt("rating"))
                        .comment(resultSet.getString("comment"))
                        .createdAt(resultSet.getObject("created_at", LocalDateTime.class)) // Для LocalDateTime используем getObject
                        .build();
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get all reviews", e);
        }
        return reviews;
    }

}
