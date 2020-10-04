package edu.poly.fpt.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbdatphong")
public class DatPhong implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngaydat;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngayden;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngaytra;

	@Column(columnDefinition = "nvarchar(50)")
	private String dichvu;

	@Column
	private int nguoilon;
	@Column
	private int trecon;

	@Column(columnDefinition = "nvarchar(200)")
	private String ghichu;

	@Column
	private Float thanhtien;

	@Column
	private Boolean dahuy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tentaikhoan", columnDefinition = "varchar(50)")
	private TaiKhoan taikhoan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phongId")
	private Phong phong;

	

	public DatPhong() {
		super();
	}



	public DatPhong(Integer id, Date ngaydat, Date ngayden, Date ngaytra, String dichvu, int nguoilon, int trecon,
			String ghichu, Float thanhtien, Boolean dahuy, TaiKhoan taikhoan, Phong phong) {
		super();
		this.id = id;
		this.ngaydat = ngaydat;
		this.ngayden = ngayden;
		this.ngaytra = ngaytra;
		this.dichvu = dichvu;
		this.nguoilon = nguoilon;
		this.trecon = trecon;
		this.ghichu = ghichu;
		this.thanhtien = thanhtien;
		this.dahuy = dahuy;
		this.taikhoan = taikhoan;
		this.phong = phong;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Date getNgaydat() {
		return ngaydat;
	}



	public void setNgaydat(Date ngaydat) {
		this.ngaydat = ngaydat;
	}



	public Date getNgayden() {
		return ngayden;
	}



	public void setNgayden(Date ngayden) {
		this.ngayden = ngayden;
	}



	public Date getNgaytra() {
		return ngaytra;
	}



	public void setNgaytra(Date ngaytra) {
		this.ngaytra = ngaytra;
	}



	public String getDichvu() {
		return dichvu;
	}



	public void setDichvu(String dichvu) {
		this.dichvu = dichvu;
	}



	public int getNguoilon() {
		return nguoilon;
	}



	public void setNguoilon(int nguoilon) {
		this.nguoilon = nguoilon;
	}



	public int getTrecon() {
		return trecon;
	}



	public void setTrecon(int trecon) {
		this.trecon = trecon;
	}



	public String getGhichu() {
		return ghichu;
	}



	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}



	public Float getThanhtien() {
		return thanhtien;
	}



	public void setThanhtien(Float thanhtien) {
		this.thanhtien = thanhtien;
	}



	public Boolean getDahuy() {
		return dahuy;
	}



	public void setDahuy(Boolean dahuy) {
		this.dahuy = dahuy;
	}



	public TaiKhoan getTaikhoan() {
		return taikhoan;
	}



	public void setTaikhoan(TaiKhoan taikhoan) {
		this.taikhoan = taikhoan;
	}



	public Phong getPhong() {
		return phong;
	}



	public void setPhong(Phong phong) {
		this.phong = phong;
	}

}
