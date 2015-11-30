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
        $query = $db->doquery("SELECT id,name,image FROM {{table}} LIMIT ".$offset.",20","girls");
        $items = [];
        while($row = mysqli_fetch_array($query)){
            $items[] = [
                "id" => $row['id'],
                "name" => $row['name'],
                "image" => $row['image'],
            ];

//            $items[] = [
//                "id" => $row['id'],
//                "name" => $row['name'],
//                "image" => $row['image'],
//            ];
//            $items[] = [
//                "id" => $row['id'],
//                "name" => $row['name'],
//                "image" => $row['image'],
//            ];
        }
        echo json_encode($items);
    }
    if(isset($_GET['search'])){
        header('Content-Type: application/json');

        $offset = isset($_GET['offset']) ? $_GET['offset'] : "0";
        $query = $db->doquery("SELECT id,name,image FROM {{table}} WHERE name like '%".$_GET['search']."%' LIMIT ".$offset.",20","girls");
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
    if(isset($_GET['id'])){
        header('Content-Type: application/json');

        $query = $db->doquery("SELECT * FROM {{table}} WHERE id='".$_GET['id']."' ","girls");
        $row = mysqli_fetch_array($query);

        $item = [
            "id" => $row['id'],
            "name" => $row['name'],
            "image" => $row['image'],
            "hair_color" => $row['hair_color'],
            "eye_color" => $row['eye_color'],
            "birth" => $row['birthday'],
            "tier" => $row['tier'],
            "rating" => $row['rating'],
            "rates" => $row['rates'],
        ];
        echo json_encode($item);
    }
    if(isset($_GET['plus'])){
        header('Content-Type: application/json');

        $query = $db->doquery("SELECT * FROM {{table}} WHERE id='".$_GET['plus']."' ","girls");
        $row = mysqli_fetch_array($query);
        $rating = intval($row['rating']);
        $rates = intval($row['rates']);
        $rating++;
        $rates++;

        $db->doquery("UPDATE {{table}} SET rating='".$rating."',rates='".$rates."' WHERE id='".$_GET['plus']."'","girls");
        echo json_encode(true);
    }
    if(isset($_GET['minus'])){
        header('Content-Type: application/json');

        $query = $db->doquery("SELECT * FROM {{table}} WHERE id='".$_GET['minus']."' ","girls");
        $row = mysqli_fetch_array($query);
        $rating = intval($row['rating']);
        $rates = intval($row['rates']);
        $rating--;
        $rates++;
        $db->doquery("UPDATE {{table}} SET rating='".$rating."',rates='".$rates."' WHERE id='".$_GET['minus']."'","girls");

        echo json_encode(true);
    }
?>

