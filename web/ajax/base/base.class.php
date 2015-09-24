<?php
    class Database {

        private static $server = "localhost";
        private static $user = "dummy";
        private static $lozinka = "challenge";
        private static $baza = "challenge";
        private $mysqli;

        private function connectDB(){
            $this->mysqli = new mysqli(self::$server, self::$user, self::$lozinka, self::$baza);
            $this->mysqli->set_charset("utf8");

            if($this->mysqli->connect_errno){
                echo "Error while trying to connect to database: " . $this->mysqli->connect_errno;
            }
        }

        function closeDB() {
            $this->mysqli->close();
        }

        function selectDB($query){
            $this->connectDB();

            $result = $this->mysqli->query($query) or trigger_error("Query error: {$query} - "."Error: ".$this->mysqli->error . " " . E_USER_ERROR);

            if(!$result){
                $result = null;
            }

            $this->closeDB();

            return $result;
        }

        function updateDB($query, $script=''){
            $this->connectDB();

            if($result = $this->mysqli->query($query)){
                $this->closeDB();

                if($script != ''){
                    header("Location: $skripta");
                }

                return $result;
            }
            else {

                echo "Error: ".$this->mysqli->error;
                $this->closeDB();

                return $result;
            }
        }
    }
?>