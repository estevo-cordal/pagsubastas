package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PriceDto {

    public interface AllValidations {}

    private BigDecimal price;

    public PriceDto() {}

    public PriceDto(BigDecimal price) {
        this.price = price;
    }

    @NotNull(groups={PriceDto.AllValidations.class})
    @Min(value = 0, groups={PriceDto.AllValidations.class})
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
