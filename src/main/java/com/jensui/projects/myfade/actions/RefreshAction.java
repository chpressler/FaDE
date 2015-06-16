package com.jensui.projects.myfade.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import com.jensui.projects.myfade.ResourceHandler;

public class RefreshAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public RefreshAction() {
		putValue(Action.NAME, "refresh");
        String image = ResourceHandler.getInstance().getIcons().getString("refresh");
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("/icons/"+image)));
		putValue(Action.SHORT_DESCRIPTION, ResourceHandler.getInstance().getStrings().getString("refresh"));
	}

	public void actionPerformed(ActionEvent e) {

	}

}
