package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.InvalidDateException;
import es.udc.paproject.backend.model.services.ProductService;
import es.udc.paproject.backend.rest.dtos.ProductDto;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.CategoryDao;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.entities.ProductDao;
import es.udc.paproject.backend.model.services.Block;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;

    private User createUser(String userName) {
        return new User(userName, "password", "firstName", "lastName",
                userName + "@" + userName + ".com");
    }

    private Product createProduct(User user, String name, LocalDateTime expirationDate,
                                  BigDecimal startPrice, Category category) {
        return new Product(user, name, "description", LocalDateTime.now(), expirationDate, startPrice, startPrice, "ship info", category);
    }

    @Test
    public void testFindProductById() throws InstanceNotFoundException {
        BigDecimal price1 = new BigDecimal(10);
        Category category = new Category("cat1");
        User user = createUser("admin");
        Product product = createProduct(user,"testProd", LocalDateTime.now().plusMinutes(20),price1, category);

        userDao.save(user);
        categoryDao.save(category);
        productDao.save(product);

        assertEquals(product, productService.getProductInfo(product.getId()));

    }

    @Test
    public void testFindProductByNonExistentId() {

        assertThrows(InstanceNotFoundException.class, () -> productService.getProductInfo(3763L));

    }

    @Test
    public void testProductGetMinutes() {
        Category category = new Category("cat1");
        User user = createUser("admin");
        LocalDateTime minutes = LocalDateTime.now().plusMinutes(20);
        Product product = createProduct(user,"testProd", minutes, BigDecimal.valueOf(10f), category);

        userDao.save(user);
        categoryDao.save(category);
        productDao.save(product);

        System.out.println(product.remainingMinutes());

        assertEquals(ChronoUnit.MINUTES.between(LocalDateTime.now(), minutes), product.remainingMinutes());

    }

    @Test
    public void testFindProductCorrectDetails() throws InstanceNotFoundException {
        BigDecimal price1 = new BigDecimal(10);
        Category category = new Category("cat1");
        User user = createUser("admin");
        Product product = createProduct(user,"testProd", LocalDateTime.now().plusMinutes(20),price1, category);

        userDao.save(user);
        categoryDao.save(category);
        productDao.save(product);

        assertEquals(product, productService.getProductInfo(product.getId()));

    }


    @Test
    public void testFindProductsByKeywords() {
        BigDecimal price1 = new BigDecimal(10);
        User user = createUser("admin");
        Category category = new Category("category");
        Product product1 = createProduct(user, "Canon PowerShot SX50 HS", LocalDateTime.now().plusDays(8), price1, category);
        Product product2 = createProduct(user, "sony", LocalDateTime.now().plusMinutes(20), price1, category);
        Product product3 = createProduct(user, "another", LocalDateTime.now().plusMinutes(20), price1, category);

        userDao.save(user);
        categoryDao.save(category);
        productDao.save(product1);
        productDao.save(product2);
        productDao.save(product3);

        Block<Product> expectedBlock = new Block<>(List.of(product1), false);

        assertEquals(expectedBlock, productService.findProducts(null, "canon sx50", 0, 1));
        assertEquals(expectedBlock, productService.findProducts(null, "sx50 CAN", 0, 1));
    }

    @Test
    public void testFindProductsByCategory() {
        BigDecimal price1 = new BigDecimal(40);
        BigDecimal price2 = new BigDecimal(10);

        User user = createUser("admin1");
        Category category1 = new Category("category1");
        Category category2 = new Category("category2");
        Product product1 = createProduct(user, "product1", LocalDateTime.now().plusMinutes(20), price1, category1);
        Product product2 = createProduct(user, "product2", LocalDateTime.now().plusMinutes(20), price2, category2);

        userDao.save(user);
        categoryDao.save(category1);
        categoryDao.save(category2);
        productDao.save(product1);
        productDao.save(product2);

        Block<Product> expectedBlock = new Block<>(List.of(product1), false);
        Block<Product> expectedBlock1 = new Block<>(List.of(product2), false);

        assertEquals(expectedBlock, productService.findProducts(category1.getId(), null, 0, 1));
        assertEquals(expectedBlock1, productService.findProducts(category2.getId(), null, 0, 1));

    }

    @Test
    public void testFindProductsByAllCriteria() {
        BigDecimal price1 = new BigDecimal(40);
        User user = createUser("admin1");
        Category category1 = new Category("category 1");
        Product product1 = createProduct(user, "product 1", LocalDateTime.now().plusMinutes(20), price1, category1);
        Product product2 = createProduct(user,"another", LocalDateTime.now().plusMinutes(20), price1, category1);
        Category category2 = new Category("category 2");
        Product product3 = createProduct(user, "product 3", LocalDateTime.now().plusMinutes(20), price1, category2);

        userDao.save(user);
        categoryDao.save(category1);
        productDao.save(product1);
        productDao.save(product2);
        categoryDao.save(category2);
        productDao.save(product3);

        Block<Product> expectedBlock = new Block<>(List.of(product1), false);

        assertEquals(expectedBlock, productService.findProducts(category1.getId(), "product", 0, 2));

    }


    @Test
    public void testFindAllProducts() {
        BigDecimal price1 = new BigDecimal(40);
        User user = createUser("admin1");
        Category category1 = new Category("category 1");
        Product product1 = createProduct(user, "product 1", LocalDateTime.now().plusMinutes(20), price1, category1);
        Category category2 = new Category("category 2");
        Product product2 = createProduct(user,"product 2", LocalDateTime.now().plusMinutes(20), price1, category2);
        Product product3 = createProduct(user,"product 3", LocalDateTime.now().minusDays(7), price1, category2);

        userDao.save(user);
        categoryDao.save(category1);
        productDao.save(product1);
        categoryDao.save(category2);
        productDao.save(product2);
        productDao.save(product3);

        Block<Product> expectedBlock = new Block<>(Arrays.asList(product1, product2), false);

        assertEquals(expectedBlock, productService.findProducts(null, "", 0, 2));
        assertEquals(expectedBlock, productService.findProducts(null, null, 0, 2));

    }

    @Test
    public void testFindAllCategories() {

        Category category1 = new Category("category1");
        Category category2 = new Category("category2");

        categoryDao.save(category2);
        categoryDao.save(category1);

        assertEquals(Arrays.asList(category1, category2), productService.findAllCategories());

    }

    @Test
    public void testAnnounceProduct() throws InstanceNotFoundException {
        BigDecimal price1 = new BigDecimal(10);
        Category category = new Category("cat1");
        User user = createUser("admin");

        userDao.save(user);
        categoryDao.save(category);

        Product p = new Product(user, "prueba", "desc", LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10), price1, price1, "hey", category);

        Product saved = productService.announceProduct(p.getUser().getId(), p.getName(), p.getDescription(),
                p.remainingMinutes(), p.getStartPrice(), p.getShipInfo(), p.getCategory().getId());

        assertTrue(productDao.existsById(saved.getId()));

    }

    @Test
    public void testAnnounceProdNoUser() {
        BigDecimal price1 = new BigDecimal(10);
        Category category = new Category("cat1");
        User user = createUser("admin");
        categoryDao.save(category);

        Product p = new Product(user, "prueba", "desc", LocalDateTime.now(), LocalDateTime.now().plusMinutes(10),
                price1, price1, "hey", category);

        assertThrows(InstanceNotFoundException.class, () -> productService.announceProduct(p.getUser().getId(),
                p.getName(), p.getDescription(), p.remainingMinutes(), p.getStartPrice(), p.getShipInfo(),
                p.getCategory().getId()));

    }

    @Test
    public void testAnnounceProdNoCategory() {
        BigDecimal price1 = new BigDecimal(10);
        Category category = new Category("cat1");
        User user = createUser("admin");
        userDao.save(user);

        Product p = new Product(user, "prueba", "desc", LocalDateTime.now(), LocalDateTime.now().plusMinutes(10),
                price1, price1, "hey", category);

        assertThrows(InstanceNotFoundException.class, () -> productService.announceProduct(p.getUser().getId(),
                p.getName(), p.getDescription(), p.remainingMinutes(), p.getStartPrice(), p.getShipInfo(),
                p.getCategory().getId()));

    }

    @Test
    public void testFindAnnouncedProducts() throws InstanceNotFoundException {
        BigDecimal price1 = new BigDecimal(10);
        User user = createUser("admin");
        Category category1 = new Category("category 1");
        //Product product1 = createProduct(user, "product 1", LocalDateTime.now(), 40f, category1);
        Category category2 = new Category("category 2");

        userDao.save(user);
        categoryDao.save(category1);
        categoryDao.save(category2);

        Product p1 = new Product(user, "prueba", "desc", LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),
                price1, price1, "hey", category1);
        Product p2 = new Product(user, "prueba", "desc", LocalDateTime.now(), LocalDateTime.now().plusMinutes(10),
                price1, price1, "hey", category2);

        //productService.announceProduct(product1);
        Product s1 = productService.announceProduct(p1.getUser().getId(), p1.getName(), p1.getDescription(),
                p1.remainingMinutes(), p1.getStartPrice(), p1.getShipInfo(), p1.getCategory().getId());
        Product s2 = productService.announceProduct(p2.getUser().getId(), p2.getName(), p2.getDescription(),
                p2.remainingMinutes(), p2.getStartPrice(), p2.getShipInfo(), p2.getCategory().getId());

        Product product2 = productDao.findById(s1.getId()).get();
        Product product3 = productDao.findById(s2.getId()).get();
        Block<Product> expectedBlock = new Block<>(Arrays.asList(product2, product3), false);
        Block<Product> otherBlock = productService.findUserProducts(user.getId(), 0, 2);
        assertEquals(expectedBlock, otherBlock);

    }
}