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

import Dangki.Dangki;

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
	/*public Mon(monId id, String tenmon,String phonghoc) {
		setId(id);
		this.tenmon=tenmon;
		this.phonghoc=phonghoc;
	}*/
	/*public monId getId() {
		return id;
	}
	public void setId(monId id) {
		this.id = id;
	}*/
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

