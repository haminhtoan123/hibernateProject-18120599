package Mon;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import SinhVien.SinhVien;
import util.HibernateUtil;

public class MonDAO {
	private static SessionFactory factory;
	 public static List<Mon> layDanhSachMon() 
	 {    
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<Mon> ds= null;
    	try {
    		ds = session.createQuery("FROM Mon").list();
       
    	} catch (HibernateException e) {
        e.printStackTrace();
    	} finally {
        session.close();
    	}
		return ds;
	}
	  public static void listSinhVien() {
		  factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        try {
	            List Mons = session.createQuery("FROM Mon").list();
	            for (Iterator iterator = Mons.iterator(); iterator.hasNext();) {
	                Mon mon = (Mon) iterator.next();
	                System.out.println("Phong hoc: " + mon.getPhongHoc());
	            }
	        } catch (HibernateException e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }
	  public static void main(String args[])
	  {
		  listSinhVien();
	  }
}
