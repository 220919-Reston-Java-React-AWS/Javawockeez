async function createAccount(){
    // get form info - firstname, lastname, username, password
    let firstname = document.querySelector("#first-name").value;
    let lastname = document.querySelector("#last-name").value;
    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;


    // check that username and password are filled
    if(username === "" || password === "" || lastname === "" || firstname === ""){
        alert('Username, Password, Firstname, and Lastname are required to create an account');
        return;
    }
    else{
        // use await
        const response = await fetch("http://localhost:8080/create", {
            method:'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'firstName':firstname, 'lastName':lastname, 'username': username, 'password':password})
        });

        console.log(response);

        if(response.ok){
            document.querySelector("#result").innerHTML = "";
                
            let span = document.createElement('span');

            span.innerHTML = "Server Code = " + response.status + " which means " + response.statusText + ". So backend did something (create a new record in database).";
            document.querySelector("#result").append(span);
        }
        else{
            document.querySelector("#result").innerHTML = "";
            
            let span = document.createElement('span');

            span.innerHTML = "Server Code = " + response.status + " which means " + response.statusText;
            document.querySelector("#result").append(span);
        
        }
    }
    

}