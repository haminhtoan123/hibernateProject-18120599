package Processing;

import Import.JDBC;
import Swing.LoginJFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JDBC j = new JDBC();
		j.Connect();
		j.Close();
		LoginJFrame lg = new LoginJFrame();
		lg.setVisible(true);
	}

}
