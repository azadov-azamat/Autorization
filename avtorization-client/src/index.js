import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {ToastContainer} from "react-toastify";
import {BrowserRouter} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';


const app = (
    <>
        <ToastContainer/>
        <BrowserRouter><App/></BrowserRouter>
    </>
);
ReactDOM.render(app, document.getElementById('root'));

