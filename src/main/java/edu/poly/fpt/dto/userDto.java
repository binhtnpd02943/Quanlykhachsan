package edu.poly.fpt.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class userDto {
	@NotBlank
	private String tentaikhoan;

	@Size(min = 6, max = 25)
	private String matkhau;

	@Size(min = 6, max = 25)
	private String reMatkhau;

	@NotBlank
	private String hoten;

	@Column
	private Boolean gioitinh;

	@NotNull(message = "vui lòng nhập SĐT!")
	private String sodt;

	@NotNull(message = "Vui lòng nhập email!")
	private String email;

	@NotNull
	private String role;

	public userDto() {
		super();
	}

	public userDto(@NotBlank String tentaikhoan, @Size(min = 6, max = 25) String matkhau,
			@Size(min = 6, max = 25) String reMatkhau, @NotBlank String hoten, Boolean gioitinh,
			@NotNull(message = "vui lòng nhập SĐT!") String sodt,
			@NotNull(message = "Vui lòng nhập email!") String email, @NotNull String role) {
		super();
		this.tentaikhoan = tentaikhoan;
		this.matkhau = matkhau;
		this.reMatkhau = reMatkhau;
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

	public String getReMatkhau() {
		return reMatkhau;
	}

	public void setReMatkhau(String reMatkhau) {
		this.reMatkhau = reMatkhau;
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
