package ru.kpfu.servlets.Repositories;

import ru.kpfu.servlets.models.Tables;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TableDAO {
    List<Tables> getAllTables();
    boolean isTableAvailable(int tableId, LocalDate bookingDate, LocalTime bookingTime);

}
