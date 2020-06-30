package Import;


import java.io.*;
import java.sql.*;
import java.util.List;

import SinhVien.SinhVien;

public class JDBC
{
	static Connection con = null;
	static Statement st;
	public static void ImportBangDiem(String link, String TenLop, String mamon)
	{
		
	}
	public static String ImportDSSV( String Link, String TenLop)// Giao Vu nhap ten lop truoc
	{
		Connect();
		try {

			st.execute(
					"BULK\r\n" + 
					"INSERT SinhVienS\r\n" + 
					"FROM '"+Link+"'\r\n" + 
					"WITH\r\n" + 
					"(\r\n" + 
					"	FORMAT = 'CSV', \r\n" + 
					"\r\n" + 
					"	FIRSTROW = 2,\r\n" + 
					"    FIELDTERMINATOR = ',',  --CSV field delimiter\r\n" + 
					"    ROWTERMINATOR = '\\n',   --Use to shift the control to next row\r\n" + 
					"    TABLOCK\r\n" + 
					")");
			st.execute("update SinhVien\r\n" + 
					"Set TenLop = '"+TenLop+"'\r\n" + 
					"where TenLop is null;");
			st.execute("update SinhVien\r\n" + 
					"set MK = MSSV\r\n" + 
					"where MK is null;");
			Close();
			return "succeed";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Close();
			return e.toString();
		}
		
	}
	public static void ImportTKB(String link)
	{
	
		try {
		
		
		st.execute("BULK\r\n" + 
				"INSERT TKB\r\n" + 
				"from '"+link+"'\r\n" + 
				"with\r\n" + 
				"(\r\n" + 
				"	FORMAT = 'CSV',\r\n" + 
				"	FIRSTROW =2,\r\n" + 
				"	 FIELDTERMINATOR = ',',  --CSV field delimiter\r\n" + 
				"    ROWTERMINATOR = '\\n',   --Use to shift the control to next row\r\n" + 
				"    TABLOCK\r\n" + 
				")");
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	public static void CreateDB() 
	{
		try {
		st = con.createStatement();
		st.execute("create database HibernateData");
		st.execute("use HibernateData");
		//tao table sv
		st.execute("create table SinhVien\r\n" + 
				"(\r\n" + 
				"	MSSV varchar(10),\r\n" + 
				"	HoTen nvarchar(50),\r\n" + 
				"	GioiTinh nvarchar(3),\r\n" + 
				"	CMND varchar(10),\r\n" + 
				"	TenLop varchar(10),\r\n" + 
				"	MK varchar(10)\r\n" + 
				"	primary key (MSSV)\r\n" + 
				")");
		/*//tao ds dang ki
		st.execute("create table DangKi\r\n" + 
				"(\r\n" + 
				"	MSSV varchar(10),\r\n" + 
				"	MaMon varchar(10),\r\n" + 
				"	TenLop varchar(10)\r\n" + 
				"	primary key (MSSV, MaMon, TenLop)\r\n" + 
				")");*/
		//tao ds Mon
		st.execute("create table Mon\r\n" + 
				"(\r\n" + 
				"	MaMon varchar(10),\r\n" + 
				"	TenLop varchar(10),\r\n" + 
				"	TenMon nvarchar(50),\r\n" + 
				"	PhongHoc varchar(3)\r\n" + 
				"	primary key  (TenLop,MaMon)\r\n" + 
				")");
		//tao bang diem
		st.execute("create table BangDiem\r\n" + 
				"(\r\n" + 
				"	MSSV varchar(10),\r\n" + 
				"	HoTen nvarchar(50),\r\n" + 
				"	TenLop varchar(10),\r\n" + 
				"	MaMon varchar(10),\r\n" + 
				"	DiemGK float,\r\n" + 
				"	DiemCK float,\r\n" + 
				"	DiemKhac float,\r\n" + 
				"	DiemTong float\r\n" + 
				"	primary key (MSSV,TenLop,MaMon)\r\n" + 
				")");
		// tao table GiaoVu
		st.execute("create table GiaoVu\r\n" + 
				"(\r\n" + 
				"	ID varchar(10),\r\n" + 
				"	MK varchar(10)\r\n" + 
				"	primary key(ID)\r\n" + 
				")");
		st.execute("alter table BangDiem add constraint FK_BangDiem_SinhVien foreign key(MSSV) references SinhVien(MSSV)");
		st.execute("alter table BangDiem add constraint FK_BandDiem_Mon foreign key(TenLop,MaMon) references Mon(TenLop,MaMon)");
		
		st.execute("create View SinhVienS\r\n" + 
				"AS\r\n" + 
				"select MSSV,HoTen,GioiTinh,CMND\r\n" + 
				"from SinhVien\r\n" );
		st.execute("create View TKB\r\n" + 
				"as \r\n" + 
				"select  TenLop,MaMon,TenMon,PhongHoc\r\n" + 
				"from Mon ");
		//con.close();
		}
		catch(Exception ex){
			System.out.println("Error: " + ex.getMessage());
			}
	}
	
			
		
	

	public static void Connect()
	{
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection("jdbc:sqlserver://localhost:49690;integratedSecurity=true");
        st=con.createStatement();
		st.execute("use HibernateData");
        }
    	catch (Exception ex){
    		System.out.println("Database not Exist, Create DB");
			CreateDB();
		}
	}
	public static void Close()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public static void JDBC()
	{
		try {
		Connect();
		}
		catch(Exception ex) {
			System.out.println("connection fail"); 
			CreateDB();
		}
		
	}
	public static void main(String args[]) 
	{
		Connect();
		/*try {
			
			
		}catch(Exception ex) {
			System.out.println("connection fail"); 
			CreateDB();
		}*/
		//System.out.println(rs.getString(1)+" " + rs.getString(2) + " ");
		/*ImportDSSV("E:\\hox hanh\\java\\Public-CQ2018-20200609T013918Z-001\\17HCB.csv","18HCB");
		ImportTKB("E:\\hox hanh\\java\\Public-CQ2018-20200609T013918Z-001\\17HCB-TKB.csv");*/
		System.out.println("succeed"); 
	
	}
}
	