import {FormattedMessage, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';

import {ProductLink} from '../../common';
const UserProducts = ({userProducts}) => (

    <table className="table table-striped table-hover">

        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.global.fields.name'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.currPrice'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.minsLeft'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.email'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {userProducts.map(product =>
            <tr key={product.id}>
                <td><ProductLink id={product.id} name={product.name}/></td>
                {/* eslint-disable-next-line */}
                <td><FormattedNumber value={product.price} style="currency" currency="EUR"/></td>
                {product.minutes>0 && <td>{product.minutes}</td>}
                {product.minutes===0 && <FormattedMessage id="project.products.UserProducts.finalizada" />}
                 <td>{product.email}</td>

            </tr>
        )}
        </tbody>

    </table>

);

UserProducts.propTypes = {
    userProducts: PropTypes.array.isRequired
};

export default UserProducts;
