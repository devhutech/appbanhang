<?php
    include "connect.php";
    $page = $_GET['page'];
    $idsp = $_POST['idNSX'];
    $space = 12;
    $limit = ($page - 1) * $space;
    $mangsanpham = array();
    $query = "SELECT * FROM sanpham WHERE idNSX = $idsp LIMIT $limit,$space";
    $data = mysqli_query($conn,$query);
    
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangsanpham,new Sanpham(
                   $row['id'],
                   $row['tensanpham'],
                   $row['giasanpham'],
                   $row['hinhanhsanpham'],
                   $row['motasanpham'],
                   $row['idsanpham'],
                   $row['idNSX']));
    }
    echo json_encode($mangsanpham);
    class Sanpham{
        function Sanpham($id,$tensp,$giasp,$hinhanhsp,$motasp,$idsanpham,$idNSX){
            $this->id = $id;
            $this->tensp = $tensp;
            $this->giasp = $giasp;
            $this->hinhanhsp = $hinhanhsp;
            $this->motasp = $motasp;
            $this->idsanpham = $idsanpham;
            $this->idNSX = $idNSX;
        }
    }
?>
