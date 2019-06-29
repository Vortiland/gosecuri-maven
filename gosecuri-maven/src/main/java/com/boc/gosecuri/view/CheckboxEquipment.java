package com.boc.gosecuri.view;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.boc.gosecuri.controller.Equipment;


public class CheckboxEquipment extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Equipment equipment;

	private JCheckBox checkbox;

//	private JSpinner boutonFleche;

	/**
	 * 
	 * @param equipment
	 */
	public CheckboxEquipment(Equipment equipment) {
		this.equipment = equipment;

		checkbox = new JCheckBox(equipment.getName());

		if (equipment.getActualQuantity() == 0)
			checkbox.setEnabled(false);

	}

	/**
	 * 
	 * @return true the checkbox is selected, false otherwise
	 */
	public boolean isSelected() {
		return checkbox.isSelected();
	}

	/**
	 * 
	 * @return the checkbox
	 */
	public JCheckBox getCheckbox() {
		return checkbox;
	}

	/**
	 * 
	 * @return the equipment
	 */
	public Equipment getEquipment() {
		return equipment;
	}

	/**
	 * 
	 * @return equipment's name, same as getEquipment().getName()
	 */
	public String getEquipmentName() {
		return equipment.getName();
	}

}
