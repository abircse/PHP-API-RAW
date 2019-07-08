<?php

include 'connection.php';

$studentid = $_POST['student_id'];

$sql = "SELECT * FROM student WHERE student.student_id = '".$studentid."'";

$result = mysqli_query($conn,$sql);
$allData= array();

if($result)
{
	while($row = mysqli_fetch_assoc($result))
	{
		$alldata[] = $row;
	}
}
else
{
	echo mysqli_error($conn);
}

echo json_encode($alldata);

?>