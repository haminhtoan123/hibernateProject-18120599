package Mon;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


//@Embeddable


//@Entity(name = "Mon")
//@Table(name = "Mon")
public class Mon implements java.io.Serializable{
	// @EmbeddedId
	//private monId id;
	
	//@Column(name = "TenMon")
	private String mamon;
	private String tenlop;
	private String tenmon;
	//@Column(name = "PhongHoc")
	private String phonghoc;
	public Mon() {}
	public Mon(String mamon,String tenlop)
	{
		this.mamon = mamon;
		this.tenlop = tenlop;
	}
	public String getTenmon() {
		return tenmon;
	}
	public void setTenmon(String tenmon) {
		this.tenmon = tenmon;
	}
	public String getPhonghoc() {
		return phonghoc;
	}
	public void setPhonghoc(String phonghoc) {
		this.phonghoc = phonghoc;
	}
	public String getMamon() {
		return mamon;
	}
	public void setMamon(String mamon) {
		this.mamon = mamon;
	}
	public String getTenlop() {
		return tenlop;
	}
	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}

	
}

