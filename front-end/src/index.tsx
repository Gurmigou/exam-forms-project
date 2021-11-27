import React from 'react';
import ReactDOM from 'react-dom';
import './style/index.css';
import App from './component/App';
import {Provider} from "react-redux";
import { store } from './utils/redux/reduxUtils';

ReactDOM.render(
    <Provider store={store}>
        <React.StrictMode>
            <App/>
        </React.StrictMode>
    </Provider>,
  document.getElementById('root')
);