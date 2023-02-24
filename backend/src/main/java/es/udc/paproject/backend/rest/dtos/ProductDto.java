package es.udc.paproject.backend.rest.dtos;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductDto {

    public interface AllValidations {}

    private String name;
    private String description;
    private long minutes;
    private BigDecimal startPrice;
    private String shipInfo;
    private Long categoryId;

    public ProductDto() {}

    public ProductDto(String name, String description, long minutes,
                      BigDecimal startPrice, String shipInfo, Long categoryId) {
        this.name = name;
        this.description = description;
        this.minutes = minutes;
        this.startPrice = startPrice;
        this.shipInfo = shipInfo;
        this.categoryId = categoryId;
    }

    @NotNull(groups = ProductDto.AllValidations.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(groups = ProductDto.AllValidations.class)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(groups = ProductDto.AllValidations.class)
    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    @NotNull(groups = ProductDto.AllValidations.class)
    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    @NotNull(groups = ProductDto.AllValidations.class)
    public String getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(String shipInfo) {
        this.shipInfo = shipInfo;
    }

    @NotNull(groups = ProductDto.AllValidations.class)
    @Min(0)
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}