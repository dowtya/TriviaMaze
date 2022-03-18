package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Game;

/**
 * A simple MenuBar that gives options to save, load, exit, and a button that displays a pop-up providing help.
 * @author Alec Dowty
 * @author Aaron Gitell
 * @author Joel Hemphill
 */
@SuppressWarnings("serial")
public class OptionBar extends JMenuBar {
	
	Game myGame;
	
	JMenu myFileMenu;
	JMenuItem mySaveMenuItem;
	JMenuItem myLoadMenuItem;
	
	JMenuItem myExitMenuItem;
	JMenuItem myHelpMenuItem;
	
	/**
	 * Initializes the OptionBar and its swing components.
	 * @param theGame A reference to the owning Game object.
	 */
	public OptionBar(Game theGame) {
		myGame = theGame;
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		myFileMenu = new JMenu("File");
		myFileMenu.setMnemonic(KeyEvent.VK_F);
		
		mySaveMenuItem = new JMenuItem("Save");
		mySaveMenuItem.setMnemonic(KeyEvent.VK_S);
		myFileMenu.add(mySaveMenuItem);
		
		myLoadMenuItem = new JMenuItem("Load");
		myLoadMenuItem.setMnemonic(KeyEvent.VK_L);
		myFileMenu.add(myLoadMenuItem);
		this.add(myFileMenu);
		
		myExitMenuItem = new JMenuItem("Exit");
		myExitMenuItem.setMnemonic(KeyEvent.VK_E);
		myExitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent theEvent) {
				myGame.myExitRoutine.apply(null);
			}
		});
		this.add(myExitMenuItem);
		
		myHelpMenuItem = new JMenuItem("Help");
		myHelpMenuItem.setMnemonic(KeyEvent.VK_H);
		myHelpMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent theEvent) {
				JOptionPane.showMessageDialog(myExitMenuItem, "Click the buttons on the grid to navigate between rooms.\nAnswering questions incorrectly will block the path in that direction.\nNavigate to the bottom-right corner to win.");
			}
		});
		this.add(myHelpMenuItem);
	}
}
