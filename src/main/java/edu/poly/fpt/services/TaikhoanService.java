package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.poly.fpt.entities.PasswordResetToken;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;

public interface TaikhoanService {

	void deleteAll();

	void deleteAll(List<TaiKhoan> entities);

	void delete(TaiKhoan entity);

	void deleteById(String id);

	long count();

	Iterable<TaiKhoan> findAllById(Iterable<String> ids);

	Iterable<TaiKhoan> findAll();

	boolean existsById(String id);

	Optional<TaiKhoan> findById(String id);

	List<TaiKhoan> saveAll(List<TaiKhoan> entities);

	TaiKhoan save(TaiKhoan entity);
	Page<TaiKhoan> findAll(Pageable pageable);
	
	TaiKhoan findByEmail(String email);
	TaiKhoan findByTentaikhoan(String tentaikhoan);
	
	List<TaiKhoan> findByTentaikhoanLikeOrderByTentaikhoan(String hoten);
	
	
	 PasswordResetToken getPasswordResetToken(final String token);
	  
	  void createPasswordResetTokenForUser( final TaiKhoan user, final String token);

}
