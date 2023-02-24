import backend from '../../backend';
import * as actionTypes from './actionTypes';

const bidCompleted = (id, currentPrice, minutesLeft, hasBidder) => ({
    type: actionTypes.BID_COMPLETED,
    id, currentPrice, minutesLeft, hasBidder
});

export const placeBid = (price, productId,
                          onSuccess, onErrors) => dispatch =>
    backend.bidService.placeBid( price,
        productId, ({id, currentPrice, minutesLeft, bidStatus, hasBidder}) => {
            dispatch(bidCompleted(id, currentPrice, minutesLeft, hasBidder));
            onSuccess(id, currentPrice, minutesLeft, bidStatus, hasBidder);
        },
        onErrors);

const findOrdersCompleted = orderSearch => ({
    type: actionTypes.FIND_ORDERS_COMPLETED,
    orderSearch
});

const clearOrderSearch = () => ({
    type: actionTypes.CLEAR_ORDER_SEARCH
});

export const findOrders = (page) => dispatch => {

    dispatch(clearOrderSearch());
    backend.bidService.findUserBids(page,
        result => dispatch(findOrdersCompleted({page, result})));
}

export const previousFindOrdersResultPage = page =>
    findOrders({page: page.page-1});

export const nextFindOrdersResultPage = page =>
    findOrders({page: page.page+1});
