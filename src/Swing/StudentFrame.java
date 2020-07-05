package Swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BangDiem.BangDiem;
import BangDiem.BangDiemDAO;
import SinhVien.SinhVienDAO;



	public class StudentFrame extends JFrame {

		private JPanel contentPane;
		private JLabel status;
		private JLabel status_2;
		private List<BangDiem> rows;
		private JPasswordField  mkCu;
		private JPasswordField mkMoi;
	   // private SimpleTableModel<BangDiem> simpleTableModel;
	    private JTable jTable;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						StudentFrame frame = new StudentFrame("1742001");
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
		public StudentFrame(String MSSV) {
			setTitle("Hoc Sinh");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 781, 611);
			
			CardLayout cardLayout=new CardLayout();
			
			
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			String columns[]= {"MSSV","Họ Tên", "Tên Lớp","Mã Môn","Điểm Giữa Kì","Điểm Cuối Kì","Điểm Khác", "Điểm Tổng"};
			//String Data[][] = {{"18120599","HMT","1","2","3","4","5","6"}};
			String Data[][] = BangDiemDAO.XemDiemSV(MSSV);

	    	
	        JLabel jLabel = new JLabel();
	        jLabel.setText("BẢNG ĐIỂM");      
	        jLabel.setFont(new Font("Lucida Grande", 0, 36)); // NOI18N
	        jLabel.setHorizontalAlignment(SwingConstants.CENTER);     
	        JPanel P1 = new JPanel();
	        P1.setLayout(new BorderLayout());
	        P1.setBorder(new EmptyBorder(5, 5, 5, 5));
	       P1.add(jLabel, BorderLayout.PAGE_START);
	        JScrollPane jScrollPane = new JScrollPane();
	        jTable = new JTable(Data,columns);

	        jScrollPane.setViewportView(jTable);
	       P1.add(jScrollPane, BorderLayout.CENTER);
	            
	        JLabel jLabel2 = new JLabel();
	        jLabel2.setText("Sinh Viên");
	       P1.add(jLabel2, BorderLayout.PAGE_END);
	        
	        JMenuBar jMenuBar = new JMenuBar();
	        
	        JMenu file = new JMenu();
	        file.setText("Lựa Chọn");
	        	JMenuItem Diem = new JMenuItem();
	        	Diem.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        			cardLayout.first(contentPane);
	        		}
	        	});
	        	Diem.setText("Xem Điểm");
	        	file.add(Diem);
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
	    	JPanel P2 = new JPanel();
			P2.setBorder(new EmptyBorder(5, 5, 5, 5));
			
			P2.setLayout(null);
			
			mkCu = new JPasswordField ();
			mkCu.setBounds(134, 62, 116, 22);
			P2.add(mkCu);
			mkCu.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Mật Khẩu Cũ");
			lblNewLabel.setBounds(22, 65, 85, 16);
			P2.add(lblNewLabel);
			
			mkMoi = new JPasswordField ();
			mkMoi.setBounds(134, 97, 116, 22);
			P2.add(mkMoi);
			mkMoi.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Mật Khẩu mới");
			lblNewLabel_1.setBounds(22, 100, 85, 16);
			P2.add(lblNewLabel_1);
			
			JButton btnNewButton = new JButton("Đổi Mật Khẩu");
			btnNewButton.setBounds(107, 161, 120, 25);
			btnNewButton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			 changePassPerformed(e,MSSV);
        		}
        	});

			P2.add(btnNewButton);
			
			status = new JLabel();
			status.setBounds(262, 65, 134, 16);
			status_2 = new JLabel();
			status_2.setBounds( 130, 200, 134, 16);
			P2.add(status);
			P2.add(status_2);
			//
			contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(cardLayout);
	        contentPane.add(P1);
	        contentPane.add(P2);
			setContentPane(contentPane);
	
	        setJMenuBar(jMenuBar);  
	        pack();
		}
		
	
		 private void quitActionPerformed(ActionEvent actionEvent) {
			 this.setVisible(false);
			 this.dispose();
		 }
		 private void changePassPerformed(ActionEvent actionEvent,String MSSV) {
			 if(SinhVienDAO.changePass(MSSV, mkCu.getText(), mkMoi.getText()))
			 {
				 status.setText("");
				 status_2.setText("Thành Công");
			 }
			 else status.setText("Sai Mật Khẩu!");
		 }
	}

