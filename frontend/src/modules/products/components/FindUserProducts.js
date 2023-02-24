import {useEffect, useState} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';

import * as actions from '../actions';

const FindUserProducts = () => {

    const dispatch = useDispatch();
    const history = useHistory();


    useEffect(() => {

        dispatch(actions.findUserProducts({page: 0}));
        history.push('/products/find-user-products-result');

    });
    return null;

}

export default FindUserProducts;