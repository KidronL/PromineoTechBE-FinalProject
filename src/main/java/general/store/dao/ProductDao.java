package general.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import general.store.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long> {

}
