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
import javax.swing.table.DefaultTableModel;

import BangDiem.BangDiemDAO;
import Mon.Mon;
import Mon.MonDAO;
import SinhVien.SinhVienDAO;

public class XemDsLopFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox Lop ;
	public static JComboBox MaMon;
	private NormalTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private List<Mon> dsMon;
	private List<String> mamon;
	private DefaultComboBoxModel boxModel;
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
		
		Lop = new JComboBox(new DefaultComboBoxModel<String>(BangDiemDAO.LayDSLop().toArray(new String[0])));
		Lop.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		      	comboBoxEvent(e);
		    }
		});
		//comboBox.setBounds(133, 55, 72, 22);
		contentPane.add(Lop);

		dsMon = MonDAO.LayDanhSachMonTheoLop(Lop.getItemAt(Lop.getSelectedIndex()).toString());
	
		mamon =MonDAO.ListMonToString(dsMon);
		mamon.add(0, null );
		boxModel = new DefaultComboBoxModel<String>(mamon.toArray(new String[0]));
		MaMon = new JComboBox(boxModel);
		
		contentPane.add(MaMon);
		JButton btnNewButton = new JButton("Xem");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemActionPerformed(e);
			}
		});
		contentPane.add(btnNewButton);
		String columns[]= {"MSSV","Họ Tên","Giới Tính","CMND"};
		String Data[][] = SinhVienDAO.LayDSSVTheoLop(Lop.getItemAt(Lop.getSelectedIndex()).toString());
		model = new NormalTableModel(Data,columns);
		
		//System.out.println(model.Data[0][0]);
		table = new JTable(model);
		 scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		

		contentPane.add(scrollPane);
	}
	private void comboBoxEvent(ActionEvent e)
	{
		dsMon = MonDAO.LayDanhSachMonTheoLop(Lop.getItemAt(Lop.getSelectedIndex()).toString());
		mamon =MonDAO.ListMonToString(dsMon);
	//	boxModel.addElement("ecs");
		boxModel.removeAllElements();
		boxModel.addElement(null);
		for(int i=0;i<mamon.size();i++)
		{
			boxModel.addElement(mamon.get(i));
		}
		//model = new DefaultComboBoxModel<String>(mamon.toArray(new String[0]));
	
		contentPane.revalidate();
		contentPane.repaint();
	}
	private void xemActionPerformed(ActionEvent e)
	{
	
		//System.out.println(Data[0][0]+ " : " +Data[0][1]+ " : " + Data[0][2]);
		//table.removeAll();
		//table.setModel(temp);
		if(MaMon.getItemAt(MaMon.getSelectedIndex())==null)
		model.update(SinhVienDAO.LayDSSVTheoLop(Lop.getItemAt(Lop.getSelectedIndex()).toString()));
		else
			model.update(BangDiemDAO.LayDSSVTheoLop(Lop.getItemAt(Lop.getSelectedIndex()).toString(), MaMon.getItemAt(MaMon.getSelectedIndex()).toString()));
	//	scrollPane.setBounds(27, 394, 505, -285);
	
	    contentPane.revalidate();
	    contentPane.repaint();
	}
}
