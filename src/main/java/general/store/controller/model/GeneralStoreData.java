package general.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import general.store.entity.Employee;
import general.store.entity.GeneralStore;
import general.store.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralStoreData {
	
	//Setting values to match entity
	public Long generalStoreId;
	public String generalStoreName;
	public Long generalStoreStaffCount;
	
	public Set<GeneralStoreProduct> products = new HashSet<>();
	public Set<GeneralStoreEmployee> employees = new HashSet<>();
	
	//Constructor to convert into GeneralStoreData
	public GeneralStoreData(GeneralStore generalStore) {
		
		this.generalStoreId = generalStore.generalStoreId;
		this.generalStoreName = generalStore.generalStoreName;
		this.generalStoreStaffCount = generalStore.generalStoreStaffCount;
		
		if (generalStore.products != null) {
			this.products = new HashSet<>();
			for (Product product : generalStore.products) {
				this.products.add(new GeneralStoreProduct(product));
				
			}
		}
		
		if (generalStore.employees != null) {
			this.employees = new HashSet<>();
			for (Employee employee : generalStore.employees) {
				this.employees.add(new GeneralStoreEmployee(employee));
				
			}
		}
			
		
		
		
	}
	
	
	

}
