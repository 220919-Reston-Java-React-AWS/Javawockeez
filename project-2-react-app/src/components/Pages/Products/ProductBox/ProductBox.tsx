import { productModel } from "../../../models/productModel";
import "./ProductBox.css";

//bootstrap
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import ButtonToolbar from 'react-bootstrap/ButtonToolbar';
import InputGroup from 'react-bootstrap/InputGroup';


//@ts-ignore
import ReactStars from "react-rating-stars-component";
import "./ProductBox.css"
import { cartModel } from "../../../models/cartModel";
import { useAppDispatch } from "../../../../shared/hooks";
import { setProduct } from "../../Cart/CartSlicer";

interface Iprop extends productModel{
    onButtonClick(infoToParent:number):void,
    productButton(infoToParent:number):void
}


function ProductBox(props: Iprop){

    const dispatch = useAppDispatch();

    let currency_format = {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: 2,
    }

    return <div className="box">
        <div className="product-img">
            <img src={`/${props.imagePath}`} onClick={() => {props.productButton(props.id)}}/>
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
                <Button variant="success"  onClick={() => {props.onButtonClick(props.id)}}>Add to Cart</Button>
            </div>
            </div>
            </div>
            dispatch(setProduct(props))
}

function formatLength(description:string, length:number){
    if ( description.length < length) {
        return description;
    } else {
        return description.substring(0, length-3) + "..."
    }
}


export default ProductBox;
