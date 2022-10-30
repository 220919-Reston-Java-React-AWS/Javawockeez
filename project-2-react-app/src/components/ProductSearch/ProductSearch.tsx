import { useState } from "react";
import { Card, Container } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { productModel } from "../models/productModel";
import CartBox from "../Pages/Cart/CartBox/CartBox";
import ProductBox from "../Pages/Products/ProductBox/ProductBox";
import "./ProductSearch.css"

export function ProductSearch(){

    const {keyword} = useParams();

    const [thisURL, setThisURL] = useState("") //for checking redirects

    const [productList, setProductList] = useState([])


    async function getProducts(){

        await fetch(`http://127.0.0.1:8080/products/search=${keyword}`, {
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
                            There are no products with that description.
                        </h1>
                    </Card>
                </Container>
            )
        } else {

            let tmp:any[] = [];

            productList.forEach(function(product:productModel){
                //tmp.push(<li key={product.id}> {product.brand} </li>)
                tmp.push( <Card key={product.id} className="product-box mb-3">
                    {ProductBox(product)}
                </Card> )
            })

            return <Container fluid className="d-flex text-center justify-content-center vertical-center-col pb-5">
                    {tmp}
                </Container>
        }
    }



    if (thisURL != window.location.href){
        getProducts();
        setThisURL(window.location.href);
    }

    return <main className="min-vh-100 background-search">
        {displayProducts()}
    </main>
}