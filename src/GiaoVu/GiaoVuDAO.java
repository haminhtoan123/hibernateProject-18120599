package GiaoVu;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;

public class GiaoVuDAO {
	public static void changePass(String mkMoi)
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 Query query = session.createQuery("UPDATE GiaoVu SET mk=:mkMoi WHERE id=:id");
		 query.setString("mkMoi", mkMoi);
		 query.setString("id", "GIAOVU");
		 session.close();
	}
	public static boolean CertifyUser(String id,String mk)
	{
		try {
	SessionFactory factory = HibernateUtil.getSessionFactory();
	 Session session = factory.openSession();
	 Query query = session.createQuery("FROM GiaoVu");
	 GiaoVu temp = (GiaoVu) query.uniqueResult();
	 if(id.equals(temp.getId())&&mk.equals(temp.getMk()))
	 
		 return true;
	 
	 else 
		 return false;
		}
		catch(HibernateException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
	 
	
	}
	public static void main(String args[])
	  {
		if(CertifyUser("GIAOVU","GIAOVU")) System.out.println("AAAA");
		 // listSinhVien();
	  }
}
