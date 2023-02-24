import {FormattedMessage, FormattedDate, FormattedTime, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';

import ProductLink from '../../common/components/ProductLink';
import React, {useState} from "react";

const Orders = ({orders}) => {

    function switchStatus(bidStatus) {
        if (bidStatus.localeCompare("GANANDO") === 0) {
            return (<FormattedMessage id='project.global.bid.winningBid'/>)
        } else if (bidStatus.localeCompare("PERDEDORA") === 0){
            return (<FormattedMessage id='project.global.bid.loserBid'/>)
        } else {
        return (<FormattedMessage id='project.global.bid.winnerBid'/>)
    }
    }


    return <table className="table table-striped table-hover">

        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.shopping.Orders.date'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.shopping.Orders.product'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.shopping.Orders.quantity'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.shopping.Orders.status'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {orders.map(order =>
            <tr key={order.id}>
                <td><FormattedDate value={order.bidDate} type="datetime-local"/>{' '}
                    <FormattedMessage id='project.global.fields.atTime'/>{' '}
                    <FormattedTime value={order.bidDate} type="datetime-local" hour='numeric' minute='numeric' second='numeric'/>
                </td>
                <td><ProductLink id={order.productId} name={order.productName}/></td>
                <td><FormattedNumber value={order.price} style="currency" currency="EUR"/></td>
                <td>{switchStatus(order.bidStatus)}</td>
            </tr>
        )}
        </tbody>

    </table>;

}

Orders.propTypes = {
    orders: PropTypes.array.isRequired
};

export default Orders;

