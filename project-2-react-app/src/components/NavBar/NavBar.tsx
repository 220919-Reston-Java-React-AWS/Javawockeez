import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { useNavigate } from "react-router-dom";

// for Reactful page links
import { Link, NavLink } from 'react-router-dom';

//import Button from 'react-bootstrap/Button';
//import Form from 'react-bootstrap/Form';
import Offcanvas from 'react-bootstrap/Offcanvas';


import logo from "./icon.svg";
import "./NavBar.css";

import { Button, Form, NavItem } from 'react-bootstrap';
import { selectUser } from '../Login/UserSlicer';
import { IUserModel } from '../models/userModel';
import { useAppSelector } from '../../shared/hooks';
import { useState } from 'react';
//import {ReactComponent as logo} from "./icon.svg";
//import "./icon.svg"

function NavigationBar() {

    const user:IUserModel = useAppSelector(selectUser);

    const [query, setSearchQuery] = useState("")

    let navigate = useNavigate();
    
    
    
    function getUser(){
        return <Navbar.Text className="userName">
            {user.firstName}
            </Navbar.Text>
    }


    function setQuery(event:React.ChangeEvent<HTMLInputElement>){
        setSearchQuery(event.target.value)
    }

    function search(event:React.FormEvent<HTMLFormElement>){
        event.preventDefault();
        navigate(`/products/search=${query}`)
    }


    function getAccountPages(){
        if (user.id) {
            return <NavDropdown title="Account" id={`offcanvasNavbarDropdown-expand-$'md'`}>
                {getUser()}

                <NavDropdown.Divider />
                <NavDropdown.Item as={Link} to='/profile'>Profile</NavDropdown.Item>
                <NavDropdown.Item as={Link} to='/profile/order-history'>Order History</NavDropdown.Item>
    
                <NavDropdown.Divider />
                <NavDropdown.Item as={Link} to='/logout'>Logout</NavDropdown.Item>
            </NavDropdown>  
        } else {
            return <NavDropdown title="Account" id={`offcanvasNavbarDropdown-expand-$'md'`}>
                <NavDropdown.Item as={Link} to='/login'>Login</NavDropdown.Item>
                <NavDropdown.Item as={Link} to='/sign-up'>Signup</NavDropdown.Item>
            </NavDropdown>  
        }
    }
    
    function getSearchBar(){
        return <Form className="d-flex flex-grow-1 searchBarMargin" onSubmit={search}>
                <Form.Control
                type="search"
                placeholder="Search"
                className="me-2"
                aria-label="Search"
                onChange={setQuery}
                    />
                <Button variant="outline-success" type="submit">Search</Button>
        </Form>
    }


    return <header>
        <Navbar key='md' expand='md' className="mb-0 nav-color">
          <Container fluid className="pt-2 pb-2 justify-content-center">
            {/* Website Logo */}
            {/* "Link" in brand component since just redirect is needed */}
            <Navbar.Brand as={Link} to='/'>
                <img src={logo} width={128} height={48} alt="..." />
            </Navbar.Brand>

            {/* Search Bar */}
            {getSearchBar()}

            <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-$'md'`} />
            <Navbar.Offcanvas id={`offcanvasNavbar-expand-$'md'`} aria-labelledby={`offcanvasNavbarLabel-expand-$'md'`} placement="end">
                {/* Nav bar Menu */}
                <Offcanvas.Header closeButton>
                    <Offcanvas.Title id={`offcanvasNavbarLabel-expand-$'md'`}>Menu</Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>

                    <Nav className="justify-content-end flex-grow-1 pe-3">
                        {/* "NavLink" here since "active" class styling is needed */}


                        {/* Home Page */}
                        <Nav.Link as={NavLink} to='/'>Home</Nav.Link>
                  
                        {/* Shop Pages */}
                        <NavDropdown title="Shopping" id={`offcanvasNavbarDropdown-expand-$'md'`}>
                            <NavDropdown.Item as={Link} to='/products'>Products</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to='/cart'>Shopping Cart</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item as={Link} to='/cart'>Checkout</NavDropdown.Item>
                        </NavDropdown>       

                        {/* Account Pages */}
                        {getAccountPages()}

                        {/* About Page */}
                        <Nav.Link as={NavLink} to='/about'>About</Nav.Link>
                    </Nav>
                </Offcanvas.Body>
            </Navbar.Offcanvas>
          </Container>
        </Navbar>
    </header>

}

export default NavigationBar;

function getAppSelector(selectUser: (state: { user: any; }) => any) {
    throw new Error('Function not implemented.');
}
