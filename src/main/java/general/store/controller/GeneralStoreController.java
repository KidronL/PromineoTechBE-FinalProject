package general.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import general.store.controller.model.GeneralStoreData;
import general.store.controller.model.GeneralStoreEmployee;
import general.store.controller.model.GeneralStoreProduct;
import general.store.service.GeneralStoreService;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/general_store")
@Slf4j
public class GeneralStoreController {
	
	//Mapping logic for CRUD operations
	private GeneralStoreService generalStoreService;
	
	@Autowired
	public GeneralStoreController(GeneralStoreService generalStoreService) {
		this.generalStoreService = generalStoreService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public GeneralStoreData createGeneralStore(@RequestBody GeneralStoreData generalStoreData) {
		log.info("Creating general store location: {}", generalStoreData);
		return generalStoreService.generateGeneralStore(generalStoreData);
	}
	
	@PutMapping("/{generalStoreId}")
	public GeneralStoreData updateGeneralStore(@PathVariable Long generalStoreId, @RequestBody GeneralStoreData generalStoreData) {
		log.info("Updating general store ID {}: {}", generalStoreId, generalStoreData);
		generalStoreData.setGeneralStoreId(generalStoreId);
		return generalStoreService.generateGeneralStore(generalStoreData);
	}

	@PostMapping("/{generalStoreId}/product")
	@ResponseStatus(HttpStatus.CREATED)
	public GeneralStoreProduct addProduct(@PathVariable Long generalStoreId, @RequestBody GeneralStoreProduct product) {
		log.info("Adding product to general store ID {}: {}", generalStoreId, product);
		return generalStoreService.saveProduct(generalStoreId, product);
	}
	
    @PostMapping("/{generalStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public GeneralStoreEmployee addEmployee(@PathVariable Long generalStoreId, @RequestBody GeneralStoreEmployee employee) {
        log.info("Adding employee to general store ID {}: {}", generalStoreId, employee);
        return generalStoreService.saveEmployee(generalStoreId, employee);
    }
    
    @GetMapping
    public List<GeneralStoreData> retrieveAllStores() {
    	log.info("Getting all Stores");
    	return generalStoreService.retrieveAllStores();
    }
    
    @GetMapping("/{generalStoreId}")
    public GeneralStoreData retrieveStoreById(@PathVariable Long generalStoreId) {
    	return generalStoreService.rereieveStoreById(generalStoreId);
    }
    
    @DeleteMapping("/{generalStoreId}")
    public Map<String, String> deleteStoreById(@PathVariable Long generalStoreId) {
    	generalStoreService.deleteStoreById(generalStoreId);
    	Map<String, String> response = new HashMap<>();
    	response.put("message", "Store has been deleted");
    	return response;
    }
    
}
