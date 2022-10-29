import { useState } from "react";
import { Card, Container } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { productModel } from "../models/productModel";
import ProductBox from "../Pages/Products/ProductBox/ProductBox";

export function ProductSearch(){

    //const [keyword, setKeyword] = useState( useParams() );
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
            return <p>
                There are no products with that description.
            </p>
        } else {

            let tmp:any[] = [];

            productList.forEach(function(product:productModel){
                //tmp.push(<li key={product.id}> {product.brand} </li>)
                tmp.push( <Card className="product-box">
                    {ProductBox(product)}
                    {/* <img src = {`.../public/${product.imagePath}`} />
                    <ul>
                        <li key={product.name}>Name: {product.name}</li>
                        <li key={product.brand}>Brand: {product.brand}</li>
                        <li></li>
                    </ul> */}
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

    return <div>
        {displayProducts()}
    </div>
}