package general.store.controller.model;

import general.store.entity.Employee;
import general.store.entity.GeneralStore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralStoreEmployee {
	
	//Populating the values for GeneralStoreEmployee
	public Long employeeId;
	public String employeeFirstName;
	public String employeeLastName;
	public Long employeePhoneNumber;
	public String employeeJobTitle;
	public Long employeeShift;
	public GeneralStore generalStore;

	//Constructor to convert to GeneralStoreEmployee
	public GeneralStoreEmployee(Employee employee) {
		this.employeeId = employee.getEmployeeId();
		this.employeeFirstName = employee.getEmployeeFirstName();
		this.employeeLastName = employee.getEmployeeLastName();
		this.employeePhoneNumber = employee.getEmployeePhoneNumber();
		this.employeeJobTitle = employee.getEmployeeJobTitle();
		this.employeeShift = employee.getEmployeeShift();
		this.generalStore = employee.getGeneralStore();
	
	}

}
