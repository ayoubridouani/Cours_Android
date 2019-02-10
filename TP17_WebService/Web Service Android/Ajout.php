<?php 

     
	 
	 $c=$_GET["ref"];
	 $n=$_GET["des"];
	
   
   $user="root";
   $password="";
   $host="localhost";
   $db_name="stock";
   
   
   $con=mysqli_connect($host,$user,$password,$db_name);
   $req="insert into produit values('".$c."','".$n."');";
   
   if(mysqli_query($con,$req))
	  echo "Insertion reussie";
    else
      echo"erreur d'insertion!!!"	;	
   
   
   ?>
