import { useEffect } from "react";
import { getEnvironmentData } from "worker_threads";
import ProductBox from "./ProductBox/ProductBox";
import { useState } from "react";
import { useParams } from "react-router-dom";
import "./Products.css"
import { productModel } from "../../models/productModel";

export default function Products(){
    const { keyword } = useParams();
    const [productList, setProductList] = useState<productModel[]>([])
    async function getProducts(){
        await fetch(`http://127.0.0.1:8080/products`, {
            method: "GET",
            credentials: 'same-origin',
        })
        .then( response => response.json())
        .then( result => {
            setProductList(result)
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }
    getProducts()
    return <div className="grid">
        {productList.map((product) => <ProductBox key={product.id} {...product}></ProductBox>)}
    </div>
}