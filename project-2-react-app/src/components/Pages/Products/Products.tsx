import { useEffect, useState } from "react";
import ProductBox from "./ProductBox/ProductBox";
import "./Products.css"
import { productModel } from "../../models/productModel";
//import AddToCart from "../Cart/AddToCart";
import { useAppSelector } from "../../../shared/hooks";
import { selectUser } from "../../Login/UserSlicer";


export default function Products(){

    const [productList, setProductList] = useState<productModel[]>([])
    const user = useAppSelector(selectUser)
    const id = user.id

    
    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
      }, []);

    
    
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
        {productList.map((product) => <ProductBox key={product.id} {...product} onButtonClick={handleOnClickEvent}></ProductBox>)}
    </div>
    function handleOnClickEvent(infoFromChild: number){
        alert("we have hit the button")
        alert(infoFromChild)

        alert("we have entered the function")
        let productId = infoFromChild
        
        
        if (id === 0){
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
            alert("we should have done the function")
        }
    }
