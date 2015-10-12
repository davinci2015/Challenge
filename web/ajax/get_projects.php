<?php
require_once 'base/base.class.php';

$DB = new Database();

$query = "SELECT * FROM project ORDER BY id DESC";

$result = $DB->selectDB($query);

if($result->num_rows != 0)
{
	$projectsArr = array();

	while($row = $result->fetch_assoc())
	{
		$projectsArr[] = $row;
	}

	header('Content-Type: application/json');
	echo json_encode($projectsArr);
}
else
{
	http_response_code(400);
}
?>
