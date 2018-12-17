<?php
    include "connect.php";
    $query = "SELECT * FROM SanPham ORDER BY ID DESC LIMIT 4";
    $data = mysqli_query($conn,$query);
    $mangspmoinhat = array();
    while ($row = mysqli_fetch_assoc($data)){
        array_push($mangspmoinhat,new Sanphammoinhat(
                    $row['id'],
                    $row['tensanpham'],
                    $row['giasanpham'],
                    $row['hinhanhsanpham'],
                    $row['motasanpham'],
                    $row['idsanpham'],
                    $row['idNSX']));
    }
    echo json_encode($mangspmoinhat);
    class Sanphammoinhat{
        function Sanphammoinhat($id,$tensp,$giasp,$hinhanhsp,$motasp,$idsanpham,$idNSX){
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

