package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class UserProductsDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Long minutes;
    private String email;

    public UserProductsDto() {}

    public UserProductsDto(Long id, String name, BigDecimal price, Long minutes, String email) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.minutes = minutes;
        this.email = email;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
