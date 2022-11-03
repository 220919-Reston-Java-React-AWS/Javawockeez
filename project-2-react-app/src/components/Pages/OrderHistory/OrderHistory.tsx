import { useEffect, useState } from "react";
import { Card, Container } from "react-bootstrap";
import { useAppSelector } from "../../../shared/hooks";
import { selectUser } from "../../Login/UserSlicer";
import { productModel } from "../../models/productModel";
import { IOrderProp, ProductOrderBox } from "../Products/ProductBox/ProductOrderBox";

import "./OrderHistory.css"


export function OrderHistory(){
    const user = useAppSelector(selectUser)
    const [productList, setProductList] = useState([])


    let count = 0;
    function getCount(){
        count++;
        return count;
    }


    async function getProducts(){

        await fetch(`http://127.0.0.1:8080/history/${user.id}`, {
            method: "GET",
            credentials: 'same-origin',
        })
        .then( response => response.json())
        .then( result => {

            if (result.message){
                setProductList([])

            } else {
                setProductList(result)
            }
            
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }

    function displayProducts(){
        if ( productList.length == 0 ){
            return(
                <Container fluid className="d-flex text-center justify-content-center vertical-center-col pb-5">
                    <Card className="p-5">
                        <h1>
                            You have no order's to display.
                        </h1>
                    </Card>
                </Container>
            )
        } else {

            let tmp:any[] = [];

            productList.forEach(function(cartItem:IOrderProp){
                //tmp.push(<li key={product.id}> {product.brand} </li>)
                //nested product box in card did not seem to effect layout -cm
                tmp.push( <Card className="product-box mb-3"><ProductOrderBox key={getCount()} {...cartItem} ></ProductOrderBox></Card> )
            })

            return <Container fluid className="d-flex text-center justify-content-center vertical-center-col pb-5">
                    {tmp}
                </Container>
        }
    }


    useEffect( ()=>{getProducts()}, [] )
    

    return <main className="background-order-history min-vh-100">
        {displayProducts()}
    </main>
}