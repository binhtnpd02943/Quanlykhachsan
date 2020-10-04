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

public class serviceDto {

	
	private Integer id;
	
	@NotNull
	@NotEmpty(message = "Vui lòng nhập tên!")
	@Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!")
	private String ten;
	
	@NotNull(message = "Vui lòng nhập giá!")
	private Float giadv;
	
	
	@NotNull(message = "Vui lòng nhập khách sạn!")
	private Long khachsanId;


	public serviceDto() {
		super();
	}


	public serviceDto(Integer id,
			@NotNull @NotEmpty(message = "Vui lòng nhập tên!") @Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!") String ten,
			@NotNull(message = "Vui lòng nhập giá!") Float giadv,
			@NotNull(message = "Vui lòng nhập khách sạn!") Long khachsanId) {
		super();
		this.id = id;
		this.ten = ten;
		this.giadv = giadv;
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


	public Float getGiadv() {
		return giadv;
	}


	public void setGiadv(Float giadv) {
		this.giadv = giadv;
	}


	public Long getKhachsanId() {
		return khachsanId;
	}


	public void setKhachsanId(Long khachsanId) {
		this.khachsanId = khachsanId;
	}


}
