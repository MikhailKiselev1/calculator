package vacationCalculator.model.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VacationRequest {
    private long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
}
