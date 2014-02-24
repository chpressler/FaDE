package biz.pressler.myfade.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import biz.pressler.myfade.ResourceHandler;

public class RefreshAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public RefreshAction() {
		putValue(Action.NAME, "refresh");
		putValue(Action.SMALL_ICON, new ImageIcon("resources/icons/" + ResourceHandler.getInstance().getIcons().getString("refresh")));
		putValue(Action.SHORT_DESCRIPTION, ResourceHandler.getInstance().getStrings().getString("refresh"));
	}

	public void actionPerformed(ActionEvent e) {

	}

}
