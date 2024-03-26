package general.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import general.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
