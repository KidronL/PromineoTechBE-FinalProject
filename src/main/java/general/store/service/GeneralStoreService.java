package general.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import general.store.controller.model.GeneralStoreData;
import general.store.controller.model.GeneralStoreEmployee;
import general.store.controller.model.GeneralStoreProduct;
import general.store.dao.EmployeeDao;
import general.store.dao.GeneralStoreDao;
import general.store.dao.ProductDao;
import general.store.entity.Employee;
import general.store.entity.GeneralStore;
import general.store.entity.Product;

@Service
public class GeneralStoreService {

	//Instantiating DAO objects
	private GeneralStoreDao generalStoreDao;
	private EmployeeDao employeeDao;
	private ProductDao productDao;
	
	//Declaring DAO interfaces
	@Autowired
	public GeneralStoreService(GeneralStoreDao generalStoreDao) {
		this.generalStoreDao = generalStoreDao;
	}
	
	@Autowired
	public void EmployeeService(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	@Autowired
	public void ProductService(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	//POST method for creating store locations
	public GeneralStoreData generateGeneralStore(GeneralStoreData generalStoreData) {
		GeneralStore generalStore = findOrGenerateGeneralStore(generalStoreData.getGeneralStoreId());
		copyGeneralStoreFields(generalStore, generalStoreData);
		generalStoreDao.save(generalStore);
		return new GeneralStoreData(generalStore);
		
	}

	private void copyGeneralStoreFields(GeneralStore generalStore, GeneralStoreData generalStoreData) {
		generalStore.generalStoreName = generalStoreData.generalStoreName;
		generalStore.generalStoreStaffCount = generalStoreData.generalStoreStaffCount;
		
	}

	private GeneralStore findOrGenerateGeneralStore(Long generalStoreId) {
		if (generalStoreId == null) {
			return new GeneralStore();
		} else {
			return generalStoreDao.findById(generalStoreId)
					.orElseThrow(() -> new NoSuchElementException("General Store " + generalStoreId + "does not exist."));
		}
	}
	
	@Transactional(readOnly = false)
	public GeneralStoreProduct saveProduct(Long generalStoreId, GeneralStoreProduct generalStoreProduct) {
		GeneralStore generalStore = findGeneralStoreById(generalStoreId);
		Long productId = generalStoreProduct.getProductId();
		Product product = findOrGenerateProduct(productId, generalStoreId);
		copyProductFields(product, generalStoreProduct);
		product.generalStores.add(generalStore);
		generalStore.products.add(product);
		product = productDao.save(product);
		
		return new GeneralStoreProduct(product);
		
	}

	private void copyProductFields(Product product, GeneralStoreProduct generalStoreProduct) {
		product.productId = generalStoreProduct.productId;
		product.productName = generalStoreProduct.productName;
		product.productDescription = generalStoreProduct.productDescription;
		product.productStock = generalStoreProduct.productStock;
		product.generalStores = generalStoreProduct.generalStores;
		
	}

	private Product findOrGenerateProduct(Long productId, Long generalStoreId) {
		
		if (productId == null) {
			return new Product();
		} else {
			return findProductById(productId, generalStoreId);
		}
	}

	private Product findProductById(Long productId, Long generalStoreId) {
		Product product = productDao.findById(productId)
				.orElseThrow(() -> new NoSuchElementException("Product with ID " + productId + " does not exist."));
		
		boolean generalStoreFound = false;
		for (GeneralStore generalStore : product.getGeneralStores()) {
			if (generalStore.getGeneralStoreId().equals(generalStoreId)) {
				generalStoreFound = true;
				break;
			}
		}
		
		if (!generalStoreFound) {
			throw new IllegalArgumentException("General Store ID " + generalStoreId + "does carry Product ID " + productId);
		}
		
		return product;
	}

	private GeneralStore findGeneralStoreById(Long generalStoreId) {
		return generalStoreDao.findById(generalStoreId)
				.orElseThrow(() -> new NoSuchElementException("General store with ID " + generalStoreId + " could not be located"));
	}

	//Methods for creating and finding Employees
		@Transactional(readOnly = false)
		public GeneralStoreEmployee saveEmployee(Long generalStoreId, GeneralStoreEmployee generalStoreEmployee) {
			GeneralStore generalStore = findGeneralStoreById(generalStoreId);
			Long employeeId = generalStoreEmployee.getEmployeeId();
			Employee employee = findOrCreateEmployee(employeeId);
			
			copyEmployeeFields(employee, generalStoreEmployee);
			
			employee.setGeneralStore(generalStore);
			generalStore.employees.add(employee);
			
			employee = employeeDao.save(employee);
			
			return new GeneralStoreEmployee(employee);
			
		}

		private void copyEmployeeFields(Employee employee, GeneralStoreEmployee generalStoreEmployee) {
			
			employee.employeeId = generalStoreEmployee.employeeId;
			employee.employeeFirstName = generalStoreEmployee.employeeFirstName;
			employee.employeeLastName = generalStoreEmployee.employeeLastName;
			employee.employeePhoneNumber = generalStoreEmployee.employeePhoneNumber;
			employee.employeeJobTitle = generalStoreEmployee.employeeJobTitle;
			employee.employeeShift = generalStoreEmployee.employeeShift;
			employee.generalStore = generalStoreEmployee.generalStore;
			
		}

		private Employee findOrCreateEmployee(Long employeeId) {
			
			if (employeeId == null) {
				return new Employee();
			} else {
				return findEmployeeById(employeeId);
			}
		}

		private Employee findEmployeeById(Long employeeId) {
			return employeeDao.findById(employeeId)
					.orElseThrow(() -> new NoSuchElementException("Employee with ID " + employeeId + " not found"));
		}
		
		//Get Method for all stores
		@Transactional(readOnly = true)
		public List<GeneralStoreData> retrieveAllStores() {
			List<GeneralStore> generalStores = generalStoreDao.findAll();
			
			List<GeneralStoreData> genStores = new LinkedList<>();
			

	        for(GeneralStore generalStore : generalStores) {
	        	GeneralStoreData gsData = new GeneralStoreData(generalStore);
	        	
	        	gsData.getProducts().clear();
	        	gsData.getEmployees().clear();
	        	
	        	genStores.add(gsData);
	        }
	        
	        return genStores;
		}
		
		@Transactional(readOnly = true)
		public GeneralStoreData rereieveStoreById(Long generalStoreId) {
			return new GeneralStoreData(findGeneralStoreById(generalStoreId));
		}
		
		@Transactional(readOnly = false)
		public void deleteStoreById(Long generalStoreId) {
			GeneralStore generalStore = findGeneralStoreById(generalStoreId);
			generalStoreDao.delete(generalStore);
		}

}

