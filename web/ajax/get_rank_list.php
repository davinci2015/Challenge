<?php
require_once '../ajax/base/base.class.php';

$DB = new Database();

$query = "SELECT p.id, CONCAT(p.name, ' ', p.surname) AS 'Person', COUNT(*) * 10 AS 'Rank' FROM person p, feedback f WHERE f.person_id = p.id GROUP BY person ORDER BY Rank DESC;";
$result = $DB->selectDB($query);

if($result->num_rows != 0)
{
	$ranksArr = array();

	while($row = $result->fetch_assoc())
	{
		$ranksArr[] = $row;
	}

	header('Content-Type: application/json');
	echo json_encode($ranksArr);
}
else
{
	http_response_code(400);
}
?>
