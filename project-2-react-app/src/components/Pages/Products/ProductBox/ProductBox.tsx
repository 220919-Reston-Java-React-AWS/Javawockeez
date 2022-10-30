import { productModel } from "../../../models/productModel";
//@ts-ignore
import ReactStars from "react-rating-stars-component";
import "./ProductBox.css"

import { Button } from "react-bootstrap";
import { IUserModel } from "../../../models/userModel";
import { useAppSelector } from "../../../../shared/hooks";
import { selectUser } from "../../../Login/UserSlicer";
import React from "react";
import { AddToCart } from "../../Cart/Cart";

function ProductBox(props: productModel){

    /*let user:IUserModel = useAppSelector(selectUser);*/

    let currency_format = {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 2,
    }

    async function addToCart(event: React.MouseEvent<HTMLButtonElement, MouseEvent>){
        let response = AddToCart(event.currentTarget.ariaLabel ? event.currentTarget.ariaLabel : '0')
        response.then((result)=>{alert(result)});
    }

    return <div className="box">
        <div className="product-img">
            <img src={`/${props.imagePath}`}/>
        </div>
        <div className="fit-content">
            <h4 className="description text-centered">{formatLength(props.name, 50)}</h4>
            <div className="product-info">
                <h6 >Price: { Intl.NumberFormat("en-US", currency_format).format( props.price ) }</h6>
                <h6>Brand: {props.brand}</h6>
                {props.avgRating>0 ? <ReactStars value={props.avgRating} edit={false}  /> : "This product doesn't have any ratings yet" }
                {/* <div>
                    <p className="description">Description: {formatDescription(props.description)}</p>
                </div> */}
            </div>
            <div className="cart-button">
                <Button aria-label={props.id.toString()} onClick={addToCart}>Add to Cart</Button>
                {/* <button>Add to Cart</button> */}
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


export default ProductBox;