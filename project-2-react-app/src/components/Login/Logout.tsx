import { useEffect } from "react";
import { Container } from "react-bootstrap";
import { useAppDispatch } from "../../shared/hooks";
import { IUserModel } from "../models/userModel";
import { setUser } from "./UserSlicer";

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
    
    return <Container>
        You have been successfully logged out.
    </Container>
}