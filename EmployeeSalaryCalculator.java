package employeesalarycalculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Main class to demonstrate employee salary calculation.
 */
public class EmployeeSalaryCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Employee employee = new Employee(1001, "John Doe", LocalDate.of(1990, 5, 15));
        employee.displayInfo();
        SalaryCalculator.calculateSalary(employee, 40);
    }
}

class Employee {
    private final int employeeNumber;
    private String name;
    private final LocalDate birthday;

    public Employee(int employeeNumber, String name, LocalDate birthday) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void displayInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        System.out.println("Employee Details");
        System.out.println("----------------");
        System.out.println("Employee Number: " + employeeNumber);
        System.out.println("Employee Name: " + name);
        System.out.println("Birthday: " + birthday.format(formatter));
        System.out.println();
    }
}

class SalaryCalculator {
    private static final double HOURLY_RATE = 15.00;
    private static final double SSS_RATE = 0.04;
    private static final double PHILHEALTH_RATE = 0.02;
    private static final double PAGIBIG_RATE = 0.01;
    private static final double TAX_RATE = 0.10;

    public static void calculateSalary(Employee employee, int hoursWorked) {
        if (hoursWorked < 0 || hoursWorked > 168) {
            System.out.println("Error: Invalid number of hours worked. Must be between 0 and 168.");
            return;
        }

        double grossSalary = hoursWorked * HOURLY_RATE;
        double sss = grossSalary * SSS_RATE;
        double philHealth = grossSalary * PHILHEALTH_RATE;
        double pagIbig = grossSalary * PAGIBIG_RATE;
        double tax = grossSalary * TAX_RATE;
        double netSalary = grossSalary - (sss + philHealth + pagIbig + tax);

        System.out.println("Salary Calculation for " + employee.getName());
        System.out.println("--------------------------------------");
        System.out.printf("Hours Worked: %d%n", hoursWorked);
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
