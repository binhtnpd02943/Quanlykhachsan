package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.support.PagingAndSortingTemplateVariables;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.entities.Phong;

@Repository
public interface PhongRepository extends JpaRepository<Phong, Integer>,PagingAndSortingRepository<Phong, Integer>{
	Page<Phong> findByTenLikeOrderByTen(String ten,Pageable pageable);
	
	@Query(value="select * from tbphong where khachsan_id = ?1",nativeQuery = true)
	List<Phong> findAllphongbyksid(Long id);
	
	List<Phong> findByid(Integer id);
	
	List<Phong> findByKhachsan_idAndTiennghiEndingWith(Long id,String tiennghi);
	
	List<Phong> findByKhachsan_idAndDientichGreaterThanEqualAndTiennghiEndingWith(Long id,Float dientich,String tiennghi);

	List<Phong> findByKhachsan_idAndTiennghiEndingWithAndGiathueGreaterThanEqual(Long id,String tiennghi,Float gia);
	
	List<Phong> findByKhachsan_idAndDientichGreaterThanEqualAndTiennghiEndingWithAndGiathueGreaterThanEqual(Long id,Float dientich,String tiennghi,Float gia);
	
	@Query(value="select * from tbphong where khachsan_id = ?1 and dientich >=?2",nativeQuery = true)
	List<Phong> Filter1(Long id,Float dienTich);
	
		
	

}

