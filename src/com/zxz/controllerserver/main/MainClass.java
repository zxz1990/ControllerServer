package com.zxz.controllerserver.main;

import java.io.IOException;

import com.zxz.controllerserver.logic.MainLogic;
import com.zxz.controllerserver.ui.impls.MainUI;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainLogic logic;
		try {
			logic = new MainLogic(6666);
			MainUI ui = new MainUI(logic);
			logic.setNotifier(ui);
			ui.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
