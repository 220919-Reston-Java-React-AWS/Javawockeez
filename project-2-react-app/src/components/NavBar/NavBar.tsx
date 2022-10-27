import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

// for Reactful page links
import { Link, NavLink } from 'react-router-dom';

//import Button from 'react-bootstrap/Button';
//import Form from 'react-bootstrap/Form';
import Offcanvas from 'react-bootstrap/Offcanvas';


import logo from "./icon.svg";
import { NavItem } from 'react-bootstrap';
import { selectUser } from '../Login/UserSlicer';
import { IUserModel } from '../models/userModel';
import { useAppSelector } from '../../shared/hooks';
//import {ReactComponent as logo} from "./icon.svg";
//import "./icon.svg"

function NavigationBar() {

    const user:IUserModel = useAppSelector(selectUser);
    
    
    function getUser(){
        return user.firstName;
    }

    return <header>
        <Navbar key='md' bg="light" expand='md' className="mb-0">
          <Container fluid className="pt-2 pb-2">
            {/* Website Logo */}
            {/* "Link" in brand component since just redirect is needed */}
            <Navbar.Brand as={Link} to='/'>
                <img src={logo} width={128} height={48} alt="..." />
            </Navbar.Brand>

            <Navbar.Toggle aria-controls={`offcanvasNavbar-expand-$'md'`} />
            <Navbar.Offcanvas id={`offcanvasNavbar-expand-$'md'`} aria-labelledby={`offcanvasNavbarLabel-expand-$'md'`} placement="end">
                {/* Nav bar Menu */}
                <Offcanvas.Header closeButton>
                    <Offcanvas.Title id={`offcanvasNavbarLabel-expand-$'md'`}>Menu</Offcanvas.Title>
                </Offcanvas.Header>
                <Offcanvas.Body>
                    <Nav className="justify-content-end flex-grow-1 pe-3">
                        {/* "NavLink" here since "active" class styling is needed */}

                        <NavItem>{getUser()}</NavItem>

                        {/* Home Page */}
                        <Nav.Link as={NavLink} to='/'>Home</Nav.Link>
                  
                        {/* Shop Pages */}
                        <NavDropdown title="Shopping" id={`offcanvasNavbarDropdown-expand-$'md'`}>
                            <NavDropdown.Item as={Link} to='/products'>Products</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to='/cart'>Shopping Cart</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item as={Link} to='#'>Checkout</NavDropdown.Item>
                        </NavDropdown>       

                        {/* Account Pages */}
                        <NavDropdown title="Account" id={`offcanvasNavbarDropdown-expand-$'md'`}>
                            <NavDropdown.Item as={Link} to='/login'>Login</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to='/sign-up'>Signup</NavDropdown.Item>
                            <NavDropdown.Item as={Link} to='#'>Profile</NavDropdown.Item>
                    
                            <NavDropdown.Divider />
                            <NavDropdown.Item as={Link} to='#'>Logout</NavDropdown.Item>
                        </NavDropdown>     

                        {/* About Page */}
                        <Nav.Link as={NavLink} to='/about'>About</Nav.Link>
                    </Nav>
                
                    {/* <Form className="d-flex">
                    <Form.Control
                        type="search"
                        placeholder="Search"
                        className="me-2"
                        aria-label="Search"
                    />
                    <Button variant="outline-success">Search</Button>
                    </Form> */}
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
