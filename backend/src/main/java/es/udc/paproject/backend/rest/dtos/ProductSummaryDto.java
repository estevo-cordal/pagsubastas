package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class ProductSummaryDto {

    private Long id;
    private String name;
    private long categoryId;
    private Long minutes;
    private BigDecimal price;

    public ProductSummaryDto() {}

    public ProductSummaryDto(Long id, String name, long categoryId, BigDecimal price, Long minutes) {

        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.minutes = minutes;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

}
