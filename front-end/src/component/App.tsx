import React from 'react';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import MainPage from './mainpage/MainPage';
import Registration from "./security/Registration";
import Login from "./security/Login";
import AccountSettings from "./appComponents/AccountSettings";
import UserFormList from "./appComponents/UserFormList";
import FormEditor from "./appComponents/FormEditor";
import FormResults from "./appComponents/FormResults";
import Form from "./appComponents/Form";
import Navigation from "./navbarFooter/Navigation";
import Footer from "./navbarFooter/Footer";
import NotFound from "./info/NotFound";

import 'primereact/resources/themes/lara-light-indigo/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import "primeflex/primeflex.css";
import '../style/App.css';
import AboutProject from "./info/AboutProject";


function App() {
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
                <Route path="*" component={NotFound}/>
            </Switch>
        </Router>
    </div>
  );
}

export default App;