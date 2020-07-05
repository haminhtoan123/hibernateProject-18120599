/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Swing;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import BangDiem.BangDiemDAO;


/**
 *
 * @author bob50
 * @param String
 */
public class xemBDModel extends AbstractTableModel 
{
   protected String[] cols;
   protected String[][] Data;

    public xemBDModel(String[][] Data, String[] cols ) {
        this.cols = cols;
        this.Data = Data;
    }
    public void update (String[][] Data)
    {
    	this.Data = Data;
    }
    public void getByMSSV(String MSSV)
    {
    	for(int i = 0; i<this.getRowCount();i++)
    	{
    		if(!Data[i][0].equals(MSSV))
    		{
    			removeRow(i);
    			i--;
    		}
    	}
    	
    }
    public void removeRow(int row)
    {
    	List<String[]> temp = new ArrayList<String[]>(Arrays.asList(Data));
    	temp.remove(row);
        Data = temp.toArray(new String[][]{});
        fireTableRowsDeleted(row, row);
    }
    
    public String[] getCols() {
        return cols;
    }

    public void setCols(String[] cols) {
        this.cols = cols;
    }

    public String[][] getData() {
        return Data;
    }

    public void setDat(String[][] Data) {
        this.Data = Data;
    }
 
    @Override
    public int getRowCount() {
        return Data.length;
    }

    @Override
    public int getColumnCount() {
        return  cols.length;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return Data[rowIndex][columnIndex];
	}
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	@Override 
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		Data[rowIndex][columnIndex] = (String) aValue;
		
		BangDiemDAO.Update(Data[rowIndex][0],xemBDFrame.Lop.getItemAt(xemBDFrame.Lop.getSelectedIndex()).toString() ,xemBDFrame.MaMon.getItemAt(xemBDFrame.MaMon.getSelectedIndex()).toString(),Float.parseFloat(Data[rowIndex][2]),Float.parseFloat(Data[rowIndex][3]),Float.parseFloat(Data[rowIndex][4]),Float.parseFloat(Data[rowIndex][5]));
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
	    return true;
	}

    
}
