package com.boc.gosecuri.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelBorrow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param paneldisconnection
	 * @param panelPicture
	 * @param panelEquipments
	 */
	public PanelBorrow(PanelDisconnection paneldisconnection, PanelPicture panelPicture,
			PanelEquipments panelEquipments) {
		this.setLayout(new BorderLayout());

		JPanel panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		panelTop.add(paneldisconnection, BorderLayout.WEST);
		panelTop.add(panelPicture, BorderLayout.EAST);

		this.add(panelTop, BorderLayout.NORTH);
		this.add(panelEquipments, BorderLayout.CENTER);
	}

}
