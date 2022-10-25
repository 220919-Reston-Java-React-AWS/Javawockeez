// react-bootstrap
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import Form from 'react-bootstrap/Form';

//to scroll to the top of page when loaded
import {useEffect, useState} from 'react';

// custom css
import '../Login/custom.css'

function Signup() {

    let newUser = {
        email: "",
        password: "",
        firstName:"",
        lastName:"",
    }

    let [registrationMessage, setRegistrationMessage] = useState("");
    let [registering, setRegistering] = useState(true);


    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
      }, []);

    
    // Set the users email as they fill it out
    function setEmail(event: React.ChangeEvent<HTMLInputElement>){
        newUser.email = event.target.value;
    }

    // Set the password as they fill it out
    function setPassword(event: React.ChangeEvent<HTMLInputElement>){
        newUser.password = event.target.value;
    }

    // Set the users first name as they fill it out
    function setFirstName(event: React.ChangeEvent<HTMLInputElement>){
        newUser.firstName = event.target.value;
    }

    // Set the last name as they fill it out
    function setLastName(event: React.ChangeEvent<HTMLInputElement>){
        newUser.lastName = event.target.value;
    }

    // Attempt to log into the back end
    async function submit(event: React.FormEvent<HTMLFormElement>){
        event.preventDefault();

        // Response message based on the back ends response
        let message:string;

        await fetch("http://127.0.0.1:8080/register", {
            body: JSON.stringify(newUser),
            method: "POST",
            credentials: 'same-origin',

            headers:{
                'Content-Type': 'application/json',
            },
        })
        .then( response => response.json())
        .then( result => {

            if (result.message){ // Error message
                message = result.message

            } else { // Success message/login
                localStorage.setItem("email", result.email) // **** NEEDS TO CHANGE **** //
                message = `Welcome ${result.firstName}!`
            }

            // Set the message to the response card, and display it
            setRegistrationMessage(message)
            setRegistering(false)
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }

    // Get the form for the body
    function getForm(){
        return <Form onSubmit={submit}>
        <h1 className="h3 mb-5 fw-normal">Create Account</h1>
        
        {/* First Name */}
        <FloatingLabel controlId="floatingInput" label="First Name" className="mb-4">
            <Form.Control type="text" placeholder="First Name" onChange={setFirstName}/>
        </FloatingLabel>

        {/* Last Name */}
        <FloatingLabel controlId="floatingInput" label="Last Name" className="mb-4">
            <Form.Control type="text" placeholder="Last Name" onChange={setLastName}/>
        </FloatingLabel>

        {/* Email address */}
        <FloatingLabel controlId="floatingInput" label="Email address" className="mb-4">
            <Form.Control type="email" placeholder="name@example.com" onChange={setEmail} />
        </FloatingLabel>
        
        {/* Password */}
        <FloatingLabel controlId="floatingPassword" label="Password" className="mb-5">
            <Form.Control type="text" placeholder="Password" onChange={setPassword} />
        </FloatingLabel>

        <Button type="submit" className='w-100 btn-lg btn-success'>Sign up</Button>
    </Form>
    }

    // Get the response message for afterwards
    function getMessage(){
        return <p>
            {registrationMessage}
            <Button type="submit" className='w-100 btn-lg btn-success' onClick={()=>setRegistering(true)}>Go Back</Button>
        </p>
    }

    return(
        <main className="min-vh-100 background">
            <Container fluid className="d-flex text-center justify-content-center vertical-center pb-5">
                <Card className='card-width p-5'>
                    {registering ? getForm():getMessage()}
                </Card>
            </Container>

        </main>
    );
}

export default Signup;