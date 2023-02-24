package es.udc.paproject.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ProductDao extends PagingAndSortingRepository<Product, Long>, CustomizedProductDao {

    Slice<Product> findByUserIdOrderByExpirationDateDesc(Long userId, Pageable pageable);

}