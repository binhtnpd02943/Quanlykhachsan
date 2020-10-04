package edu.poly.fpt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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

	public DanhGia(Integer id, TaiKhoan taikhoan, KhachSan khachsan, @Max(5) @Min(0) int danhgia, String noidung) {
		super();
		this.id = id;
		this.taikhoan = taikhoan;
		this.khachsan = khachsan;
		this.danhgia = danhgia;
		this.noidung = noidung;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public int getDanhgia() {
		return danhgia;
	}

	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}

	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

}
