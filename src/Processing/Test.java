package Processing;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import BangDiem.BangDiemDAO;
import Import.JDBC;
import SinhVien.*;
import util.HibernateUtil;

public class Test {
	private static SessionFactory factory;
	public static  Transaction transaction;
	private static Session session;
	public static void Start()
	{
		factory = HibernateUtil.getSessionFactory();
		 session = factory.openSession();
		// transaction = session.beginTransaction(); 
		  //NEED TO CLOSE SESSION
	}
	  public static void main(String args[])
	  {

		  JDBC.Connect();
		  Start();
		  
		// JDBC.ImportDSSV("E:\\hox hanh\\java\\Public-CQ2018-20200609T013918Z-001\\17HCB.csv", "17HCB");
		//  JDBC.ImportDSSV("E:\\hox hanh\\java\\Public-CQ2018-20200609T013918Z-001\\18HCB.csv", "18HCB");
		  
		//  Import.Import.ImportTKB("E:\\hox hanh\\java\\Public-CQ2018-20200609T013918Z-001\\18HCB-TKB.csv", session,transaction);
		  SinhVien test = new SinhVien("1742001");
		  test.setTenlop("17HCB");
		  System.out.println(BangDiemDAO.CheckDangKi(test, "CTT013", session));
		  BangDiemDAO.HuyDangKiMon(test, "CTT013", session, transaction);
		  session.close();
		
	  }
	  
}
