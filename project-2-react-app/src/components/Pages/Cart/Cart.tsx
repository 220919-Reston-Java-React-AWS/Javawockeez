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
import { cartModel } from "../../models/cartModel";
import ProductBox from "../Products/ProductBox/ProductBox";



export default function Cart(){
    //grabbing user from the store
    useEffect(() => {
    // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});

        getCart();

        }, []); 

    const user = useAppSelector(selectUser);
    const [cartList, setCartList] = useState<cartModel[]>([])
    let carTotal:number = 0;
    const id = user.id;
    if (id == 0){
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
        for (let i=0; i<cartList.length; i++){
                carTotal += (cartList[i].quantity * cartList[i].product.price)
            }
        return <div>
            <ListGroup as="ol" numbered>
                {cartList.map((product) => <CartBox key={product.id} {...product} updateButton={handleOnClickEvent} onButton={handleRemove}></CartBox>)}
            </ListGroup>
            <p className="total"> Total = {currencyFormat(carTotal)} </p>
            <div className="checkout"><Button variant="success"> Checkout </Button>{' '}</div>
            </div>

        function currencyFormat(num:number) {
            return '$' + num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
        }
 

        // start of add to cart
    function handleOnClickEvent(infoFromChild: cartModel, count:number){

        
        let productId = infoFromChild.product.id
        let quantity = count

        let newCart = {
            userId: id,
            productId: productId,
            quantity: quantity
        }
    
        if (id == 0){
            alert("You need to sign in to use the cart feature.")
        }

        if (quantity >= 5){
            alert("add input form")
        }
   
        let message: string
        
        async function cartAdd(){
            await fetch(`http://127.0.0.1:8080/cart/`, {
                body: JSON.stringify(newCart),
                method: "POST",
                credentials: 'same-origin',

            headers:{
                'Content-Type': 'application/json',
            },
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
                }
            cartAdd() 
        }


        //remove from cart
        function handleRemove(infoFromChild:cartModel){

            let productId = infoFromChild.product.id
            let quantity = 0

            let updateCart = {
                userId: id,
                productId: productId,
                quantity: quantity
            }
        

        async function deleteItem(){
            await fetch(`http://127.0.0.1:8080/cart/`, {
                body: JSON.stringify(updateCart),
                method: "DELETE",
                credentials: 'same-origin',

            headers:{
                'Content-Type': 'application/json',
            },
        })
            .then( response => response.json())
            .then( result => {
                    alert(result.message)
                })
            .catch( (error) => {
            console.error(error)
                alert(error.message)
            } )
        }
        deleteItem()
    }
    }

    