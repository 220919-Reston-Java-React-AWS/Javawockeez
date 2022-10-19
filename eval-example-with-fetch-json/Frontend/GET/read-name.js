async function readName(){
    // get form info - firstname, lastname, username, password
    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;

    // check that username and password are filled
    if(username === "" || password === ""){
        alert('Username and Password are required to get account name');
        return;
    }
    else{
        // use await
        // NOTE: With the fetch api, you CAN NOT have a body using GET http request
        // therefore, use the uri to pass the values
        const response = await fetch(`http://localhost:8080/read-name-${username}-${password}`, {
            method:'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        console.log(response);

        if(response.ok === true){
            document.querySelector("#result").innerHTML = "";
                
            let span = document.createElement('span');

            span.innerHTML = "Server Code = " + response.status + " which means " + response.statusText + ". So backend did something. ";
            document.querySelector("#result").append(span);

            const data = await response.json();
            console.log(data);
                    
            let span_name = document.createElement('span');

            span_name.innerHTML = "First Name: " + data.firstName + " with Last Name: " + data.lastName;
            document.querySelector("#result").append(span_name);
        }
        else{
            document.querySelector("#result").innerHTML = "";
            
            let span = document.createElement('span');

            span.innerHTML = "Server Code = " + response.status + " which means " + response.statusText;
            document.querySelector("#result").append(span);
        
        }
    }

}