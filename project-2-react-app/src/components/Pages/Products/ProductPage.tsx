import { useEffect, useState } from "react";
import { Button, Card, Container } from "react-bootstrap";
import { useParams } from "react-router-dom";

import {productModel} from "../../models/productModel"

//@ts-ignore
import ReactStars from "react-rating-stars-component";
import "./ProductPage.css"
import ProductBox from "./ProductBox/ProductBox";

export function ProductPage(){

    const {id} = useParams();
    const [count, setCount] = useState(1)

    const [product, setProduct] = useState<productModel>({
        avgRating: 0,
        brand:"",
        description:"",
        id:0,
        imagePath:"",
        price:0,
        name:"",  
        weight:0,
        stripeKey:""
    })

    const currency_format = {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 2,
    }

    useEffect( () => {getInfo(); console.log("getting info")}, [] )
    
    async function getInfo(){
        await fetch(`http://127.0.0.1:8080/products/id=${id}`, {
            method: "GET",
            credentials: 'same-origin',
        })
        .then( response => response.json())
        .then( result => {

            if (result.message){
                console.log(result.message)

            } else {
                setProduct(result)
            }
            
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }

    function displayProduct(){
        return <Card className="product-box mb-3">
                <ProductBox key={product.id} onButtonClick={handleOnClickEvent} productButton={function (infoToParent: number): void {
                throw new Error("Function not implemented.");
            } } avgRating={0} brand={""} description={""} id={0} imagePath={""} price={0} name={""} weight={0} stripeKey={""}/>
                <Card className="product-description">
                    Weight: {product.weight} oz
                    <br/>
                    <br/>
                    {product.description}
                </Card>
            </Card>
    }
    

    //add to cart implementation
    function handleOnClickEvent(infoFromChild: number){
 
        let productId = infoFromChild
        
        
        if (product.id === 0){
            return alert("You must be signed in to add items to the cart.")
        }   
        
        let message: string
        
        async function cartAdd(){
            await fetch(`http://127.0.0.1:8080/cart/${id}/${productId}`, {
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
        }

        cartAdd() 
    }

    return <main className="background-product">
        <Container>
            {displayProduct()}
        </Container>
    </main>

}