import { IProduceWithPatches } from "immer/dist/internal";
import { useState } from "react";
import { Card, Container } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { isPropertyAccessChain } from "typescript";
import { useAppSelector } from "../../shared/hooks";
import { selectUser } from "../Login/UserSlicer";
import { cartModel } from "../models/cartModel";
import { productModel } from "../models/productModel";
import CartBox from "../Pages/Cart/CartBox/CartBox";
import ProductBox from "../Pages/Products/ProductBox/ProductBox";
import "./ProductSearch.css"

export function ProductSearch(){

    const [cartList, setCartList] = useState<cartModel[]>([])

    const [count, setCount] = useState(1)

    const user = useAppSelector(selectUser)
    const id = user.id

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
                //nested product box in card did not seem to effect layout -cm
                tmp.push( <Card className="product-box mb-3"><ProductBox  key={product.id} {...product} onButtonClick={handleOnClickEvent}></ProductBox></Card> )
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



        //add to cart implementation
     function handleOnClickEvent(infoFromChild: number){

         let quantity:number = 0
 
         let productId = infoFromChild
         
         
         if (id === 0){
             return alert("You must be signed in to add items to the cart.")
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
