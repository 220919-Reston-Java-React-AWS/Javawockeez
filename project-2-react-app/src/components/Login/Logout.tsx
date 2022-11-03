import { useEffect } from "react";
import { Card, Container } from "react-bootstrap";
import { useAppDispatch } from "../../shared/hooks";
import { IUserModel } from "../models/userModel";
import { setUser } from "./UserSlicer";

//setting up http cookies
import {useCookies} from 'react-cookie';
import { useNavigate } from "react-router-dom";

export function Logout(){
    
    const emptyUser:IUserModel = {
        id:0,
        firstName:"",
        lastName:"",
        email:"",
        role:0,
    } 
    
    const navigate = useNavigate();
    const dispatch = useAppDispatch();

    dispatch(setUser(emptyUser)) // **************** //

    //clear the cookies
    const [cookies, setCookie, removeCookie] = useCookies(['userId','userFirstName', 'userLastName', 'userEmail', 'userRole']);
    removeCookie('userId');
    removeCookie('userFirstName');
    removeCookie('userLastName');
    removeCookie('userEmail');
    removeCookie('userRole');


    setTimeout( () => {
        navigate( '/' )
    }, 5000)
    
    return (
    <main className="min-vh-100 background-logout justify-content-center d-flex flex-column">
        <Container>
            <Card className="text-center p-5 pt-15 pb-40 background-form">
                <h1>You have been successfully logged out.</h1>
            </Card>
        </Container>
    </main>
    );
}