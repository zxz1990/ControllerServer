package com.zxz.controllerserver.ui.impls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.zxz.controllerserver.logic.MainLogic;
import com.zxz.controllerserver.ui.interfaces.INotifier;
import com.zxz.controllerserver.utils.Strings;

public class MainUI implements INotifier{
	JButton mButtonStart =  new JButton(Strings.start_listening);
	JButton mButtonStop = new JButton(Strings.stop_listening);
	JLabel mText = new JLabel("text");
	JFrame mFrame = new JFrame(Strings.server);
	
	public MainUI(MainLogic logic) {
		// TODO Auto-generated constructor stub
		mText.setPreferredSize(new Dimension(750, 50));
		mText.setBackground(Color.CYAN);
		mFrame.add(mText);
		mFrame.add(mButtonStart);
		mFrame.add(mButtonStop);
		
		mFrame.setSize(800, 600);
		mFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		mFrame.setLocationRelativeTo(null);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mButtonStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logic.startListening();
			}
		});
		
		mButtonStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logic.stopListening();
			}
		});
		
	}
	
	public void show() {
		mFrame.setVisible(true);
	}

	@Override
	public void notifyMessage(String msg) {
		// TODO Auto-generated method stub
		mText.setText(msg);
	}	
}
