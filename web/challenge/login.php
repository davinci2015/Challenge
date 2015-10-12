<?php
require_once '../ajax/base/base.class.php';
$DB = new Database();

if(isset($_POST['obj'])) {
	
	$jsonObject = $_POST['obj'];
	$jsonObject = json_decode(html_entity_decode($jsonObject));

	$username = $jsonObject->{'username'};
	$password = $jsonObject->{'password'};

	$query = "SELECT * FROM person WHERE username = '$username' AND password = '$password'";

	$result = $DB->selectDB($query);

	if($result->num_rows != 0)
	{
		$row = $result->fetch_assoc();
		echo json_encode($row);
	}
	else
	{
		http_response_code(400);
	}
}
?>
