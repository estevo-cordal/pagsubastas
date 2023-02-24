import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';
import {FindProductsResult, ProductDetails} from "../../products";
import AnnounceProductForm from "../../products/components/announceProductForm";

import {FindOrders, FindOrdersResult} from "../../shopping";
import {FindUserProducts, FindUserProductsResult} from "../../products";
const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/products/find-products-result"><FindProductsResult/></Route>
                <Route exact path="/products/product-details/:id"><ProductDetails/></Route>
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/products/announce-product-form"><AnnounceProductForm/></Route>}
                {loggedIn && <Route exact path="/products/find-user-products"><FindUserProducts/></Route>}
                {loggedIn && <Route exact path="/products/find-user-products-result"><FindUserProductsResult/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
                {loggedIn && <Route exact path="/bids/find-user-bids"><FindOrders/></Route>}
                {loggedIn && <Route exact path="/bids/find-bids-result"><FindOrdersResult/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;
