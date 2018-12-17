<?php
    include "connect.php";
    $query = "SELECT * FROM loaisanpham";
    $data = mysqli_query($conn,$query);
    $mangloaisp = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangloaisp,new Loaisp(
                   $row['id'],
                   $row['tenloaisanpham']));
    }
    echo json_encode($mangloaisp);
    class Loaisp{
        function Loaisp($id,$tenloaisp){
            $this->id = $id;
            $this->tenloaisp = $tenloaisp;
        }
    }
?>
