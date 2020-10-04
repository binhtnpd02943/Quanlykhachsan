package edu.poly.fpt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.entities.DatPhong;
@Repository
public interface DatphongRepository extends CrudRepository<DatPhong, Integer>{

}
