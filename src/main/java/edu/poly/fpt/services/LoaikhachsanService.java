package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import edu.poly.fpt.entities.LoaiKhachSan;

public interface LoaikhachsanService {

	void deleteAll();

	void deleteAll(List<LoaiKhachSan> entities);

	void delete(LoaiKhachSan entity);

	void deleteById(Integer id);

	long count();

	Iterable<LoaiKhachSan> findAllById(Iterable<Integer> ids);

	List<LoaiKhachSan> findAll();

	boolean existsById(Integer id);

	Optional<LoaiKhachSan> findById(Integer id);

	List<LoaiKhachSan> saveAll(List<LoaiKhachSan> entities);
	
	Page<LoaiKhachSan> findByTenLikeOrderByTen(String ten, Pageable pageable);
	
	Page<LoaiKhachSan> findAll(Pageable pageable);

	LoaiKhachSan save(LoaiKhachSan entity);

}
