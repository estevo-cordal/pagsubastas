import {combineReducers} from 'redux';

import app from '../modules/app';
import users from '../modules/users';
import products from '../modules/products';
import bids from '../modules/shopping';

const rootReducer = combineReducers({
    app: app.reducer,
    users: users.reducer,
    products: products.reducer,
    bids: bids.reducer
});

export default rootReducer;
