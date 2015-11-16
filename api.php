<?php
/**
 * Created by PhpStorm.
 * User: alwin_000
 * Date: 29-10-2015
 * Time: 10:57
 */
require_once('Database.php');
$db = new Database();
$db->opendb();

    if(isset($_GET['items'])){
        header('Content-Type: application/json');
        $offset = isset($_GET['offset']) ? $_GET['offset'] : "0";
        $query = $db->doquery("SELECT * FROM {{table}} LIMIT ".$offset.",20","girls");
        $items = [];
        while($row = mysqli_fetch_array($query)){
            $items[] = [
                "id" => $row['id'],
                "name" => $row['name'],
                "image" => $row['image'],
            ];
        }
        echo json_encode($items);
    }
    if(isset($_GET['search'])){
        header('Content-Type: application/json');

        $offset = isset($_GET['offset']) ? $_GET['offset'] : "0";
        $query = $db->doquery("SELECT * FROM {{table}} WHERE name like '%".$_GET['search']."%' LIMIT ".$offset.",20","girls");
        $items = [];
        while($row = mysqli_fetch_array($query)){
            $items[] = [
                "id" => $row['id'],
                "name" => $row['name'],
                "image" => $row['image'],
            ];
        }
        echo json_encode($items);
    }
?>

