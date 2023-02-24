import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';
import bid from '../shopping';
const initialState = {
    categories: null,
    productSearch: null,
    product: null,
    userProducts:null
};

const categories = (state = initialState.categories, action) => {

    switch (action.type) {

        case actionTypes.FIND_ALL_CATEGORIES_COMPLETED:
            return action.categories;

        default:
            return state;

    }

}

const userProducts = (state = initialState.userProducts, action) => {

    switch (action.type) {

        case actionTypes.FIND_USER_PRODUCTS_COMPLETED:
            return action.userProducts;

            case actionTypes.CLEAR_USER_PRODUCTS:
            return initialState.userProducts;
        default:
            return state;

    }

}

const productSearch = (state = initialState.productSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_PRODUCTS_COMPLETED:
            return action.productSearch;

        case actionTypes.CLEAR_PRODUCT_SEARCH:
            return initialState.productSearch;

        default:
            return state;

    }

}

const product = (state = initialState.product, action) => {

    switch (action.type) {

        case actionTypes.FIND_PRODUCT_BY_ID_COMPLETED:
            return action.product;

        case actionTypes.CLEAR_PRODUCT:
            return initialState.product

        case bid.actionTypes.BID_COMPLETED:
            return {...state, currentPrice: action.currentPrice, minutesLeft: action.minutesLeft,
                hasBidder: action.hasBidder};

        case actionTypes.ANNOUNCE_PRODUCT_COMPLETED:
            return {...state, id: action.id}

        default:
            return state;

    }

}

const reducer = combineReducers({
    categories,
    productSearch,
    product,
    userProducts

});

export default reducer;


