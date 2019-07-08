<?php

require_once 'connection.php';

$id = $_POST["id"];
$studentid = $_POST["student_id"];
$name = $_POST["name"];
$email = $_POST["email"];
$phone = $_POST["phone"];

$sql = "UPDATE student SET student_id = '$studentid' ,name = '$name', email = '$email', phone = '$phone'  WHERE student.id = '$id'";

$response = array();
if(mysqli_query($conn,$sql))
{
    $code = "Yes";
	$message = "DATA Updated Successfull";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);
}
else {
	
	$code = "Sorry";
	$message = "DATA Not Updated Successfull";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);

}

mysqli_close($conn);

?>