package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class BidSummaryDto {
    private Long id;
    private BigDecimal currentPrice;
    private Long minutesLeft;
    private String bidStatus;
    private boolean hasBidder;

    public BidSummaryDto() {}

    public BidSummaryDto(Long id, BigDecimal productPrice, Long minutesLeft, String bidStatus, boolean hasBidder) {

        this.id = id;
        this.currentPrice = productPrice;
        this.minutesLeft = minutesLeft;
        this.bidStatus = bidStatus;
        this.hasBidder = hasBidder;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(Long minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }

    public boolean getHasBidder() {
        return hasBidder;
    }

    public void setHasBidder(boolean hasBidder) {
        this.hasBidder = hasBidder;
    }
}
