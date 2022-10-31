import { productModel } from "../../../models/productModel";
import "./ProductBox.css";

interface IProp extends productModel{
    onButtonClick(infoToParent:number):void
}

function ProductBox(props: IProp){
    return <div className="box">
        <img src={props.imagePath}/>
        <h4>{props.name}</h4>
        <p>${props.price}</p>
        <p>{props.brand}</p>
        <button onClick={() => {props.onButtonClick(props.id)}}> Add to Cart </button>
    </div>
}
export default ProductBox;

