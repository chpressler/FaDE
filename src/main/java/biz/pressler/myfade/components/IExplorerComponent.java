package biz.pressler.myfade.components;

import java.io.File;
import java.util.ArrayList;

public interface IExplorerComponent {
	
	File getRoot();
	
	void setRoot(File f);
	
	File getCurrentDirectory();
	
	void setCurrentDirectory(File f);
	
	File[] getSelectedFiles();
	
	File getLastSelected();
	
	void setSelectedFiles(File[] selection);
	
	void addExplorerComponentListener(ExplorerComponentListener l);
	
	void removeExplorerComponentListener(ExplorerComponentListener l);
	
	ArrayList<ExplorerComponentListener> getExplorerComponentListeners();
	
	void selectionChanged(File lastSelected);
}
