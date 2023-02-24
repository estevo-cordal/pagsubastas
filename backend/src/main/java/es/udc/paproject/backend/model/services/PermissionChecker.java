package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.entities.User;

public interface PermissionChecker {
	
	void checkUserExists(Long userId) throws InstanceNotFoundException;
	
	User checkUser(Long userId) throws InstanceNotFoundException;

	Category checkCategory(Long categoryId) throws InstanceNotFoundException;

	void checkCategoryExists(Long categoryId) throws InstanceNotFoundException;
  
	Product checkProduct(Long productId) throws InstanceNotFoundException;
	
}
