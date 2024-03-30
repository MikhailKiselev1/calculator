package vacationCalculator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vacationCalculator.model.dto.request.VacationRequest;
import vacationCalculator.repository.EmployeeRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VacationCalculatorService {

    private final EmployeeRepository employeeRepository;
    private final HolidayService holidayService;


    public int calculateVacationPay(VacationRequest request) {
        double averageSalary = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found")).getAverageSalary();

        // Проверка на ненулевое значение перед делением
        if (averageSalary == 0) {
            throw new IllegalArgumentException("Average salary cannot be zero");
        }

        // Учитываем только рабочие дни
        int workDays = calculateWorkDays(request.getStartDate(), request.getEndDate());

        return (int) (averageSalary * workDays / 12); // Рассчитываем отпускные и возвращаем их
    }

    // Метод для вычисления количества рабочих дней между начальной и конечной датами отпуска
    private int calculateWorkDays(LocalDate startDate, LocalDate endDate) {
        int workDays = 0;
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            if (!holidayService.isWeekend(currentDate) && !holidayService.isHoliday(currentDate)) {
                workDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return workDays;
    }

}
