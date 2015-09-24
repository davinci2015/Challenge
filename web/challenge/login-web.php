<?php
require_once '../ajax/base/base.class.php';
$DB = new Database();
$postdata = json_decode(file_get_contents('php://input'), true);
var_dump($postdata);
$username = $postdata['username'];
$password = $postdata['password'];

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

?>
