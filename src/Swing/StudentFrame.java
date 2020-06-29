package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BangDiem.BangDiem;
import BangDiem.BangDiemDAO;



	public class StudentFrame extends JFrame {

		private JPanel contentPane;
		//private List<String> cols;
	   // private List<Student> rows;
	  //  private StudentDaoImpl studentDaoImpl;
		private List<BangDiem> rows;
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
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			String columns[]= {"MSSV","Họ Tên", "Tên Lớp","Mã Môn","Điểm Giữa Kì","Điểm Cuối Kì","Điểm Khác", "Điểm Tổng"};
			//String Data[][] = {{"18120599","HMT","1","2","3","4","5","6"}};
			String Data[][] = BangDiemDAO.XemDiemSV(MSSV);

	    	
	        JLabel jLabel = new JLabel();
	        jLabel.setText("BẢNG ĐIỂM");      
	        jLabel.setFont(new Font("Lucida Grande", 0, 36)); // NOI18N
	        jLabel.setHorizontalAlignment(SwingConstants.CENTER);        
	        getContentPane().add(jLabel, BorderLayout.PAGE_START);
	        JScrollPane jScrollPane = new JScrollPane();
	        jTable = new JTable(Data,columns);

	        jScrollPane.setViewportView(jTable);
	        getContentPane().add(jScrollPane, BorderLayout.CENTER);
	            
	        JLabel jLabel2 = new JLabel();
	        jLabel2.setText("Sinh Viên");
	        getContentPane().add(jLabel2, BorderLayout.PAGE_END);
	        
	        JMenuBar jMenuBar = new JMenuBar();
	        
	        JMenu file = new JMenu();
	        file.setText("Lựa Chọn");
	        	
	
	        	JMenuItem ChangePass = new JMenuItem();
	        	ChangePass.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        			//changePassActionPerformed(e);
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
	        
	    
	        setJMenuBar(jMenuBar);  
	        pack();
		}
		
		
//		 private void insertStudentActionPerformed(ActionEvent evt) {
//		     new InsertStudentJFrame().setVisible(true);
//		 }
//
//		 private void refreshActionPerformed(ActionEvent evt) {
//			 rows=studentDaoImpl.getAllStudent();
//		     simpleTableModel.setRows(rows);
//		     simpleTableModel.fireTableDataChanged();
//		 }
//		 
//		 private void updateStudentActionPerformed(ActionEvent evt) {
//			 try {
//				 	rows=studentDaoImpl.getAllStudent();
//				 	simpleTableModel.setRows(rows);
//					Student student=rows.get(jTable.getSelectedRow());
//					new UpdateStudentJFrame(student).setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//					JOptionPane.showMessageDialog(this, "请选中具体学生...","更新学生信息",JOptionPane.ERROR_MESSAGE);
//			}    	             
//		 }
//		 
//		 private void queryByIDActionPerformed(ActionEvent evt) {
//			 rows=studentDaoImpl.getAllStudent();
//		     simpleTableModel.setRows(rows);
//			 new QueryByIDJFrame(jTable).setVisible(true);
//		 }
//		 
//		 private void delStudentActionPerformed(ActionEvent actionEvent) {		 
//		     try {
//				Student student=rows.get(jTable.getSelectedRow());
//				studentDaoImpl.delStudentbyID(student.getSid());
//				rows=studentDaoImpl.getAllStudent();
//			    simpleTableModel.setRows(rows);
//			    simpleTableModel.fireTableDataChanged();
//			    JOptionPane.showMessageDialog(this, "删除成功", "删除学生信息", JOptionPane.INFORMATION_MESSAGE);
//			} catch (Exception e) {
//				// TODO: handle exception
//				JOptionPane.showMessageDialog(this, "删除失败!请选中学生...", "删除学生信息", JOptionPane.ERROR_MESSAGE);
//			}
//		 }
//		 
		 private void quitActionPerformed(ActionEvent actionEvent) {
			 this.setVisible(false);
			 this.dispose();
		 }
		 private void changePassPerformed(ActionEvent actionEvent) {
			 this.setVisible(false);
			 this.dispose();
		 }
//		 private void queryAllActionPerformed(ActionEvent actionEvent) {
//			 rows=studentDaoImpl.getAllStudent();
//		     simpleTableModel.setRows(rows);
//		     simpleTableModel.fireTableDataChanged();
//		 }
	}

