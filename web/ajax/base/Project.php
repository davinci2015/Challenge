<?php
require_once 'base.class.php';
require_once '../../../../../../usr/share/php/Mail.php';

class Project
{
	public $id;
	public $name;
	public $desc;
	public $groupName;
	public $public;
	public $DB;
	public $open;
	public $emails;

	private function generateRandomString($length) 
	{
	    $characters = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	    $charactersLength = strlen($characters);
	    $randomString = '';
	    for ($i = 0; $i < $length; $i++) {
	        $randomString .= $characters[rand(0, $charactersLength - 1)];
	    }
	    return $randomString;
	}

	public function AddProject()
	{
		$DB = new Database();
		$postdata         = file_get_contents("php://input");
		$request          = json_decode($postdata);
		$counter          = count($request) - 1;
		$this->name       = $request[$counter]->name;
		$this->desc       = $request[$counter]->desc;
		$this->public     = $request[$counter]->public;
		$this->open       = $request[$counter]->open;
		$this->emailReg   = $request[$counter]->emailReg;
		$this->emailNoreg = $request[$counter]->emailNoreg;

		$date = date("Y-m-d");
		$query = "INSERT INTO project VALUES(default, '$this->name', '$date', '$this->desc', $this->public)";
		$DB->updateDB($query);

		$query    = "SELECT max(id) as 'max' from project";
		$result   = $DB->selectDB($query);
		$row      = $result->fetch_assoc();
		$this->id = $row['max'];

		if(!$this->public)
		{
			$randString = $this->generateRandomString(6);
			$query = "INSERT INTO closed_project_type VALUES(default, '$randString', $this->open, $this->id)";
			$DB->updateDB($query);
			foreach($this->emailReg as $email)
			{
				$query = "INSERT INTO invitation VALUES ('$email', $this->id, 0)";
				$DB->updateDB($query);
				$body = "Give feedback on our project! Project CODE -> " . $randString;
				echo $body;
				//$this->SendMail($email, $body);
			}
			foreach($this->emailNoreg as $email)
			{
				$body = "Try our new application!";
		        $this->SendMail($email, $body);
			}
		}

		if($counter)
		{
			for($i = 0; $i < count($request) - 1; $i++)
			{
				$group = $request[$i]->groupName;
				$query = "INSERT INTO groups VALUES(default, '$group', $this->id)";
				$DB->updateDB($query);
			}
		}
	}
	public function SendMail($email, $body)
	{
		$from = "Studenti <studenti@stedimoenergiju.com>";
        $to = $email;
        $subject = "Naslov";

        $host = "mail.stedimoenergiju.com";
        $port = "25";
        $username = "studenti@stedimoenergiju.com";
        $password = "challenge!5";

        $headers = array ('From' => $from,
           'To' => $to,
           'Subject' => $subject,
           'Reply-To' => $from,
           'MIME-Version' => '1.0',
           'Content-Type' => 'text/html; charset=UTF-8');
        
        $smtp = Mail::factory('smtp',
           array ('host' => $host,
             'port' => $port,
             'auth' => true,
             'username' => $username,
             'password' => $password));
       
        $mail = $smtp->send($to, $headers, $body);  
        if (PEAR::isError($mail)) {
           return("<p>" . $mail->getMessage() . "</p>");  
       	} 
        else {   
           	return("<p>Message successfully sent!</p>");  
        } 
	}
}
?>