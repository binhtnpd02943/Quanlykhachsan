package edu.poly.fpt.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import edu.poly.fpt.entities.KhachSan;




@Repository
public interface KhachsanRepository extends JpaRepository<KhachSan, Long>,PagingAndSortingRepository<KhachSan, Long>{
	
	 Page<KhachSan> findByTenLikeOrderByTen(String ten,Pageable pageble);
	 
	 @Query(value="select * from tbkhachsan ks join tbphong p on ks.id=p.khachsan_id join tbthanhpho tp on tp.id = ks.thanhpho_id group by ks.id  having tp.ten=? && ks.danhgia<=? &&  min(p.giathue)>=? && max(p.giathue)<=?", nativeQuery = true)
	 Page<KhachSan> filterKhachSanbyCity(String tentp,int dg,int min,int max,Pageable pageble);
	 
	 @Query(value="select * from tbkhachsan ks join tbphong p on ks.id=p.khachsan_id join tbloaikhachsan lks on lks.id = ks.thanhpho_id group by ks.id  having lks.id=? && ks.danhgia<=? &&  min(p.giathue)>=? && max(p.giathue)<=? ", nativeQuery = true)
	 Page<KhachSan> filterKhachSanbyTypeofCity(int lks,int dg,int min,int max,Pageable pageble);
	 
	 @Query(value="select * from tbkhachsan ks join tbphong p on ks.id=p.khachsan_id group by ks.id  having ks.danhgia<=? && min(p.giathue)>=? && max(p.giathue)<=?",nativeQuery = true)
	 Page<KhachSan> filterKhachSanbyNone(int dg,int min,int max,Pageable pageable);
	 
	 @Query(value="select * from tbkhachsan ks join tbphong p on ks.id=p.khachsan_id join tbloaikhachsan lks on lks.id = ks.thanhpho_id join tbthanhpho tp on tp.id= ks.thanhpho_id group by ks.id  having tp.ten=? && lks.id=? && ks.danhgia<=? &&  min(p.giathue)>=? && max(p.giathue)<=?",nativeQuery = true)
	 Page<KhachSan> filterKhachSanbyAll(String tentp,int lks,int dg,int min,int max,Pageable pageable);
	 
	 
}
