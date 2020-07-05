package Mon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import BangDiem.BangDiem;
import SinhVien.SinhVien;
import util.HibernateUtil;

public class MonDAO {
	//private static SessionFactory factory;
//	public static List<String> LayDSAllLop()
//	{
//		List<String> rt = new ArrayList<String>();
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//		 Session session = factory.openSession();
//		 List<Mon> ds= null;
//	    	try {
//	    		ds = session.createQuery("FROM Mon").list();
//	       
//	    	} catch (HibernateException e) {
//	        e.printStackTrace();
//	    	} finally {
//	        session.close();
//	    	}
//	    for(int i=0;i<ds.size();i++)
//	    {
//	    	rt.add(ds.get(i).getTenlop() +"-"+ ds.get(i).getMamon());
//	    }
//		return rt;
//	}
	public static List<String> layMonSVChuaDki(String MSSV)
	{

		SessionFactory factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		List<Mon> ds = null;
		List<String> rt= new ArrayList<String>();
		try {
			 
   		//ds = session.createQuery("FROM Mon WHERE tenlop =:tenlop").list();
   		String hql ="FROM Mon M WHERE  M.mamon NOT IN(SELECT D.mamon FROM BangDiem D WHERE D.mssv =:MSSV)";
   		Query query = session.createQuery(hql);
   		//query.setString("tenlop",tenlop);
   		query.setString("MSSV",MSSV);
   		ds = query.list();
   		if(ds.isEmpty()) return null;
   	} catch (HibernateException e) {
       e.printStackTrace();
       return null;
   	}
		System.out.println(ds.size());
		for(int i=0;i<ds.size();i++)
		{
			String temp =ds.get(i).getTenlop()+"-" + ds.get(i).getMamon() ;
			rt.add(temp);
		}
		
		return rt;
	}
	public static String[][] LayTKB(String tenlop)
	{
		SessionFactory factory;
		String rt[][] = null;
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<Mon> ds= null;
	   	try {
	   		Query query = session.createQuery("FROM Mon B WHERE B.tenlop =:tenlop ");
	   	 query.setString("tenlop", tenlop);
	   	 ds=query.list();
	   	} catch (HibernateException e) {
	       e.printStackTrace(); 
	   	} finally {
	       session.close();
	   	}
		
	 	rt = new String [ds.size()][3];
	   	for(int i=0;i<ds.size();i++)
	   	{
	   		//System.out.println(ds.get(i).getMssv());
	   		//rt[i][0]=ds.get(i).getTenlop();
	   		rt[i][0] = ds.get(i).getMamon();
	   		rt[i][1] = ds.get(i).getTenmon();
	   		rt[i][2] = ds.get(i).getPhonghoc();
	   		
	   	}
	   	return rt;
	}
	
	 public static List<String> ListMonToString(List<Mon> ls)// Lay Ma mon
	 {
		 List<String> ds = new Vector();
		 if(ls==null) return null;
		 for(int i=0;i<ls.size();i++)
		 {
			 ds.add(ls.get(i).getMamon());
		 }
		 return ds;
		 
	 }
	 public static List<Mon> LayDanhSachMonTheoLop(String tenlop)
	 {	
			
		 SessionFactory factory;
		
		 factory = HibernateUtil.getSessionFactory();
		 
		 Session session = factory.openSession();
		 List<Mon> ds= null;
		 try {
			 
	    		//ds = session.createQuery("FROM Mon WHERE tenlop =:tenlop").list();
	    		String hql ="FROM Mon WHERE tenlop =:tenlop";
	    		Query query = session.createQuery(hql);
	    		query.setParameter("tenlop",tenlop);
	    		ds = query.list();
	    		
	    	} catch (HibernateException e) {
	        e.printStackTrace();
	        return null;
	    	}
		 	finally
		 	{
		 		session.close();
		 	}
		//session.close();
		return ds;
		 
	 }
	 
	 public static List<Mon> layDanhSachMon() 
	 {    
		 SessionFactory factory;
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
	 
	  public static void main(String args[])
	  {
		System.out.println(layMonSVChuaDki("1742001").get(0));
	  }
}
