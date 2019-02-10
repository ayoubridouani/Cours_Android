<?php
if(isset($_GET["ref"]) || isset($_POST["ref"])){
    if(isset($_GET["ref"])) $c=$_GET["ref"];
    if(isset($_POST["ref"])) $c=$_POST["ref"];
    
    $user="id8362570_wisdvolley";
    $password="wisdvolley";
    $host="localhost";
    $db_name="id8362570_wisdvolley";
    
    $con=mysqli_connect($host,$user,$password,$db_name);
    $req="delete  from produit where ref='".$c."';";
    
   if(mysqli_query($con,$req))
	  echo "Enregistrement supprimé ";
    else
      echo"problème de suppression";	
}else{
    echo "no request method !!";
}
?>