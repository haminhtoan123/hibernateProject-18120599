package Processing;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import BangDiem.BangDiemDAO;
import Import.JDBC;
import Mon.Mon;
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

		 /// JDBC.Connect();
	//	  Start();
		  
		// JDBC.ImportDSSV("E:\\hox hanh\\java\\Public-CQ2018-20200609T013918Z-001\\17HCB.csv", "17HCB");
	//	JDBC.ImportDSSV("E:\\hox hanh\\java\\Public-CQ2018-20200609T013918Z-001\\18HCB.csv", "18HCB");
	//	JDBC.ImportDSSV("file:/E:/hox%20hanh/Java/Public-CQ2018-20200609T013918Z-001/18HCB.csv", "18HCB");
//		//  Import.Import.ImportTKB("E:\\hox hanh\\java\\Public-CQ2018-20200609T013918Z-001\\18HCB-TKB.csv", session,transaction);
//		  SinhVien test = new SinhVien("1742001");
//		  test.setTenlop("17HCB");
//		  System.out.println(BangDiemDAO.CheckDangKi(test, "CTT013", session));
//		  BangDiemDAO.HuyDangKiMon(test, "CTT013", session, transaction);
	//  session.close();
			
			SessionFactory factory;
			 factory = HibernateUtil.getSessionFactory();
			 Session session = factory.openSession();
			 Transaction transaction =  session.beginTransaction();
			// -> lay ra mon vua add vao
			
		    String hql = "SELECT distinct(tenlop) FROM Mon M WHERE tenlop NOT IN (SELECT tenlop FROM BangDiem B)";
	        Query query = session.createQuery(hql);
	      //  long SL = (long) query.uniqueResult() ;
	        String TenLopVuaThem=  (String) query.uniqueResult();
			System.out.println(TenLopVuaThem);// Lop cua mon
			
			//List <Mon> MonVuaThem = MonDAO.LayDanhSachMonTheoLop(TenLopVuaThem);
			
			String hql2 ="FROM Mon WHERE tenlop =:tenlop";
			Query query2 = session.createQuery(hql2);
			query2.setParameter("tenlop",TenLopVuaThem);
			List <Mon> MonVuaThem= query2.list();
			System.out.println(MonVuaThem.size());
			
			 List<SinhVien> ds= SinhVienDAO.LayDanhSachSinhVienTheoLop(TenLopVuaThem);// ds lop cua TKB vua them
			 
			 
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
