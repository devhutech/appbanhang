<?php
    include "connect.php";
    $query = "SELECT * FROM Nhasanxuat";
    $data = mysqli_query($conn,$query);
    $mangNSX = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangNSX,new NSX(
                   $row['id'],
                   $row['tennhasanxuat']));
    }
    echo json_encode($mangNSX);
    class NSX{
        function NSX($id,$tennhasanxuat){
            $this->id = $id;
            $this->tennhasanxuat = $tennhasanxuat;
        }
    }
?>
