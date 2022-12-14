// react-bootstrap
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import FloatingLabel from 'react-bootstrap/FloatingLabel';
import Form from 'react-bootstrap/Form';

//to scroll to the top of page when loaded
import React, {useEffect, useState} from 'react';

// custom css
import './custom.css'
import { useAppDispatch } from '../../shared/hooks';
import { setUser } from './UserSlicer';

//setting up http cookies
import {useCookies} from 'react-cookie';
import { useNavigate } from 'react-router-dom';


function Login() {
    //setting up http cookie
    const [cookies, setCookie, removeCookie] = useCookies(['userId','userFirstName', 'userLastName', 'userEmail', 'userRole']);
    const navigate = useNavigate();
    const dispatch = useAppDispatch();
    
    //placing values
    function setUserCookie(result:any){
        setCookie('userId', result.id, { path: '/' });
        setCookie('userFirstName', result.firstName, { path: '/' });
        setCookie('userLastName', result.lastName, { path: '/' });
        setCookie('userEmail', result.email, { path: '/' });
        setCookie('userRole', result.role, { path: '/' });
    }

    // The user logging in. Fills out as the form does.
    let newUser = {
        email: "",
        password: ""
    }
    // The message you see after filling out the form
    let [loginMessage, setLoginMessage] = useState("");
    // Whether or not you ARE filling out the form
    let [loggingIn, setLoggingIn] = useState(true);


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
    
    // Attempt to log into the back end
    async function submit(event: React.FormEvent<HTMLFormElement>){
        event.preventDefault();

        // Response message based on the back ends response
        let message:string;

        await fetch("http://127.0.0.1:8080/login", {
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
                setUserCookie(result)   // http cookie
                dispatch(setUser(result))
                message = `Welcome back ${result.firstName}!`
            }

            // Set the message to the response card, and display it
            setLoginMessage(message)
            setLoggingIn(false)
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }

    // Get the form for the body
    function getForm(){
        return <Form onSubmit={submit}>
                <h1 className="h3 mb-5 fw-normal">User Login</h1>
        
                {/* Email address */}
                <FloatingLabel controlId="floatingInput" label="Email address" className="mb-4">
                    <Form.Control type="email" placeholder="name@example.com" onChange={setEmail}/>
                </FloatingLabel>
        
                {/* Password */}
                <FloatingLabel controlId="floatingPassword" label="Password" className="mb-5">
                    <Form.Control type="password" placeholder="Password" onChange={setPassword} />
                </FloatingLabel>

                <Button type="submit" className='w-100 btn-lg btn-success'>Sign in</Button>
            </Form>
    }

    // Get the message for the body
    function getMessage(){
        return <p>
            {loginMessage}
            <Button type="submit" className='w-100 btn-lg btn-success' onClick={()=>setLoggingIn(true)}>Go Back</Button>
        </p>
    }

    if (loginMessage.length > 10 && loginMessage.substring(0, 7)=="Welcome"){
        setTimeout( () => {
            navigate( '/' )
        }, 3000)
    }

    // Displays the amazing art, and either the form or response afterward
    return(
        <main className="min-vh-100 background_login">
            <Container fluid className="d-flex text-center justify-content-center vertical-center pb-5">
                <Card className='card-width p-5 background-form' >
                    {loggingIn ? getForm() : getMessage()}
                </Card>
            </Container>

        </main>
    );
}

export default Login;