package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BidDto {

    public interface AllValidations {}

    private Long id;
    private Long userId;
    private BigDecimal price;
    private LocalDateTime bidDate;
    private String productName;
    private Long productId;
    private String bidStatus;

    public BidDto() {}

    public BidDto(Long id, Long userId, BigDecimal price, LocalDateTime bidDate, String productName, Long productId, String bidStatus) {
        this.id = id;
        this.userId = userId;
        this.price = price;
        this.bidDate = bidDate;
        this.productName = productName;
        this.productId = productId;
        this.bidStatus = bidStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(groups={BidDto.AllValidations.class})
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @NotNull(groups={BidDto.AllValidations.class})
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(groups={BidDto.AllValidations.class})
    public LocalDateTime getBidDate() {
        return bidDate;
    }

    public void setBidDate(LocalDateTime bidDate) {
        this.bidDate = bidDate;
    }

    @NotNull(groups={BidDto.AllValidations.class})
    @Size(min=1, max=60, groups={BidDto.AllValidations.class})
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @NotNull(groups={BidDto.AllValidations.class})
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @NotNull(groups={BidDto.AllValidations.class})
    @Size(min=1, max=20, groups={BidDto.AllValidations.class})
    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

}