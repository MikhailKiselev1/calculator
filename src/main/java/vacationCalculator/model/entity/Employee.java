package vacationCalculator.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    private long id;
    private double averageSalary;
    private int vacationDays;
}
