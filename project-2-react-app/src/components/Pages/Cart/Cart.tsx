import { useState } from "react";
import { useParams } from "react-router-dom";
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
                {cartList.map((product) => <CartBox key={product.id} {...product}></CartBox>)}
            </ListGroup>
            <p className="total">Total = $ </p>
            <div className="checkout"><Button variant="success"> Checkout </Button>{' '}</div>
            </div>
    }

    //add to cart feature in progress
/*export function AddToCart(){
    const user = useAppSelector(selectUser);
    const email = user.email
    const id = <productModel className="id"></productModel>
    if (email == ""){
        return <div>
            <h3 className="error">You need to sign in to access the cart feature.</h3>
        </div>
    }
    async function cartAdd(){
        await fetch(`http://127.0.0.1:8080/cart/${email}/${id}`, {
            body: JSON.stringify(id),
            method: "POST",
            credentials: "same-origin",
            headers: {'Content-Type': 'application/json'}
        })
        .then( response => response.json())
            .then( result => {
                console.log(result)
            } )
            .catch( (error) => {
            console.error(error)
            } )
        }
    }*/