package com.boc.gosecuri.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelDisconnection extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private UI frameMain;

	private JButton buttonDisconnection;

	/**
	 * 
	 * @param mainFrame
	 */
	public PanelDisconnection(UI mainFrame) {
		this.frameMain = mainFrame;

		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		buttonDisconnection = new JButton("Deconnexion");
		buttonDisconnection.addActionListener(this);
		buttonDisconnection.setPreferredSize(new Dimension(150, 20));

		this.add(buttonDisconnection);
	}

	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
