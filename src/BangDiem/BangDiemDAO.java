package BangDiem;

import java.util.ArrayList;
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
	public float  tiledau;
	public int soluongdau;
	public BangDiemDAO(){}
	public int getSoluongdau()
	{
		return soluongdau;
	}
	public float getTiledau()
	{
		return tiledau;
	}
//	public void setTiledau(float tiledau) 
//	{
//		this.tiledau= tiledau;
//	}
	
	public static String[][] ListMonToArray(List<Mon> ds)
	{
	
	 	String[][] rt = new String [ds.size()][3];
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
	public static List<Mon> LayDSMonTheoSV(String MSSV)
	{

		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		List<BangDiem> ds = null;
		List<Mon> rt= new ArrayList<Mon>();
		try {
			 
    		//ds = session.createQuery("FROM Mon WHERE tenlop =:tenlop").list();
    		String hql ="FROM BangDiem  WHERE  MSSV =:MSSV";
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
			Mon temp =new Mon();
			temp.setMamon(ds.get(i).getMon().getMamon());
			temp.setPhonghoc(ds.get(i).getMon().getPhonghoc());
			temp.setTenmon(ds.get(i).getMon().getTenmon());;
			System.out.println(temp.getMamon());
			rt.add(temp);
		}
		
		return rt;
	}
	public static String [][] LayDSSVTheoLop(String tenlop,String mamon)
	{

		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<BangDiem> ds= null;
		 try {
			 
	    		//ds = session.createQuery("FROM Mon WHERE tenlop =:tenlop").list();
	    		String hql ="FROM BangDiem  WHERE tenlop =:tenlop AND mamon =:mamon";
	    		Query query = session.createQuery(hql);
	    		query.setString("tenlop",tenlop);
	    		query.setString("mamon",mamon);
	    		ds = query.list();
	    		
	    	} catch (HibernateException e) {
	        e.printStackTrace();
	    	}
		 System.out.println(ds.get(0).getSinhVien().getHoten());
		 String rt[][] = new String[ds.size()][4];
		 for(int i=0; i<ds.size();i++)
		 {
			 rt[i][0] = ds.get(i).getSinhVien().getMssv();
			 rt[i][1] = ds.get(i).getSinhVien().getHoten();
			 rt[i][2] = ds.get(i).getSinhVien().getGioitinh();
			 rt[i][3] = ds.get(i).getSinhVien().getCmnd();
		 }
		return rt;
	
	}
	public static void Update(String MSSV,String tenLop,String mamon,float diemGk,float diemCk,float diemKhac, float diemTong) 
	{

		factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 Transaction transaction = session.beginTransaction();
		 try {
		 Query query  = session.createQuery("UPDATE BangDiem SET diemgk =:diemGk ,diemck =:diemCk ,diemkhac=:diemKhac, diemtong=:diemTong "
		 		+ "WHERE MSSV =:MSSV AND  tenlop=:tenLop AND mamon=:mamon");
		 query.setString("MSSV", MSSV);
		 query.setString("tenLop", tenLop);
		 query.setString("mamon",mamon);
		 query.setParameter("diemGk", diemGk);
		 query.setParameter("diemCk",  diemCk);
		 query.setParameter("diemKhac",  diemKhac);
		 query.setParameter("diemTong", diemTong);
		 query.executeUpdate();
		 transaction.commit();
		 }
		 catch (HibernateException e) {
		       e.printStackTrace(); 
		       transaction.rollback();
		   	} finally {
		       session.close();
		   	}
	}
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
	public  String[][] LayDanhSachDiem(String tenlop,String mamon)
	{
		
		String rt[][] = null;
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 List<BangDiem> ds= null;
	   	try {
	   		Query query = session.createQuery("FROM BangDiem B WHERE B.tenlop =:tenlop AND B.mamon =:mamon");
	   	 query.setString("tenlop", tenlop);
	   	 query.setString("mamon", mamon);
	   	 ds=query.list();
	   	} catch (HibernateException e) {
	       e.printStackTrace(); 
	   	} finally {
	       session.close();
	   	}
	
	   	soluongdau =0;
	   	rt = new String [ds.size()][7];
	   	for(int i=0;i<ds.size();i++)
	   	{
	   		System.out.println(ds.get(i).getMssv());
	   		rt[i][0]=ds.get(i).getMssv();
	   	
	   		rt[i][1]=ds.get(i).getHoten();
	   		rt[i][2]=Float.toString(ds.get(i).getDiemgk());
	   		rt[i][3]=Float.toString(ds.get(i).getDiemck());
	   		rt[i][4]=Float.toString(ds.get(i).getDiemkhac());
	   		rt[i][5]=Float.toString(ds.get(i).getDiemtong());
	   		if(ds.get(i).getDiemtong()>5) 
	   			{
	   			rt[i][6] = "Đậu";
	   			soluongdau++;
	   			}
	   		else rt[i][6] = "Rớt";
	   	}
	   	
	   	tiledau =(float)soluongdau /ds.size();
	 
		return rt;
		
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
	
	public static void DangKiMon(SinhVien sv, Mon mon,Session session, Transaction transaction)
	{
		BangDiem temp = new BangDiem(sv.getMssv(),sv.getHoten() ,mon.getTenlop(), mon.getMamon());
		
		//System.out.println(sv.getMssv()+sv.getHoten() + mon.getMamon()+mon.getTenlop());
		//Transaction transaction = session.beginTransaction();
		//transaction= null;
		try {
		//transaction = session.beginTransaction();
	
		session.save(temp);
		transaction.commit();
		}
		catch (HibernateException ex)
		{
			 transaction.rollback();         
			 System.err.println(ex.getMessage());
		}
		
	}
	

	public static Boolean HuyDangKiMon(SinhVien sv,String mamon)
	{
		// check sv co dki mamon (dung join)
		 factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 Transaction transaction=session.beginTransaction();
		if (CheckDangKi(sv,mamon,session) == false)
		{
			return false;
		}
		boolean succeed = true;
		try {
		 String hql = "delete from BangDiem where mssv = :mssv AND tenlop= :tenlop AND mamon = :mamon";
		 Query query = session.createQuery(hql);
		 query.setString("mssv", sv.getMssv());
		 query.setString("tenlop", sv.getTenlop());
		 query.setString("mamon", mamon);
		 query.executeUpdate();
		 
		//session.delete(temp);
		transaction.commit();
		session.close();
		
		}
		catch (HibernateException ex)
		{
			succeed = false;
			 transaction.rollback();         
			 System.err.println(ex);
			 session.close();
	
		}
		return succeed;
		
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
		 SessionFactory  factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 Transaction transaction=session.beginTransaction();
		 SinhVien sv = new SinhVien();
		 sv.setMssv("1742001");
		 Mon mon = new Mon();
		 mon.setTenlop("18HCB");
		 mon.setMamon("CTT011");
		 
		 DangKiMon(sv, mon, session, transaction);
		 session.close();
	  }
}
