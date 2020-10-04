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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "khachsanId")
	private KhachSan khachsan;
	

	public DichVu() {
		
	}


	public DichVu(Integer id, String ten, Float giadv, KhachSan khachsan) {
		super();
		this.id = id;
		this.ten = ten;
		this.giadv = giadv;
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


	public KhachSan getKhachsan() {
		return khachsan;
	}


	public void setKhachsan(KhachSan khachsan) {
		this.khachsan = khachsan;
	}


}
