// react-bootstrap
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import Form from 'react-bootstrap/Form';

//to scroll to the top of page when loaded
import {useEffect} from 'react';

// custom css
import './custom.css'

function Login() {

    let newUser = {
        email: "",
        password: ""
    }

    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
      }, []);

    
    function setEmail(event: React.ChangeEvent<HTMLInputElement>){
        newUser.email = event.target.value;
    }

    function setPassword(event: React.ChangeEvent<HTMLInputElement>){
        newUser.password = event.target.value;
    }
    
    async function submit(event: React.FormEvent<HTMLFormElement>){
        event.preventDefault();

        await fetch("http://127.0.0.1:8080/login")
    }


    return(
        <main className="min-vh-100 background">
            <Container fluid className="d-flex text-center justify-content-center vertical-center pb-5">
                <Card className='card-width p-5' >
                    <Form >
                        <h1 className="h3 mb-5 fw-normal">User Login</h1>
                        
                        {/* Email address */}
                        <FloatingLabel controlId="floatingInput" label="Email address" className="mb-4">
                            <Form.Control type="email" placeholder="name@example.com" />
                         </FloatingLabel>
                        
                        {/* Password */}
                        <FloatingLabel controlId="floatingPassword" label="Password" className="mb-5">
                            <Form.Control type="password" placeholder="Password" />
                        </FloatingLabel>

                        <Button type="submit" className='w-100 btn-lg btn-success'>Sign in</Button>
                    </Form>
                </Card>
            </Container>

        </main>
    );
}

export default Login;