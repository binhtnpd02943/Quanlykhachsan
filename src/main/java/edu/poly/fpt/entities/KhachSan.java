package edu.poly.fpt.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;


@Entity
@Table(name = "tbkhachsan")
public class KhachSan implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column( columnDefinition = "nvarchar(100)")
	private String ten;
	
	@Column( columnDefinition = "nvarchar(100)")
	private String diachi;
	
	@Column(length = 20)
	private String sodt;
	
	@Column
	private int cachtrungtam;
	
	@Column(length = 100)
	private String urlhinhanh;
	
	@Column( columnDefinition = "nvarchar(255)")
	private String mota;
	
	@Column
	private Boolean cachbien;
	
	@Column
	@Max(5)
	@Min(0)
	private int danhgia;
	
	@Column( columnDefinition = "nvarchar(50)")
	private String buaan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="thanhphoId")
	private ThanhPho thanhpho;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="loaikhachsanId")
	private LoaiKhachSan loaikhachsan;
	
	@OneToMany(mappedBy = "khachsan",cascade = CascadeType.ALL)
	private Set<DanhGia> danhgias;
	
	@OneToMany(mappedBy = "khachsan",cascade = CascadeType.ALL)
	private Set<Phong> phong;
	
	@OneToMany(mappedBy = "khachsan", cascade = CascadeType.ALL)
	private Set<DichVu> dichvu;

	public KhachSan() {
		
	}

	public KhachSan(Long id, String ten, String diachi, String sodt, int cachtrungtam, String urlhinhanh, String mota,
			Boolean cachbien, @Max(5) @Min(0) int danhgia, String buaan, ThanhPho thanhpho, LoaiKhachSan loaikhachsan,
			Set<DanhGia> danhgias, Set<Phong> phong) {
		super();
		this.id = id;
		this.ten = ten;
		this.diachi = diachi;
		this.sodt = sodt;
		this.cachtrungtam = cachtrungtam;
		this.urlhinhanh = urlhinhanh;
		this.mota = mota;
		this.cachbien = cachbien;
		this.danhgia = danhgia;
		this.buaan = buaan;
		this.thanhpho = thanhpho;
		this.loaikhachsan = loaikhachsan;
		this.danhgias = danhgias;
		this.phong = phong;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSodt() {
		return sodt;
	}

	public void setSodt(String sodt) {
		this.sodt = sodt;
	}

	public int getCachtrungtam() {
		return cachtrungtam;
	}

	public void setCachtrungtam(int cachtrungtam) {
		this.cachtrungtam = cachtrungtam;
	}

	public String getUrlhinhanh() {
		return urlhinhanh;
	}

	public void setUrlhinhanh(String urlhinhanh) {
		this.urlhinhanh = urlhinhanh;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Boolean getCachbien() {
		return cachbien;
	}

	public void setCachbien(Boolean cachbien) {
		this.cachbien = cachbien;
	}

	public int getDanhgia() {
		return danhgia;
	}

	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}

	public String getBuaan() {
		return buaan;
	}

	public void setBuaan(String buaan) {
		this.buaan = buaan;
	}

	public ThanhPho getThanhpho() {
		return thanhpho;
	}

	public void setThanhpho(ThanhPho thanhpho) {
		this.thanhpho = thanhpho;
	}

	public LoaiKhachSan getLoaikhachsan() {
		return loaikhachsan;
	}

	public void setLoaikhachsan(LoaiKhachSan loaikhachsan) {
		this.loaikhachsan = loaikhachsan;
	}

	public Set<DanhGia> getDanhgias() {
		return danhgias;
	}

	public void setDanhgias(Set<DanhGia> danhgias) {
		this.danhgias = danhgias;
	}

	public Set<Phong> getPhong() {
		return phong;
	}

	public void setPhong(Set<Phong> phong) {
		this.phong = phong;
	}

}
