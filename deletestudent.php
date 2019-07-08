<?php

require_once 'connection.php';
$studentid = $_POST['id'];

$sql = "DELETE FROM student WHERE id= '$studentid'";
$response = array();
if(mysqli_query($conn,$sql))
{
    $code = "Yes";
	$message = "DATA Deleted Successfull";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);
}
else {
	
	$code = "Sorry";
	$message = "DATA Not Deleted Successfull";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);

}

mysqli_close($conn);

?>