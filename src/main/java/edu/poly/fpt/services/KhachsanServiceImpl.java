package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.ThanhPho;
import edu.poly.fpt.repositories.DichvuRepository;
import edu.poly.fpt.repositories.KhachsanRepository;
import edu.poly.fpt.repositories.LoaikhachsanRepository;
import edu.poly.fpt.repositories.ThanhphoRepositoty;



@Service
public class KhachsanServiceImpl implements KhachsanService{

	@Override
	public Page<KhachSan> filterKhachSanbyCity(String tentp, int dg, int min, int max, Pageable pageble) {
		return khachsanRepository.filterKhachSanbyCity(tentp, dg, min, max, pageble);
	}
	@Override
	public Page<KhachSan> filterKhachSanbyTypeofCity(int lks, int dg, int min, int max, Pageable pageble) {
		return khachsanRepository.filterKhachSanbyTypeofCity(lks, dg, min, max, pageble);
	}
	@Override
	public Page<KhachSan> filterKhachSanbyNone(int dg, int min, int max, Pageable pageable) {
		return khachsanRepository.filterKhachSanbyNone(dg, min, max, pageable);
	}
	
	@Override
	public Page<KhachSan> filterKhachSanbyAll(String tentp, int lks, int dg, int min, int max, Pageable pageable) {
		return khachsanRepository.filterKhachSanbyAll(tentp, lks, dg, min, max, pageable);
	}

	@Autowired
	private KhachsanRepository khachsanRepository;
	
	@Autowired
	private ThanhphoRepositoty thanhphoRepositoty;
	
	@Autowired 
	private LoaikhachsanRepository loaikhachsanRepository;
	
	@Autowired
	private DichvuRepository dichvuRepository;
	
	@Override
	public List<DichVu> findAllDichvu(){
		return dichvuRepository.findAll();
	}

	@Override
	public List<ThanhPho>findAllThanhpho(){
		return (List<ThanhPho>)thanhphoRepositoty.findAll();
	}
	@Override
	public List<LoaiKhachSan>findAllLoaikhachsan(){
		return (List<LoaiKhachSan>)loaikhachsanRepository.findAll();
	}
	
	@Override
	public KhachSan save(KhachSan entity) {
		return khachsanRepository.save(entity);
	}
	
	@Override
	public List<KhachSan> saveAll(List<KhachSan> entities) {
		return (List<KhachSan>)khachsanRepository.saveAll(entities);
	}
	@Override
	public Optional<KhachSan> findById(Long id) {
		return khachsanRepository.findById(id);
	}
	
	@Override
	public boolean existsById(Long id) {
		return khachsanRepository.existsById(id);
	}
	@Override
	public List<KhachSan> findAll() {
		return khachsanRepository.findAll();
	}
	public Page<KhachSan> findAll(Pageable pageable) {
        return khachsanRepository.findAll(pageable);
    }
	
	@Override
	public Iterable<KhachSan> findAllById(Iterable<Long> ids) {
		return khachsanRepository.findAllById(ids);
	}
	@Override
	public long count() {
		return khachsanRepository.count();
	}
	@Override
	public void deleteById(Long id) {
		khachsanRepository.deleteById(id);
	}
	@Override
	public void delete(KhachSan entity) {
		khachsanRepository.delete(entity);
	}
	@Override
	public void deleteAll(List<KhachSan> entities) {
		khachsanRepository.deleteAll(entities);
	}
	@Override
	public void deleteAll() {
		khachsanRepository.deleteAll();
	}
	@Override
	public Page<KhachSan> findByTenLikeOrderByTen(String ten, Pageable pageable) {
		// TODO Auto-generated method stub
		return khachsanRepository.findByTenLikeOrderByTen("%"+ten+"%", pageable);
	}
}
