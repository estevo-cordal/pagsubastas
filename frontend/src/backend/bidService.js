import {config, appFetch} from './appFetch';

export const placeBid =(price, productId, onSuccess,
                        onErrors) =>
    appFetch(`/bids/products/${productId}`,
        config('POST', {price,productId}), onSuccess, onErrors);


export const findUserBids = ({page},
                             onSuccess) => {

    let path = `/bids/user?page=${page}`;

    appFetch(path, config('GET'), onSuccess);

}
