<?php
//library for php password luat de la alcineva 
    require("password.php");

    $connect = mysqli_connect("my_host", "my_user", "my_password", "my_database");
    
    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];

     function registerUser() {
        global $connect, $name, $age, $username, $password;
		//create a password hash; with password_hash cu default ca algortihm for password
		//encryption 
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO user (name, age, username, password) VALUES (?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "siss", $name, $age, $username, $passwordHash);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }

    function usernameAvailable() {
        global $connect, $username;
		//selects all users names from db  where the user name
		//is the one inputted; 
        $statement = mysqli_prepare($connect, "SELECT * FROM user WHERE username = ?"); 
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
		//if it exits....
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }

    $response = array();
    $response["success"] = false;  
//when the user first registers it checks if the username is available, so 2 people
//don t have the same username

    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
    echo json_encode($response);
?>
