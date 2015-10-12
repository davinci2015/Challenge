<?php
header( "Content-Type: application/vnd.ms-excel" );
header( "Content-disposition: attachment; filename=spreadsheet.xls" );

require_once '../ajax/base/base.class.php';

$DB = new Database();

if(isset($_GET['project_id'])) {

	$projectID = $_GET['project_id'];
	$groupname = "";

	$query = "SELECT pr.name, f.id, f.text, f.timestamp AS 'date', f.tip_feedback, CONCAT(p.name, ' ', p.surname) AS 'person', f.group_id FROM feedback f, person p, project pr WHERE f.person_id = p.id AND f.project_id = '$projectID' AND pr.id = '$projectID'";
	$result = $DB->selectDB($query);

	if($result->num_rows != 0)
	{
		echo "Belongs to project" . "\t" . "Feedback content" . "\t" . "Feedback created at" . "\t" . "Feedback creator" . "\t" . "Belongs to group" . "\n";
		while($row = $result->fetch_assoc())
		{
			if(empty($row['group_id']))
				$groupname = "Does not belong to group.";
			else
			{
				$query = "SELECT name FROM groups WHERE id = '$row[group_id]'";
				$qResult = $DB->selectDB($query);

				if($qResult->num_rows != 0)
				{
					$tempHolder = $qResult->fetch_assoc();
					$groupname = $gname['name'];
				}
			}

			echo $row['name'] . "\t" . $row['text'] . "\t" . $row['date'] . "\t" . $row['person'] . "\t" . $groupname . "\n";
		}
	}
}
?>
