package com.jensui.projects.fade.components;

import com.jensui.projects.fade.IFile;

import javax.swing.*;
import java.util.ArrayList;

public class FileThumbnailComponent extends JPanel implements IExplorerComponent {

	private static final long serialVersionUID = 1L;


	@Override
	public IFile getRoot() {
		return null;
	}

	@Override
	public void setRoot(IFile f) {

	}

	@Override
	public IFile getCurrentDirectory() {
		return null;
	}

	@Override
	public void setCurrentDirectory(IFile f) {

	}

	@Override
	public IFile[] getSelectedFiles() {
		return new IFile[0];
	}

	@Override
	public IFile getLastSelected() {
		return null;
	}

	@Override
	public void addExplorerComponentListener(ExplorerComponentListener l) {

	}

	@Override
	public void removeExplorerComponentListener(ExplorerComponentListener l) {

	}

	@Override
	public ArrayList<ExplorerComponentListener> getExplorerComponentListeners() {
		return null;
	}

	@Override
	public void selectionChanged(IFile lastSelected) {

	}

}
