package in.sherbin.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sherbin.springbootbackend.model.Employee;

@Repository
public interface EmployeeReopsitory  extends JpaRepository<Employee, Long> {

}
