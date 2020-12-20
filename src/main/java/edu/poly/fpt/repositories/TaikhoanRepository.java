package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.entities.TaiKhoan;

@Repository
public interface TaikhoanRepository extends JpaRepository<TaiKhoan, String>{

	List<TaiKhoan> findByTentaikhoanLikeOrderByTentaikhoan(String hoten);
	TaiKhoan findByTentaikhoan(String tentaikhoan);
	TaiKhoan findByEmail(String email); 
	
}
