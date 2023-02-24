import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import * as actions from '../actions';
import * as selectors from '../selectors';
import {Pager} from '../../common';
import UserProducts from './UserProducts';

const FindOrdersResult = () => {
    const dispatch = useDispatch();
    const userProducts = useSelector(selectors.getUserProducts);

    if (!userProducts) {
        return null;
    }

    if (userProducts.result.items.length === 0) {
        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.products.findUserProductsResult.noProducts'/>
            </div>
        );
    }

    return (

        <div>
            <UserProducts userProducts={userProducts.result.items} />
            <Pager
                back={{
                    enabled: userProducts.page.page >= 1,
                    onClick: () => dispatch(actions.previousFindUserProductsResultPage(userProducts.page))}}
                next={{
                    enabled: userProducts.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindUserProductsResultPage(userProducts.page))}}/>
        </div>

    );

}

export default FindOrdersResult;