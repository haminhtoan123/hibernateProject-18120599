package SinhVien;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;


public class SinhVienDAO {
	 private static SessionFactory factory;
	 public static boolean ThemSinhVien(SinhVien sv, Session session,Transaction transaction)
	 {
			//if(1) {};-> catch except
			try {
				transaction = session.beginTransaction(); 
				session.save(sv);
				transaction.commit();
			}catch(HibernateException ex)
			{
				 transaction.rollback();       
				 System.err.println(ex); 
			}
		return true;
		 
	 }
	 public static List<SinhVien> layDanhSachSinhVien() 
	 {    
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<SinhVien> ds= null;
     	try {
     		ds = session.createQuery("FROM SinhVien").list();
        
     	} catch (HibernateException e) {
         e.printStackTrace();
     	} finally {
         session.close();
     	}
		return ds;
	}
	  public void listSinhVien() {
		  factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        try {
	            List SVs = session.createQuery("FROM SinhVien").list();
	            for (Iterator iterator = SVs.iterator(); iterator.hasNext();) {
	                SinhVien sv = (SinhVien) iterator.next();
	                System.out.print("First Name: " + sv.getHoten());
	                System.out.print("  Last Name: " + sv.getCmnd());
	                System.out.println("  Salary: " + sv.getGioitinh());
	            }
	        } catch (HibernateException e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }

}

