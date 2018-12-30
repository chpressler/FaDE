package com.github.chpressler.fade.components;

public interface ExplorerComponentListener {
	
	void rootChanged(ExplorerComponentEvent e);
	
	void currentDirectoryPathChanged(ExplorerComponentEvent e);
	
	void selectionChanged(ExplorerComponentEvent e);
	
}
