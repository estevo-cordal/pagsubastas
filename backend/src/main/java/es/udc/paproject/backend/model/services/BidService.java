package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Bid;
import es.udc.paproject.backend.model.exceptions.ExpiredProductException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidPriceException;

import java.math.BigDecimal;

public interface BidService {

    Bid placeBid(Long userId, BigDecimal price, Long productId)
            throws InstanceNotFoundException, ExpiredProductException, InvalidPriceException;

    public Block<Bid> findUserBids(Long userId, int page, int size);
}
