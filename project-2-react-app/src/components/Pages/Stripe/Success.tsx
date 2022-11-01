// React-Bootstrap Components
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Image from 'react-bootstrap/Image'

//to scroll to the top of page when loaded
import React, {useEffect} from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Success(){
    const navigate = useNavigate();

    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});

        //timeout to redirect to main page
        setTimeout(() =>{
            navigate('/');
        }, 15000)
      }, []);

    return (
        <main className="min-vh-100">
            <Container className='text-center justify-content-center p-5'>
                <Row>
                    <Col>
                        <Image src="StripeWordmark.jpg" />
                    </Col>
                </Row>
                
                <Row >
                    <Col>
                        <h1 className='display-4'>Checkout Payment was Successful</h1>
                    </Col>
                </Row>
                
                <Row >
                    <Col>
                        <h3>Thank you shopping with us.</h3>
                        <p>Redirecting to the home page in 10 seconds. If not, please click <Link to={'/'}>here</Link></p>
                    </Col>
                </Row>
            </Container>
        </main>

    );
}

export default Success;