import React from "react";
import {Link} from "react-router-dom";

export default function NavbarJs(){
    return(
        <>
            <div className="navbar d-flex mt-3 ">
                <div className="">
                    <h2><Link to='/' style={{color: 'black'}} className='nav-link'>Autorization</Link></h2>
                </div>
                <div className="float-end codeIO">
                    <button type="button" className="btn btn-outline-primary">Nimadir</button>
                </div>
            </div>
        </>
    )
}
