import { useEffect, useState } from "react";
import ProductBox from "./ProductBox/ProductBox";
import "./Products.css"
import { productModel } from "../../models/productModel";
import { useAppSelector } from "../../../shared/hooks";
import { selectUser } from "../../Login/UserSlicer";
import { selectProduct } from "../Cart/CartSlicer";
import { cartModel } from "../../models/cartModel";
import CartBox from "../Cart/CartBox/CartBox";


export default function Products(){



    const [cartList, setCartList] = useState<cartModel[]>([])


    const [productList, setProductList] = useState<productModel[]>([])
    const user = useAppSelector(selectUser)
    const id = user.id


    //on page load
    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
        // get all products
        getProducts();
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
    

    return <main className="background">
        <div className="grid">
        {productList.map((item) => <ProductBox key={item.id} {...item} onButtonClick={handleOnClickEvent} productButton={handleProduct} ></ProductBox>)}
    </div>
    </main>

    // start of add to cart
    function handleOnClickEvent(infoFromChild: number){

        //get quantity of item for user in cart
        let quantity:number = 0;

        if (id == undefined){
             alert("You need to sign in to use the cart feature.")
        }
            async function getCart(){
                await fetch(`http://127.0.0.1:8080/cart/${id}`, {
                    method: "GET",
                    credentials: 'same-origin',
                })
                .then( response => response.json())
                .then( result => {
                        setCartList(result)
                    })
                .catch( (error) => {
                console.error(error)
                } )
                
                for (let i=0; i<cartList.length; i++){
                    if (cartList[i].product.id == infoFromChild){
                        quantity = cartList[i].quantity
                    }
                }
            }
            getCart()

            let amount = quantity + 1
        
        
        let updateCart = {
            userId: id,
            productId: infoFromChild,
            quantity: amount
        }
        
        if (id === 0){
            return alert("You must be signed in to add items to the cart.")
        }   
        
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

        //implementing page link to pages

        function handleProduct(infoFromChild:number){
            alert("IndividualProduct(infoFromChild)")
        }

    }
