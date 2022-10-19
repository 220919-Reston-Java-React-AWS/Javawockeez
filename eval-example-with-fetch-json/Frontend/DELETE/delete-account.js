async function deleteAccount(){
    // get form info - firstname, lastname, username, password
    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;
    
    // check that username and password are filled
    if(username === "" || password === ""){
        alert('Username and Password is required to update name');
        return;
    }
    else{
        // use await
        const response = await fetch(`http://localhost:8080/delete-account`, {
            method:'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({'username': username, 'password':password})
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