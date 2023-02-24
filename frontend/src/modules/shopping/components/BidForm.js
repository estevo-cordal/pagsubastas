import {useState} from 'react';
import React from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber} from 'react-intl';
import {useHistory} from 'react-router-dom';

import {Errors, Success} from '../../common';
import * as actions from '../actions';

const BidForm = ({product}) => {
    const dispatch = useDispatch();
    const history = useHistory();
    const [backendErrors, setBackendErrors] = useState(null);
    const [message, setMessage] = useState(null);
    const [price, setPrice] = useState((product.currentPrice + (product.hasBidder ? 0.01 : 0)).toFixed(2));
    let minim;
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.placeBid(
                price, product.id,
                (id, currentPrice, minutesLeft, bidStatus, hasBidder) => (switchMessage(id, bidStatus),
                    setMinimum(hasBidder), setPrice((currentPrice + 0.01).toFixed(2))),
                errors => setBackendErrors(errors)));

        } else {

            setBackendErrors(null);
            form.classList.add('was-validated');

        }

    }

    function switchMessage(id, bidStatus) {
        if (bidStatus.localeCompare("GANANDO") === 0) {
            setMessage(<FormattedMessage id='project.global.BuyForm.winningBid'/>)
        } else {
            setMessage(<FormattedMessage id='project.global.BuyForm.losingBid'/>)
        }
    }

    function setMinimum(hasBidder) {
        console.log(hasBidder);
        if (hasBidder)
            minim = (product.currentPrice + 0.01).toFixed(2);
        else {
            minim = product.currentPrice.toFixed(2);
        }
    }

    return <div>
        <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
        <Success message={message} onClose={() => setMessage(null)}/>
        <div className="offset-md-4 col-form-label" onLoad={setMinimum(product.hasBidder)}>
            <FormattedMessage id='project.global.fields.minimumPrice'  />
            <FormattedNumber value={minim} style="currency" currency="EUR"/>
        </div>
        <form ref={node => form = node}
            className="needs-validation" noValidate
            onSubmit={e => handleSubmit(e)}>
            <div className="form-group row">
                <label htmlFor="price" className="offset-md-3 col-form-label">
                    <FormattedMessage id="project.global.fields.quantity"/>
                </label>
                <div className="col-md-2">
                    <input type="number" step="0.01" id="price"
                           value={price}
                           onChange={e => (
                                   setPrice(Number(e.target.value))
                           )}
                    autoFocus
                    min={minim} />
                    <div className="invalid-feedback">
                        <FormattedMessage id='project.global.validator.incorrectQuantity'/>
                    </div>
                </div>

                <div className="offset-md-6 col-md-2">
                    <button type="submit" className="btn btn-primary">
                        <FormattedMessage id="project.shopping.BuyForm.confirmation"/>
                    </button>
                </div>
            </div>
        </form>
    </div>;

}

export default BidForm;
