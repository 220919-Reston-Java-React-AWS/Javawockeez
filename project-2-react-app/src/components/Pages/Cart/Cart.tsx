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
import Form from "react-bootstrap/Form";
import Modal from "react-bootstrap/esm/Modal";
import { render } from "@testing-library/react";



export default function Cart(){
    //grabbing user from the store
    useEffect(() => {
    // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});

        getCart();

        }, []); 

        let updateCart = {
            userId: 0,
            productId: 0,
            quantity: 0
        }

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const user = useAppSelector(selectUser);

    updateCart.userId = user.id

    const [cartList, setCartList] = useState<cartModel[]>([])
    const [quantity, setQuantity] = useState("")
    const [productId, setProductId] = useState("")

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

        return (<div>
            <ListGroup as="ol" numbered>
                {cartList.map((item) => <CartBox key={item.id} {...item} updateButton={handleOnClickEvent} onButton={handleRemove} flipButton={handleShow} holdButton={setValues}></CartBox>)}
            </ListGroup>
            <p className="total"> Total = {currencyFormat(carTotal)} </p>
            <div className="checkout"><Button variant="success"> Checkout </Button>{' '}</div>

                <Modal show={show} onHide={handleClose} animation={false}>
                    <Modal.Header closeButton>
                    <Modal.Title>Update Amount</Modal.Title>
                    </Modal.Header>
                    <Modal.Body><form>
                        <label> Enter Quantity for Product (Maximum is 100):
                        <input type="number" 
                        value={quantity}
                        onChange={(q) => setQuantity(q.target.value)}/>
                        </label>
                    </form></Modal.Body>
                    <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                Cancel
                </Button>
                <Button variant="primary" onClick={largeCart}>
                Save Changes
                </Button>
                    </Modal.Footer>
                </Modal>
            </div>
            );
        

        function setValues(infoFromChild:cartModel){
            let update:string = infoFromChild.product.id.toString()
            setProductId(update)
            alert(productId)
        }

        function currencyFormat(num:number) {
            return '$' + num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,')
        }


        // start of add to cart
        function handleOnClickEvent(infoFromChild:cartModel, count:number){

        let amount:number = count
        
        if(amount == NaN || amount == null || amount == undefined || amount > 100 || amount < 1){
            alert("You must enter a valid number")
        }


        if (id == 0){
            alert("You need to sign in to use the cart feature.")
        }

        let newCart = {
            userId: id,
            productId: infoFromChild.product.id,
            quantity: amount
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

        // start of large cart
        function largeCart(){

            let amount:number = parseInt(quantity, 10)
            alert(amount)

            let product:number = parseInt(productId, 10)
            alert(product)

            updateCart.productId = product
    
    
            if(amount == NaN || amount == null || amount == undefined || amount > 100 || amount < 1){
                alert("You must enter a valid number")
            }
    
            updateCart.quantity = amount
    
            if (id == 0){
                alert("You need to sign in to use the cart feature.")
            }

            alert(JSON.stringify(updateCart))
        
            let message: string
            
            async function cartAdd(){
                await fetch(`http://127.0.0.1:8080/cart/`, {
                    body: JSON.stringify(updateCart),
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
    