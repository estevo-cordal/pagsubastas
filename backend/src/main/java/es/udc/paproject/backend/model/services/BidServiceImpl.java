package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.ExpiredProductException;
import es.udc.paproject.backend.model.exceptions.InvalidPriceException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional
public class BidServiceImpl implements BidService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PermissionChecker permissionChecker;

    @Autowired
    private BidDao bidDao;

    public Bid placeBid(Long userId, BigDecimal price, Long productId)
            throws InstanceNotFoundException, ExpiredProductException, InvalidPriceException {
        BigDecimal zero = new BigDecimal(0);
        BigDecimal suma = new BigDecimal("0.5");
        User user = permissionChecker.checkUser(userId);
        Product product = productService.getProductInfo(productId);

        if (product.getExpirationDate().isBefore(LocalDateTime.now()))
            throw new ExpiredProductException("project.services.bidservice", productId);

        if (price.compareTo(zero) <= 0 || product.getStartPrice().compareTo(price) > 0)
            throw new InvalidPriceException("project.services.bidservice", price);

        Bid newBid = new Bid(user, price, product, LocalDateTime.now(), Bid.status.PERDEDORA);
        bidDao.save(newBid);

        Bid winningBid = product.getWinningBid();

        if (product.getHasBidder()) {
            BigDecimal winPrice = winningBid.getPrice();
            BigDecimal currentPrice = product.getCurrentPrice();

            if (price.compareTo(currentPrice) <= 0 )
                throw new InvalidPriceException("project.services.bidservice", price);

            // la mayor puja se mantiene
            if (winPrice.compareTo(price) > 0) {
                if(winPrice.compareTo(price.add(suma)) < 0)
                    product.setCurrentPrice(winPrice);
                else
                    product.setCurrentPrice(price.add(suma));
            }
            // la mayor puja pasa a ser esta puja
            else if (winPrice.compareTo(price) < 0) {
                product.setCurrentPrice(winPrice.add(suma));
                winningBid.setBidStatus(Bid.status.PERDEDORA);
                newBid.setBidStatus(Bid.status.GANANDO);
                product.setWinningBid(newBid);
            }
            // empate
            else
                product.setCurrentPrice(price);
        } else {  // es la primera Bid
            System.out.println("hola :D");
            product.setHasBidder(true);
            System.out.println(product.getHasBidder());
            newBid.setBidStatus(Bid.status.GANANDO);
            product.setWinningBid(newBid);
        }

        return newBid;
    }

    public Block<Bid> findUserBids(Long userId, int page, int size){
        Slice<Bid> slice = bidDao.findByUserIdOrderByBidDateDesc(userId, PageRequest.of(page, size));

        for (Bid bid : slice) {
            checkBidStatus(bid.getProduct());
        }

        return new Block<>(slice.getContent(), slice.hasNext());
    }

    public void checkBidStatus(Product p) {
        if (p.getExpirationDate().isBefore(LocalDateTime.now()) && p.getWinningBid() != null &&
                !Objects.equals(p.getUser().getUserName(), "admin") &&
                p.getWinningBid().getBidStatus() != Bid.status.GANADORA)
            p.getWinningBid().setBidStatus(Bid.status.GANADORA);
    }

}
