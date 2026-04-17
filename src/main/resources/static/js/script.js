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
            window.location.href= "/login";
        }else{
            alert("Failed to save user!");
        }
    }catch(error){
        console.error("Error:", error);
    }
});
