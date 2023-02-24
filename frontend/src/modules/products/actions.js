import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
import backend from '../../backend';

const findAllCategoriesCompleted = categories => ({
    type: actionTypes.FIND_ALL_CATEGORIES_COMPLETED,
    categories
}); 

export const findAllCategories = () => (dispatch, getState) => {

    const categories = selectors.getCategories(getState());

    if (!categories) {

        backend.productService.findAllCategories(
            categories => dispatch(findAllCategoriesCompleted(categories))
        );
        
    }

}

const announceProductCompleted = (id) => ({
    type: actionTypes.ANNOUNCE_PRODUCT_COMPLETED,
    id
});

export const announceProduct = (name, description, minutes, startPrice, shipInfo, categoryId,
                         onSuccess, onErrors) => dispatch =>
    backend.productService.announceProduct(name, description, minutes, startPrice, shipInfo, categoryId,
        (id) => {
            dispatch(announceProductCompleted(id));
            onSuccess(id);
        },
        onErrors);

const findUserProductsCompleted = userProducts => ({
    type: actionTypes.FIND_USER_PRODUCTS_COMPLETED,
    userProducts
});
export const findUserProducts = (page) => dispatch => {
    dispatch(clearUserProducts());
    backend.productService.findUserProducts(page,
        result => dispatch(findUserProductsCompleted({page, result})));
}
export const previousFindUserProductsResultPage = page =>
    findUserProducts({page: page.page-1});

export const nextFindUserProductsResultPage = page =>
    findUserProducts({page: page.page+1});


const findProductsCompleted = productSearch => ({
    type: actionTypes.FIND_PRODUCTS_COMPLETED,
    productSearch
});

export const findProducts = criteria => dispatch => {

    dispatch(clearProductSearch());
    backend.productService.findProducts(criteria,
        result => dispatch(findProductsCompleted({criteria, result})));

}


const clearUserProducts = () => ({
    type: actionTypes.CLEAR_USER_PRODUCTS
});

export const previousFindProductsResultPage = criteria =>
    findProducts({...criteria, page: criteria.page-1});

export const nextFindProductsResultPage = criteria =>
    findProducts({...criteria, page: criteria.page+1});

const clearProductSearch = () => ({
    type: actionTypes.CLEAR_PRODUCT_SEARCH
});

const findProductByIdCompleted = product => ({
    type: actionTypes.FIND_PRODUCT_BY_ID_COMPLETED,
    product
});
    
export const findProductById = id => dispatch => {
    backend.productService.findByProductId(id,
        product => dispatch(findProductByIdCompleted(product)));
}

export const clearProduct = () => ({
    type: actionTypes.CLEAR_PRODUCT
});
