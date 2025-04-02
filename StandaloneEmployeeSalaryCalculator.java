import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class StandaloneEmployeeSalaryCalculator {

    public static void main(String[] args) {
        Employee employee = new Employee(1001, "John Doe", LocalDate.of(1990, 5, 15));
        employee.displayInfo();
        
        // Record working hours
        employee.recordWorkingHours("Monday", 8);
        employee.recordWorkingHours("Tuesday", 7.5);
        employee.recordWorkingHours("Wednesday", 8);
        employee.recordWorkingHours("Thursday", 8.5);
        employee.recordWorkingHours("Friday", 7);
        
        // Display and calculate salary
        employee.displayWorkingHours();
        SalaryCalculator.calculateSalary(employee.getTotalWeeklyHours());
    }
}

class Employee {
    private final int employeeNumber;
    private final String name;
    private final LocalDate birthday;
    private final Map<String, Double> workingHours;

    public Employee(int employeeNumber, String name, LocalDate birthday) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.birthday = birthday;
        this.workingHours = new HashMap<>();
    }
    
    public void recordWorkingHours(String day, double hours) {
        workingHours.put(day, hours);
    }
    
    public double getTotalWeeklyHours() {
        return workingHours.values().stream()
                          .mapToDouble(Double::doubleValue)
                          .sum();
    }
    
    public void displayWorkingHours() {
        System.out.println("\nWeekly Working Hours");
        System.out.println("---------------------");
        workingHours.forEach((day, hours) -> 
            System.out.printf("%-10s: %.1f hours%n", day, hours));
        System.out.printf("Total: %.1f hours%n%n", getTotalWeeklyHours());
    }
    
    public void displayInfo() {
        System.out.println("Employee Details");
        System.out.println("----------------");
        System.out.println("Employee Number: " + employeeNumber);
        System.out.println("Employee Name: " + name);
        System.out.println("Birthday: " + birthday.format(DateTimeFormatter.ISO_DATE));
    }
}

class SalaryCalculator {
    private static final double HOURLY_RATE = 15.00;
    private static final double SSS_RATE = 0.04;
    private static final double PHILHEALTH_RATE = 0.02;
    private static final double PAGIBIG_RATE = 0.01;
    private static final double TAX_RATE = 0.10;

    public static void calculateSalary(double hoursWorked) {
        double grossSalary = hoursWorked * HOURLY_RATE;
        double sss = grossSalary * SSS_RATE;
        double philHealth = grossSalary * PHILHEALTH_RATE;
        double pagIbig = grossSalary * PAGIBIG_RATE;
        double tax = grossSalary * TAX_RATE;
        double netSalary = grossSalary - (sss + philHealth + pagIbig + tax);

        System.out.println("Salary Calculation");
        System.out.println("------------------");
        System.out.printf("Hours Worked: %.1f%n", hoursWorked);
        System.out.printf("Hourly Rate: $%.2f%n%n", HOURLY_RATE);
        System.out.printf("Gross Salary: $%.2f%n%n", grossSalary);
        System.out.println("Deductions:");
        System.out.printf("- SSS: $%.2f%n", sss);
        System.out.printf("- PhilHealth: $%.2f%n", philHealth);
        System.out.printf("- Pag-IBIG: $%.2f%n", pagIbig);
        System.out.printf("- Withholding Tax: $%.2f%n%n", tax);
        System.out.printf("Net Salary: $%.2f%n", netSalary);
    }
}