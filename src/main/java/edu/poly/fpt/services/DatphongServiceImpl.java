package edu.poly.fpt.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import edu.poly.fpt.dto.datphongDto;
import edu.poly.fpt.entities.DatPhong;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;
import edu.poly.fpt.repositories.DatphongRepository;
import edu.poly.fpt.repositories.PhongRepository;
import edu.poly.fpt.repositories.TaikhoanRepository;
@Service
public class DatphongServiceImpl implements DatphongService{
@Autowired
private DatphongRepository datphongRepository;

@Autowired 
private TaikhoanRepository taikhoanRepository;

@Autowired
private PhongRepository phongRepository;


@Override
public List<TaiKhoan> findAllTaikhoan(){
	return (List<TaiKhoan>) taikhoanRepository.findAll();
}
@Override
public List<Phong> findAllPhong(){
	return (List<Phong>) phongRepository.findAll();
}

@Override
public DatPhong save(DatPhong  entity) {
	return datphongRepository.save(entity);
}

@Override
public List<DatPhong > saveAll(List<DatPhong > entities) {
	return (List<DatPhong >)datphongRepository.saveAll(entities);
}

@Override
public Optional<DatPhong> findById(Integer id) {
	return datphongRepository.findById(id);
}

@Override
public boolean existsById(Integer id) {
	return datphongRepository.existsById(id);
}

@Override
public Iterable<DatPhong> findAll() {
	return datphongRepository.findAll();
}

@Override
public List<DatPhong > findAllById(List<Integer>ids) {
	return (List<DatPhong>)datphongRepository.findAllById(ids);
}

@Override
public long count() {
	return datphongRepository.count();
}

@Override
public void deleteById(Integer id) {
	datphongRepository.deleteById(id);
}

@Override
public void delete(DatPhong entity) {
	datphongRepository.delete(entity);
}

@Override
public void deleteAll(List<DatPhong> entities) {
	datphongRepository.deleteAll(entities);
}

@Override
public void deleteAll() {
	datphongRepository.deleteAll();
}

@Override
@Query(value =  "SELECT a.id,a.dahuy,a.dichvu,a.ngaydat,a.ngayden,a.ngaytra,a.thanhtien,b.ten,b.urlhinhanh , c.ten FROM dbquanlykhachsan.tbdatphong a inner join dbquanlykhachsan.tbphong b  on a.phong_id = b.id inner join dbquanlykhachsan.tbkhachsan c on b.khachsan_id = c.id where a.tentaikhoan = :tentaikhoan ", nativeQuery = true)
public List<Object[]> listDatphong(String tentaikhoan) {
	
	return datphongRepository.listDatphong(tentaikhoan);
}
@Modifying
@Query(value = "update dbquanlykhachsan.tbdatphong b set b.dahuy = 1 where b.id = :id",nativeQuery = true)
public void huyphong(Integer id){
	 datphongRepository.cancerRoom(id);
}
@Override
@Query(value =  "SELECT SUM(a.sophong) as 'so' FROM dbquanlykhachsan.tbdatphong a where a.ngayden = :date and a.phong_id = :id", nativeQuery = true)
public Float soLuongPhong(Integer id , Date date) {
	
	return datphongRepository.soLuongPhong(id,date);
}
}
