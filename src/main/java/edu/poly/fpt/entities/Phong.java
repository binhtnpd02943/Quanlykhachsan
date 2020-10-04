package edu.poly.fpt.entities;

import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

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

@Entity
@Table(name = "tbphong")
public class Phong implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column( columnDefinition = "nvarchar(100)")
	private String ten;
	
	@Column
	private Float dientich;
	
	@Column
	private Float giathue;
	
	@Column( columnDefinition = "nvarchar(100)")
	private String tiennghi;
	
	@Column
	private Boolean loaigiuong;
	
	@Column(length = 100)
	private String urlhinhanh;
	
	@Column( columnDefinition = "nvarchar(255)")
	private String mota;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "khachsanId")
	private KhachSan khachsan;
	
	@OneToMany(mappedBy = "phong",cascade = CascadeType.ALL)
	private Set<DatPhong> datphong;

	public Phong() {
		super();
	}

	public Phong(Integer id, String ten, Float dientich, Float giathue, String tiennghi, Boolean loaigiuong,
			String urlhinhanh, String mota, KhachSan khachsan, Set<DatPhong> datphong) {
		super();
		this.id = id;
		this.ten = ten;
		this.dientich = dientich;
		this.giathue = giathue;
		this.tiennghi = tiennghi;
		this.loaigiuong = loaigiuong;
		this.urlhinhanh = urlhinhanh;
		this.mota = mota;
		this.khachsan = khachsan;
		this.datphong = datphong;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Float getDientich() {
		return dientich;
	}

	public void setDientich(Float dientich) {
		this.dientich = dientich;
	}

	public Float getGiathue() {
		return giathue;
	}

	public void setGiathue(Float giathue) {
		this.giathue = giathue;
	}

	public String getTiennghi() {
		return tiennghi;
	}

	public void setTiennghi(String tiennghi) {
		this.tiennghi = tiennghi;
	}

	public Boolean getLoaigiuong() {
		return loaigiuong;
	}

	public void setLoaigiuong(Boolean loaigiuong) {
		this.loaigiuong = loaigiuong;
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

	public KhachSan getKhachsan() {
		return khachsan;
	}

	public void setKhachsan(KhachSan khachsan) {
		this.khachsan = khachsan;
	}

	public Set<DatPhong> getDatphong() {
		return datphong;
	}

	public void setDatphong(Set<DatPhong> datphong) {
		this.datphong = datphong;
	}

}
