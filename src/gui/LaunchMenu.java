package gui;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import controller.Game;

public class LaunchMenu {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				JFrame frame = new JFrame("HelloWorldSwing");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				Game game = new Game();
 
				// New Game - has a button which creates new 'game' panel
				JButton startButton = new JButton("Start");
				startButton.addActionListener((ActionEvent e)->{
					game.start("", (Void)->{
						frame.dispose();
						return Void;
					});
				});
				frame.getContentPane().add(startButton);
				
				// Load Game - has a button creates new game from file provided by file-browser
				JButton loadButton = new JButton("Load");
				loadButton.addActionListener((ActionEvent e)->{
					String filename = "testFilename";
					game.start(filename, (Void)->{
						frame.dispose();
						return Void;
					});
				});
				frame.getContentPane().add(loadButton);
				
				// Exit - has a button which exits the program
				JButton exitButton = new JButton("Exit");
				exitButton.addActionListener((ActionEvent e)->{frame.dispose();});
				frame.getContentPane().add(exitButton);
 
				frame.pack();
				frame.setVisible(true);
			}
        });
		
		return;
	}
}
