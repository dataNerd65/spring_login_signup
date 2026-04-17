
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
            window.location.href="/dashboard";
            document.getElementById("loginForm").reset();
        }else{
            alert("Login failed!");
        }
    }catch(error){
        console.error("Error:", error);
    }

});