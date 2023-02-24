import {config, appFetch} from './appFetch';

export const announceProduct =(name, description, minutes, startPrice, shipInfo, categoryId,
                               onSuccess, onErrors) =>
    appFetch(`/catalog/add`,
        config('POST', {name, description, minutes, startPrice, shipInfo, categoryId}),
            onSuccess, onErrors);

export const findUserProducts = ({page},
                                 onSuccess) => {
    let path = `/catalog/products/user?page=${page}`;
        appFetch(path, config('GET'), onSuccess);
}

export const findAllCategories = (onSuccess) =>
    appFetch('/catalog/categories', config('GET'), onSuccess);

export const findProducts = ({categoryId, keywords, page},
                             onSuccess) => {

    let path = `/catalog/products?page=${page}`;

    path += categoryId ? `&categoryId=${categoryId}` : "";
    path += keywords.length > 0 ? `&keywords=${encodeURIComponent(keywords)}` : "";

    appFetch(path, config('GET'), onSuccess);

}

export const findByProductId = (id, onSuccess) =>
    appFetch(`/catalog/products/${id}`, config('GET'), onSuccess);
