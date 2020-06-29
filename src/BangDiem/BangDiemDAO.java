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
	
	public static List<String> LayDSLop()
	{
		factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<String> ds= null;
	   	try {
	   		Query query = session.createQuery("SELECT DISTINCT tenlop FROM BangDiem B");
	   	 ds=query.list();
	   	} catch (HibernateException e) {
	       e.printStackTrace(); 
	   	} finally {
	       session.close();
	   	}
		
		return ds;
		
	}
	public static String[][] XemDiemSV(String MSSV)
	{
		String rt[][] = null;
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<BangDiem> ds= null;
	   	try {
	   		Query query = session.createQuery("FROM BangDiem B WHERE B.mssv =:MSSV ");
	   	 query.setString("MSSV", MSSV);
	   	 ds=query.list();
	   	} catch (HibernateException e) {
	       e.printStackTrace(); 
	   	} finally {
	       session.close();
	   	}
	
	   	
	   	rt = new String [ds.size()][8];
	   	for(int i=0;i<ds.size();i++)
	   	{
	   		System.out.println(ds.get(i).getMssv());
	   		rt[i][0]=ds.get(i).getMssv();
	   	
	   		rt[i][1]=ds.get(i).getHoten();
	   		rt[i][2]=ds.get(i).getTenlop();
	   		rt[i][3]=ds.get(i).getMamon();
	   		rt[i][4]=Float.toString(ds.get(i).getDiemgk());
	   		rt[i][5]=Float.toString(ds.get(i).getDiemck());
	   		rt[i][6]=Float.toString(ds.get(i).getDiemkhac());
	   		rt[i][7]=Float.toString(ds.get(i).getDiemtong());
	   	}
		return rt;
		
	}
	
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
		
		 List<BangDiem > ds =layDanhBangDiem() ;
			
		 // listSinhVien();
	  }
}
