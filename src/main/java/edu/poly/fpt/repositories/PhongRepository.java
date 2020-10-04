package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.support.PagingAndSortingTemplateVariables;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.entities.Phong;
@Repository
public interface PhongRepository extends JpaRepository<Phong, Integer>,PagingAndSortingRepository<Phong, Integer>{
	Page<Phong> findByTenLikeOrderByTen(String ten,Pageable pageable);
	
	

}
