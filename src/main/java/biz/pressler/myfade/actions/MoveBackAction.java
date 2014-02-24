package biz.pressler.myfade.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import biz.pressler.myfade.ResourceHandler;

public class MoveBackAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	
	public MoveBackAction() {
		putValue(Action.NAME, "moveBack");
		putValue(Action.SMALL_ICON, new ImageIcon("resources/icons/" + ResourceHandler.getInstance().getIcons().getString("moveBack")));
		putValue(Action.SHORT_DESCRIPTION, ResourceHandler.getInstance().getStrings().getString("moveBack"));
	}

	public void actionPerformed(ActionEvent e) {

	}

}
