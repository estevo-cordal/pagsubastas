import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';

import * as actions from '../actions';

const FindOrders = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    useEffect(() => {

        dispatch(actions.findOrders({page: 0}));
        history.push('/bids/find-bids-result');

    });

    return null;

}

export default FindOrders;
