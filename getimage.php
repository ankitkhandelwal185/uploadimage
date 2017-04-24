<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $name  = $_GET['name'];
 
 require_once('dbConnect.php');
 
 $sql = "SELECT * FROM image WHERE name='".$name."'";
 
 $r = mysqli_query($con,$sql);
 
 $res = mysqli_fetch_array($r);
 
 $result = array();
 
 array_push($result,array(
 "name"=>$res['name'],
 "desc"=>$res['desc'],
 "time"=>$res['time']
 )
 );
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }