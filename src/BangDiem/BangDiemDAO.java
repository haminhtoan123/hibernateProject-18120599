package BangDiem;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Mon.Mon;
import SinhVien.SinhVien;
import util.HibernateUtil;

public class BangDiemDAO {
	private static SessionFactory factory;
	public static void DangKiMon(SinhVien sv, Mon mon, Session session,Transaction transaction)
	{
		BangDiem temp = new BangDiem(sv.getMssv(),sv.getHoten() ,mon.getTenlop(), mon.getMamon());
		
		//System.out.println(sv.getMssv()+sv.getHoten() + mon.getMamon()+mon.getTenlop());
		//Transaction transaction = session.beginTransaction();
		//transaction= null;
		
		try {
		transaction = session.beginTransaction();
		session.save(temp);
		transaction.commit();
		}
		catch (HibernateException ex)
		{
			 transaction.rollback();         
			 System.err.println(ex);
		}
		
	}
	

	public static Boolean HuyDangKiMon(SinhVien sv,String mamon,Session session, Transaction transaction)
	{
		// check sv co dki mamon (dung join)
		if (CheckDangKi(sv,mamon,session) == false)
		{
			return false;
		}
		try {
		transaction = session.beginTransaction();
		 String hql = "delete from BangDiem where mssv = :mssv AND tenlop= :tenlop AND mamon = :mamon";
		 Query query = session.createQuery(hql);
		 query.setString("mssv", sv.getMssv());
		 query.setString("tenlop", sv.getTenlop());
		 query.setString("mamon", mamon);
		 query.executeUpdate();
		 
		//session.delete(temp);
		transaction.commit();
		return true;
		}
		catch (HibernateException ex)
		{
			 transaction.rollback();         
			 System.err.println(ex);
			 return false;
		}
		
	}
	public static boolean CheckDangKi(SinhVien sv,String mamon,Session session)
	{
	//	boolean rt=false;
		
		
	    String hql = "SELECT mamon FROM Mon M WHERE M.mamon IN (SELECT B.mamon FROM BangDiem B, SinhVien S WHERE B.mssv =S.mssv)";
        Query query = session.createQuery(hql);
      //  long SL = (long) query.uniqueResult() ;
        List<String> ds = query.list();
        return ds.contains(mamon);
		
	}
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
		
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 BangDiem temp = new BangDiem("1742001","Nguyễn Văn A","17HCB","CTT013");
			Transaction transaction = null; 
			try {
			transaction = session.beginTransaction();
			session.save(temp);
			transaction.commit();
			}
			catch (HibernateException ex)
			{
				 transaction.rollback();         
				 System.err.println(ex);
			}
		 // listSinhVien();
	  }
}
