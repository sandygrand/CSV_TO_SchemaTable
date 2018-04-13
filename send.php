<?php
require "conn1.php";
$user_id = $_POST["id"];
$user_name= $_POST["name"];
$user_addr = $_POST["addr"];
$user_num= $_POST["num"];
$mysql_qry = "insert into csvdata values ('$user_id','$user_name','$user_num','$user_addr')";
$result = mysqli_query($conn ,$mysql_qry);
	echo "Entry Success";
?>
