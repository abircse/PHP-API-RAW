<?php

$host="localhost";
$userName="root";
$password="";
$dbName="myfirstdatabase";

$conn= mysqli_connect($host,$userName,$password,$dbName);

$studentid = $_POST["student_id"];
$name = $_POST["name"];
$email = $_POST["email"];
$phone = $_POST["phone"];

$sql = "select * from student where student.student_id = '$studentid'";
$result = mysqli_query($conn, $sql);
$response = array();
if (mysqli_num_rows($result)>0)
{
	$code = "Sorry";
	$message = "Student ID Already Exist";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);
}
else
{
     $sql = "INSERT INTO student (student_id,name,email,phone) VALUES ('$studentid','$name','$email','$phone')";
     $result = mysqli_query($conn, $sql);
     $code = "Congratulation!";
     $message = "Student Data Inserter Successfully";
     array_push($response,array("code"=>$code,"message"=>$message));
     echo json_encode($response);

}
mysqli_close($conn);

?>