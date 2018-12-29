package com.github.chpressler.fade.components;

import com.github.chpressler.fade.actions.*;

import javax.swing.*;
import java.awt.*;

public class ActionsComponent extends JPanel {

	private static final long serialVersionUID = 1L;

    public ActionsComponent() {
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton copy_btn = new JButton("copy");
		copy_btn.addActionListener(new FileCopyAction());
        JButton new_btn = new JButton("new");
		new_btn.addActionListener(new FileNewAction());
        JButton move_btn = new JButton("move");
		move_btn.addActionListener(new FileMoveAction());
        JButton del_btn = new JButton("delete");
		del_btn.addActionListener(new FileDeleteAction());
        JButton rename_btn = new JButton("rename");
		rename_btn.addActionListener(new FileRenameAction());
        JButton mkdir_btn = new JButton("mkdir");
		mkdir_btn.addActionListener(new FileMKDIRAction());
		
		this.setLayout(new GridLayout(1, 0, 5, 5));
		this.add(copy_btn);
		this.add(new_btn);
		this.add(move_btn);
		this.add(del_btn);
		this.add(rename_btn);
		this.add(mkdir_btn);
	}

}
