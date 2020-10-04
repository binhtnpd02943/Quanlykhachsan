package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.repositories.LoaikhachsanRepository;

@Service
public class LoaikhachsanServiceImpl implements LoaikhachsanService{

	@Autowired
	private LoaikhachsanRepository loaikhachsanRepository;

	@Override
	public LoaiKhachSan save(LoaiKhachSan entity) {
		return loaikhachsanRepository.save(entity);
	}

	@Override
	public List<LoaiKhachSan> saveAll(List<LoaiKhachSan> entities) {
		return (List<LoaiKhachSan>)loaikhachsanRepository.saveAll(entities);
	}

	@Override
	public Optional<LoaiKhachSan> findById(Integer id) {
		return loaikhachsanRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return loaikhachsanRepository.existsById(id);
	}

	@Override
	public List<LoaiKhachSan> findAll() {
		return loaikhachsanRepository.findAll();
	}
	
	public Page<LoaiKhachSan> findByTenLikeOrderByTen(String ten, Pageable pageable){
		return loaikhachsanRepository.findByTenLikeOrderByTen("%"+ten+"%",pageable);
	}

	@Override
	public Iterable<LoaiKhachSan> findAllById(Iterable<Integer> ids) {
		return loaikhachsanRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return loaikhachsanRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		loaikhachsanRepository.deleteById(id);
	}

	@Override
	public void delete(LoaiKhachSan entity) {
		loaikhachsanRepository.delete(entity);
	}

	@Override
	public void deleteAll(List<LoaiKhachSan> entities) {
		loaikhachsanRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		loaikhachsanRepository.deleteAll();
	} 
	public Page<LoaiKhachSan> findAll(Pageable pageable) {
        return loaikhachsanRepository.findAll(pageable);
    }
	
	
}
