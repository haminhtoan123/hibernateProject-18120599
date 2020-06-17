package Dangki;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Mon.Mon;
import util.HibernateUtil;

public class DangkiDAO {
	private static SessionFactory factory;
	 public static List<Dangki> layDanhSachDangki() 
	 {    
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<Dangki> ds= null;
   	try {
   		ds = session.createQuery("FROM Dangki").list();
      
   	} catch (HibernateException e) {
       e.printStackTrace();
   	} finally {
       session.close();
   	}
		return ds;
	}
	 /* public static void listSinhVien() {
		  factory = HibernateUtil.getSessionFactory();
	        Session session = factory.openSession();
	        try {
	            List Mons = session.createQuery("FROM Mon").list();
	            for (Iterator iterator = Mons.iterator(); iterator.hasNext();) {
	                Mon mon = (Mon) iterator.next();
	                System.out.println("Phong hoc: " + mon.getPhonghoc());
	            }
	        } catch (HibernateException e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }*/
	  public static void main(String args[])
	  {
		
		  List<Dangki> temp = layDanhSachDangki() ;
		  for(int i=0;i<temp.size();i++)
		  {
			  System.out.println(" MSSV: "+ temp.get(i).getSinhVien().getMssv()+"\n");
		  }
		 // listSinhVien();
	  }
}
