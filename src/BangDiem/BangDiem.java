package BangDiem;

import Mon.Mon;
import SinhVien.SinhVien;

public class BangDiem implements java.io.Serializable{
	private String mssv;
	private String tenlop;
	private String mamon;
	private String hoten;
	private float diemgk;
	private float diemck;
	private float diemkhac;
	private float diemtong;
	private SinhVien sinhVien;
	private Mon mon;
	
	BangDiem(String mssv,String hoten,String tenlop, String mamon)
	{
		this.mssv=mssv;
		this.hoten = hoten;
		this.tenlop = tenlop;
		this.mamon = mamon;
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
	public String getTenlop() {
		return tenlop;
	}
	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}
	public String getMamon() {
		return mamon;
	}
	public void setMamon(String mamon) {
		this.mamon = mamon;
	}
	public float getDiemgk() {
		return diemgk;
	}
	public void setDiemgk(float diemgk) {
		this.diemgk = diemgk;
	}
	public float getDiemck() {
		return diemck;
	}
	public void setDiemck(float diemck) {
		this.diemck = diemck;
	}
	public float getDiemkhac() {
		return diemkhac;
	}
	public void setDiemkhac(float diemkhac) {
		this.diemkhac = diemkhac;
	}
	public float getDiemtong() {
		return diemtong;
	}
	public void setDiemtong(float diemtong) {
		this.diemtong = diemtong;
	}
	public SinhVien getSinhVien() {
		return sinhVien;
	}
	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}
	public Mon getMon() {
		return mon;
	}
	public void setMon(Mon mon) {
		this.mon = mon;
	}
	
}
