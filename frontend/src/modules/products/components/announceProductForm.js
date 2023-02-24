import React, {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';
import CategorySelector from "./CategorySelector";

const AnnounceProductForm = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [name, setName] = useState('');
    const [desc, setDesc] = useState('');
    const [minutes, setMinutes] = useState('');
    const [startPrice, setStartPrice] = useState('');
    const [shipInfo, setShipInfo] = useState('');
    const [categoryId, setCategoryId]  = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    //const [passwordsDoNotMatch, setPasswordsDoNotMatch] = useState(false);
    let form;
    let confirmPasswordInput;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.announceProduct(
                name, desc, minutes, startPrice, shipInfo, toNumber(categoryId),
                (id) => (history.push('/products/product-details/' + `${id}`)),
                errors => setBackendErrors(errors)),
            );


        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');

        }

    }

    const toNumber = value => value.length > 0 ? Number(value) : null;

    return (
        <div>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.products.announceProduct.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                          className="needs-validation" noValidate
                          onSubmit={e => handleSubmit(e)}>
                        <div className="form-group row">
                            <label htmlFor="name" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.name"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="name" className="form-control"
                                       value={name}
                                       onChange={e => setName(e.target.value)}
                                       autoFocus
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="description" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.description"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="description" className="form-control"
                                       value={desc}
                                       onChange={e => setDesc(e.target.value)}
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="minutes" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.minutes"/>
                            </label>
                            <div className="col-md-4">
                                <input ref={node => confirmPasswordInput = node}
                                       type="number" id="minutes" min="1" className="form-control"
                                       value={minutes}
                                       onChange={e => setMinutes(e.target.value)}
                                       required/>
                                <div className="invalid-feedback">
                                        <FormattedMessage id='project.global.validator.positive'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="startPrice" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.startPrice"/>
                            </label>
                            <div className="col-md-4">
                                <input type="number" min="0.01" step="0.01" id="startPrice" className="form-control"
                                       value={startPrice}
                                       onChange={e => setStartPrice(e.target.value)}
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.positive'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="shipInfo" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.shipInfo"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="shipInfo" className="form-control"
                                       value={shipInfo}
                                       onChange={e => setShipInfo(e.target.value)}
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="categoryId" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.department"/>
                            </label>
                            <div className="col-md-4">
                                <CategorySelector id="categorySelect" className="custom-select my-1 mr-sm-2"
                                                  value={categoryId} onChange={e => setCategoryId(e.target.value)}
                                                  required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <div className="offset-md-3 col-md-2">
                                <button id="announceBtn" type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.products.announceProduct.title"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );

}

export default AnnounceProductForm;
