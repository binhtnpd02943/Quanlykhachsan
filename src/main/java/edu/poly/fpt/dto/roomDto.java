package edu.poly.fpt.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.fpt.entities.KhachSan;

public class roomDto implements Serializable{

	private Integer id;
	
	
	@NotNull
	@NotEmpty(message = "Vui lòng nhập tên!")
	@Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!")
	private String ten;
	
	@NotNull(message = "Vui lòng nhập diện tích")
	private Float dientich;
	
	@NotNull(message = "Vui lòng nhập giá thuê!")
	private Float giathue;
	
	@NotNull
	@NotEmpty(message = "Vui lòng nhập tiện nghi!")
	private String tiennghi;
	
	
	private Boolean loaigiuong;
	
	@NotNull(message = "vui lòng chọn ảnh!")
	private MultipartFile photo;
	
	private String imageName;
	
	private String mota;

	@NotNull(message = "Vui lòng nhập khách sạn!")
	private Long khachsanId;

	public roomDto() {
		super();
	}

	public roomDto(Integer id,
			@NotNull @NotEmpty(message = "Vui lòng nhập tên!") @Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!") String ten,
			@NotNull(message = "Vui lòng nhập diện tích") Float dientich,
			@NotNull(message = "Vui lòng nhập giá thuê!") Float giathue,
			@NotNull @NotEmpty(message = "Vui lòng nhập tiện nghi!") String tiennghi, Boolean loaigiuong,
			@NotNull(message = "vui lòng chọn ảnh!") MultipartFile photo, String imageName, String mota,
			@NotNull(message = "Vui lòng nhập khách sạn!") Long khachsanId) {
		super();
		this.id = id;
		this.ten = ten;
		this.dientich = dientich;
		this.giathue = giathue;
		this.tiennghi = tiennghi;
		this.loaigiuong = loaigiuong;
		this.photo = photo;
		this.imageName = imageName;
		this.mota = mota;
		this.khachsanId = khachsanId;
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

	public Long getKhachsanId() {
		return khachsanId;
	}

	public void setKhachsanId(Long khachsanId) {
		this.khachsanId = khachsanId;
	}

}
