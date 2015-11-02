<?php
/**
 * Created by PhpStorm.
 * User: alwin_000
 * Date: 16-10-2015
 * Time: 14:03
 */

$context = stream_context_create(array(
    'http'=>array(
        'user_agent'=>"User-Agent: Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11\r\n"
    )
));
$html = file_get_contents("http://www.facebook.com/search/results/?q=".urlencode($_GET['q'])."&type=users", true, $context);
//$html = gzuncompress($html);
//echo $html;
echo $html;
$search = explode(" ",$_GET['q']);
$firstname = $search[0];
$lastname = $search[1];
?>

<script>
    setTimeout(function(){
        var searchRes = document.getElementById("pagelet_search_results_objects");
        var searchFirst = searchRes.getElementsByClassName("_1yt")[0].childNodes;
        var item = 0;
        for(var i=0;i<searchFirst.length;i++){
            console.log(searchFirst[i].innerHTML);
            console.log();
            if(searchFirst[i].innerHTML.indexOf("<?php echo $lastname; ?>.") > -1 || searchFirst[i].innerHTML.indexOf("<?php echo $lastname; ?>?") > -1 ){
                item = i;
            }
        }
        searchFirst = searchFirst[item];
        var json = JSON.parse(searchFirst.getAttribute("data-bt"));
        var img = searchFirst.querySelector("img");


        var fb = document.getElementById("facebook");
        fb.innerHTML = json["id"];
        console.log(img.src);
    },300);
</script>
