import { productModel } from "../../../models/productModel";
import "./ProductBox.css";



//@ts-ignore
import ReactStars from "react-rating-stars-component";
import "./ProductBox.css"
import AddToCart from "../../Cart/Cart"

export interface Iprop extends productModel{
    onButtonClick(infoToParent:number):void
}


function ProductBox(props: Iprop){

    let currency_format = {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 2,
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
                <button onClick={() => {props.onButtonClick(props.id)}}>Add to Cart</button>
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
