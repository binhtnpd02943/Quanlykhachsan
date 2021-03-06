package edu.poly.fpt.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbdanhgia")
public class DanhGia implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@Max(5)
	@Min(0)
	private int danhgia;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngay;

	@Column(columnDefinition = "nvarchar(200)")
	private String noidung;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tentaikhoan",columnDefinition = "varchar(50)")
	private TaiKhoan taikhoan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "khachsanId")
	private KhachSan khachsan;


	public DanhGia() {
		super();
	}


	public DanhGia(Integer id, @Max(5) @Min(0) int danhgia, Date ngay, String noidung, TaiKhoan taikhoan,
			KhachSan khachsan) {
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


	public TaiKhoan getTaikhoan() {
		return taikhoan;
	}


	public void setTaikhoan(TaiKhoan taikhoan) {
		this.taikhoan = taikhoan;
	}


	public KhachSan getKhachsan() {
		return khachsan;
	}


	public void setKhachsan(KhachSan khachsan) {
		this.khachsan = khachsan;
	}



}
