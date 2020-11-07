package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.ThanhPho;



public interface KhachsanService {

	void deleteAll();

	void deleteAll(List<KhachSan> entities);

	void delete(KhachSan entity);

	void deleteById(Long id);

	long count();

	Iterable<KhachSan> findAllById(Iterable<Long> ids);

	List<KhachSan> findAll();
	
	
	boolean existsById(Long id);

	Optional<KhachSan> findById(Long id);
	
	List<KhachSan> saveAll(List<KhachSan> entities);

	KhachSan save(KhachSan entity);
	
	Page<KhachSan> findAll(Pageable pageable);
	
	List<LoaiKhachSan> findAllLoaikhachsan();

	List<ThanhPho> findAllThanhpho();

	List<DichVu> findAlldichvu();
	 Page<KhachSan> findByTenLikeOrderByTen(String ten,Pageable pageble);

	Page<KhachSan> filterKhachSanbyNone(int dg, int min, int max, Pageable pageable);

	Page<KhachSan> filterKhachSanbyTypeofCity(int lks, int dg, int min, int max, Pageable pageble);

	Page<KhachSan> filterKhachSanbyCity(String tentp, int dg, int min, int max, Pageable pageble);

	Page<KhachSan> filterKhachSanbyAll(String tentp, int lks, int dg, int min, int max, Pageable pageable);

	List<DichVu> findAllDichvu();
	
	
	

}
