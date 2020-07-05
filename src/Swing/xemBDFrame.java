package Swing;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import BangDiem.BangDiemDAO;
import SinhVien.SinhVienDAO;
import Mon.*;
import javax.swing.JTextField;
public class xemBDFrame extends JFrame  
{

	private JPanel contentPane;
	public static JComboBox Lop;
	public static JComboBox MaMon;
	private JTable table;
	private List<Mon> dsMon;
	private List<String> mamon;
	private DefaultComboBoxModel boxModel;
	private xemBDModel tableModel;
	private JScrollPane scrollPane;
	private JLabel tiLeDau;
	private JLabel soLuongDau;
	private BangDiemDAO DAO;
	private JLabel nhapMSSV;
	private JTextField MSSV;
	//private static final CellEditor tableCellEditor = new CellEditor();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					xemBDFrame frame = new xemBDFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public xemBDFrame() {
		setTitle("Xem Danh Sách Lớp");
		 addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	                System.out.println("Closed");
	                e.getWindow().dispose();
	            }
	        });
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new  FlowLayout());
		setContentPane(contentPane);
		
		
		JLabel chonLop = new JLabel("Chọn Lớp");
		//chonLop.setBounds(73, 58, 56, 16);
		contentPane.add(chonLop);
		
		Lop = new JComboBox(new DefaultComboBoxModel<String>(BangDiemDAO.LayDSLop().toArray(new String[0])));
		//comboBox.setBounds(133, 55, 72, 22);
		contentPane.add(Lop);
		Lop.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		      	comboBoxEvent(e);
		    }
		});
		JButton btnNewButton = new JButton("Xem");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemActionPerformed(e);
			}
		});
		
		
		System.out.println(Lop.getItemAt(Lop.getSelectedIndex()).toString());
		//System.out.println(MonDAO.LayDanhSachMonTheoLop(comboBox.getItemAt(comboBox.getSelectedIndex()));
		//DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(MonDAO.LayDanhSachMonTheoLop(comboBox.getItemAt(comboBox.getSelectedIndex()).toString()).toArray(new String[0]));
		dsMon = MonDAO.LayDanhSachMonTheoLop(Lop.getItemAt(Lop.getSelectedIndex()).toString());
		mamon =MonDAO.ListMonToString(dsMon);
		boxModel = new DefaultComboBoxModel<String>(mamon.toArray(new String[0]));
		MaMon = new JComboBox(boxModel);
		
		contentPane.add(MaMon);
		
		nhapMSSV = new JLabel("MSSV ");
		contentPane.add(nhapMSSV);
		
		MSSV = new JTextField();
		contentPane.add(MSSV);
		MSSV.setColumns(10);
//		
		contentPane.add(btnNewButton);
		
		DAO = new BangDiemDAO();
		String columns[]= {"MSSV","Họ Tên","Điểm GK","Điểm CK","Điểm Khác","Điểm tổng","Trạng Thái"};
		String Data[][] = DAO.LayDanhSachDiem(Lop.getItemAt(Lop.getSelectedIndex()).toString(),MaMon.getItemAt(MaMon.getSelectedIndex()).toString());
		tableModel = new xemBDModel(Data,columns);
		
		
		//System.out.println(model.Data[0][0]);
		table = new JTable(tableModel);
		
		
		
		
		 scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		

		contentPane.add(scrollPane);
		tiLeDau= new JLabel("Tỉ Lệ Đậu: "+ DAO.getTiledau()*100 +"%");
		soLuongDau = new JLabel("Số Lượng Đậu: " + DAO.getSoluongdau() );
		contentPane.add(soLuongDau);
		contentPane.add(tiLeDau);
		}
	private void comboBoxEvent(ActionEvent e)
	{
		dsMon = MonDAO.LayDanhSachMonTheoLop(Lop.getItemAt(Lop.getSelectedIndex()).toString());
		mamon =MonDAO.ListMonToString(dsMon);
	//	boxModel.addElement("ecs");
		boxModel.removeAllElements();
		for(int i=0;i<mamon.size();i++)
		{
			boxModel.addElement(mamon.get(i));
		}
		//model = new DefaultComboBoxModel<String>(mamon.toArray(new String[0]));
		tableModel.update(DAO.LayDanhSachDiem(Lop.getItemAt(Lop.getSelectedIndex()).toString(),MaMon.getItemAt(MaMon.getSelectedIndex()).toString()));
		tableModel.fireTableDataChanged();
		tiLeDau.setText("Tỉ Lệ Đậu: "+ String.format("%.2f",DAO.getTiledau()*100) +"%");
		soLuongDau.setText("Số Lượng Đậu: " + DAO.getSoluongdau() );
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	private void xemActionPerformed(ActionEvent e)
	{
		
		tableModel.update(DAO.LayDanhSachDiem(Lop.getItemAt(Lop.getSelectedIndex()).toString(),MaMon.getItemAt(MaMon.getSelectedIndex()).toString()));
		tiLeDau.setText("Tỉ Lệ Đậu: "+ String.format("%.2f",DAO.getTiledau()*100) +"%");
		soLuongDau.setText("Số Lượng Đậu: " + DAO.getSoluongdau() );
		if(!MSSV.getText().equals(""))
		tableModel.getByMSSV(MSSV.getText());//**
		
		tableModel.fireTableDataChanged();
	
		System.out.println(DAO.getTiledau());
		contentPane.revalidate();
	    contentPane.repaint();
	    System.out.println(tableModel.getRowCount());
	}

}

