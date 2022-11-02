import { useEffect, useState } from "react";
import { useAppSelector } from "../../../../shared/hooks";
import { selectUser } from "../../../Login/UserSlicer";
import { productModel } from "../../../models/productModel";
import { selectProduct, setProduct } from "../../Cart/CartSlicer";
import ProductBox from "../ProductBox/ProductBox";
import "./InvidviualPRoducts.css";


export default function IndividualProduct(productId:number){
    alert("we entered the function")

    const [product, setProduct] = useState<productModel>()
    
    const user = useAppSelector(selectUser)
    const id = user.id


    //on page load
    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
        // get all products
        getProduct();
    }, []);
    
    async function getProduct(){
        await fetch(`http://127.0.0.1:8080/products/${productId}`, {
            method: "GET",
            credentials: 'same-origin',
        })
        .then( response => response.json())
        .then( result => {
            setProduct(result)
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }

    return <main className="background_product">
        <div className="grid">
    </div>
    </main>
}