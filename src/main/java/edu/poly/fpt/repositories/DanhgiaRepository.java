package edu.poly.fpt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.entities.DanhGia;
@Repository
public interface DanhgiaRepository extends CrudRepository<DanhGia, Integer>{

}
