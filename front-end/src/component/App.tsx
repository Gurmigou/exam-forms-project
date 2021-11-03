import React from 'react';
import '../style/App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import MainPage from './MainPage';
import Registration from "./Registration";
import Login from "./Login";
import AccountSettings from "./AccountSettings";
import UserFormList from "./UserFormList";
import FormEditor from "./FormEditor";
import FormResults from "./FormResults";
import Form from "./Form";
import Navigation from "./Navigation";

function App() {
  return (
    <div className="App">
        <Router>
            {/*не совсем ясно, как только конкретным компонентам добавить навигацию */}
            {/*<Navigation/>*/}
            <Switch>
                <Route exact path="/" component={MainPage}/>
                <Route exact path="/login" component={Login}/>
                <Route exact path="/registration" component={Registration}/>
                <Route exact path="/user/account" component={AccountSettings}/>
                <Route exact path="/user/form-list" component={UserFormList}/>
                <Route exact path="/form/construct" component={FormEditor}/>
                <Route exact path="/form/:id" component={Form}/>
                <Route exact path="/form/:id/view-result" component={FormResults}/>
            </Switch>
        </Router>
    </div>
  );
}

export default App;