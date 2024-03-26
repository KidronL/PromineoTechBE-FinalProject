package general.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import general.store.entity.GeneralStore;

public interface GeneralStoreDao extends JpaRepository<GeneralStore, Long> {

}
