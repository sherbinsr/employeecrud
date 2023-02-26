package in.sherbin.springbootbackend.contoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.sherbin.springbootbackend.exception.ResourceNotFoundException;
import in.sherbin.springbootbackend.model.Employee;
import in.sherbin.springbootbackend.repository.EmployeeReopsitory;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeReopsitory employeeReopsitory;
	
	
	//get all employees
	
	@GetMapping("/employees")
   public List<Employee> getAllEmployees(){
	   return employeeReopsitory.findAll();
   }
	
	//create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeReopsitory.save(employee);
	}
	//get employee by id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee =employeeReopsitory.findById(id)
	.orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with id:"+id));
		return ResponseEntity.ok(employee);
	}
	//update employee Rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id ,@RequestBody Employee employeeDetails){
		Employee employee =employeeReopsitory.findById(id)
	.orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with id:"+id));
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		employee.setEmailid(employeeDetails.getEmailid());
		
		Employee updateEmployee = employeeReopsitory.save(employee);
		return ResponseEntity.ok(updateEmployee);
	}
	//delete employee rest api
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id ){
		Employee employee =employeeReopsitory.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with id:"+id));
		employeeReopsitory.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
