package Import;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import BangDiem.BangDiemDAO;
import Mon.Mon;
import Mon.MonDAO;
import Processing.Test;
import SinhVien.SinhVien;
import SinhVien.SinhVienDAO;

public class Import {
	public static void ImportTKB(String link,Session session, Transaction transaction)
	{
		
		//JDBC.ImportTKB(link);
		//JDBC.Close();
		// - > lay ra sinh vien cua lop do
		
		// -> lay ra mon vua add vao
		
	    String hql = "SELECT distinct(tenlop) FROM Mon M WHERE tenlop NOT IN (SELECT tenlop FROM BangDiem B)";
        Query query = session.createQuery(hql);
      //  long SL = (long) query.uniqueResult() ;
        String TenLopVuaThem=  (String) query.uniqueResult();
		System.out.println(TenLopVuaThem);// Lop cua mon
		
		List <Mon> MonVuaThem = MonDAO.LayDanhSachMonTheoLop(TenLopVuaThem, session);
		System.out.println(MonVuaThem.size());
	
		 List<SinhVien> ds= SinhVienDAO.LayDanhSachSinhVienTheoLop(TenLopVuaThem, session);// ds lop cua TKB vua them
		 
		 
	    for(int i=0;i<ds.size();i++)
	    {
	    	System.out.println("\n Ho ten: " + ds.get(i).getHoten()+ "\n MSSV: " + ds.get(i).getMssv() );
	    	for(int j=0;j<MonVuaThem.size();j++)
	    	{
	    	System.out.println("\nMa Mon: " +  MonVuaThem.get(j).getMamon() +" \n TenLop: "+ MonVuaThem.get(j).getTenlop());
	    	BangDiemDAO.DangKiMon(ds.get(i), MonVuaThem.get(j), session,transaction);
	    	}
	    }
	}
}
