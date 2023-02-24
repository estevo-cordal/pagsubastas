package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Bid {

    private Long id;
    private User user;
    private BigDecimal price;
    private Product product;
    private LocalDateTime bidDate;

    public enum status {
        PERDEDORA, GANANDO, GANADORA;

        public static status parse(String value) {
            if (value.equalsIgnoreCase("perdedora"))
                return status.PERDEDORA;
            if (value.equalsIgnoreCase("ganando"))
                return status.GANANDO;
            if (value.equalsIgnoreCase("ganadora"))
                return status.GANADORA;
            return null;
        }
    }
    private status bidStatus;

    public Bid() {}

    public Bid(User user, BigDecimal price, Product product, LocalDateTime bidDate, status bidStatus) {
        this.user = user;
        this.price = price;
        this.product = product;
        this.bidDate = bidDate;
        this.bidStatus = bidStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getBidDate() {
        return bidDate;
    }

    public void setBidDate(LocalDateTime bidDate) {
        this.bidDate = bidDate;
    }

    public status getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(status bidStatus) {
        this.bidStatus = bidStatus;
    }
}