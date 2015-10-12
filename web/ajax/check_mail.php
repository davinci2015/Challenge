<?php
require_once '../ajax/base/base.class.php';
$DB = new Database();
$postdata = json_decode(file_get_contents('php://input'), true);
$responseArr = array();
$i           = 0;
foreach($postdata as $value)
{
	$query = "SELECT * FROM person where mail = '$value'";
	$result = $DB->selectDB($query);
	if($result->num_rows)
		$responseArr[$i++] = 1;
	else
		$responseArr[$i++] = 0;
}
header('Content-Type: application/json');
echo json_encode($responseArr);

?>
