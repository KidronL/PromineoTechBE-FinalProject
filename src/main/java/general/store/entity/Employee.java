package general.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Employee {
	
	//Code to populate employee values
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long employeeId;
	public String employeeFirstName;
	public String employeeLastName;
	public Long employeePhoneNumber;
	public String employeeJobTitle;
	public Long employeeShift;
	
	//Populates general store with employee
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "general_store_id")
	public GeneralStore generalStore;

}
