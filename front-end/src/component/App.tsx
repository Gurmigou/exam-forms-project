import React, {useEffect} from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import MainPage from './mainpage/MainPage';
import Registration from "./security/Registration";
import Login from "./security/Login";
import AccountSettings from "./appComponents/account/AccountSettings";
import UserFormList from "./appComponents/accountFormsInfo/UserFormList";
import FormEditor from "./appComponents/formEditor/FormEditor";
import FormResults from "./appComponents/FormResults";
import Form from "./appComponents/Form";
import NotFound from "./info/NotFound";
import AboutProject from "./info/AboutProject";
import FormStatistics from "./appComponents/formAnalytics/FormStatistics";

import 'primereact/resources/themes/lara-light-indigo/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import "primeflex/primeflex.css";
import '../style/App.css';
import {useDispatch, useSelector} from "react-redux";
import {relogin} from "../utils/security/securityUtils";

function App() {
    const isAuth = useSelector((state: any) => state.isAuth);
    const dispatch = useDispatch();

    useEffect(() => {
        if (isAuth === false && localStorage.getItem("token") !== null) {
            relogin(dispatch);
        }
     }, [isAuth, dispatch]);

    return (
        <div className="App">
            <Router>
                <Switch>
                    <Route exact path="/" component={MainPage}/>
                    <Route exact path="/login" component={Login}/>
                    <Route exact path="/registration" component={Registration}/>
                    <Route exact path="/about" component={AboutProject}/>
                    <Route exact path="/user/account" component={AccountSettings}/>
                    <Route exact path="/user/form-list" component={UserFormList}/>
                    <Route exact path="/form/construct" component={FormEditor}/>
                    <Route exact path="/form/:id" component={Form}/>
                    <Route exact path="/form/:id/view-result" component={FormResults}/>
                    <Route exact path="/form/:id/view-stats" component={FormStatistics}/>
                    <Route path="*" component={NotFound}/>
                </Switch>
            </Router>
        </div>
  );
}

export default App;