package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.ThanhPho;
@Repository
public interface ThanhphoRepositoty extends JpaRepository<ThanhPho, Integer>,PagingAndSortingRepository<ThanhPho, Integer>{
	
	 Page<ThanhPho> findByTenLikeOrderByTen(String ten, Pageable pageable);
}
