function login(){
    let userEmail = document.querySelector("#loginEmail");
    let userPassword = document.querySelector("#loginPassword");
    
    const formData = new FormData();

    formData.append('email', userEmail.value);
    formData.append('password', userPassword.value);

    fetch('http://127.0.0.1:8080/login', {
        method: 'POST',
        body: formData
    })
        .then((response) => response.json())
        .then((result) => {
            console.log('Success:', result);
        })
        .catch((error) => {
            console.error('Error:', error);
        });

    const output = document.getElementById('result');

    for (const [key, value] of formData) {
        output.textContent += `${key}: ${value}\n`;
    }

}

async function login2(){
    try{
        let userEmail = document.querySelector("#loginEmail").value;
        let userPassword = document.querySelector("#loginPassword").value;
        
        const formData = new FormData();
        formData.append('email', userEmail);
        formData.append('password', userPassword);

        let response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            mode: 'no-cors', // no-cors 
            body: formData,
        })

        await response.json()
    
    } catch (err) {
        // 👇️ SyntaxError: Unexpected end of JSON input
        console.log('error', err);
      }

}
