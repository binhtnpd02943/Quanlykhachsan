package edu.poly.fpt.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import edu.poly.fpt.dto.datphongDto;
import edu.poly.fpt.entities.DatPhong;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;

public interface DatphongService {


    

	void deleteAll();

	void deleteAll(List<DatPhong> entities);

	void delete(DatPhong entity);

	void deleteById(Integer id);

	@Modifying
	@Query(value = "update dbquanlykhachsan.tbdatphong b set b.dahuy = 1 where b.id = :id",nativeQuery = true)
	void huyphong(Integer id);

	long count();

	List<DatPhong > findAllById(List<Integer>ids);

	Iterable<DatPhong> findAll();

	boolean existsById(Integer id);

	Optional<DatPhong> findById(Integer id);

	List<DatPhong > saveAll(List<DatPhong > entities);

	DatPhong save(DatPhong  entity);

	List<Phong> findAllPhong();

	List<TaiKhoan> findAllTaikhoan();
	
	@Query(value =  "SELECT a.id,a.dahuy,a.dichvu,a.ngaydat,a.ngayden,a.ngaytra,a.thanhtien,b.ten,b.urlhinhanh , c.ten FROM dbquanlykhachsan.tbdatphong a inner join dbquanlykhachsan.tbphong b  on a.phong_id = b.id inner join dbquanlykhachsan.tbkhachsan c on b.khachsan_id = c.id where a.tentaikhoan = :tentaikhoan ", nativeQuery = true)
	List<Object[]> listDatphong(String tentaikhoan); 
	@Query(value =  "SELECT SUM(a.sophong) as 'so' FROM dbquanlykhachsan.tbdatphong a where a.ngayden = :date and a.phong_id = :id", nativeQuery = true)
	Float soLuongPhong(Integer id , Date date); 
}
