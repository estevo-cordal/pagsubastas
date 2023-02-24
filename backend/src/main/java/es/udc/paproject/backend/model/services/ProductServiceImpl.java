package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidDateException;
import es.udc.paproject.backend.rest.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BidDao bidDao;

    public Product announceProduct(Long userId, String name, String description, long minutes, BigDecimal startPrice,
                                   String shipInfo, Long categoryId) throws InstanceNotFoundException {

        User user = permissionChecker.checkUser(userId);
        Category category = permissionChecker.checkCategory(categoryId);
        User admin = permissionChecker.checkUser(userDao.findByUserName("admin").get().getId());
        BigDecimal zero = new BigDecimal(0);

        Product newProd = new Product(user, name, description, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(minutes), startPrice, startPrice, shipInfo, category);

        productDao.save(newProd);
        Bid bid = new Bid(admin, zero, newProd, LocalDateTime.now(), Bid.status.GANANDO);
        bidDao.save(bid);

        newProd.setWinningBid(bid);

        return newProd;
    }

    @Override
    public Product getProductInfo(Long productId) throws InstanceNotFoundException {
        Product p = permissionChecker.checkProduct(productId);
        if (p.getExpirationDate().isBefore(LocalDateTime.now()) && p.getWinningBid() != null &&
                !Objects.equals(p.getUser().getUserName(), "admin") &&
                p.getWinningBid().getBidStatus() != Bid.status.GANADORA)
            p.getWinningBid().setBidStatus(Bid.status.GANADORA);
        return p;
    }

    @Override
    public Block<Product> findProducts(Long categoryId, String keywords, int page, int size) {

        Slice<Product> slice = productDao.find(categoryId, keywords, page, size);

        return new Block<>(slice.getContent(), slice.hasNext());

    }

    @Override
    public List<Category> findAllCategories() {

        Iterable<Category> categories = categoryDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<Category> categoriesAsList = new ArrayList<>();

        categories.forEach(c -> categoriesAsList.add(c));

        return categoriesAsList;
    }

    @Override
    @Transactional(readOnly=true)
    public Block<Product> findUserProducts(Long userId, int page, int size) {

        Slice<Product> slice = productDao.findByUserIdOrderByExpirationDateDesc(userId, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());

    }

}
