package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Product;

public class ProductConversor {

    private ProductConversor() {
    }

    public final static List<ProductSummaryDto> toProductSummaryDtos(List<Product> products) {
        return products.stream().map(p -> toProductSummaryDto(p)).collect(Collectors.toList());
    }

    private final static ProductSummaryDto toProductSummaryDto(Product product) {
        if (product.getExpirationDate().isAfter(LocalDateTime.now())) {
            LocalDateTime exp = product.getExpirationDate();
            LocalDateTime now = LocalDateTime.now();
            Long minutes = ChronoUnit.MINUTES.between(now, exp);


            return new ProductSummaryDto(product.getId(), product.getName(), product.getCategory().getId(),
                    product.getCurrentPrice(), minutes);
        } else
            return new ProductSummaryDto(product.getId(), product.getName(), product.getCategory().getId(),
                    product.getCurrentPrice(), 0L);
    }

    public final static List<UserProductsDto> toUserProductsDtos(List<Product> products) {
        return products.stream().map(p -> toUserProductsDto(p)).collect(Collectors.toList());
    }

    private final static UserProductsDto toUserProductsDto(Product product) {
        String email = "No hay pujas";
        if (product.getWinningBid().getUser().getId() != 4L) {
            email = product.getWinningBid().getUser().getEmail();
        }
        if (product.getExpirationDate().isAfter(LocalDateTime.now())) {
            LocalDateTime exp = product.getExpirationDate();
            LocalDateTime now = LocalDateTime.now();
            Long minutes = ChronoUnit.MINUTES.between(now, exp);

            return new UserProductsDto(product.getId(), product.getName(), product.getCurrentPrice(), minutes, email);
        } else
            return new UserProductsDto(product.getId(), product.getName(), product.getCurrentPrice(), 0L, email);
    }

    public final static ProductInfoDto toProductInfoDto(Product product) {

        return new ProductInfoDto(product.getId(), product.getCategory().getId(), product.getName(),
                product.getDescription(), product.getUser().getUserName(), product.getPublicationDate(),
                product.remainingMinutes(), product.getStartPrice(), product.getCurrentPrice(), product.getShipInfo(),
                product.getWinningBid().getId(), product.getHasBidder());

    }
}
