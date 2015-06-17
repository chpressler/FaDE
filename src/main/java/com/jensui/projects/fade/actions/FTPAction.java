package com.jensui.projects.fade.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import com.jensui.projects.fade.ResourceHandler;

public class FTPAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public FTPAction() {
		putValue(Action.NAME, "ftp");
        String image = ResourceHandler.getInstance().getIcons().getString("ftp");
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("/icons/"+image)));
		putValue(Action.SHORT_DESCRIPTION, ResourceHandler.getInstance().getStrings().getString("ftp"));
	}

	public void actionPerformed(ActionEvent e) {

	}

}
