package edu.poly.fpt.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tbtaikhoan")
public class TaiKhoan implements Serializable {

	@Id
	@Length(min = 0, max = 50)
	private String tentaikhoan;

	@Length(min = 0, max = 60)
	private String matkhau;

	@Column(columnDefinition = "nvarchar(50)")
	private String hoten;

	@Column
	private Boolean gioitinh;

	@Column(length = 20)
	private String sodt;

	@Length(min = 0, max = 60)
	private String email;

	@Length(min = 0, max = 50)
	private String role;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(@Length(min = 0, max = 50) String tentaikhoan, @Length(min = 0, max = 60) String matkhau,
			String hoten, Boolean gioitinh, String sodt, @Length(min = 0, max = 60) String email,
			@Length(min = 0, max = 50) String role) {
		super();
		this.tentaikhoan = tentaikhoan;
		this.matkhau = matkhau;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.sodt = sodt;
		this.email = email;
		this.role = role;
	}

	public String getTentaikhoan() {
		return tentaikhoan;
	}

	public void setTentaikhoan(String tentaikhoan) {
		this.tentaikhoan = tentaikhoan;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public Boolean getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(Boolean gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getSodt() {
		return sodt;
	}

	public void setSodt(String sodt) {
		this.sodt = sodt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
