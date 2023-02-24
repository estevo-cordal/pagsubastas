package es.udc.paproject.backend.model.entities;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BidDao extends PagingAndSortingRepository<Bid, Long> {

    Slice<Bid> findByUserIdOrderByBidDateDesc(Long userId, Pageable pageable);
}