package es.udc.paproject.backend.rest.controllers;

import java.math.BigDecimal;
import java.util.Locale;

import es.udc.paproject.backend.model.entities.Bid;
import es.udc.paproject.backend.model.exceptions.ExpiredProductException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidPriceException;
import es.udc.paproject.backend.model.services.BidService;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bids")
public class BidController {

    private final static String EXPIRED_PRODUCT_EXCEPTION_CODE = "project.exceptions.ExpiredProductException";
    private final static String INVALID_PRICE_EXCEPTION_CODE = "project.exceptions.InvalidPriceException";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BidService bidService;

    @ExceptionHandler(InvalidPriceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleInvalidPriceException(InvalidPriceException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INVALID_PRICE_EXCEPTION_CODE,
                new Object[] {exception.getKey()}, INVALID_PRICE_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @ExceptionHandler(ExpiredProductException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleExpiredProductException(ExpiredProductException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(EXPIRED_PRODUCT_EXCEPTION_CODE,
                new Object[] {exception.getKey()}, EXPIRED_PRODUCT_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @PostMapping("/products/{productId}")
    public BidSummaryDto placeBid(@RequestAttribute Long userId, @Validated({PlaceBidDto.AllValidations.class})
                                    @RequestBody PlaceBidDto p)
            throws InvalidPriceException, ExpiredProductException, InstanceNotFoundException {

        return(BidConversor.toBidSummaryDto(bidService.placeBid(userId, BigDecimal.valueOf(p.getPrice()),
                p.getProductId())));

    }

    @GetMapping("/user")
    public BlockDto<BidDto> findUserBids(@RequestAttribute Long userId,
                                            @RequestParam(defaultValue="0") int page) {

        Block<Bid> orderBlock = bidService.findUserBids(userId, page, 2);

        return new BlockDto<>(BidConversor.toBidDtos(orderBlock.getItems()), orderBlock.getExistMoreItems());

    }

}
