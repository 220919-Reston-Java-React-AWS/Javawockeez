import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

export function ProductSearch(){

    const { keyword } = useParams();

    const [productList, setProductList] = useState([])


    async function getProducts(){

        await fetch(`http://127.0.0.1:8080/products/search=${keyword}`, {
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

    function displayProducts(){
        let tmp:any[] = [];

        productList.forEach(function(product:any){
            tmp.push(<li key={product.id}> {product.brand} </li>)
        })

        return <ul>
                {tmp}
            </ul>
    }


    getProducts()

    return <div>
        {displayProducts()}
    </div>
}