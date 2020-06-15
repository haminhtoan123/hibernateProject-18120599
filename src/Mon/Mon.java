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
class monId implements Serializable 
{
	//@Column(name = "MaMon")
	private String mamon;
	//@Column(name = "TenLop")
	private String tenLop;
	
	public monId() {}
	public monId(String mamon, String tenLop) {
		this.mamon=mamon;
		this.tenLop = tenLop;
	}
	public String getMamon() {
		return mamon;
	}
	public void setMamon(String mamon) {
		this.mamon = mamon;
	}
	public String getTenLop() {
		return tenLop;
	}
	
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof monId)) return false;
        monId that = (monId) o;
        return Objects.equals(getMamon(), that.getMamon()) &&
                Objects.equals(getTenLop(), that.getTenLop());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMamon(), getTenLop());
    }
}

//@Entity(name = "Mon")
//@Table(name = "Mon")
public class Mon implements java.io.Serializable{
	// @EmbeddedId
	private monId id;
	
	//@Column(name = "TenMon")
	//private String mamon;
	//private String tenLop;
	private String tenMon;
	//@Column(name = "PhongHoc")
	private String phongHoc;
	
	public Mon() {}
	public Mon(String mamon, String tenLop, String tenMon,String phonghoc) {}
	public monId getId()
	{
		return id;
	}
	public void setId(monId id)
	{
		this.id = id;
	}
	//@Column(name = "TenMon", length = 50)
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	//@Column(name = "PhongHoc", length = 3)
	public String getPhongHoc() {
		return phongHoc;
	}
	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}
	/*public String getMamon() {
		return mamon;
	}
	public void setMamon(String mamon) {
		this.mamon = mamon;
	}
	public String getTenLop() {
		return tenLop;
	}
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	*/
}

