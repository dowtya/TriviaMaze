package gui;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import controller.Game;

public class LaunchMenu {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JButton startButton = new JButton("Start");
				JButton loadButton  = new JButton("Load");
				JButton exitButton  = new JButton("Exit");
				
				JFrame frame = new JFrame("HelloWorldSwing");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				Game game = new Game();
				
				// New Game - has a button which creates new 'game' panel
				startButton.setBounds(130,50,100, 40);
				startButton.addActionListener((ActionEvent e)->{
					
					frame.remove(startButton);
					frame.remove(loadButton);
					frame.remove(exitButton);
					frame.validate();
					
					game.start("", (Void)->{
						frame.dispose();
						return Void;
					});
					System.out.println(game.getQuestionBox());
					frame.add(game.getQuestionBox());
					frame.add(game.getMap());
					frame.revalidate();
					frame.pack();
					frame.setSize(400,500);//400 width and 500 height  
					frame.setLayout(null);//using no layout managers  
					frame.repaint();
				});
				frame.add(startButton);
				
				// Load Game - has a button creates new game from file provided by file-browser
				loadButton.setBounds(130,100,100, 40);
				loadButton.addActionListener((ActionEvent e)->{
					String filename = "testFilename";
					game.start(filename, (Void)->{
						frame.dispose();
						return Void;
					});
				});
				frame.add(loadButton);

				// Exit - has a button which exits the program
				exitButton.setBounds(130,150,100, 40);
				exitButton.addActionListener((ActionEvent e)->{frame.dispose();});
				frame.add(exitButton);
				
				frame.setSize(400,500);//400 width and 500 height  
				frame.setLayout(null);//using no layout managers  
				
				
				frame.setVisible(true);	
			}
        });
		
		return;
	}
}
