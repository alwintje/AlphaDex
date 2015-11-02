<?php
/**
 * Created by PhpStorm.
 * User: alwin_000
 * Date: 29-10-2015
 * Time: 10:57
 */


if(isset($_GET['api'])){
    if(isset($_GET['items'])){
        header('Content-Type: application/json');
        $items = [
            [
                "id" => 1,
                "name" => "Twan Withaar",
                "image" => "http://partyflock.nl/images/user/738002_1938009o.jpg",
            ],
            [
                "id" => 2,
                "name" => "Odette Reinderink",
                "image" => "http://images.all-free-download.com/images/wallpapers_large/frozen_heart_wallpaper_3d_models_3d_wallpaper_58.jpg",
            ],
            [
                "id" => 3,
                "name" => "Amber Voorhuis",
                "image" => "http://www.planwallpaper.com/static/images/abstract_wallpaper_xVBXbWX.jpg",
            ],
            [
                "id" => 4,
                "name" => "Odette Reinderink",
                "image" => "http://images.all-free-download.com/images/wallpapers_large/frozen_heart_wallpaper_3d_models_3d_wallpaper_58.jpg",
            ],
        ];
        echo json_encode($items);
    }
}
?>

