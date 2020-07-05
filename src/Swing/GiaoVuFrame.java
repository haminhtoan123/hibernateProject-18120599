package Swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import GiaoVu.GiaoVuDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiaoVuFrame extends JFrame {

	private JPanel contentPane;
	
	private JPasswordField  mkCu;
	private JPasswordField mkMoi;
	private JLabel status;
	private JLabel status_2;
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
		JPanel P1 = new JPanel();
		P1.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(P1);
		P1.setLayout(null);
		//****
		CardLayout cardLayout=new CardLayout();
	       JMenuBar jMenuBar = new JMenuBar();
	       
	        JMenu file = new JMenu();
	        file.setText("Lựa Chọn");
	        	JMenuItem chinh = new JMenuItem();
	        	chinh.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        			cardLayout.first(contentPane);
	        		}
	        	});
	        	chinh.setText("Màn Hình Chính");
	        	file.add(chinh);
	        	JMenuItem ChangePass = new JMenuItem();
	        	ChangePass.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        			cardLayout.last(contentPane);
	        		}
	        	});
	        	ChangePass.setText("Đổi Mật Khẩu");
	        	file.add(ChangePass);
	        	JMenuItem quit = new JMenuItem();
	        	quit.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        			quitActionPerformed(e);
	        		}
	        	});
	        	
	        		quit.setText("Quit");
	        		file.add(quit);
	        jMenuBar.add(file);
	        //
	    	
			P1.setBorder(new EmptyBorder(5, 5, 5, 5));
			
			P1.setLayout(null);
			
			mkCu = new JPasswordField ();
			mkCu.setBounds(134, 62, 116, 22);
			P1.add(mkCu);
			mkCu.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Mật Khẩu Cũ");
			lblNewLabel.setBounds(22, 65, 85, 16);
			P1.add(lblNewLabel);
			
			mkMoi = new JPasswordField ();
			mkMoi.setBounds(134, 97, 116, 22);
			P1.add(mkMoi);
			mkMoi.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Mật Khẩu mới");
			lblNewLabel_1.setBounds(22, 100, 85, 16);
			P1.add(lblNewLabel_1);
			
			JButton doi= new JButton("Đổi Mật Khẩu");
			doi.setBounds(107, 161, 120, 25);
			doi.addActionListener(new ActionListener() {
       		public void actionPerformed(ActionEvent e) {
       			 changePassPerformed(e);
       		}
       	});

		P1.add(doi);
		
		
		//*****
		JPanel P2 = new JPanel();
		JButton btnNewButton = new JButton("Import Danh Sách Sinh Viên");// chua test
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importDsActionPerformed(e);
			}
		});
		btnNewButton.setBounds(23, 38, 208, 59);
		P2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Thêm 1 Sinh Viên");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add1SvActionPerformed(e);
			}
		});
		btnNewButton_1.setBounds(20, 110, 208, 59);
		P2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Import Thời Khóa Biểu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importTKBActionPerformed(e);
			}
		});
		btnNewButton_2.setBounds(306, 38, 205, 57);
		P2.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Đăng Kí & Hũy Đăng Kí Môn");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangKiActionPerFormed(e);
			}
		});
		btnNewButton_3.setBounds(306, 111, 205, 57);
		P2.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Xem Danh Sách Lớp");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemDSActionPerformed(e);
			}
		});
		btnNewButton_4.setBounds(23, 181, 205, 57);
		P2.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Xem Thời Khóa Biểu");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemTKBActionPerformed(e);
			}
		});
		btnNewButton_5.setBounds(306, 181, 205, 57);
		P2.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Import Bảng Điểm");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importBDActionPerformed(e);
			}
		});
		btnNewButton_6.setBounds(23, 253, 205, 57);
		P2.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Xem Bảng Điểm & Sửa Điểm");// bao gom sua diem
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemBDActionPerformed(e);
			}
		});
		btnNewButton_7.setBounds(306, 251, 205, 57);
		P2.add(btnNewButton_7);
		//
		status = new JLabel();
		status.setBounds(262, 65, 134, 16);
		status_2 = new JLabel();
		status_2.setBounds( 130, 200, 134, 16);
		P1.add(status);
		P1.add(status_2);
		//
		contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(cardLayout);
        contentPane.add(P2);
        contentPane.add(P1);
		setContentPane(contentPane);

        setJMenuBar(jMenuBar);  
     //   pack();
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
	private void xemBDActionPerformed(ActionEvent event)
	{
		xemBDFrame xbd= new xemBDFrame();
		xbd.setVisible(true);
	}
	private void importBDActionPerformed(ActionEvent event)
	{
		ImportBDFrame ibd = new ImportBDFrame();
		ibd.setVisible(true);
	}
	private void DangKiActionPerFormed(ActionEvent event)
	{
		DangKiMonFrame dkm = new DangKiMonFrame();
		dkm.setVisible(true);
	}
	 private void quitActionPerformed(ActionEvent actionEvent) {
		 this.setVisible(false);
		 this.dispose();
	 }
	 private void changePassPerformed(ActionEvent event)
	 {
		 if(GiaoVuDAO.CertifyUser("GIAOVU", mkCu.getText()))
		 {
			 GiaoVuDAO.changePass(mkMoi.getText());
			 status_2.setText("Thành Công");
		 }
		 else
		 {
			 status.setText("Sai Mật Khẩu");
		 }
	 }
}
