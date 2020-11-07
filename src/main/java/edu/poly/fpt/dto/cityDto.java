package edu.poly.fpt.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public class cityDto implements Serializable{
	
	private Integer id;
	
	@NotNull
	@NotEmpty(message = "Vui lòng nhập tên")
	@Length(min = 0, max = 50, message = "Tên nằm ngoài phạm vi!")
	private String ten;
	
	@NotNull(message = "vui long nhập mô tả dịch vụ")
	private String mota;
	
	
	@NotNull(message = "vui lòng chọn ảnh")
	private MultipartFile photo;
	
	private String imageName;



	public cityDto() {
		super();
	}



	public cityDto(Integer id,
			@NotNull @NotEmpty(message = "Vui lòng nhập tên") @Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!") String ten,
			String mota, @NotNull(message = "vui lòng chọn ảnh") MultipartFile photo, String imageName) {
		super();
		this.id = id;
		this.ten = ten;
		this.mota = mota;
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

}
