import React, {useState} from "react";
import NavbarJs from "../NavbarJs";
import {Col, Container, Row} from "reactstrap";
import {Link} from "react-router-dom";

export default function Pages() {

    return (
        <div className='homePage'>
            <Container>
                <NavbarJs/>
                <ul>
                    <li className='list-group'>
                        <Link to='signIn' style={{color: 'black'}} className='nav-link'>Sign In</Link>
                    </li>

                    <li className='list-group'>
                        <Link to='signUp' style={{color: 'black'}} className='nav-link'>Sign Up</Link>
                    </li>
                </ul>
            </Container>
        </div>

    )
}
