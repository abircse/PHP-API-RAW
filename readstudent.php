<?php

include 'connection.php';

$myquery = "SELECT * FROM student";

$result = mysqli_query($conn,$myquery);

if($result)
{
	while($row = mysqli_fetch_assoc($result))
	{
		$alldata[] = $row;
	}

	echo json_encode($alldata);
}
else
{
	echo mysqli_error($conn);
}

?>