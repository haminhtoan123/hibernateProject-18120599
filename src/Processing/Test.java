package Processing;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Import.JDBC;
import SinhVien.*;
import util.HibernateUtil;

public class Test {
	private static SessionFactory factory;
	private static  Transaction transaction;
	private static Session session;
	public static void Start()
	{
		factory = HibernateUtil.getSessionFactory();
		 session = factory.openSession();
		 transaction = session.beginTransaction(); 
		  //NEED TO CLOSE SESSION
	}
	  public static void main(String args[])
	  {
		  //Start();
		 // SessionFactory 
		  //Session session = factory.openSession();
		 // Transaction transaction=null;
		  JDBC.Connect();
		  JDBC.ImportDSSV("E:\\hox hanh\\java\\Public-CQ2018-20200609T013918Z-001\\18HCB.csv", "17HCB");
		 /* try {
			  Start();
			 // transaction= session.beginTransaction();
			  SinhVien temp= new SinhVien("12123","HMT","Nam","0123");
			session.save(temp);
			transaction.commit();
		  } catch(HibernateException ex)
			{
				 transaction.rollback();       
				 System.err.println(ex); 
			}
		  finally {
		
		  session.close();}
		  */
	  }
	  
}
