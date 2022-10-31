import { useEffect, useState } from "react";
import { Button, Card, Container, FloatingLabel, Form } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../shared/hooks";
import { selectUser, setUser } from "../Login/UserSlicer";
import "./UserProfile.css"

export function UserProfile(){

    const dispatch = useAppDispatch();
    const [currentUser, setCurrentUser] = useState( useAppSelector(selectUser) );

    // The new-user object that gets filled in as the form does
    let newProfile = {
        email: "",
        password: "",
        firstName:"",
        lastName:"",
    }
    // The message to be displayed after registration
    let [registrationMessage, setRegistrationMessage] = useState("");
    // Whether or not to show the form vs the message afterwards.
    let [registering, setRegistering] = useState(true);


    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
      }, []);

    
    // Set the users email as they fill it out
    function setEmail(event: React.ChangeEvent<HTMLInputElement>){
        newProfile.email = event.target.value;
    }

    // Set the password as they fill it out
    function setPassword(event: React.ChangeEvent<HTMLInputElement>){
        newProfile.password = event.target.value;
    }

    // Set the users first name as they fill it out
    function setFirstName(event: React.ChangeEvent<HTMLInputElement>){
        newProfile.firstName = event.target.value;
    }

    // Set the last name as they fill it out
    function setLastName(event: React.ChangeEvent<HTMLInputElement>){
        newProfile.lastName = event.target.value;
    }

    // Attempt to log into the back end
    async function submit(event: React.FormEvent<HTMLFormElement>){
        event.preventDefault();

        // Response message based on the back ends response
        let message:string;

        await fetch(`http://127.0.0.1:8080/profile/${currentUser.id}`, {
            body: JSON.stringify(newProfile),
            method: "PATCH",
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
                dispatch(setUser(result))
                console.log("Before", result, currentUser)
                setCurrentUser(result);
                console.log("\n\nAfter", currentUser)
                message = `Your profile has successfully been updated!`
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
        return <Card className='card-width p-5'>
            <Form onSubmit={submit}>
        <h1 className="h3 mb-5 fw-normal">Update Profile</h1>
        
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

        <Button type="submit" className='w-100 btn-lg btn-success'>Change Fields</Button>
        </Form>
    </Card>
    }

    // Get the response message for afterwards
    function getMessage(){
        return <Card className='card-width p-5 text-left'>
            {registrationMessage}
            <Button type="submit" className='w-100 btn-lg btn-success' onClick={()=>setRegistering(true)}>Go Back</Button>
        </Card>
    }

    function getProfile(){
        return <Container fluid className="text-center justify-content-center vertical-center-col pb-5">
            <Card className='card-width p-5 text-left mb-3'>
                <ul className="profile-list">
                <li>Name: {currentUser.firstName + " " + currentUser.lastName}</li>
                <li>Email: {currentUser.email}</li>
                </ul>
            </Card>
            {registering ? getForm():getMessage()}
        </Container>
    }

    function getNotLoggedInMessage(){
        return <Container fluid className="text-center justify-content-center vertical-center-col pb-5">
            <Card className='card-width p-5'>
            <h2>You must Log In to View your profile</h2>
            <Link to='/login' className="btn btn-primary">Log In</Link>
            </Card>
        </Container>
    }

    return(
        <main className="min-vh-100 background_profile">
            {currentUser.id ? getProfile() : getNotLoggedInMessage()}
        </main>
    );
}

function capitalizeFirstLetter(str:string) {
    return str.charAt(0).toUpperCase() + str.slice(1);
  }