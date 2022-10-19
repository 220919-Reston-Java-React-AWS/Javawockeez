async function updateAccountName(){
    // get form info - firstname, lastname, username, password
    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;
    let firstname = document.querySelector("#firstname").value;
    let lastname = document.querySelector("#lastname").value;
    
    // check that username and password are filled
    if(username === "" || password === ""){
        alert('Username and Password is required to update name');
        return;
    }

    // check if etiher firstname or lastname is filled, both works too
    if(firstname === "" && lastname === ""){
        alert('Either firstname or lastname, or both must be filled');
        return;
    }
    // if firstname empty, can assume lastname if filled
    else if(firstname === ""){
        // use await
        const response = await fetch(`http://localhost:8080/update-account-name`, {
            method:'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'lastName':lastname, 'username': username, 'password':password})
        });

        console.log(response);

        if(response.ok === true){
            document.querySelector("#result").innerHTML = "";
                
            let span = document.createElement('span');

            span.innerHTML = "Server Code = " + response.status + " which means " + response.statusText + ". So backend did something (might not have changed anything if bad username/password).";
            document.querySelector("#result").append(span);
        }
        else{
            document.querySelector("#result").innerHTML = "";
            
            let span = document.createElement('span');

            span.innerHTML = "Server Code = " + response.status + " which means " + response.statusText;
            document.querySelector("#result").append(span);
    
        }

    }
    // if lastname empty, can assume firstname is filled
    else if(lastname === ""){
        // use await
        // NOTE: With the fetch api, you CAN NOT have a body 
        // therefore, use the uri to pass the values
        const response = await fetch(`http://localhost:8080/update-account-name`, {
            method:'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'firstName':firstname, 'username': username, 'password':password})
        });

        console.log(response);

        if(response.ok === true){
            document.querySelector("#result").innerHTML = "";
                
            let span = document.createElement('span');

            span.innerHTML = "Server Code = " + response.status + " which means " + response.statusText + ". So backend did something (might not have changed anything if bad username/password).";
            document.querySelector("#result").append(span);
        }
        else{
            document.querySelector("#result").innerHTML = "";
            
            let span = document.createElement('span');

            span.innerHTML = "Server Code = " + response.status + " which means " + response.statusText;
            document.querySelector("#result").append(span);
    
        }

    }
    // both are filled
    else{
        // use await
        // NOTE: With the fetch api, you CAN NOT have a body 
        // therefore, use the uri to pass the values
        const response = await fetch(`http://localhost:8080/update-account-name`, {
            method:'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'firstName':firstname, 'lastName':lastname, 'username': username, 'password':password})
        });

        console.log(response);

        if(response.ok === true){
            document.querySelector("#result").innerHTML = "";
                
            let span = document.createElement('span');

            span.innerHTML = "Server Code = " + response.status + " which means " + response.statusText + ". So backend did something (might not have changed anything if bad username/password).";
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