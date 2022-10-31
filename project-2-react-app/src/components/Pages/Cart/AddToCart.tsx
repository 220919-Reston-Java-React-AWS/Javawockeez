import { useAppSelector } from "../../../shared/hooks";
import { selectUser, setDefault } from "../../Login/UserSlicer";
import { selectProduct } from "./CartSlicer";


//add to cart feature in progress
export default function AddToCart(productId:number){
    const user = useAppSelector(selectUser)
    const id = user.id
    alert("we have entered the function")


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
    }