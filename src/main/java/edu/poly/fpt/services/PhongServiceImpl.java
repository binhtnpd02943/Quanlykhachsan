package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;
import edu.poly.fpt.repositories.KhachsanRepository;
import edu.poly.fpt.repositories.PhongRepository;

@Service
public class PhongServiceImpl implements PhongService {
	@Autowired
	private PhongRepository phongRepository;
	@Autowired
	private KhachsanRepository khachsanRepository;

	@Override
	public List<KhachSan> findAllKhachsan() {
		return (List<KhachSan>) khachsanRepository.findAll();
	}

	@Override
	public Phong save(Phong entity) {
		return phongRepository.save(entity);
	}

	@Override
	public List<Phong> saveAll(List<Phong> entities) {
		return (List<Phong>) phongRepository.saveAll(entities);
	}

	@Override
	public Optional<Phong> findById(Integer id) {
		return phongRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return phongRepository.existsById(id);
	}

	@Override
	public Iterable<Phong> findAll() {
		return phongRepository.findAll();
	}

	@Override
	public List<Phong> findAllById(List<Integer> ids) {
		return (List<Phong>) phongRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return phongRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		phongRepository.deleteById(id);
	}

	@Override
	public void delete(Phong entity) {
		phongRepository.delete(entity);
	}

	public Page<Phong> findAll(Pageable pageable) {
		return phongRepository.findAll(pageable);
	}

	@Override
	public void deleteAll(List<Phong> entities) {
		phongRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		phongRepository.deleteAll();
	}

	public Page<Phong> findByTenLikeOrderByTen(String ten, Pageable pageable) {
		return phongRepository.findByTenLikeOrderByTen("%" + ten + "%", pageable);
	}

}
