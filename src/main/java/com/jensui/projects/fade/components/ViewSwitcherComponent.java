package com.jensui.projects.fade.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewSwitcherComponent extends JPanel {

	private static final long serialVersionUID = 1L;

	private final ArrayList<ViewSwitcherListener> listeners;
	
	public ViewSwitcherComponent(final FaDEComponent fc) {
		setOpaque(false);
		listeners = new ArrayList<ViewSwitcherListener>();
		setLayout(new FlowLayout());
		ButtonGroup bg = new ButtonGroup();
		JToggleButton tableview = new JToggleButton("table", true);
		JToggleButton treeview = new JToggleButton("tree");
		bg.add(tableview);
		bg.add(treeview);
		tableview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IExplorerComponent iec = new FileTableComponent(fc.getURLComponent(), fc.getDriveSelectComponent());
				iec.setCurrentDirectory(fc.getIExplorerComponent().getCurrentDirectory());
				iec.setRoot(fc.getIExplorerComponent().getRoot());
				fc.setFileComponent(iec);
				for(ViewSwitcherListener l : listeners) {
					l.viewChanged(iec);
				}
			}});
		treeview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IExplorerComponent iec = new FileTreeComponent(fc.getURLComponent(), fc.getDriveSelectComponent());
				iec.setCurrentDirectory(fc.getIExplorerComponent().getCurrentDirectory());
				iec.setRoot(fc.getIExplorerComponent().getRoot());
				fc.setFileComponent(iec);
				for(ViewSwitcherListener l : listeners) {
					l.viewChanged(iec);
				}
			}});
		add(tableview);
		add(treeview);
	}

}
