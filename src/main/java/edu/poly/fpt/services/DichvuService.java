package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.Phong;

public interface DichvuService {

	void deleteAll();

	void deleteAll(List<DichVu> entities);

	void delete(DichVu entity);

	void deleteById(Integer id);

	long count();

	List<DichVu> findAllById(List<Integer> ids);

	Iterable<DichVu> findAll();

	boolean existsById(Integer id);

	Optional<DichVu> findById(Integer id);

	List<DichVu> saveAll(List<DichVu> entities);

	DichVu save(DichVu entity);

	List<KhachSan> findAllKhachsan();

	Page<DichVu> findByTenLikeOrderByTen(String ten, Pageable pageable);

	Page<DichVu> findAll(Pageable pageable);

}
