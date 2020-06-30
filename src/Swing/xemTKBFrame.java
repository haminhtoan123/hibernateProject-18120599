package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import BangDiem.BangDiemDAO;
import Mon.MonDAO;
import SinhVien.SinhVienDAO;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class xemTKBFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JComboBox comboBox ;
	private SimpleTableModel model;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					xemTKBFrame frame = new xemTKBFrame();
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
	public xemTKBFrame() {
		setTitle("Thời Khóa Biểu");
		 addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	                System.out.println("Closed");
	                e.getWindow().dispose();
	            }
	        });
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new  FlowLayout());
		
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

		String columns[]= {"Mã Môn","Tên Môn","Phòng Học"};
		String Data[][] = MonDAO.LayTKB(comboBox.getItemAt(comboBox.getSelectedIndex()).toString());
		model = new SimpleTableModel(Data,columns);
		
		//System.out.println(model.Data[0][0]);
		table = new JTable(model);
		 scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		

		contentPane.add(scrollPane);
	//	btnNewButton.setBounds(246, 54, 97, 25);
		
	}
	private void xemActionPerformed(ActionEvent e)
	{
		model.update(MonDAO.LayTKB(comboBox.getItemAt(comboBox.getSelectedIndex()).toString()));
	

		
	    contentPane.revalidate();
	    contentPane.repaint();
	    
	}
}
