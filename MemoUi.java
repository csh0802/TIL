package test3.notepad;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.java.swing.action.SaveAction;

import java.awt.*;

public class MemoUi {
	
	public void createUi() {
		Frame f = new Frame("�޸�"); 
		TextArea ta = new TextArea();
		MenuBar mb = new MenuBar();
		Menu file_menu = new Menu("����");
		Menu edit_menu = new Menu("����");
		MenuItem new_item = new MenuItem("���θ����");
		MenuItem open_item = new MenuItem("����");
		MenuItem save_item = new MenuItem("�����ϱ�");
				
		file_menu.add(new_item);
		file_menu.add(open_item);
		file_menu.add(save_item);
		mb.add(file_menu);
		mb.add(edit_menu);
		f.setMenuBar(mb);
		f.add(ta);
		f.setSize(400,500);
		f.setLocation(900,150);
		
	
		
		open_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.setText("");
			}
		});
		
		new_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.setText("");
			}
		});
		save_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ec) {
				//������̾�α� ������
				FileDialog save_dialog = new FileDialog(f,"����",FileDialog.SAVE);
				save_dialog.setVisible(true);
				
			}
		});
		

		f.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new MemoUi().createUi();
	}

}
