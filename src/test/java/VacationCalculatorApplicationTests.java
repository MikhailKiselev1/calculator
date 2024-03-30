import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vacationCalculator.model.dto.request.VacationRequest;
import vacationCalculator.model.entity.Employee;
import vacationCalculator.repository.EmployeeRepository;
import vacationCalculator.service.HolidayService;
import vacationCalculator.service.VacationCalculatorService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class VacationCalculatorApplicationTests {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private HolidayService holidayService;

    @InjectMocks
    private VacationCalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateVacationPay() {
        VacationRequest request = new VacationRequest();
        request.setEmployeeId(1);
        request.setStartDate(LocalDate.of(2024, 4, 1));
        request.setEndDate(LocalDate.of(2024, 4, 10));

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setAverageSalary(50000);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));


        int result = calculatorService.calculateVacationPay(request);
        assertEquals(4167, result); // Результат рассчитан для 10 рабочих дней и средней зарплаты 50000

        verify(employeeRepository, times(1)).findById(1);
        verify(holidayService, times(10)).isHoliday(any());
        verify(holidayService, times(10)).isWeekend(any());
    }
}
