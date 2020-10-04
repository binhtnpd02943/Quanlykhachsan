package edu.poly.fpt.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.Phong;
@Repository
public interface DichvuRepository extends JpaRepository<DichVu, Integer>,PagingAndSortingRepository<DichVu, Integer>{
	Page<DichVu> findByTenLikeOrderByTen(String ten,Pageable pageable);

}
