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


@Entity
@Table(name = "tbdichvu")
public class DichVu implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column( columnDefinition = "nvarchar(100)")
	private String ten;
	
	@Column
	private Float giadv;
	
	@Column(columnDefinition = "nvarchar(200)")
	private String mota;
	
	@Column(length = 100)
	private String hinhanh;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "khachsanId")
	private KhachSan khachsan;
	

	public DichVu() {
		
	}


	public DichVu(Integer id, String ten, Float giadv, String mota, String hinhanh, KhachSan khachsan) {
		super();
		this.id = id;
		this.ten = ten;
		this.giadv = giadv;
		this.mota = mota;
		this.hinhanh = hinhanh;
		this.khachsan = khachsan;
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


	public Float getGiadv() {
		return giadv;
	}


	public void setGiadv(Float giadv) {
		this.giadv = giadv;
	}
	
	public String getMota() {
		return mota;
	}


	public void setMota(String mota) {
		this.mota = mota;
	}


	public String getHinhanh() {
		return hinhanh;
	}


	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}


	public KhachSan getKhachsan() {
		return khachsan;
	}


	public void setKhachsan(KhachSan khachsan) {
		this.khachsan = khachsan;
	}


}
