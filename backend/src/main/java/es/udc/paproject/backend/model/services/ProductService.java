package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product announceProduct(Long userId, String name, String description, long minutes, BigDecimal startPrice,
                                   String shipInfo, Long categoryId) throws InstanceNotFoundException;

    Product getProductInfo(Long productId) throws InstanceNotFoundException;

    Block<Product> findProducts(Long categoryId, String keywords, int page, int size);

    List<Category> findAllCategories();

    Block<Product> findUserProducts(Long userId, int page, int size);
}
