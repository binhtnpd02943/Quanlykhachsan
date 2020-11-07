package edu.poly.fpt.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import edu.poly.fpt.entities.KhachSan;
import edu.poly.fpt.entities.TaiKhoan;

public class ratingDto {

	private Integer id;

	@NotNull(message = "Vui lòng chọn đánh giá!")
	private int danhgia;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date ngay;

	@NotNull(message = "Vui lòng nhập nội dung!")
	private String noidung;
	
	
	private String taikhoan;

	
	private Long khachsan;


	public ratingDto() {
		super();
	}


	public ratingDto(Integer id, @NotNull(message = "Vui lòng chọn đánh giá!") int danhgia, Date ngay,
			@NotNull(message = "Vui lòng nhập nội dung!") String noidung, String taikhoan, Long khachsan) {
		super();
		this.id = id;
		this.danhgia = danhgia;
		this.ngay = ngay;
		this.noidung = noidung;
		this.taikhoan = taikhoan;
		this.khachsan = khachsan;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getDanhgia() {
		return danhgia;
	}


	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}


	public Date getNgay() {
		return ngay;
	}


	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}


	public String getNoidung() {
		return noidung;
	}


	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}


	public String getTaikhoan() {
		return taikhoan;
	}


	public void setTaikhoan(String taikhoan) {
		this.taikhoan = taikhoan;
	}


	public Long getKhachsan() {
		return khachsan;
	}


	public void setKhachsan(Long khachsan) {
		this.khachsan = khachsan;
	}

	
}
