package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SinhVien.SinhVien;
import SinhVien.SinhVienDAO;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class add1SvFrame extends JFrame {

    private JLabel statusLabel;
    
    private JTextField tenlop;
    private JTextField MSSV;
    private JTextField CMND;
    JComboBox comboBox;
   private JTextField hoten;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add1SvFrame frame = new add1SvFrame();
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
	public add1SvFrame() {
		 setTitle("Thêm Một Sinh Viên ");
	     setSize(513, 548);
	     addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	                System.out.println("Closed");
	                e.getWindow().dispose();
	            }
	        });
	        //headerLabel = new JLabel("", JLabel.CENTER);
	        statusLabel = new JLabel("", JLabel.CENTER);
	        statusLabel.setLocation(0, 434);
	        statusLabel.setSize(482, 67);
	        getContentPane().setLayout(null);
	        getContentPane().add(statusLabel);
	        setVisible(true);
	       // headerLabel.setText("Control in action: JFileChooser");
	       //Jlabel
	        JLabel nhapTL = new JLabel("Nhập tên lớp: ");
	        nhapTL.setBounds(47, 54, 85, 16);
	        getContentPane().add(nhapTL);
	        
	        JLabel mssv = new JLabel("Nhập MSSV: ");
	        mssv.setBounds(44, 159, 88, 16);
	        getContentPane().add(mssv);
	   
	        
	        JLabel NhapCMND = new JLabel("CMND: ");
	        NhapCMND.setBounds(47, 220, 56, 16);
	        getContentPane().add(NhapCMND);
	        
	        JLabel LgioiTinh = new JLabel("Giới Tính: ");
	        LgioiTinh.setBounds(47, 261, 85, 16);
	        getContentPane().add(LgioiTinh);
	        
	        JLabel Lhoten = new JLabel("Họ Tên: ");
	        Lhoten.setBounds(47, 100, 56, 16);
	        getContentPane().add(Lhoten);
	   

	        JButton them = new JButton("Thêm");
	        them.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		them1SvActionPerformed(arg0);
	        	}
	        });
	        them.setBounds(303, 159, 159, 118);
	        getContentPane().add(them);
	        
	        //Jtexfield
	        tenlop = new JTextField();
	        tenlop.setBounds(144, 51, 123, 22);
	        getContentPane().add(tenlop);
	        tenlop.setColumns(10);
	        
	        MSSV = new JTextField();
	        MSSV.setBounds(144, 164, 123, 22);
	        getContentPane().add(MSSV);
	        MSSV.setColumns(10);
	        
	        CMND = new JTextField();
	        CMND.setBounds(144, 217, 123, 22);
	        getContentPane().add(CMND);
	        CMND.setColumns(10);
	        
	        hoten = new JTextField();
	        hoten.setBounds(144, 97, 123, 22);
	        getContentPane().add(hoten);
	        hoten.setColumns(10);
	        String gioiTinh[] = {"Nam","Nữ"};
	        comboBox  = new JComboBox(gioiTinh);
	        comboBox.setBounds(144, 258, 123, 22);
	        getContentPane().add(comboBox);
	        
	       	setVisible(true);
	}
	private void them1SvActionPerformed(ActionEvent e)
	{
		SinhVien temp= new SinhVien(tenlop.getText(),MSSV.getText(),hoten.getText(),comboBox.getItemAt(comboBox.getSelectedIndex()).toString(),CMND.getText());
		temp.print();
		statusLabel.setText(SinhVienDAO.ThemSinhVien(temp));
	}
}
