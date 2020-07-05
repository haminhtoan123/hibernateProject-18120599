package SinhVien;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Mon.Mon;
import util.HibernateUtil;


public class SinhVienDAO {
	 private static SessionFactory factory;
	 public static boolean changePass(String MSSV,String mkCu,String mkMoi)
	 {
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 Transaction transaction = session.beginTransaction();
		 Query query = session.createQuery("SELECT mk FROM SinhVien WHERE mssv=:MSSV");
		 query.setString("MSSV", MSSV);
		 String mk = (String) query.uniqueResult();
		 if(mkCu.equals(mk))
		 {
			 query =session.createQuery("UPDATE SinhVien SET mk=:mkMoi WHERE mssv=:MSSV");
			 query.setString("MSSV", MSSV);
			 query.setString("mkMoi", mkMoi);
			 query.executeUpdate();
			 transaction.commit();
			 session.close();
			 
			 return true;
		 }
		 else
			 { 
			 session.close();
			 return false;
			 }
		 
		
	 }
	 public static String LayLopSV(String MSSV)
	 {

		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 Query query = session.createQuery("SELECT tenlop FROM SinhVien WHERE  MSSV=:MSSV");
		 query.setString("MSSV", MSSV);
		 String rt=(String) query.uniqueResult();
		 session.close();
		 return rt;
	 }
	 public static boolean CertifyUser(String id,String pass)
	 {
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 Query query = session.createQuery("FROM SinhVien WHERE  MSSV=:id AND mk =:pass");
		 query.setString("id", id);
		 query.setString("pass",pass);
		 if(query.uniqueResult()==null)
			 
		 return false;
		 else
			 return true;
	 }
	 public static String[][] LayDSSVTheoLop(String tenlop)
	 {

		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<SinhVien> ds= null;
		 try {
			 
	    		//ds = session.createQuery("FROM Mon WHERE tenlop =:tenlop").list();
	    		String hql ="FROM SinhVien WHERE tenlop =:tenlop";
	    		Query query = session.createQuery(hql);
	    		query.setParameter("tenlop",tenlop);
	    		ds = query.list();
	    		
	    	} catch (HibernateException e) {
	        e.printStackTrace();
	    	}
		 
		 String rt[][] = new String[ds.size()][4];
		 for(int i=0; i<ds.size();i++)
		 {
			 rt[i][0] = ds.get(i).getMssv();
			 rt[i][1] = ds.get(i).getHoten();
			 rt[i][2] = ds.get(i).getGioitinh();
			 rt[i][3] = ds.get(i).getCmnd();
		 }
		return rt;
		 
	 }
	 public static List<SinhVien> LayDanhSachSinhVienTheoLop(String tenlop)
	 {
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<SinhVien> ds= null;
		 try {
			 
	    		//ds = session.createQuery("FROM Mon WHERE tenlop =:tenlop").list();
	    		String hql ="FROM SinhVien WHERE tenlop =:tenlop";
	    		Query query = session.createQuery(hql);
	    		query.setParameter("tenlop",tenlop);
	    		ds = query.list();
	    		
	    	} catch (HibernateException e) {
	        e.printStackTrace();
	    	}
		 
		
		return ds;
	 }
	 public static String ThemSinhVien(SinhVien sv)
	 {
			//if(1) {};-> catch except
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 
		 String hql1= "SELECT DISTINCT tenlop FROM SinhVien S ";
		 Query query  = session.createQuery(hql1);
		 List <String> dslop = query.list();
		 if(dslop.contains(sv.getTenlop()))
		 {
			 String hql = "SELECT mssv FROM SinhVien S ";
		        query = session.createQuery(hql);
		      //  long SL = (long) query.uniqueResult() ;
		        List<String> ds = query.list();
		        
		        if(!ds.contains(sv.getMssv()))
		        {
		        	  
		   		 Transaction transaction= null ;
		   			try {
		   				transaction = session.beginTransaction(); 
		   				
		   				session.save(sv);
		   				transaction.commit();
		   				session.close();
		   			}catch(HibernateException ex)
		   			{
		   				 transaction.rollback();       
		   				 System.err.println(ex); 
		   				 return "Lỗi hệ thống";
		   			}
		   		return "Thành Công";
		        }
		        else
		        	return "Sinh Viên đẫ tồn tại trong Lớp";
		 }
		 else return "Lớp Không tồn tại!!!";
	  
		 
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

