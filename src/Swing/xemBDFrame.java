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

import BangDiem.BangDiemDAO;
import SinhVien.SinhVienDAO;
import Mon.*;
public class xemBDFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JTable table;
	private List<Mon> dsMon;
	private List<String> mamon;
	private DefaultComboBoxModel boxModel;
	private SimpleTableModel tableModel;
	private JScrollPane scrollPane;
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
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new  FlowLayout());
		setContentPane(contentPane);
		
		
		JLabel chonLop = new JLabel("Chọn Lớp");
		//chonLop.setBounds(73, 58, 56, 16);
		contentPane.add(chonLop);
		
		comboBox = new JComboBox(new DefaultComboBoxModel<String>(BangDiemDAO.LayDSLop().toArray(new String[0])));
		//comboBox.setBounds(133, 55, 72, 22);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener () {
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
		
		contentPane.add(btnNewButton);
		
		System.out.println(comboBox.getItemAt(comboBox.getSelectedIndex()).toString());
		//System.out.println(MonDAO.LayDanhSachMonTheoLop(comboBox.getItemAt(comboBox.getSelectedIndex()));
		//DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(MonDAO.LayDanhSachMonTheoLop(comboBox.getItemAt(comboBox.getSelectedIndex()).toString()).toArray(new String[0]));
		dsMon = MonDAO.LayDanhSachMonTheoLop(comboBox.getItemAt(comboBox.getSelectedIndex()).toString());
		mamon =MonDAO.ListMonToString(dsMon);
		boxModel = new DefaultComboBoxModel<String>(mamon.toArray(new String[0]));
		comboBox_1 = new JComboBox(boxModel);
	
		contentPane.add(comboBox_1);
//		
		
		
		String columns[]= {"MSSV","Họ Tên","Điểm GK","Điểm CK","Điểm Khác","Điểm tổng"};
		String Data[][] = BangDiemDAO.LayDanhSachDiem(comboBox.getItemAt(comboBox.getSelectedIndex()).toString(),comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString());
		tableModel = new SimpleTableModel(Data,columns);
		
		//System.out.println(model.Data[0][0]);
		table = new JTable(tableModel);
		 scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		

		contentPane.add(scrollPane);
		}
	private void comboBoxEvent(ActionEvent e)
	{
		dsMon = MonDAO.LayDanhSachMonTheoLop(comboBox.getItemAt(comboBox.getSelectedIndex()).toString());
		mamon =MonDAO.ListMonToString(dsMon);
		boxModel.addElement("ecs");
		boxModel.removeAllElements();
		for(int i=0;i<mamon.size();i++)
		{
			boxModel.addElement(mamon.get(i));
		}
		//model = new DefaultComboBoxModel<String>(mamon.toArray(new String[0]));
		tableModel.update(BangDiemDAO.LayDanhSachDiem(comboBox.getItemAt(comboBox.getSelectedIndex()).toString(),comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString()));
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	private void xemActionPerformed(ActionEvent e)
	{
		
		tableModel.update(BangDiemDAO.LayDanhSachDiem(comboBox.getItemAt(comboBox.getSelectedIndex()).toString(),comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString()));
	    contentPane.revalidate();
	    contentPane.repaint();
	}
	

}
