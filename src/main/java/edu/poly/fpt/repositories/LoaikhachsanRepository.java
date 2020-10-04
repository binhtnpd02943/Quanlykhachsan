package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import edu.poly.fpt.entities.LoaiKhachSan;

@Repository
public interface LoaikhachsanRepository extends JpaRepository<LoaiKhachSan, Integer>,PagingAndSortingRepository<LoaiKhachSan, Integer>{

	Page<LoaiKhachSan> findByTenLikeOrderByTen(String ten, Pageable pageable);
}
