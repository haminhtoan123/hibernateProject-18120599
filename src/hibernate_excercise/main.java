package hibernate_excercise;

import java.util.List;

import SinhVien.SinhVien;
import SinhVien.SinhVienDAO;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!!");
	       
	    	//<editor-fold defaultstate="collapsed" desc="1. Lấy danh sách sinh viên">         
	    	List<SinhVien> ds=SinhVienDAO.layDanhSachSinhVien();        
	    	for(int i=0; i<ds.size(); i++){             
	    		SinhVien sv=ds.get(i);             
	    		System.out.println("MSSV: "+sv.getMssv());             
	    		System.out.println("Họ và tên: "+sv.getHoten());            
	    		System.out.println("Gioi Tinh: " + sv.getGioitinh());             
	    		System.out.println("Lop : "+ sv.getTenlop());         
	    		}
	    	/*SinhVienDAO test = new SinhVienDAO();
	    	test.listSinhVien();*/
	}

}
