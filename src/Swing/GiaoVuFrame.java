package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiaoVuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoVuFrame frame = new GiaoVuFrame();
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
	public GiaoVuFrame() {
		setTitle("Quản Lý Sinh Viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Import Danh Sách Sinh Viên");// chua test
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importDsActionPerformed(e);
			}
		});
		btnNewButton.setBounds(23, 38, 208, 59);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Thêm 1 Sinh Viên");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add1SvActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(20, 110, 208, 59);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Import Thời Khóa Biểu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importTKBActionPerformed(e);
			}
		});
		btnNewButton_2.setBounds(306, 38, 205, 57);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Xử lí Danh Sách Lớp");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//xuLiActionPerformed(event)
			}
		});
		btnNewButton_3.setBounds(306, 111, 205, 57);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Xem Danh Sách Lớp");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemDSActionPerformed(e);
			}
		});
		btnNewButton_4.setBounds(23, 181, 205, 57);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Xem Thời Khóa Biểu");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemTKBActionPerformed(e);
			}
		});
		btnNewButton_5.setBounds(306, 181, 205, 57);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Import Bảng Điểm");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//importBDActionPerformed(e)
			}
		});
		btnNewButton_6.setBounds(23, 253, 205, 57);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Xem Bảng Điểm");// bao gom sua diem
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//xemBDActionPerformed(e)
			}
		});
		btnNewButton_7.setBounds(306, 251, 205, 57);
		contentPane.add(btnNewButton_7);
	}	
	private void importDsActionPerformed(ActionEvent event)
	{
		ImportDSSVFrame o = new ImportDSSVFrame();
		o.setVisible(true);
	}
	private void add1SvActionPerformed(ActionEvent event)
	{
		add1SvFrame a = new add1SvFrame();
		a.setVisible(true);
	}
	private void xemTKBActionPerformed(ActionEvent event)
	{
		xemTKBFrame tkb= new xemTKBFrame();
		tkb.setVisible(true);
	}
	private void xemDSActionPerformed(ActionEvent event)
	{
		XemDsLopFrame dsl = new XemDsLopFrame();
		dsl.setVisible(true);
	}
	
	private void importTKBActionPerformed(ActionEvent event)
	{
		ImportTKBFrame itkb= new ImportTKBFrame();
		itkb.setVisible(true);
	}
}
