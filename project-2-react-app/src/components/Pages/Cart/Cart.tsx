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
    let carTotal = 0;
    const id = user.id;
    if (id == undefined){
        return <div>
        <h3 className="error">You need to sign in to access the cart feature.</h3>
        </div>
    }
        async function getCart(){
            await fetch(`http://127.0.0.1:8080/cart/${id}`, {
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
                {cartList.map((product) => <CartBox key={product.id} {...product} onButton={handleOnButton}></CartBox>)}
            </ListGroup>
            <p className="total">Total = $ </p>
            <div className="checkout"><Button variant="success"> Checkout </Button>{' '}</div>
            </div>

        function handleOnButton(infoFromChild:number){

            let productId = infoFromChild

            let message: string
        
        async function cartRemove(){
            alert("waiting for fetch to be made")
            /*await fetch(`http://127.0.0.1:8080/cart/${id}/${productId}`, {
                method: "POST",
                credentials: "same-origin",
                headers: {'Content-Type': 'application/json'}
            })
            .then( response => response.json())
                .then( result => {
                    console.log(result);
                    message = result.message;
                    alert(message);
                } )
                .catch( (error) => {
                    console.error(error)
                    message = error.message;
                    alert(message)
                    } )
                }*/  
        }
        cartRemove()
        }
    }

    