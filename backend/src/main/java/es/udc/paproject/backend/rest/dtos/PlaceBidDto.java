package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PlaceBidDto {

    public interface AllValidations {}

    private Long productId;
    private float price;

    public PlaceBidDto() {}

    public PlaceBidDto(float price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @NotNull(groups={PlaceBidDto.AllValidations.class})
    @Min(value = 0, groups={PlaceBidDto.AllValidations.class})
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
