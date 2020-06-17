package Import;

import java.util.List;

import org.hibernate.Session;

import Mon.Mon;
import SinhVien.SinhVien;

public class Import {
	public static void ImportTKB(String link, List<SinhVien> ds,Session session)
	{
		JDBC.ImportTKB(link);
		for(int i=0;i<ds.size();i++)
		{
			
			
			//BangDiem temp = 
		}
	}
}
