import React, {useState} from "react";
import {Col, Container, Modal, ModalBody, ModalHeader, Row} from "reactstrap";
import {AvField, AvForm} from 'availity-reactstrap-validation';
import {useHistory} from "react-router-dom";
import axios from "axios";
import NavbarJs from "../NavbarJs";

export default function RegisterPage() {

    const history = useHistory();

    const [showModal, setShowModal] = useState(false);
    const openModal = () => setShowModal(true);
    const closeModal = () => setShowModal(false);


    let headers = {
        "Access-Control-Allow-Origin": "*",
        "Content-type": "application/json"
    }

    function registerUser(e, v) {

        let app = v.name;
        const sentence = app.replace(/\s+/g, ' ').trim()
        let arr = sentence.split(' '); // split string on comma space
        // console.log( arr );

        const user = {
            email: v.email,
            phoneNumber: v.phoneNumber,
            userName: v.userName,
            firstName: arr[0],
            lastName: arr[1],
            surName: arr[2]
        }

        axios({
            url: "http://localhost:8070/api/auth/register",
            method: 'POST',
            data: user,
            headers: headers
        }).then(
            function (res) {
                localStorage.setItem("User Token", "Bearer " + res.data.object);
                alert(res.data.message)
            }).catch(
            function (err) {
                console.log(err)
            })
    }

    function login(e, v) {
        axios({
            url: "http://localhost:8070/api/auth/login",
            method: 'POST',
            data: v,
            headers: headers
        }).then(res => {
            // localStorage.setItem("User Token", "Bearer " + res.data);
            console.log(res);
        }).catch(err => {
            console.log(err);
        })
    }

    return (
        <div className='homePage'>
            <Container>
                <Row>
                    <Col md={12} sm={12}>
                        <NavbarJs/>
                    </Col>
                </Row>
                <Row>
                    <Col md={12} className='d-flex justify-content-center autorizationPage'>
                        <Row className='align-items-center signUp ' style={{width: '37em'}}>
                            <Col md={6}>
                                <div style={{width: '18em'}} className="text-center ">
                                    <h2>Welcome to our blog! </h2>
                                    <h5>If you are new to our blog, register now or use the easy login feature</h5>
                                    <button type='submit' id='btn ' className='btn btn-outline-info mt-3'
                                            onClick={openModal}>Sign
                                        In
                                    </button>
                                    <div className="mt-3">
                                        <h5>Or</h5>
                                    </div>
                                    <div className="social d-flex justify-content-center align-content-center">
                                        <div>
                                            <div
                                                className="icon facebook align-items-center d-flex justify-content-center">
                                                <a href="https://www.facebook.com/azamat.azadov.9/">
                                                    <i className="fa fa-facebook"/>
                                                </a>
                                            </div>
                                            <div style={{marginLeft: '3vh'}} className='mt-2'>
                                                <p>Facebook</p>
                                            </div>
                                        </div>
                                        <div className="">
                                            <div
                                                className="icon intagram align-items-center d-flex justify-content-center">
                                                <a href="https://www.instagram.com/azadov_azamat_life/">
                                                    <i className="fa fa-google "/>
                                                </a>
                                            </div>
                                            <div style={{marginLeft: '4.5vh'}} className='mt-2'>
                                                <p>Google</p>
                                            </div>
                                        </div>
                                        <div className="">
                                            <div
                                                className="icon telegram align-items-center d-flex justify-content-center">
                                                <a href="https://t.me/azadov_azamat">
                                                    <i className="fa fa-github"/>
                                                </a>
                                            </div>
                                            <div style={{marginLeft: '2vh'}} className='mt-2'>
                                                <p>Github</p>
                                            </div>
                                        </div>


                                    </div>

                                </div>
                            </Col>

                            <Col md={6}>
                                <div style={{width: '18em'}}
                                     className='text-center mb-3 mt-2 d-flex justify-content-center align-content-center'>
                                    <AvForm onValidSubmit={registerUser}>
                                        <AvField
                                            label='F. I. Sh.'
                                            name='name'
                                            placeholder='Ex: Ivan Ivanov Ivanovich'
                                        />
                                        <AvField
                                            label='nickname :)'
                                            name='userName'
                                            placeholder='Ex: Ivan1122'
                                        />
                                        <AvField
                                            label='Your phone number'
                                            name='phoneNumber'
                                            placeholder='Ex: +998 99 680 22 08'
                                            maxLength={14}
                                            required
                                        />
                                        <AvField
                                            label='Your email'
                                            name='email'
                                            placeholder='Ex: ivan@gmail.com'
                                        />
                                        <button type='submit' id='btn '
                                                className='btn btn-outline-success mt-3'>Registration
                                        </button>
                                    </AvForm>
                                </div>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </Container>
            <Modal isOpen={showModal} modalTransition={{timeout: 700}} backdropTransition={{timeout: 1300}}>
                <ModalHeader style={{margin: '0 3rem'}}>
                    <h4 style={{margin: '0 2rem'}}>Welcome to your Account</h4>
                </ModalHeader>
                <ModalBody>
                    <Container>
                        <div className='d-flex justify-content-between autorizationPage'>
                            <Col md={6} className='align-items-center '>
                                <div className='text-center mb-5  d-flex justify-content-center align-content-center'>
                                    <AvForm onValidSubmit={login}>
                                        <AvField
                                            label='Your phone number'
                                            name='phoneNumber'
                                            placeholder='Ex: +998 99 680 22 08'
                                            minlength="9"
                                            maxlength="17"
                                            required
                                        />
                                        <AvField
                                            type={'password'}
                                            label='Your password'
                                            name='password'
                                            placeholder='Ex: root123'

                                        />
                                        <button type='submit' id='btn ' className='btn btn-outline-info mt-3'>Sign
                                            In
                                        </button>
                                    </AvForm>
                                </div>
                            </Col>

                            <Col md={6}>
                                <div className="text-center signIn">
                                    <div className="">
                                        <h4>Or</h4>
                                    </div>
                                    <p>If you do not want to enter logins and passwords, use the easy login function</p>
                                    <div className="social d-flex justify-content-center align-content-center">
                                        <div
                                            className="icon facebook align-items-center d-flex justify-content-center">
                                            <a href="https://www.facebook.com/azamat.azadov.9/">
                                                <i className="fa fa-facebook"/>
                                            </a>
                                        </div>
                                        <div
                                            className="icon intagram align-items-center d-flex justify-content-center">
                                            <a href="https://www.instagram.com/azadov_azamat_life/">
                                                <i className="fa fa-google "/>
                                            </a>
                                        </div>
                                        <div
                                            className="icon telegram align-items-center d-flex justify-content-center">
                                            <a href="https://t.me/azadov_azamat">
                                                <i className="fa fa-github"/>
                                            </a>
                                        </div>

                                    </div>
                                    <div className="d-flex justify-content-between mt-2">
                                            <div className="">
                                                <p>Facebook</p>
                                            </div>
                                            <div className="" style={{marginRight: '3vh'}}>
                                                <p>Google</p>
                                            </div>
                                            <div className=""  style={{marginRight: '0.9vh'}}>
                                                <p>Github</p>
                                            </div>
                                    </div>
                                    <div className="">
                                        <button type='submit' id='btn ' className='btn btn-outline-danger mt-3'
                                                onClick={closeModal}>Out put
                                        </button>
                                    </div>
                                </div>
                            </Col>
                        </div>
                    </Container>
                </ModalBody>
            </Modal>
        </div>

    )
}
