package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BangDiem.BangDiemDAO;
import Mon.MonDAO;
import SinhVien.SinhVienDAO;

public class XemDsLopFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox ;
	private SimpleTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	//JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XemDsLopFrame frame = new XemDsLopFrame();
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
	public XemDsLopFrame() {
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

		
		JButton btnNewButton = new JButton("Xem");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemActionPerformed(e);
			}
		});
		contentPane.add(btnNewButton);
		String columns[]= {"MSSV","Họ Tên","Giới Tính","CMND"};
		String Data[][] = SinhVienDAO.LayDSSVTheoLop(comboBox.getItemAt(comboBox.getSelectedIndex()).toString());
		model = new SimpleTableModel(Data,columns);
		
		//System.out.println(model.Data[0][0]);
		table = new JTable(model);
		 scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		

		contentPane.add(scrollPane);
	}
	private void xemActionPerformed(ActionEvent e)
	{
	
		//System.out.println(Data[0][0]+ " : " +Data[0][1]+ " : " + Data[0][2]);
		//table.removeAll();
		//table.setModel(temp);
		
		model.update(SinhVienDAO.LayDSSVTheoLop(comboBox.getItemAt(comboBox.getSelectedIndex()).toString()));
	
	//	scrollPane.setBounds(27, 394, 505, -285);
	
	    contentPane.revalidate();
	    contentPane.repaint();
	}
}
