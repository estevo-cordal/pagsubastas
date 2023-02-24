import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as actions from '../actions';
import * as selectors from '../selectors';
import {Pager} from '../../common';
import Orders from './Orders';

const FindOrdersResult = () => {

    const orderSearch = useSelector(selectors.getOrderSearch);
    const dispatch = useDispatch();

    if (!orderSearch) {
        return null;
    }

    if (orderSearch.result.items.length === 0) {
        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.shopping.FindBidsResult.noBids'/>
            </div>
        );
    }

    return (

        <div>
            <Orders orders={orderSearch.result.items}/>
            <Pager 
                back={{
                    enabled: orderSearch.page.page >= 1,
                    onClick: () => dispatch(actions.previousFindOrdersResultPage(orderSearch.page))}}
                next={{
                    enabled: orderSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindOrdersResultPage(orderSearch.page))}}/>
        </div>

    );

}

export default FindOrdersResult;
