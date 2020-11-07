package edu.poly.fpt.dto;

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

public class serviceDto {

	
	private Integer id;
	
	@NotNull
	@NotEmpty(message = "Vui lòng nhập tên!")
	@Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!")
	private String ten;
	
	@NotNull(message = "Vui lòng nhập giá!")
	private Float giadv;
	
	@NotNull(message = "Vui lòng nhập mô tả dịch vụ!")
	private  String mota;
	
	
	@NotNull(message = "Vui lòng nhập khách sạn!")
	private Long khachsanId;
	
	@NotNull(message = "vui long chon anh")
	private MultipartFile photo;
	
	private String imageName;


	public serviceDto() {
		super();
	}


	public serviceDto(Integer id,
			@NotNull @NotEmpty(message = "Vui lòng nhập tên!") @Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!") String ten,
			@NotNull(message = "Vui lòng nhập giá!") Float giadv,
			@NotNull(message = "Vui lòng nhập mô tả dịch vụ") String mota,
			@NotNull(message = "Vui lòng nhập khách sạn!") Long khachsanId,
			@NotNull(message = "vui long chon anh") MultipartFile photo, String imageName) {
		super();
		this.id = id;
		this.ten = ten;
		this.giadv = giadv;
		this.mota = mota;
		this.khachsanId = khachsanId;
		this.photo = photo;
		this.imageName = imageName;
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


	public Long getKhachsanId() {
		return khachsanId;
	}


	public void setKhachsanId(Long khachsanId) {
		this.khachsanId = khachsanId;
	}
	
	



	

}
