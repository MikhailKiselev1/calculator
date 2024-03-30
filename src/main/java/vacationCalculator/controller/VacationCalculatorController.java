package vacationCalculator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vacationCalculator.model.dto.request.VacationRequest;
import vacationCalculator.service.VacationCalculatorService;

@RestController
@RequiredArgsConstructor
public class VacationCalculatorController {

    private final VacationCalculatorService calculatorService;

    @GetMapping("/calculate")
    public double calculateVacationPay(@RequestBody VacationRequest request) {
        return calculatorService.calculateVacationPay(request);
    }
}
