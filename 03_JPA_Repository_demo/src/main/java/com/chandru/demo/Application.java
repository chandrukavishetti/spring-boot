package com.chandru.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chandru.demo.repository.Employee;
import com.chandru.demo.repository.EmployeeRepository;

@SpringBootApplication
public class Application {

	private final EmployeeRepository employeeRepository;

	Application(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {

		return runner -> {
			// createEmployee(employeeRepository);
			// findById(employeeRepository);
			 findByName(employeeRepository);
			// deleteById(employeeRepository);
			// getAllEmployee(employeeRepository);
			// updateEmployeeName(employeeRepository);
			// findByCityCode(employeeRepository);
			//updateEmployeeCityCode(employeeRepository);
		};

	}

	private void updateEmployeeCityCode(EmployeeRepository employeeRepository) {
		Optional<Employee> employee = employeeRepository.findById(1);

		if (employee.isPresent()) {
			Employee emp = employee.get();

			emp.setCity_code(151988);

			employeeRepository.save(emp);

			System.out.println("employee citycode is updated successfully");

		} else {
			System.out.println("emplyee not found");
		}

	}

	private void findByCityCode(EmployeeRepository employeeRepository2) {
		System.out.println("fetching the employee by the city code");

		List<Employee> foundEmpByCityCode = employeeRepository.findByCityCode(302004);

		for (Employee e : foundEmpByCityCode) {
			System.out.println(e);
		}

	}

	private void updateEmployeeName(EmployeeRepository employeeRepository) {

		Optional<Employee> employee = employeeRepository.findById(4);

		if (employee.isPresent()) {

			Employee emp = employee.get();

			emp.setEmp_name("Virat");

			employeeRepository.save(emp);

			System.out.println("Employee name updated successfully");
		} else {
			System.out.println("Employee not found");
		}
	}

	private void getAllEmployee(EmployeeRepository employeeRepository) {
		System.out.println("fetching all employees from the Employee table");

		List<Employee> foundAllEmployee = employeeRepository.findAll();

		for (Employee e : foundAllEmployee) {
			System.out.println(e);
		}

	}

	private void deleteById(EmployeeRepository employeeRepository2) {
		System.out.println("deleting the Employee by the id");

		employeeRepository.deleteById(4);

		System.out.println("employee deleted successfully");

	}

	private void findByName(EmployeeRepository employeeRepository2) {
		List<Employee> foundByName = employeeRepository.findByEmpName("PRuthviraj");

		for (Employee e : foundByName) {
			System.out.println(e);
		}

	}

	private void findById(EmployeeRepository employeeRepository2) {
		System.out.println("finding the Employee by the id");

		Optional<Employee> foundEmployee = employeeRepository.findById(1);

		System.out.println("employee founded : " + foundEmployee);

	}

	private void createEmployee(EmployeeRepository employeeRepository) {
		try {
			System.out.println("creating the employee object");

			Employee employee1 = new Employee(1, "chandrashekhar", 262004, 22, 33000, "Gurgao");
			Employee employee2 = new Employee(2, "kartik", 302004, 22, 30000, "Vijayapur");
			Employee employee3 = new Employee(3, "pruthviraj", 202005, 21, 23000, "Bidadi");
			Employee employee4 = new Employee(4, "Deepak", 302003, 23, 14000, "Mumbai");

			System.out.println("saving the all employees ");

			employeeRepository.save(employee1);
			employeeRepository.save(employee2);
			employeeRepository.save(employee3);
			employeeRepository.save(employee4);

		} catch (Exception e) {
			System.out.println("Invalid data or unique id is required");
			e.printStackTrace();
		}

	}

}
