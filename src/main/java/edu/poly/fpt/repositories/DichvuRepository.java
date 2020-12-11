package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.Phong;
@Repository
public interface DichvuRepository extends JpaRepository<DichVu, Integer>,PagingAndSortingRepository<DichVu, Integer>{
	Page<DichVu> findByTenLikeOrderByTen(String ten,Pageable pageable);

	@Query(value =  "select * from dbquanlykhachsan.tbdichvu where khachsan_id = :id", nativeQuery = true)
    List<DichVu> listdichvuks(@Param("id") Integer id);
}
