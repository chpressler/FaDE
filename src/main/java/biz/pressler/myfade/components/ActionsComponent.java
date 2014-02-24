package biz.pressler.myfade.components;

import biz.pressler.myfade.actions.FileCopyAction;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionsComponent extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton copy_btn = null;
	
	private JButton new_btn = null;
	
	private JButton move_btn = null;
	
	private JButton del_btn = null;
	
	private JButton rename_btn = null;
	
	private JButton mkdir_btn = null;
	
	public ActionsComponent() {
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
		copy_btn = new JButton("copy");
		copy_btn.addActionListener(new FileCopyAction());
		new_btn = new JButton("new");
		move_btn = new JButton("move");
		del_btn = new JButton("delete");
		rename_btn = new JButton("rename");
		mkdir_btn = new JButton("mkdir");
		
		this.setLayout(new GridLayout(1, 0, 5, 5));
		this.add(copy_btn);
		this.add(new_btn);
		this.add(move_btn);
		this.add(del_btn);
		this.add(rename_btn);
		this.add(mkdir_btn);
	}

}
