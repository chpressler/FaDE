package biz.pressler.myfade.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import biz.pressler.myfade.ResourceHandler;

public class MoveForwardAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;

	public MoveForwardAction() {
		putValue(Action.NAME, "moveForward");
		putValue(Action.SMALL_ICON, new ImageIcon("resources/icons/" + ResourceHandler.getInstance().getIcons().getString("moveForward")));
		putValue(Action.SHORT_DESCRIPTION, ResourceHandler.getInstance().getStrings().getString("moveForward"));
	}

	public void actionPerformed(ActionEvent e) {

	}

}