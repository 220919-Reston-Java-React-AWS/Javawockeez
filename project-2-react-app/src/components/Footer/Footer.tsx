import { Link } from 'react-router-dom';

// React-Bootstrap Components
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import ListGroup from 'react-bootstrap/ListGroup';
 
import "./Footer.css"   // some custom css

function Footer() {
    return (
        <footer>
            <Container fluid className="pt-4 bg-light">
                <Row className="justify-content-between">
                    {/* ===== Account Menu ===== */}
                    <Col xs={6} md={3} className="text-center p-3 pb-5">
                        <Row><h5>Account</h5></Row>
                        <ListGroup variant="flush">
                            <ListGroup.Item className="bg-light"><Link to={'#login'} className="LinkStyle">Login</Link></ListGroup.Item>
                            <ListGroup.Item className="bg-light"><Link to={'#sign-up'} className="LinkStyle">Sign Up</Link></ListGroup.Item>
                            <ListGroup.Item className="bg-light"><Link to={'#profile'} className="LinkStyle">Profile</Link></ListGroup.Item>
                        </ListGroup>
                    </Col>

                    {/* ===== Shopping Menu ===== */}
                    <Col xs={6} md={3} className="text-center p-3 pb-5">
                        <Row><h5>Shop</h5></Row>
                        <ListGroup variant="flush">
                            <ListGroup.Item className="bg-light"><Link to={'/'} className="LinkStyle">Home</Link></ListGroup.Item>
                            <ListGroup.Item className="bg-light"><Link to={'#product'} className="LinkStyle">Products</Link></ListGroup.Item>
                            <ListGroup.Item className="bg-light"><Link to={'#cart'} className="LinkStyle">Shopping Cart</Link></ListGroup.Item>
                        </ListGroup>
                    </Col>

                    {/* ===== Company Pages ===== */}
                    <Col xs={6} md={3} className="text-center p-3 pb-5">
                        <Row><h5>Company</h5></Row>
                        <ListGroup variant="flush">
                            <ListGroup.Item className="bg-light"><Link to={'/about'} className="LinkStyle">About Us</Link></ListGroup.Item>
                        </ListGroup>
                    </Col>

                    {/* ===== Shopping Disclaimers ===== */}
                    <Col xs={6} md={3} className="text-center p-3 pb-5">
                        <ListGroup variant="flush">
                            <ListGroup.Item className="bg-light">
                                <h6 className="text-uppercase">Free shipping</h6>
                                <p className="text-muted fw-light text-sm">Free Shipping over $50</p>
                            </ListGroup.Item>
                            <ListGroup.Item className="bg-light">
                                <h6 className="text-uppercase">Money back guarantee</h6>
                                <p className="text-muted fw-light text-sm mb-0">7 Days Money Back Guarantee</p>
                            </ListGroup.Item>
                        </ListGroup>
                    </Col>
                </Row>
            </Container>
            <Container fluid className="text-white bg-dark text-white w-100">
                <div className="d-flex flex-column flex-sm-row justify-content-center py-3">
                    <div>© 2022 Javawockeez. All rights reserved.</div>    
                </div>   
            </Container>
        </footer>
    );
}

export default Footer;