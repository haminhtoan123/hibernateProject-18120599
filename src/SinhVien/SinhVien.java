package SinhVien;

import java.awt.event.KeyEvent;


public class SinhVien   implements java.io.Serializable {
	private String mssv;
	private String hoten;
	private String gioitinh;
	private String cmnd;
	
	private String tenlop;
	/*
	private float diemGK;
	private float diemCK;
	private float diemKhac;*/
	private String mk;
	public SinhVien() {
		
	};
	public SinhVien(String mSSV)
	{
		this.mssv=mSSV;
	}
	public SinhVien(String mssv, String hoten, String gioitinh, String cmnd )
	{
		this.mssv=mssv;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.cmnd = cmnd;
	}
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setgioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getTenlop() {
		return tenlop;
	}
	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}
	public String getMk() {
		return mk;
	}
	public void setMk(String mk) {
		this.mk = mk;
	}

	
}
