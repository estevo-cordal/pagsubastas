package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import es.udc.paproject.backend.model.entities.Bid;

public class BidConversor {

    private BidConversor() {}

    public static Long minutesLeft(Bid bid) {
        LocalDateTime exp = bid.getProduct().getExpirationDate();
        LocalDateTime now = LocalDateTime.now();
        Long minutes = ChronoUnit.MINUTES.between(now, exp);
        return minutes;
    }

    public final static BidDto toBidDto(Bid bid) {
        return new BidDto(bid.getId(), bid.getUser().getId(), bid.getPrice(), bid.getBidDate(),
                bid.getProduct().getName(), bid.getProduct().getId(), bid.getBidStatus().toString());
    }

    public final static List<BidDto> toBidDtos(List<Bid> bids) {
        return bids.stream().map(BidConversor::toBidDto).collect(Collectors.toList());
    }

    public final static List<BidSummaryDto> toBidSummaryDtos(List<Bid> bids) {
        return bids.stream().map(BidConversor::toBidSummaryDto).collect(Collectors.toList());
    }

    public final static BidSummaryDto toBidSummaryDto(Bid bid) {
        return new BidSummaryDto(bid.getId(), bid.getProduct().getCurrentPrice(), minutesLeft(bid),
                bid.getBidStatus().toString(), bid.getProduct().getHasBidder());
    }

}
