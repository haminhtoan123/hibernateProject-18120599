/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Swing;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;


/**
 *
 * @author bob50
 * @param String
 */
public class SimpleTableModel extends AbstractTableModel
{
   protected String[] cols;
   protected String[][] Data;

    public SimpleTableModel(String[][] Data, String[] cols ) {
        this.cols = cols;
        this.Data = Data;
    }
    public void update (String[][] Data)
    {
    	this.Data = Data;
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
//    @Override
//    public  Object getValueAt(int rowIndex, int columnIndex) {
//       try {
//           List<Method> getMethods=ClassRefect.getAllGetMethod(Data.get(rowIndex));          
//           return getMethods.get(columnIndex).invoke(Data.get(rowIndex), null);
//       } catch (IllegalAccessException ex) {
//           Logger.getLogger(SimpleTableModel.class.getName()).log(Level.SEVERE, null, ex);
//       } catch (IllegalArgumentException ex) {
//           Logger.getLogger(SimpleTableModel.class.getName()).log(Level.SEVERE, null, ex);
//       } catch (InvocationTargetException ex) {
//           Logger.getLogger(SimpleTableModel.class.getName()).log(Level.SEVERE, null, ex);
//       }
//       return "";
//    }
    
}
