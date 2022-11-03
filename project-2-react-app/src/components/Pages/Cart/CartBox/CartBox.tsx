import { productModel } from "../../../models/productModel";
import "./CartBox.css"
import Badge from 'react-bootstrap/Badge';
import ListGroup from 'react-bootstrap/ListGroup';
import { cartModel } from "../../../models/cartModel";
import { Button, ButtonGroup, ButtonToolbar, Dropdown, DropdownButton } from "react-bootstrap";
import { serialize } from "v8";
import { useNavigate } from "react-router-dom";

interface buttons extends cartModel{
    onButton(infoToParent:cartModel):void,
    updateButton(infoToParent:cartModel, count:any):void,
    flipButton(infoToParent:cartModel):void,
    holdButton(infoToParent:cartModel):void
}

function CartBox(props: buttons){
    let navigate = useNavigate();
     return <div className="space"><div className="box2"><ListGroup.Item
    as="li"
    className="d-flex justify-content-between align-items-start"
    >    
        <div className="ms-2 me-auto">

            <div className="fw-bold" onClick={()=>navigate(`/products/id=${props.id}`)}>{props.product.name}</div>              
            <DropdownButton id="dropdown-basic-button" title={props.quantity} onClick={() => {props.holdButton(props)}}>
                <Dropdown.Item onClick={() => {props.updateButton(props, 1)}}>1</Dropdown.Item>
                <Dropdown.Item onClick={() => {props.updateButton(props, 2)}}>2</Dropdown.Item>
                <Dropdown.Item onClick={() => {props.updateButton(props, 3)}}>3</Dropdown.Item>
                <Dropdown.Item onClick={() => {props.updateButton(props, 4)}}>4</Dropdown.Item>
                <Dropdown.Item onClick={() => {props.flipButton(props)}}>5+</Dropdown.Item>
            </DropdownButton>
            </div>
            <div className="pill"><Badge bg="primary" pill >${props.product.price}</Badge></div>   
        </ListGroup.Item>
        <p><Button variant="warning" onClick={() => {props.onButton(props)}} className="remove"> Remove from Cart </Button></p></div></div>
}
export default CartBox;