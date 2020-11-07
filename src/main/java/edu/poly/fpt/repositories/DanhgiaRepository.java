package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.entities.DanhGia;
import edu.poly.fpt.entities.KhachSan;
@Repository
public interface DanhgiaRepository extends JpaRepository<DanhGia, Integer>{

	 @Query(value="select a.* from tbdanhgia a inner join tbkhachsan b on a.khachsan_id= b.id where b.id = :id",nativeQuery = true)
	 List<int[]> getListDanhgia(@Param("id")Long id);
}
