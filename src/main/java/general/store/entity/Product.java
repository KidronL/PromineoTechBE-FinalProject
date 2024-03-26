package general.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Product {
	
	//Code to populate product values
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long productId;
	public String productName;
	public String productDescription;
	public Long productStock;
	
	//Populates products to general stores
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
	public Set<GeneralStore> generalStores = new HashSet<>();
}
