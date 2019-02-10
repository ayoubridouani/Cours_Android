<?php
$user="id8362570_wisdvolley";
$password="wisdvolley";
$host="localhost";
$db_name="id8362570_wisdvolley";

$con=mysqli_connect($host,$user,$password,$db_name);
$req="select * from produit;";
$result=mysqli_query($con,$req);

$tableau=array();
while($ligne=mysqli_fetch_assoc($result))
   $tableau[]=$ligne; 

echo json_encode($tableau);

mysqli_close($con);
?>
    
   
  
  