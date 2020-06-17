package BangDiem;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Mon.Mon;
import SinhVien.SinhVien;
import util.HibernateUtil;

public class BangDiemDAO {
	private static SessionFactory factory;
	 public static List<BangDiem> layDanhBangDiem() 
	 {    
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<BangDiem> ds= null;
   	try {
   		ds = session.createQuery("FROM BangDiem").list();
      
   	} catch (HibernateException e) {
       e.printStackTrace();
   	} finally {
       session.close();
   	}
		return ds;
	}
	 public static void main(String args[])
	  {
		
		 List<BangDiem> temp = layDanhBangDiem() ;
		  for(int i=0;i<temp.size();i++)
		  {
			  System.out.println(" MSSV: "+ temp.get(i).getMssv()+"\n");
			  System.out.println(" Mon : "+ temp.get(i).getMamon()+"\n");
			  System.out.println(" Lop: "+ temp.get(i).getTenlop()+"\n");
			  System.out.println(" DiemGk : "+ temp.get(i).getDiemgk()+"\n");
			  System.out.println(" CMND : " + temp.get(i).getSinhVien().getCmnd()+ "\n");
		  }
		 // listSinhVien();
	  }
}
