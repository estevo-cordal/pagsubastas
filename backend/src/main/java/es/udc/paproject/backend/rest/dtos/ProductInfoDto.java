package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Bid;
import es.udc.paproject.backend.model.entities.Category;
import es.udc.paproject.backend.model.entities.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductInfoDto {

    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private String sellerUser;
    private LocalDateTime publicationDate;
    private long minutesLeft;
    private BigDecimal startPrice;
    private BigDecimal currentPrice;
    private String shipInfo;
    private Long winningBid;
    private boolean hasBidder;

    public ProductInfoDto() {}

    public ProductInfoDto(Long id, Long categoryId, String name, String description,
                      String sellerUser, LocalDateTime publicationDate, long minutesLeft,
                          BigDecimal startPrice, BigDecimal currentPrice, String shipInfo,
                          Long winningBid, boolean hasBidder) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.sellerUser = sellerUser;
        this.publicationDate = publicationDate;
        this.minutesLeft = minutesLeft;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
        this.shipInfo = shipInfo;
        this.winningBid = winningBid;
        this.hasBidder = hasBidder;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory() {
        return categoryId;
    }

    public void setCategory(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSellerUser() {
        return sellerUser;
    }

    public void setSellerUser(String sellerUser) {
        this.sellerUser = sellerUser;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public long getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(long minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(String shipInfo) {
        this.shipInfo = shipInfo;
    }

    public Long getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Long winningBid) {
        this.winningBid = winningBid;
    }

    public boolean getHasBidder() {
        return hasBidder;
    }

    public void setHasBidder(boolean hasBidder) {
        this.hasBidder = hasBidder;
    }

}