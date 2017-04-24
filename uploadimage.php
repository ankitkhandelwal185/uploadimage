<?php
 
 $name = $_POST['name'];
 $image = $_POST['image'];
 $desc = $_POST['desc'];
 $image_path = $_POST['image_path'];
 $time = $_POST['time'];
 
 if($name == '' || $image == '' || $desc == '' || $image_path == ''){
 echo 'please provide Name, image, desc';
 }else{
 require_once('dbConnect.php');
 $sql = "SELECT * FROM image WHERE name='$name' OR image='$image' OR desc='$desc' OR image_path='$image_path' OR time='$time'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$sql));
 
 $sql = "INSERT INTO user (name,image,desc,image_path,time) VALUES('$name','$image','$desc','$image_path','$time')";
 if(mysqli_query($con,$sql)){
 echo 'successfully uploaded';
 }else{
 echo 'oops! Please try again!';
 }
 mysqli_close($con);
 }