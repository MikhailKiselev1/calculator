package vacationCalculator.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class HolidayService {

    private static int currentYear = LocalDate.now().getYear();
    private static final List<LocalDate> holidays = Arrays.asList(
            LocalDate.of(currentYear, 1, 1),  // Новый год
            LocalDate.of(currentYear, 1, 7),  // Православное Рождество
            LocalDate.of(currentYear, 2, 23), // День защитника Отечества
            LocalDate.of(currentYear, 3, 8),  // Международный женский день
            LocalDate.of(currentYear, 5, 1),  // Праздник Весны и Труда
            LocalDate.of(currentYear, 5, 9),  // День Победы
            LocalDate.of(currentYear, 6, 12), // День России
            LocalDate.of(currentYear, 11, 4) // День народного единства
    );
    public boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6; // Пятница (6) и выходные (7) считаются выходными днями
    }

    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }
}
