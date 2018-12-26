package com.jensui.projects.fade.components;

interface ExplorerComponentListener {
	
	void rootChanged(ExplorerComponentEvent e);
	
	void currentDirectoryPathChanged(ExplorerComponentEvent e);
	
	void selectionChanged(ExplorerComponentEvent e);
	
}
