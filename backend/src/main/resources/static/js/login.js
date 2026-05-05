
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
        const data = await response.json();
        if(response.ok){
            //I want to get to landing page
            alert("Login Successful!");
            if(data.token){
                localStorage.setItem("token", data.token);
            }
            window.location.href="/dashboard";
            document.getElementById("loginForm").reset();
        }else{
            alert(data.message || "Login failed!");
        }
    }catch(error){
        console.error("Error:", error);
    }

});

// login by Google
function handleCredentialResponse(response) {
    const googleToken = response.credential;

    fetch("http://localhost:8080/auth/google", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ token: googleToken })
    })
        .then(async (res) => {
            if(!res.ok){
                throw new Error("Google login failed");
            }
            return res.json();
        })
        .then((data) => {
            console.log("Server response:", data);

            localStorage.setItem("token", data.token);

            alert("Welcome " + data.name);

            // redirect
            window.location.href = "/dashboard";
        })
        .catch((err) =>{
            console.error("Google Login Error: ", err);
            alert("Google login failed");
        });
}