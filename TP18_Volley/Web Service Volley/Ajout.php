<?php 
if(isset($_GET["ref"]) && isset($_GET["des"])){
    $c=$_GET["ref"];
    $n=$_GET["des"];
    
    $user="id8362570_wisdvolley";
    $password="wisdvolley";
    $host="localhost";
    $db_name="id8362570_wisdvolley";
    
    $con=mysqli_connect($host,$user,$password,$db_name);
    $req="insert into produit values('".$c."','".$n."');";
    
    if(mysqli_query($con,$req))
        echo "Insertion reussie";
    else
        echo"erreur d'insertion!!!"	;	
}else{
    echo "no request method !!";
}
?>
