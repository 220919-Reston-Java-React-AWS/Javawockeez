import { productModel } from "../../../models/productModel";
import "./CartBox.css"
import Badge from 'react-bootstrap/Badge';
import ListGroup from 'react-bootstrap/ListGroup';

interface deleteButton extends productModel{
    onButton(infoToParent:number):void
}


function CartBox(props: deleteButton){
     return <ListGroup.Item
    as="li"
    className="d-flex justify-content-between align-items-start"
    >    
        <div className="ms-2 me-auto">
            <div className="fw-bold">{props.name}</div>
            Quantity: 1
            </div>
            <Badge bg="primary" pill>${props.price}</Badge>
            <button onClick={() => {props.onButton(props.id)}}> Remove From Cart </button>
        </ListGroup.Item>
}
export default CartBox;