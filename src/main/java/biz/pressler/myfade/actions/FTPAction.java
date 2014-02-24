package biz.pressler.myfade.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import biz.pressler.myfade.ResourceHandler;

public class FTPAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public FTPAction() {
		putValue(Action.NAME, "ftp");
		putValue(Action.SMALL_ICON, new ImageIcon("resources/icons/" + ResourceHandler.getInstance().getIcons().getString("ftp")));
		putValue(Action.SHORT_DESCRIPTION, ResourceHandler.getInstance().getStrings().getString("ftp"));
	}

	public void actionPerformed(ActionEvent e) {

	}

}
