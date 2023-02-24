package es.udc.paproject.backend.model.services;

import java.util.Optional;

import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.CategoryDao;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.entities.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.entities.User;
import es.udc.paproject.backend.model.entities.UserDao;

@Service
@Transactional(readOnly=true)
public class PermissionCheckerImpl implements PermissionChecker {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private CategoryDao categoryDao;
  
  @Autowired
	private ProductDao productDao;
	
	@Override
	public void checkUserExists(Long userId) throws InstanceNotFoundException {
		
		if (!userDao.existsById(userId)) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
	}

	@Override
	public User checkUser(Long userId) throws InstanceNotFoundException {

		if (userId == null)
			throw new InstanceNotFoundException("project.entities.user", -1);

		Optional<User> user = userDao.findById(userId);
		
		if (!user.isPresent()) {
			throw new InstanceNotFoundException("project.entities.user", userId);
		}
		
		return user.get();
		
	}

	public Category checkCategory(Long categoryId) throws InstanceNotFoundException {

		if (categoryId == null)
			throw new InstanceNotFoundException("project.entities.category", -1);

		Optional<Category> category = categoryDao.findById(categoryId);

		if (!category.isPresent()) {
			throw new InstanceNotFoundException("project.entities.category", categoryId);
		}

		return category.get();

	}

	public void checkCategoryExists(Long categoryId) throws InstanceNotFoundException {

		if (!categoryDao.existsById(categoryId)) {
			throw new InstanceNotFoundException("project.entities.category", categoryId);
		}
	}
	@Override
	public Product checkProduct(Long productId) throws InstanceNotFoundException {

		Optional<Product> product = productDao.findById(productId);

		if (!product.isPresent()) {
			throw new InstanceNotFoundException("project.entities.product", productId);
		}

		return product.get();
	}

}
