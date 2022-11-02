import { useEffect } from "react";
import { Container } from "react-bootstrap";
import { useAppDispatch } from "../../shared/hooks";
import { IUserModel } from "../models/userModel";
import { setUser } from "./UserSlicer";

//setting up http cookies
import {useCookies} from 'react-cookie';

export function Logout(){
    
    const emptyUser:IUserModel = {
        id:0,
        firstName:"",
        lastName:"",
        email:"",
        role:0,
    } 
    
    const dispatch = useAppDispatch();

    dispatch(setUser(emptyUser)) // **************** //

    //clear the cookies
    const [cookies, setCookie, removeCookie] = useCookies(['userId','userFirstName', 'userLastName', 'userEmail', 'userRole']);
    removeCookie('userId');
    removeCookie('userFirstName');
    removeCookie('userLastName');
    removeCookie('userEmail');
    removeCookie('userRole');
    
    return (
    <main className="min-vh-100">
        <Container className="d-flex text-center justify-content-center">
            <h1>You have been successfully logged out.</h1>
        </Container>
    </main>
    );
}