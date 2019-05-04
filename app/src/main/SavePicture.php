<?php
    $name = $_POST["name"];
	$image = $_POST["image"];
	
	//encode th picture into base 64 string
	$decodedImage = base64_decode("$image");
	//store it in a location 
	file_get_contents("pictures/", $name, ".JPG", $decodedImage);
	
?>