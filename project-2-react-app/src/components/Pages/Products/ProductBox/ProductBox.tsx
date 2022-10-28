import { productModel } from "../../../models/productModel";
import "./ProductBox.css"
import AddToCart from "../../Cart/Cart"

function ProductBox(props: productModel){
    return <div className="box">
        <img src={props.image_path}/>
        <h4>{props.productName}</h4>
        <p>${props.price}</p>
        <p>{props.brand}</p>
        <button>Add to Cart</button>
    </div>
}
export default ProductBox;