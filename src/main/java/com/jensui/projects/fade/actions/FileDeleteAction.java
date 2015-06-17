package com.jensui.projects.fade.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class FileDeleteAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;

	public void actionPerformed(ActionEvent e) {

		System.out.println(e.getActionCommand());
		
	}

}
