package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.repositories.DichvuRepository;
import edu.poly.fpt.repositories.KhachsanRepository;

@Service
public class DichvuServiceImpl implements DichvuService {
	@Autowired
	private DichvuRepository dichvuRepository;

	@Autowired
	private KhachsanRepository khachsanRepository;

	@Override
	public List<KhachSan> findAllKhachsan() {
		return (List<KhachSan>) khachsanRepository.findAll();
	}

	@Override
	public DichVu save(DichVu entity) {
		return dichvuRepository.save(entity);
	}

	@Override
	public List<DichVu> saveAll(List<DichVu> entities) {
		return (List<DichVu>) dichvuRepository.saveAll(entities);
	}

	@Override
	public Optional<DichVu> findById(Integer id) {
		return dichvuRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return dichvuRepository.existsById(id);
	}

	@Override
	public Iterable<DichVu> findAll() {
		return dichvuRepository.findAll();
	}

	@Override
	public List<DichVu> findAllById(List<Integer> ids) {
		return (List<DichVu>) dichvuRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return dichvuRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		dichvuRepository.deleteById(id);
	}

	@Override
	public void delete(DichVu entity) {
		dichvuRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<DichVu> entities) {
		dichvuRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		dichvuRepository.deleteAll();
	}
	public Page<DichVu> findAll(Pageable pageable) {
		return dichvuRepository.findAll(pageable);
	}
	@Override
	public Page<DichVu> findByTenLikeOrderByTen(String ten, Pageable pageable) {
		return dichvuRepository.findByTenLikeOrderByTen("%" + ten + "%", pageable);
	}
	@Override
	@Query(value =  "select * from dbquanlykhachsan.tbdichvu where khachsan_id = :id", nativeQuery = true)
    public List<DichVu> listdichvuks(Integer id){
		return dichvuRepository.listdichvuks(id);
	}


}
