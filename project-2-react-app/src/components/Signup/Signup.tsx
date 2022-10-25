// react-bootstrap
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import Form from 'react-bootstrap/Form';

//to scroll to the top of page when loaded
import {useEffect} from 'react';

// custom css
import '../Login/custom.css'

function Signup() {
    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
      }, []);

    return(
        <main className="min-vh-100 background">
            <Container fluid className="d-flex text-center justify-content-center vertical-center pb-5">
                <Card className='card-width p-5'>
                    <Form>
                        <h1 className="h3 mb-5 fw-normal">Create Account</h1>
                        
                        {/* First Name */}
                        <FloatingLabel controlId="floatingInput" label="First Name" className="mb-4">
                            <Form.Control type="text" placeholder="First Name" />
                        </FloatingLabel>

                        {/* Last Name */}
                        <FloatingLabel controlId="floatingInput" label="Last Name" className="mb-4">
                            <Form.Control type="text" placeholder="Last Name" />
                        </FloatingLabel>

                        {/* Email address */}
                        <FloatingLabel controlId="floatingInput" label="Email address" className="mb-4">
                            <Form.Control type="email" placeholder="name@example.com" />
                        </FloatingLabel>
                        
                        {/* Password */}
                        <FloatingLabel controlId="floatingPassword" label="Password" className="mb-5">
                            <Form.Control type="text" placeholder="Password" />
                        </FloatingLabel>

                        <Button type="submit" className='w-100 btn-lg btn-success'>Sign up</Button>
                    </Form>
                </Card>
            </Container>

        </main>
    );
}

export default Signup;