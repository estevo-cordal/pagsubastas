import {combineReducers} from 'redux';

import users from '../users';
import * as actionTypes from './actionTypes';

const initialState = {
    orderSearch: null,
    bid:null
};

const bid = (state = initialState.bid, action) => {

    switch (action.type) {

        case users.actionTypes.LOGIN_COMPLETED:
            return action.authenticatedUser;

        case users.actionTypes.SIGN_UP_COMPLETED:
            return action.authenticatedUser;

        case actionTypes.BID_COMPLETED:
            return action.id;

        default:
            return state;

    }

}

const orderSearch = (state = initialState.orderSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_ORDERS_COMPLETED:
            return action.orderSearch;

        case actionTypes.CLEAR_ORDER_SEARCH:
            return initialState.orderSearch;

        default:
            return state;

    }

}

const reducer = combineReducers({
    orderSearch,
    bid
});

export default reducer;


