import { Button, ButtonGroup, ButtonToolbar, InputGroup } from "react-bootstrap";
import { productModel } from "../../../models/productModel";

//@ts-ignore
import ReactStars from "react-rating-stars-component";
import { useNavigate } from "react-router-dom";
import { useAppSelector } from "../../../../shared/hooks";
import { selectUser } from "../../../Login/UserSlicer";

export interface IOrderProp{
    product:productModel,
    quantity:number,
    purchaseDate:Date
}

export function ProductOrderBox(props: IOrderProp){

    const currentUser = useAppSelector(selectUser);

    let navigate = useNavigate();
    
    const currency_format = {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 2,
    }


    async function rate(rating:number){

        await fetch(`http://127.0.0.1:8080/ratings`, {
                method: "POST",
                credentials: "same-origin",
                body: JSON.stringify({
                    "rating":rating,
                    "customerId":currentUser.id,
                    "productId":props.product.id,
                }),
                headers: {'Content-Type': 'application/json'}
            })
            .then( response => response.json())
            .then( result => {
                console.log(result);
            } )
            .catch( (error) => {
                console.log(error)
            } )
    }

    console.log("before: ", props.purchaseDate)
    //let orderDate:string = (props.purchaseDate ? props.purchaseDate.toDateString() : "No info available")
    let orderDate:string = new Date(props.purchaseDate).toDateString()
    console.log("order date: ",orderDate)

    return <div className="box">
            <div className="product-img">
                <img src={`/${props.product.imagePath}`} onClick={()=>navigate(`/products/id=${props.product.id}`)} />
            </div>
            <div className="fit-content">
                <h4 className="description text-centered">{formatLength(props.product.name, 50)}</h4>
                <div className="product-info">
                    <h6 >Price: { Intl.NumberFormat("en-US", currency_format).format( props.product.price ) }</h6>
                    <h6>Brand: {props.product.brand}</h6>
                    <p>
                        Quantity: {props.quantity}
                        <br/>
                        Date of Purchase: {orderDate}
                    </p>
                    <ReactStars value={props.product.avgRating} onChange={(newRating:number)=>{rate(newRating)}} />

                </div>
            </div>
        </div>
}


function formatLength(description:string, length:number){
    if ( description.length < length) {
        return description;
    } else {
        return description.substring(0, length-3) + "..."
    }
}