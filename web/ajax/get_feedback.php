<?php
require_once '../ajax/base/base.class.php';

$DB = new Database();

if(isset($_GET['project_id'])) {

	$projectID = $_GET['project_id'];

	$query = "SELECT f.id, f.text, f.timestamp AS 'date', f.tip_feedback, CONCAT(p.name, ' ', p.surname) AS 'person' FROM feedback f, person p WHERE f.person_id = p.id AND f.project_id = '$projectID'";
	$result = $DB->selectDB($query);

	if($result->num_rows != 0)
	{
		$feedbackArr = array();

		while($fRow = $result->fetch_assoc())
		{
			$feedbackArr[] = $fRow;
		}

		header('Content-Type: application/json');
		echo json_encode($feedbackArr);
	}
	else
	{
		http_response_code(400);
	}
}
?>
