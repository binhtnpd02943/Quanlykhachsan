package edu.poly.fpt.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.fpt.entities.LoaiKhachSan;
import edu.poly.fpt.entities.ThanhPho;

public class hotelDto implements Serializable{

	
	private Long id;
	
	@NotNull
	@NotEmpty(message = "Vui lòng nhập tên")
	@Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!")
	private String ten;
	
	@NotNull(message = "vui lòng nhập địa chỉ!")
	private String diaChi;
	
	@NotNull(message = "vui lòng nhập SĐT!")
	private String sodt;
	
	@NotNull(message = "Vui lòng nhập khoảnng cách!")
	private int cachtrungtam;
	
	@NotNull(message = "vui lòng chọn ảnh!")
	private MultipartFile photo;
	
	private String imageName;
	
	private String mota;
	
	
	private Boolean cachbien;
	
	
	private int danhgia;
	
	@NotNull(message = "vui lòng nhập bữa ăn!")
	private String buaan;
	
	
	private Integer thanhphoId;

	
	private Integer loaiKhachSanId;


	public hotelDto() {
		super();
	}


	public hotelDto(Long id,
			@NotNull @NotEmpty(message = "Vui lòng nhập tên") @Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!") String ten,
			@NotNull(message = "vui lòng nhập địa chỉ!") String diaChi,
			@NotNull(message = "vui lòng nhập SĐT!") String sodt,
			@NotNull(message = "Vui lòng nhập khoảnng cách!") int cachtrungtam,
			@NotNull(message = "vui lòng chọn ảnh!") MultipartFile photo, String imageName, String mota,
			Boolean cachbien, int danhgia, @NotNull(message = "vui lòng nhập bữa ăn!") String buaan, Integer thanhphoId,
			Integer loaiKhachSanId) {
		super();
		this.id = id;
		this.ten = ten;
		this.diaChi = diaChi;
		this.sodt = sodt;
		this.cachtrungtam = cachtrungtam;
		this.photo = photo;
		this.imageName = imageName;
		this.mota = mota;
		this.cachbien = cachbien;
		this.danhgia = danhgia;
		this.buaan = buaan;
		this.thanhphoId = thanhphoId;
		this.loaiKhachSanId = loaiKhachSanId;
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


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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


	public MultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
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


	public Integer getThanhphoId() {
		return thanhphoId;
	}


	public void setThanhphoId(Integer thanhphoId) {
		this.thanhphoId = thanhphoId;
	}


	public Integer getLoaiKhachSanId() {
		return loaiKhachSanId;
	}


	public void setLoaiKhachSanId(Integer loaiKhachSanId) {
		this.loaiKhachSanId = loaiKhachSanId;
	}

}
