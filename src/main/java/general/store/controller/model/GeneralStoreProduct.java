package general.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import general.store.entity.GeneralStore;
import general.store.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralStoreProduct {
	
	public Long productId;
	public String productName;
	public String productDescription;
	public Long productStock;
	
	public Set<GeneralStore> generalStores = new HashSet<>();

	public GeneralStoreProduct(Product product) {
		
		this.productId = product.getProductId();
		this.productName = product.getProductName();
		this.productDescription = product.getProductDescription();
		this.productStock = product.getProductStock();
		this.generalStores = product.getGeneralStores();
	}

}
