package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.ExpiredProductException;
import es.udc.paproject.backend.model.exceptions.InvalidPriceException;
import es.udc.paproject.backend.model.services.ProductService;
import es.udc.paproject.backend.model.services.BidService;
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
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BidServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private BidService bidService;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BidDao bidDao;

    private User createUser(String userName) {
        return new User(userName, "password", "firstName", "lastName",
                userName + "@" + userName + ".com");
    }

    private Product createProduct(User user, String name, LocalDateTime expirationDate,
                                  BigDecimal startPrice, Category category) {
        return new Product(user, name, "description", LocalDateTime.now(), expirationDate, startPrice,
                startPrice, "ship info", category);
    }

    @Test
    public void testPlaceBidNonExistentProduct() {
        User user = createUser("admin");
        userDao.save(user);
        BigDecimal price1 = new BigDecimal(200);
        assertThrows(InstanceNotFoundException.class, () -> bidService.placeBid(user.getId(), price1, 3763L));

    }

    @Test
    public void testPlaceBidNonExistentUser() {
        BigDecimal price1 = new BigDecimal(200);
        BigDecimal price2 = new BigDecimal(10);

        Category category = new Category("cat1");
        User user = createUser("admin");
        Product product = createProduct(user,"testProd", LocalDateTime.now().plusMinutes(20),price2, category);

        categoryDao.save(category);
        userDao.save(user);
        productDao.save(product);

        assertThrows(InstanceNotFoundException.class, () -> bidService.placeBid(3763L, price1, product.getId()));

    }

    @Test
    public void testPlaceFirstBid() throws InvalidPriceException, ExpiredProductException, InstanceNotFoundException {
        Category category = new Category("cat1");
        User user = createUser("admin");
        Product product = createProduct(user,"testProd", LocalDateTime.now().plusMinutes(20), BigDecimal.valueOf(10f),
                category);

        categoryDao.save(category);
        userDao.save(user);
        productDao.save(product);

        LocalDateTime bidDate = LocalDateTime.now();
        Bid someBid = bidService.placeBid(user.getId(), BigDecimal.valueOf(20), product.getId());
        assertEquals(someBid.getBidDate().getMinute(), bidDate.getMinute(), 1);
        assertEquals(someBid.getPrice().floatValue(), 20);
        assertEquals(someBid, product.getWinningBid());
        assertEquals(product.getCurrentPrice().floatValue(), 10f);

    }

    @Test
    public void testPlaceBidUpdates() throws InvalidPriceException, ExpiredProductException,
            InstanceNotFoundException {
        Category category = new Category("cat1");
        User user = createUser("admin");
        Product product = createProduct(user,"testProd", LocalDateTime.now().plusMinutes(20),
                BigDecimal.valueOf(10f), category);

        categoryDao.save(category);
        userDao.save(user);
        productDao.save(product);

        Bid someBid = bidService.placeBid(user.getId(), BigDecimal.valueOf((float) 15.70), product.getId());
        assertEquals(someBid, product.getWinningBid());
        assertEquals(product.getCurrentPrice().floatValue(), 10f);

        someBid = bidService.placeBid(user.getId(), BigDecimal.valueOf(15), product.getId());
        assertNotEquals(someBid, product.getWinningBid());
        assertEquals(product.getCurrentPrice().floatValue(), 15.50, 0.01);

        someBid = bidService.placeBid(user.getId(), BigDecimal.valueOf((float) 15.60), product.getId());
        assertNotEquals(someBid, product.getWinningBid());
        assertEquals(product.getCurrentPrice().floatValue(), 15.70, 0.01);

    }

    @Test
    public void testPlaceBid() throws InvalidPriceException, ExpiredProductException, InstanceNotFoundException {
        BigDecimal price1 = new BigDecimal(20);
        BigDecimal price2 = new BigDecimal(15);
        BigDecimal price3 = new BigDecimal("15.50");
        BigDecimal price4 = new BigDecimal(10);
        BigDecimal price5 = new BigDecimal("20.50");
        BigDecimal price6 = new BigDecimal(25);
        Category category = new Category("cat1");
        User user = createUser("admin");
        Product product = createProduct(user,"testProd", LocalDateTime.now().plusMinutes(20),price4, category);




        categoryDao.save(category);
        userDao.save(user);
        productDao.save(product);


        Bid someBid = bidService.placeBid(user.getId(), price1, product.getId());
        assertEquals(someBid, product.getWinningBid());
        assertEquals(product.getCurrentPrice().compareTo(price4), 0);

        someBid = bidService.placeBid(user.getId(), price2, product.getId());
        assertNotEquals(someBid, product.getWinningBid());
        assertEquals(product.getCurrentPrice().compareTo(price3),0);

        someBid = bidService.placeBid(user.getId(), price1, product.getId());
        assertNotEquals(someBid, product.getWinningBid());
        assertEquals(product.getCurrentPrice().compareTo(price1) , 0);

        someBid = bidService.placeBid(user.getId(),price6, product.getId());
        assertEquals(someBid, product.getWinningBid());
        assertEquals(product.getCurrentPrice().compareTo(price5), 0);

    }

    @Test
    public void testPlaceBidInExpiredProduct() {
        BigDecimal price1 = new BigDecimal(200);
        BigDecimal price2 = new BigDecimal(10);
        Category category = new Category("cat1");
        User user = createUser("admin");
        Product product = createProduct(user,"testProd", LocalDateTime.now().minusDays(1),price2, category);

        categoryDao.save(category);
        userDao.save(user);
        productDao.save(product);

        assertThrows(ExpiredProductException.class, () -> bidService.placeBid(user.getId(), price1, product.getId()));

    }

    @Test
    public void testPlaceBidInvalidPrice() {
        BigDecimal price1 = new BigDecimal(10);
        BigDecimal price2 = new BigDecimal(9);
        BigDecimal price3 = new BigDecimal(-1);
        Category category = new Category("cat1");
        User user = createUser("admin");
        Product product = createProduct(user,"testProd", LocalDateTime.now().plusDays(1),price1, category);

        categoryDao.save(category);
        userDao.save(user);
        productDao.save(product);

        assertThrows(InvalidPriceException.class, () -> bidService.placeBid(user.getId(), price2, product.getId()));
        assertThrows(InvalidPriceException.class, () -> bidService.placeBid(user.getId(), price3, product.getId()));

    }

    @Test
    public void testFindUserBids() throws InvalidPriceException, ExpiredProductException, InstanceNotFoundException {
        BigDecimal price1 = new BigDecimal(20);
        BigDecimal price2 = new BigDecimal(25);
        BigDecimal price3 = new BigDecimal(27);
        BigDecimal price4 = new BigDecimal(30);
        BigDecimal price5 = new BigDecimal(10);

        Category category = new Category("cat1");
        User user = createUser("admin");
        Product product = createProduct(user,"testProd", LocalDateTime.now().plusMinutes(20),price5, category);

        categoryDao.save(category);
        userDao.save(user);
        productDao.save(product);

        Bid bid = bidService.placeBid(user.getId(), price1, product.getId());
        Bid bid1 = bidService.placeBid(user.getId(), price2, product.getId());
        Bid bid2 = bidService.placeBid(user.getId(), price3, product.getId());
        Bid bid3 = bidService.placeBid(user.getId(), price4, product.getId());

        Block<Bid> expectedBlock = new Block<>(Arrays.asList(bid,bid1,bid2,bid3), false);

        Block<Bid> expectedBlock1 = bidService.findUserBids(user.getId(), 0, 4);
        assertEquals(expectedBlock, expectedBlock1);

    }

}
