package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import edu.poly.fpt.entities.DanhGia;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;
import edu.poly.fpt.repositories.DanhgiaRepository;
import edu.poly.fpt.repositories.KhachsanRepository;
import edu.poly.fpt.repositories.TaikhoanRepository;

@Service
public class DanhgiaServiceImpl implements DanhgiaService {
	@Autowired
	private DanhgiaRepository danhgiaRepository;
	
	@Autowired
	private TaikhoanRepository taikhoanRepository;
	@Autowired 
	private KhachsanRepository khachsanRepository;
	
	@Override
	public List<TaiKhoan> findAllTaikhoans(){
		return (List<TaiKhoan>) taikhoanRepository.findAll();
	}
	
	@Override
	public List<KhachSan> findAllKhachsans(){
		return (List<KhachSan>) khachsanRepository.findAll();
	}

	@Override
	public DanhGia save(DanhGia entity) {
		return danhgiaRepository.save(entity);
	}

	@Override
	public List<DanhGia> saveAll(List<DanhGia> entities) {
		return (List<DanhGia>)danhgiaRepository.saveAll(entities);
	}

	@Override
	public Optional<DanhGia> findById(Integer id) {
		return danhgiaRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return danhgiaRepository.existsById(id);
	}

	@Override
	public Iterable<DanhGia> findAll() {
		return danhgiaRepository.findAll();
	}

	@Override
	public List<DanhGia> findAllById(List<Integer> ids) {
		return (List<DanhGia>)danhgiaRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return danhgiaRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		danhgiaRepository.deleteById(id);
	}

	@Override
	public void delete(DanhGia entity) {
		danhgiaRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<DanhGia> entities) {
		danhgiaRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		danhgiaRepository.deleteAll();
	}

}
