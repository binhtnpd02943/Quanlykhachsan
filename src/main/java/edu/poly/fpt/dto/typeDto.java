package edu.poly.fpt.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public class typeDto implements Serializable{
private Integer id;
	
	@NotNull
	@NotEmpty(message = "Vui lòng nhập tên")
	@Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!")
	private String ten;
	
	
	private String mota;
	
	
	@NotNull(message = "vui long chon anh")
	private MultipartFile photo;
	
	private String imageName;

	public typeDto() {
		super();
	}

	public typeDto(Integer id,
			@NotNull @NotEmpty(message = "Vui lòng nhập tên") @Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!") String ten,
			String mota, @NotNull(message = "vui long chon anh") MultipartFile photo, String imageName) {
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

