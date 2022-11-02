// React-Bootstrap Components
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Image from 'react-bootstrap/Image'

//to scroll to the top of page when loaded
import React, {useEffect, useState} from 'react';
import { Link, useNavigate } from 'react-router-dom';

//setting up http cookies
import {useCookies} from 'react-cookie';

//react redux
import { useAppDispatch } from '../../../shared/hooks';
import { setUser } from '../../Login/UserSlicer';
import {IUserModel} from '../../models/userModel';

function Cancel(){
    const navigate = useNavigate();

    //the cookies
    const [cookies, setCookie, removeCookie] = useCookies(['userId','userFirstName', 'userLastName', 'userEmail', 'userRole']);
    //for react redux
    const dispatch = useAppDispatch();

    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
        setUserRedux();

        //timeout to redirect to main page
        setTimeout(() =>{
            navigate('/');
        }, 15000)
    }, []);

    function setUserRedux(){
        //fix the @ in the email cookie
        let email = cookies.userEmail;
        let fixEmail = email.replace("%40", "@");

        //create a userObj of the IUserModel
        const userObj = {} as IUserModel;
        userObj.id = cookies.userId;
        userObj.firstName = cookies.userFirstName;
        userObj.lastName = cookies.userLastName;
        userObj.email = fixEmail;
        userObj.role = cookies.userRole;
        
        dispatch(setUser(userObj));
    }

    return (
        <main className="min-vh-100">
            <Container className='text-center justify-content-center p-5'>
                <Row>
                    <Col>
                        <Image src="StripeWordmark.jpg" />
                    </Col>
                </Row>
                
                <Row >
                    <Col>
                        <h1 className='display-4'>Checkout Payment was Canceled</h1>
                    </Col>
                </Row>
                
                <Row >
                    <Col>
                        <p>Redirecting to the home page in 10 seconds. If not, please click <Link to={'/'}>here</Link></p>
                    </Col>
                </Row>
            </Container>
        </main>

    );
}

export default Cancel;