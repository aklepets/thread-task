import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureEmployeeSalary {
    static class EmployeeService {
        CompletableFuture<List<Employee>> hiredEmployees() {
            return null;
        }

        CompletableFuture<BigDecimal> getSalary(int hiredEmployeeId) {
            return null;
        }
    }

    class Employee {
        int id;
        String name;
        BigDecimal salary;

        public void setSalary(BigDecimal salary){
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "Id="+id +
                    ", name=" + name +
                    ", salary=" + salary + "}";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        EmployeeService service = new EmployeeService();

        CompletableFuture<Void> future =
                service.hiredEmployees()
                        .thenComposeAsync(employees ->
                                CompletableFuture.allOf(employees.stream()
                                        .map(employee -> service.getSalary(employee.id)
                                                .thenApplyAsync(salary -> {
                                                    employee.setSalary(salary);
                                                    return employee;
                                                }))
                                        .toArray(CompletableFuture[]::new))
                        )
                        .thenApplyAsync(v -> {
                            service.hiredEmployees().get().forEach(System.out::println);
                            return null;
                        });

        future.get();
    }

}
