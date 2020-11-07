package edu.poly.fpt.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tbloaikhachsan")
public class LoaiKhachSan implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column( columnDefinition = "nvarchar(100)")
	private String ten;
	
	@Column( columnDefinition = "nvarchar(255)")
	private String mota;
	
	@Column(length = 100)
	private String urlhinhanh;
	

	public LoaiKhachSan() {
		
	}


	public LoaiKhachSan(Integer id, String ten, String mota, String urlhinhanh) {
		super();
		this.id = id;
		this.ten = ten;
		this.mota = mota;
		this.urlhinhanh = urlhinhanh;
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


	public String getMota() {
		return mota;
	}


	public void setMota(String mota) {
		this.mota = mota;
	}


	public String getUrlhinhanh() {
		return urlhinhanh;
	}


	public void setUrlhinhanh(String urlhinhanh) {
		this.urlhinhanh = urlhinhanh;
	}


	

	
	

	
}
