import { productModel } from "../../../models/productModel";
import "./CartBox.css"
import Badge from 'react-bootstrap/Badge';
import ListGroup from 'react-bootstrap/ListGroup';
import { cartModel } from "../../../models/cartModel";
import { Button, ButtonGroup, ButtonToolbar, Dropdown, DropdownButton } from "react-bootstrap";
import { serialize } from "v8";

interface buttons extends cartModel{
    onButton(infoToParent:cartModel):void,
    updateButton(infoToParent:cartModel, count:number):void
}

function CartBox(props: buttons){
     return <ListGroup.Item
    as="li"
    className="d-flex justify-content-between align-items-start"
    >    
        <div className="ms-2 me-auto">
             <span> Quantity </span>

            <div className="fw-bold">{props.product.name}</div>              
            <DropdownButton id="dropdown-basic-button" title={props.quantity}>
                <Dropdown.Item onClick={() => {props.updateButton(props, 1)}}>1</Dropdown.Item>
                <Dropdown.Item onClick={() => {props.updateButton(props, 2)}}>2</Dropdown.Item>
                <Dropdown.Item onClick={() => {props.updateButton(props, 3)}}>3</Dropdown.Item>
                <Dropdown.Item onClick={() => {props.updateButton(props, 4)}}>4</Dropdown.Item>
                <Dropdown.Item onClick={() => {props.updateButton(props, 5)}}>5</Dropdown.Item>
            </DropdownButton>
            </div>
            <Badge bg="primary" pill>${props.product.price}</Badge>
            <Button variant="warning" onClick={() => {props.onButton(props)}}> Remove from Cart </Button>
        </ListGroup.Item>
}
export default CartBox;