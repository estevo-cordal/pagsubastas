import {FormattedMessage, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';
import {ProductLink} from '../../common';

const Products = ({products, categories}) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.department'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.name'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.currPrice'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.minsLeft'/>
                </th>
            </tr>
        </thead>

        <tbody>
            {products.map(product => 
                <tr key={product.id}>
                    <td>{selectors.getCategoryName(categories, product.categoryId)}</td>
                    <td><ProductLink id={product.id} name={product.name}/></td>
                    {/* eslint-disable-next-line */}
                    <td><FormattedNumber value={product.price} style="currency" currency="EUR"/></td>
                    <td>{product.minutes}</td>
                </tr>
            )}
        </tbody>

    </table>

);

Products.propTypes = {
    products: PropTypes.array.isRequired,
    categories: PropTypes.array.isRequired
};

export default Products;
