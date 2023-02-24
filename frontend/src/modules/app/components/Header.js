import {useSelector} from 'react-redux';
import {Link} from 'react-router-dom';
import {FormattedMessage} from 'react-intl';

import users from '../../users';
import {FindProducts} from '../../products';

const Header = () => {

    const userName = useSelector(users.selectors.getUserName);

    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-dark border border">
            <Link className="navbar-brand" to="/">PA-10</Link>
            <button className="navbar-toggler" type="button" 
                data-toggle="collapse" data-target="#navbarSupportedContent" 
                aria-controls="navbarSupportedContent" aria-expanded="false" 
                aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">

                <ul className="navbar-nav mr-auto">
                    <li>
                        <FindProducts/>
                    </li>
                </ul>
                
                {userName ? 

                <ul className="navbar-nav">

                    <li className="nav-item">
                        <Link id="userBids" className="nav-link" to="/bids/find-user-bids">
                            <FormattedMessage id="project.shopping.header.bids"/>
                        </Link>

                    </li>
                    <li className="nav-item">
                        <Link id="findUserProd" className="nav-link" to="/products/find-user-products">
                            <FormattedMessage id="project.products.findUserProducts.title"/>
                        </Link>
                    </li>

                    <li className="nav-item dropdown">

                        <a className="dropdown-toggle nav-link" href="/"
                            data-toggle="dropdown">
                            <span className="fas fa-user"></span>&nbsp;
                            {userName}
                        </a>
                        <div className="dropdown-menu dropdown-menu-right">
                            <Link className="dropdown-item" to="/users/update-profile">
                                <FormattedMessage id="project.users.UpdateProfile.title"/>
                            </Link>
                            <Link className="dropdown-item" to="/users/change-password">
                                <FormattedMessage id="project.users.ChangePassword.title"/>
                            </Link>
                            <Link id="announceProd" className="dropdown-item" to="/products/announce-product-form">
                                <FormattedMessage id="project.products.announceProduct.title"/>
                            </Link>
                            <div className="dropdown-divider"></div>
                            <Link className="dropdown-item" to="/users/logout">
                                <FormattedMessage id="project.app.Header.logout"/>
                            </Link>
                        </div>

                    </li>

                </ul>
                
                :

                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link className="nav-link" to="/users/login">
                            <FormattedMessage id="project.users.Login.title"/>
                        </Link>
                    </li>
                </ul>
                
                }

            </div>
        </nav>

    );

};

export default Header;
