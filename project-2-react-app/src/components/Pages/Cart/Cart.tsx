import React, { ButtonHTMLAttributes, useEffect, useState } from "react";
import { getEnabledCategories } from "trace_events";
import CartBox from "./CartBox/CartBox";
import { productModel } from "../../models/productModel";
import { useAppDispatch, useAppSelector } from "../../../shared/hooks";
import { selectUser } from "../../Login/UserSlicer";
import "./Cart.css";
import Badge from 'react-bootstrap/Badge';
import ListGroup from 'react-bootstrap/ListGroup';
import Button from 'react-bootstrap/Button';
import { idText } from "typescript";



export default function Cart(){
    //grabbing user from the store
    useEffect(() => {
    // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
        }, []); 

    const user = useAppSelector(selectUser);
    const [cartList, setCartList] = useState<productModel[]>([])
    const userId = user.id;
    //user isn't signed in
    if (userId == 0){
        return <div>
        <h3 className="error">You need to sign in to access the cart feature.</h3>
        </div>
    }
        async function getCart(){
            await fetch(`http://127.0.0.1:8080/cart/${userId}`, {
                method: "GET",
                credentials: 'same-origin',
            })
            .then( response => response.json())
            .then( result => {
                    setCartList(result)
            } )
            .catch( (error) => {
            console.error(error)
            } )
        }
        getCart()
        return <div>
            <ListGroup as="ol" numbered>
                {cartList.map((product) => <CartBox key={product.id} {...product}></CartBox>)}
            </ListGroup>
            <p className="total">Total = $ </p>
            <div className="checkout"><Button variant="success"> Checkout </Button>{' '}</div>
            </div>
    }

    