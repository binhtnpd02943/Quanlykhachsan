package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import edu.poly.fpt.entities.DatPhong;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;

public interface DatphongService {

	void deleteAll();

	void deleteAll(List<DatPhong> entities);

	void delete(DatPhong entity);

	void deleteById(Integer id);

	long count();

	List<DatPhong > findAllById(List<Integer>ids);

	Iterable<DatPhong> findAll();

	boolean existsById(Integer id);

	Optional<DatPhong> findById(Integer id);

	List<DatPhong > saveAll(List<DatPhong > entities);

	DatPhong save(DatPhong  entity);

	List<Phong> findAllPhong();

	List<TaiKhoan> findAllTaikhoan();

}
