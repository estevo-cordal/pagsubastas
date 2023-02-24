import {useEffect, useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedDate, FormattedMessage, FormattedNumber, FormattedTime} from 'react-intl';
import {useParams} from 'react-router-dom';

import users from '../../users';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BidForm} from '../../shopping';
import {BackLink} from '../../common';

const ProductDetails = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const product = useSelector(selectors.getProduct);
    const categories = useSelector(selectors.getCategories);
    const dispatch = useDispatch();
    const {id} = useParams();

    useEffect(() => {

        const productId = Number(id);

        if (!Number.isNaN(productId)) {
            dispatch(actions.findProductById(productId));
        }

        return () => dispatch(actions.clearProduct());

    }, [id, dispatch]);

    if (!product) {
        return null;
    }

    return (

        <div>
            <BackLink/>
            <div className="card text-center">
                <div className="card-body">
                    <h5 className="card-title">{product.name}</h5>
                    <h6 className="card-subtitle text-muted">
                        <FormattedMessage id='project.global.fields.department'/>:&nbsp;
                        {selectors.getCategoryName(categories, product.category)}
                    </h6>
                    <h7 className="card-subtitle text-muted">
                        <FormattedMessage id='project.global.fields.sellerUser'/>{': '}
                        {/* eslint-disable-next-line */}
                        {product.sellerUser}
                    </h7>
                    <p className="card-text">{product.description}</p>
                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.publDate'/>{': '}
                        <FormattedDate value={product.publicationDate} type="datetime-local"/>{' '}
                        <FormattedMessage id='project.global.fields.atTime'/>{' '}
                        <FormattedTime value={product.publicationDate} type="datetime-local" hour='numeric' minute='numeric' second='numeric'/>
                    </p>
                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.minsLeft'/>{': '}
                        {product.minutesLeft} {' '}
                        <FormattedMessage id='project.global.fields.minutes'/>
                    </p>
                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.startPrice'/>{': '}
                        {/* eslint-disable-next-line */}
                        <FormattedNumber value={product.startPrice} style="currency" currency="EUR"/>
                    </p>

                    <p className="card-text font-weight-bold">
                        <FormattedMessage id='project.global.fields.currPrice'/>{': '}
                        {/* eslint-disable-next-line */}
                        <FormattedNumber value={product.currentPrice} style="currency" currency="EUR"/>
                    </p>
                    <p className="card-text">
                        <FormattedMessage id='project.global.fields.shipInfo'/>{': '}
                        {product.shipInfo}
                    </p>
                </div>
            </div>

            {loggedIn && (product.minutesLeft > 0) &&
            <div>
                <br/>
                {/* TODO aqui crear formulario de puja en nuevo componente*/}
                <BidForm product={product}/>
            </div>
            }

        </div>

    );

}

export default ProductDetails;
