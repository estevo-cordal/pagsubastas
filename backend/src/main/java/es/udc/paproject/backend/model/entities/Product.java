package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class Product {

    private Long id;
    private User user;
    private String name;
    private String description;
    private LocalDateTime publicationDate;
    private LocalDateTime expirationDate;
    private BigDecimal startPrice;
    private BigDecimal currentPrice;
    private String shipInfo;
    private Category category;
    private Bid winningBid;
    private Long version;
    private boolean hasBidder;

    public Product() {}

    public Product(User user, String name, String description, LocalDateTime publicationDate,
                   LocalDateTime expirationDate, BigDecimal startPrice, BigDecimal currentPrice, String shipInfo,
                   Category category) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.publicationDate = publicationDate;
        this.expirationDate = expirationDate;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
        this.shipInfo = shipInfo;
        this.category = category;
        this.winningBid = null;
        this.hasBidder = false;
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

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "winningBid")
    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    public String getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(String shipInfo) {
        this.shipInfo = shipInfo;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long remainingMinutes() {
        LocalDateTime now = LocalDateTime.now();
        if (expirationDate.isAfter(now)) {
            return ChronoUnit.MINUTES.between(now, expirationDate);
        } else return 0;
    }

    public boolean getHasBidder() {
        return hasBidder;
    }

    public void setHasBidder(boolean hasBidder) {
        this.hasBidder = hasBidder;
    }

    @Version
    //@Column(name = "vers", columnDefinition = "integer DEFAULT 0", nullable = false)
    public Long getVersion(){
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
