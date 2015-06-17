package com.jensui.projects.fade.components;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ViewSwitcherComponent extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ButtonGroup bg = null;
	
	private JToggleButton tableview = null;
	
	private JToggleButton treeview = null;
	
	private ArrayList<ViewSwitcherListener> listeners;
	
	public ViewSwitcherComponent(final FaDEComponent fc) {
		setOpaque(false);
		listeners = new ArrayList<ViewSwitcherListener>();
		setLayout(new FlowLayout());
		bg = new ButtonGroup();
		tableview = new JToggleButton("table", true);
		treeview = new JToggleButton("tree");
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
