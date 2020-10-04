package edu.poly.fpt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.ThanhPho;
import edu.poly.fpt.repositories.ThanhphoRepositoty;

@Service
public class ThanhphoServiceImpl implements ThanhphoService{
@Autowired
private ThanhphoRepositoty thanhphoRepositoty;

@Override
public ThanhPho save(ThanhPho entity) {
	return thanhphoRepositoty.save(entity);
}

@Override
public List<ThanhPho> saveAll(List<ThanhPho> entities) {
	return (List<ThanhPho>)thanhphoRepositoty.saveAll(entities);
}

@Override
public Optional<ThanhPho> findById(Integer id) {
	return thanhphoRepositoty.findById(id);
}

@Override
public boolean existsById(Integer id) {
	return thanhphoRepositoty.existsById(id);
}

@Override
public Iterable<ThanhPho> findAll() {
	return thanhphoRepositoty.findAll();
}
public Page<ThanhPho> findAll(Pageable pageable) {
    return thanhphoRepositoty.findAll(pageable);
}

@Override
public Iterable<ThanhPho> findAllById(Iterable<Integer> ids) {
	return thanhphoRepositoty.findAllById(ids);
}

@Override
public long count() {
	return thanhphoRepositoty.count();
}

@Override
public void deleteById(Integer id) {
	thanhphoRepositoty.deleteById(id);
}
@Override
public Page<ThanhPho> findByTenLikeOrderByTen(String ten, Pageable pageable) {
	// TODO Auto-generated method stub
	return thanhphoRepositoty.findByTenLikeOrderByTen("%"+ten+"%", pageable);
}

@Override
public void delete(ThanhPho entity) {
	thanhphoRepositoty.delete(entity);
}

@Override
public void deleteAll(List<ThanhPho> entities) {
	thanhphoRepositoty.deleteAll(entities);
}

@Override
public void deleteAll() {
	thanhphoRepositoty.deleteAll();
}


}
