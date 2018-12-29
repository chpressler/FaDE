package com.github.chpressler.fade.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import com.github.chpressler.fade.ResourceHandler;

public class SearchAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public SearchAction() {
		putValue(Action.NAME, "search");
        String image = ResourceHandler.getInstance().getIcons().getString("search");
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("/icons/"+image)));
		putValue(Action.SHORT_DESCRIPTION, ResourceHandler.getInstance().getStrings().getString("search"));
	}

	public void actionPerformed(ActionEvent e) {

	}

}