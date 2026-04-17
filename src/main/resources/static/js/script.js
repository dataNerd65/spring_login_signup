document.getElementById("userForm").addEventListener("submit", async function(e){
    e.preventDefault();
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    if(password !== confirmPassword){
        alert("Passwords do not match!");
        return; // stop the function
    }

    const user = {
        firstName : document.getElementById("firstName").value,
        lastName : document.getElementById("lastName").value,
        userName : document.getElementById("userName").value,
        phoneNumber : document.getElementById("phoneNumber").value,
        email : document.getElementById("email").value,
        password : password
    };
    try{
        const response = await fetch("http://localhost:8080/users",{
            method : "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify(user)
        });
        if(response.ok){
            const data  = await response.json();
            console.log("Saved user: ", data);
            alert("User saved successfully!");
            document.getElementById("userForm").reset();
        }else{
            alert("Failed to save user!");
        }
    }catch(error){
        console.error("Error:", error);
    }
});

// login logic
document.getElementById("loginForm").addEventListener("submit", async function(e){
    e.preventDefault();
    const user ={
        userName : document.getElementById("userName").value,
        password : document.getElementById("password").value
    };
    try{
        const response = await fetch("http://localhost:8080/auth/login",{
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify(user)
        });
        if(response.ok){
            //I want to get to landing page
            alert("Login Successful!");
            window.location.href="/dashboard.html";
            document.getElementById("loginForm").reset();
        }else{
            alert("Login failed!");
        }
    }catch(error){
        console.error("Error:", error);
    }

});