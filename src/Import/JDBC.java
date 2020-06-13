package Import;


import java.io.*;
import java.sql.*;
import java.util.List;

import SinhVien.SinhVien;

public class JDBC
{
	static Connection con = null;
	public static void ImportDSSV( String Link, String TenLop)
	{
		try {
			Statement st = con.createStatement();
			st.execute("create View SinhVienS\r\n" + 
					"AS\r\n" + 
					"select MSSV,HoTen,GioiTinh,CMND\r\n" + 
					"from SinhVien\r\n" );
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void CreateDB() 
	{
		try {
		Statement st = con.createStatement();
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
		//tao ds dang ki
		st.execute("create table DangKi\r\n" + 
				"(\r\n" + 
				"	MSSV varchar(10),\r\n" + 
				"	MaMon varchar(10),\r\n" + 
				"	TenLop varchar(10)\r\n" + 
				"	primary key (MSSV, MaMon, TenLop)\r\n" + 
				")");
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
				"	DiemKhac float\r\n" + 
				"	primary key (MSSV)\r\n" + 
				")");
		// tao table GiaoVu
		st.execute("create table GiaoVu\r\n" + 
				"(\r\n" + 
				"	ID varchar(10),\r\n" + 
				"	MK varchar(10)\r\n" + 
				"	primary key(ID)\r\n" + 
				")");
		System.out.println("succeed2");
		st.execute("alter table DangKi add constraint FK_DangKi_SinhVien foreign key (MSSV) references SinhVien(MSSV)");
		st.execute("alter table DangKi add constraint FK_DangKi_Mon foreign key(TenLop,MaMon)  references Mon(TenLop,MaMon)");
		st.execute("alter table BangDiem add constraint FK_BangDiem_SinhVien foreign key(MSSV) references SinhVien(MSSV)");
		
		con.close();
		System.out.println("succeed");
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
        }
    	catch (Exception ex){
			System.out.println("Error: " + ex.getMessage());
		}
	}
	public static void main(String args[])
	{
		Connect();
		try {
			Statement st = con.createStatement();
			st.execute("use HibernateData");
		}catch(Exception ex) {
			CreateDB();
		}
		ImportDSSV("E:\\\\hox hanh\\\\java\\\\Public-CQ2018-20200609T013918Z-001\\\\17HCB.csv","");
	
	}
}
	