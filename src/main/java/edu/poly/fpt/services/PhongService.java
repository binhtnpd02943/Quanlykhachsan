package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.Phong;

public interface PhongService {

	void deleteAll();

	void deleteAll(List<Phong> entities);

	void delete(Phong entity);

	void deleteById(Integer id);

	long count();

	List<Phong> findAllById(List<Integer> ids);

	Iterable<Phong> findAll();

	boolean existsById(Integer id);

	Optional<Phong> findById(Integer id);

	List<Phong> saveAll(List<Phong> entities);

	Phong save(Phong entity);

	List<KhachSan> findAllKhachsan();
	
	Page<Phong> findByTenLikeOrderByTen(String ten,Pageable pageable);
	
	Page<Phong> findAll(Pageable pageable);
	List<Phong> findByKhachsanIdAndDientichAndTiennghiAndGiathue(
			Long id, Float dientich, String tiennghi, Float gia);

	List<Phong> findByKhachsanIdAndTiennghiAndGiathue(
			Long id, String tiennghi, Float gia);

	List<Phong> findByKhachsanIdAndDientichAndTiennghi(
			Long id, Float dientich, String tiennghi);

	List<Phong> findByKhachsanidAndTiennghiEndingWith(Long id, String tiennghi);
	
	List<Phong> findAllphongbyksid(Long id);

	List<Object> thongKeTheoThang(int Thang, int Nam);

	List<Object> thongKeTheoNgay(String ngay);
}
