package com.github.chpressler.fade.components;

import com.github.chpressler.fade.actions.FTPAction;
import com.github.chpressler.fade.actions.MoveBackAction;
import com.github.chpressler.fade.actions.MoveForwardAction;
import com.github.chpressler.fade.actions.RefreshAction;
import com.github.chpressler.fade.actions.SearchAction;

import javax.swing.JToolBar;

public class FaDEToolBar extends JToolBar {
	
	private static final long serialVersionUID = 1L;

	public FaDEToolBar() {
//		setBackground(new Color(80, 30, 30));
//		putClientProperty(Options.HEADER_STYLE_KEY, HeaderStyle.BOTH);
		setFloatable(false);
//        putClientProperty("JToolBar.isRollover", Boolean.TRUE);
        
		this.add(new MoveBackAction());
		this.add(new MoveForwardAction());
		this.addSeparator();
		this.add(new RefreshAction());
		this.add(new SearchAction());
		this.add(new FTPAction());
	}

}
