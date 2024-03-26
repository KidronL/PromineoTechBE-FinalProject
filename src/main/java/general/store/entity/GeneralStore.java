package general.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class GeneralStore {
	
	//Code to populate the values in general store
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long generalStoreId;
	public String generalStoreName;
	public Long generalStoreStaffCount;
	
	//Using sets to store values for products and employees to be displayed later
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "general_store_products", joinColumns = @JoinColumn(name = "general_store_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	public Set<Product> products = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "generalStore", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Employee> employees = new HashSet<>();

}
