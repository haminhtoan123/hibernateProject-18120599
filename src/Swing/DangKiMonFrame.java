package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import BangDiem.BangDiemDAO;
import Mon.Mon;
import Mon.MonDAO;
import SinhVien.SinhVien;
import SinhVien.SinhVienDAO;
import util.HibernateUtil;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


public class DangKiMonFrame extends JFrame {

	private JPanel contentPane;
	private JTextField MSSV;
	private JLabel lopLabel; 
	private JTable table;
	private NormalTableModel model;
	private JScrollPane scrollPane;
	private  JComboBox MaMon;
	private DefaultComboBoxModel boxModel;
	private DefaultComboBoxModel boxModel2;
	private JButton btnNewButton_1;
	private String tenlop;
	private boolean deleteAble = false;
	private JLabel lblNewLabel_3;
	private JComboBox allMon;
	private JButton btnNewButton_2;
	private List<String> listForallMon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKiMonFrame frame = new DangKiMonFrame();
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
	public DangKiMonFrame() {
		 setTitle("Đăng Kí và Hủy Đăng Kí Môn");
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
	      
	        getContentPane().setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("MSSV");
	        lblNewLabel.setBounds(52, 72, 110, 35);
	        getContentPane().add(lblNewLabel);
	        
	        MSSV = new JTextField();
	        MSSV.setBounds(163, 75, 143, 29);
	        getContentPane().add(MSSV);
	        MSSV.setColumns(10);
	        
	        JButton btnNewButton = new JButton("Tìm");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		timActionPerformed(e);
	        	}
	        });
	        btnNewButton.setBounds(348, 77, 97, 25);
	        getContentPane().add(btnNewButton);
	        
	        lopLabel = new JLabel("Lớp:");
	        lopLabel.setBounds(163, 130, 143, 35);
	        getContentPane().add(lopLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel("Môn Đã Đăng Kí");
	        lblNewLabel_1.setBounds(12, 184, 102, 29);
	        getContentPane().add(lblNewLabel_1);

	        String columns[]= {"Mã Môn","Tên Môn","Phòng Học"};
	        String Data[][] = {{"","",""},{"","",""}};
			model = new NormalTableModel(Data, columns);
	        scrollPane = new JScrollPane();
	        scrollPane.setBounds(0, 214, 233, 176);
	        getContentPane().add(scrollPane);
	        
	        table = new JTable(model);
	        scrollPane.setViewportView(table);
	        boxModel = new DefaultComboBoxModel<String>();
	        MaMon = new JComboBox(boxModel);
	        MaMon.setBounds(12, 421, 150, 22);
	        getContentPane().add(MaMon);
	        
	        JLabel lblNewLabel_2 = new JLabel("Chọn Môn Để Hũy");
	        lblNewLabel_2.setBounds(10, 392, 152, 16);
	        getContentPane().add(lblNewLabel_2);
	        
	        btnNewButton_1 = new JButton("Hũy");
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		 huyActionPerformed(e);
	        	}        	
	        });
	        btnNewButton_1.setBounds(160, 420, 97, 25);
	        getContentPane().add(btnNewButton_1);
	        
	        lblNewLabel_3 = new JLabel("Chọn Môn Đăng Kí");
	        lblNewLabel_3.setBounds(245, 190, 164, 16);
	        getContentPane().add(lblNewLabel_3);
	        // all Mon combobox
	        listForallMon = MonDAO.layMonSVChuaDki(MSSV.getText());
	        boxModel2 = new DefaultComboBoxModel<String>(listForallMon.toArray(new String[0]));
	        allMon = new JComboBox(boxModel2);
	        allMon.setEditable(true);
	        final JTextField textfield = (JTextField) allMon.getEditor().getEditorComponent();
	        textfield.addKeyListener(new KeyAdapter() {
	            public void keyReleased(KeyEvent ke) {
	                SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                        if(!textfield.getText().isEmpty()){
	                            comboBoxFilter(textfield.getText());
	                        }
	                    }
	                });

	            }
	        });
	        allMon.setBounds(255, 219, 154, 22);
	        getContentPane().add(allMon);
	        // Dang Ki button
	        btnNewButton_2 = new JButton("Đăng Kí");
	        btnNewButton_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		dangKiActionPerformed(e);
	        	}
	        });
	        btnNewButton_2.setBounds(291, 254, 97, 25);
	        getContentPane().add(btnNewButton_2);
	        setVisible(true);
	}
	private void dangKiActionPerformed(ActionEvent e)
	{
		 SessionFactory  factory = HibernateUtil.getSessionFactory();
		 Session session = factory.openSession();
		 Transaction transaction=session.beginTransaction();
		 SinhVien sv = new SinhVien();
		 sv.setMssv(MSSV.getText());
		 Mon mon = new Mon();
		 String temp =allMon.getItemAt(allMon.getSelectedIndex()).toString();
		 mon.setTenlop(temp.substring(0, temp.lastIndexOf("-")));
	
		 mon.setMamon(temp.substring(temp.lastIndexOf("-")+1, temp.length()));//*
		 System.out.println(sv.getMssv() +" "+ mon.getTenlop() +" "+ mon.getMamon() );
		 BangDiemDAO.DangKiMon(sv, mon, session, transaction);
		 session.close();
		 JOptionPane.showMessageDialog(this, "Đăng Kí Thành Công");
	}
	private void huyActionPerformed(ActionEvent e)
	{
		if(deleteAble)
		{
			SinhVien temp = new  SinhVien();
			temp.setMssv(MSSV.getText());
			temp.setTenlop(tenlop);
			if (BangDiemDAO.HuyDangKiMon(temp, MaMon.getItemAt(MaMon.getSelectedIndex()).toString()))
			{
			 JOptionPane.showMessageDialog(this, "Hũy Thành Công");
			}
			else JOptionPane.showMessageDialog(this, "Hũy Không Thành Công");
		}
		else return;
	}
	private void timActionPerformed(ActionEvent e)
	{
		if(!MSSV.getText().equals("")) {
		
			tenlop=SinhVienDAO.LayLopSV(MSSV.getText());
		
		
			if(tenlop==null) 
			{
				lopLabel.setText("MSSV Không Tồn Tại");
				deleteAble=false;
				return ;
			}
			List<Mon> dsMon = BangDiemDAO.LayDSMonTheoSV(MSSV.getText());
			System.out.println(dsMon.size());
			List<String> mamon =MonDAO.ListMonToString(dsMon);
			boxModel.removeAllElements();
			if(mamon!=null)
				for(int i=0;i<mamon.size();i++)
				{
					boxModel.addElement(mamon.get(i));
				}
			listForallMon = MonDAO.layMonSVChuaDki(MSSV.getText());
			boxModel2.removeAllElements();
			if(listForallMon!=null)
				for(int i=0;i<listForallMon.size();i++)
				{
					boxModel2.addElement(listForallMon.get(i));
				}
			lopLabel.setText("Tên Lớp: " + tenlop);
			if(dsMon!=null)
				{
					String Data[][] =	BangDiemDAO.ListMonToArray(dsMon);
					
					model.update(Data);
					model.fireTableDataChanged();
				}
		    getContentPane().revalidate();
		    getContentPane().repaint();
		    deleteAble = true;
			}
		
		else 
			{
			lopLabel.setText("Nhập MSSV!!");
			deleteAble = false;
			}
		
	}
	 public void comboBoxFilter(String enteredText) {
         System.out.println(allMon.getItemCount());

            if (!allMon.isPopupVisible()) {
                allMon.showPopup();
            }

            List<String> filterArray= new ArrayList<String>();
            for (int i = 0; i < listForallMon.size(); i++) {
                if (listForallMon.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
                    filterArray.add(listForallMon.get(i));
                }
            }
            if (filterArray.size() > 0) {
                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) allMon.getModel();
                model.removeAllElements();
                model.addElement("");
                for (String s: filterArray)
                    model.addElement(s);

                JTextField textfield = (JTextField) allMon.getEditor().getEditorComponent();
                textfield.setText(enteredText);
            }
        }
}
