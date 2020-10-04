package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.ThanhPho;

public interface ThanhphoService {

	void deleteAll();

	void deleteAll(List<ThanhPho> entities);

	void delete(ThanhPho entity);

	void deleteById(Integer id);

	long count();

	Iterable<ThanhPho> findAllById(Iterable<Integer> ids);

	Iterable<ThanhPho> findAll();

	boolean existsById(Integer id);

	Optional<ThanhPho> findById(Integer id);

	List<ThanhPho> saveAll(List<ThanhPho> entities);

	Page<ThanhPho> findAll(Pageable pageable);
	
	 Page<ThanhPho> findByTenLikeOrderByTen(String ten, Pageable pageable);
	
	ThanhPho save(ThanhPho entity);

}
