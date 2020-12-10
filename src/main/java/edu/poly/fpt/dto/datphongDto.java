package edu.poly.fpt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import edu.poly.fpt.entities.DichVu;
import edu.poly.fpt.entities.Phong;
import edu.poly.fpt.entities.TaiKhoan;

public class datphongDto implements Serializable {  
    
    private Integer id;

	
		
	private Date ngaydat;
    
	@NotNull(message = "Vui lòng chọn ngày đến")
	private Date ngayden;

	
	@NotNull(message = "Vui lòng chọn ngày trả")
	private Date ngaytra;

	
	@NotNull(message = "Vui lòng chọn dịch vụ")
	private String dichvu;

	@NotNull(message = "Vui lòng nhập số phòng")
	private int sophong;

	
	@NotNull(message = "Vui lòng nhập số người lớn")
	private int nguoilon;
	
	@NotNull(message = "Vui lòng nhập số trẻ con")
    private int trecon;
    
    @NotNull
	@NotEmpty(message = "Vui lòng nhập tên")
	@Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!")
	private String ten;
	
	
	
	@NotNull(message = "Vui lòng nhập CMND")
	private String cmt;

	
	private String ghichu;

	
	private Float thanhtien;

	
	private Boolean dahuy;

	
	private String tentaikhoan;

	
    private Integer id_phong;

    public datphongDto() {
        super();
    }
    public datphongDto(Integer id, Date ngaydat, @NotNull(message = "Vui lòng chọn ngày đến") Date ngayden,
            @NotNull(message = "Vui lòng chọn ngày trả") Date ngaytra,
            @NotNull(message = "Vui lòng chọn dịch vụ") String dichvu,
            @NotNull(message = "Vui lòng nhập số phòng") int sophong,
            @NotNull(message = "Vui lòng nhập số người lớn") int nguoilon,
            @NotNull(message = "Vui lòng nhập số trẻ con") int trecon,
            @NotNull @NotEmpty(message = "Vui lòng nhập tên") @Length(min = 5, max = 50, message = "Tên nằm ngoài phạm vi!") String ten,
            @NotNull(message = "Vui lòng nhập CMND") String cmt, String ghichu, Float thanhtien, Boolean dahuy,
            String tentaikhoan, Integer id_phong) {
        this.id = id;
        this.ngaydat = ngaydat;
        this.ngayden = ngayden;
        this.ngaytra = ngaytra;
        this.dichvu = dichvu;
        this.sophong = sophong;
        this.nguoilon = nguoilon;
        this.trecon = trecon;
        this.ten = ten;
        this.cmt = cmt;
        this.ghichu = ghichu;
        this.thanhtien = thanhtien;
        this.dahuy = dahuy;
        this.tentaikhoan = tentaikhoan;
        this.id_phong = id_phong;
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

    public int getSophong() {
        return sophong;
    }

    public void setSophong(int sophong) {
        this.sophong = sophong;
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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
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

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public Integer getId_phong() {
        return id_phong;
    }

    public void setId_phong(Integer id_phong) {
        this.id_phong = id_phong;
    }

    
    

    
    

    
    

    
}
