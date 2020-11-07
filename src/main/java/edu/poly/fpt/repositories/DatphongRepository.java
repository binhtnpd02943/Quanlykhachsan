package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.fpt.dto.datphongDto;
import edu.poly.fpt.entities.DatPhong;
@Repository
public interface DatphongRepository extends JpaRepository<DatPhong, Integer>{
   
    @Query(value =  "SELECT a.id,a.dahuy,a.dichvu,a.ngaydat,a.ngayden,a.ngaytra,a.thanhtien,b.ten as 'tenphong',a.phong_id, c.ten,a.tentaikhoan FROM dbquanlykhachsan.tbdatphong a inner join dbquanlykhachsan.tbphong b  on a.phong_id = b.id inner join dbquanlykhachsan.tbkhachsan c on b.khachsan_id = c.id where a.tentaikhoan = :tentaikhoan ORDER BY a.ngaydat DESC ", nativeQuery = true)
    List<Object[]> listDatphong(@Param("tentaikhoan") String tentaikhoan);
    @Transactional
    @Modifying
    @Query(value = "update dbquanlykhachsan.tbdatphong b set b.dahuy = 1 where b.id = :id",nativeQuery = true)
    void cancerRoom(@Param("id") Integer id);
}
