import { productModel } from "../../../models/productModel";
import "./CartBox.css"
import Badge from 'react-bootstrap/Badge';
import ListGroup from 'react-bootstrap/ListGroup';


function CartBox(props: productModel){
    return <ListGroup.Item
    as="li"
    className="d-flex justify-content-between align-items-start"
  >
        <div className="ms-2 me-auto">
            <div className="fw-bold">{props.productName}</div>
            Quantity: 1
            </div>
            <Badge bg="primary" pill>${props.price}</Badge>
        </ListGroup.Item>
}
export default CartBox;