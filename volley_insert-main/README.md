# volley_insert
## insert.php
```
<?php
header('Content-Type: application/json');
$datos = json_decode(file_get_contents("php://input"),true);

require("conexion.php");

$conn = retornarConexion();


$sql = "insert into articulos (codigo,descripcion, precio) values ('$datos[codigo]','$datos[descripcion]','$datos[precio]')";
if ($conn->query($sql))
	echo '{"respuesta":"ok"}';
else
	echo '{"respuesta":"error en la insercción"}';

$conn->close();

?>
```

conexion.php
```
<?php

function retornarConexion(){
	$servername = " ";
	$username = " ";
	$password = " ";
	$dbname = " ";

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}
	return $conn;
}

?>

```

## listar3.php
```
<?php
require("conexion.php");

$conn = retornarConexion();

$sql = "SELECT codigo,descripcion,precio FROM articulos";
$result = $conn->query($sql);
$miresult=mysqli_fetch_all($result,MYSQLI_ASSOC);
echo json_encode($miresult);
$conn->close();
?>
```

## insert2.php
```
<?php
header('Content-Type: application/json');
$datos = json_decode(file_get_contents("php://input"),true);


$micodigo = htmlspecialchars($_GET['codigo']);
$midescripcion = htmlspecialchars($_GET['descripcion']);
$miprecio = htmlspecialchars($_GET['precio']);
//echo $micodigo;

require("conexion.php");

$conn = retornarConexion();


$sql = "insert into articulos (codigo,descripcion, precio) values ({$micodigo},'{$midescripcion}}',{$miprecio})";
//echo $sql;
if ($conn->query($sql))
	echo '{"respuesta":"ok"}';
else
	echo '{"respuesta":"error en la insercción"}';

$conn->close();

?>
```
## insertar3.php
```

<?php
header('Content-Type: application/json');
$datos = json_decode(file_get_contents("php://input"),true);
 

$usuario= $_GET['usuario'];
$email =  $_GET['email'];
$pass =  $_GET['pass'];
//echo $micodigo;

require("conectar.php");

$conn = retornarConexion();

$sql="";
$sql = "insert into usuarios (usuario, email, pass) values ('{$usuario}','{$email}','{$pass}')";
//echo $sql;
if ($conn->query($sql))
	echo '{"respuesta":"ok"}';
else
	echo '{"respuesta":"error en la insercción"}';

$conn->close();

```

