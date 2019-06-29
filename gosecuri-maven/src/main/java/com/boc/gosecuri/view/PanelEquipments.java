package com.boc.gosecuri.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelEquipments extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CheckboxEquipment[] equipments;

	private JButton buttonBorrow;

	/**
	 * 
	 * @param equipments
	 */
	public PanelEquipments(CheckboxEquipment... equipments) {

		this.equipments = equipments;

		buttonBorrow = new JButton("Louer");
		buttonBorrow.addActionListener(this);

		this.setLayout(new BorderLayout());

		JPanel checkboxes = new JPanel();
		checkboxes.setLayout(new BoxLayout(checkboxes, BoxLayout.PAGE_AXIS));

		for (CheckboxEquipment equip : equipments) {
			checkboxes.add(equip.getCheckbox());
			
		}

		this.add(checkboxes, BorderLayout.CENTER);

		this.add(buttonBorrow, BorderLayout.SOUTH);

	}

	/**
	 * 
	 * @return equipment list
	 */
	public CheckboxEquipment[] getEquipments() {
		return equipments;
	}

	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Clique bouton 'louer' / " + this.getClass());
		
		String selectedEquipement = "Equipement selectionn� : ";
		
		for(CheckboxEquipment checkEquip : equipments) {
			if(checkEquip.isSelected()) 
				selectedEquipement += checkEquip.getEquipmentName() + " ";
		}
		
		System.out.println(selectedEquipement);
		
	}

}
