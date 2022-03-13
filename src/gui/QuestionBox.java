package gui;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;
import javax.swing.*;

@SuppressWarnings("serial")
public class QuestionBox extends JPanel implements ActionListener {
	
	JLabel questionPanel;
	Timer shakeTimer;
	
	AnswerPanel answerPanel;
	
	// Settings for the shake that occurs when an incorrect answers is given
	static final long SHAKE_DURATION = 500; // Shake duration in milliseconds
	static final int SHAKE_FREQUENCY = 2; // Frequency in Hertz
	static final int SHAKE_MAGNITUDE = 10; // Amplitude of the shake in pixels
	
	public QuestionBox() {
		questionPanel = new JLabel();
		answerPanel = new AnswerPanel();
		answerPanel.setBounds(50, 20, 200, 200);
		answerPanel.setBackground(Color.gray);
		
		questionPanel.setBorder(BorderFactory.createTitledBorder("Question"));
		questionPanel.setBounds(50, 240, 200, 200);
		questionPanel.setBackground(Color.gray);
		this.add(questionPanel);
		this.add(answerPanel);
		this.setBounds(0,0,1000,1000);
		this.setLayout(null);
		this.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		// on answer result received
		if (e.getSource() == answerPanel) {
			if(!answerPanel.isCorrect) {
				final Point startPos = this.getLocation();
				final long startTime = System.currentTimeMillis();
			    shakeTimer = new Timer(5, shakeEvent -> {
			    	long elapsedTime = System.currentTimeMillis() - startTime;
			    	
			    	this.setLocation(
			    			startPos.x + (int)(Math.sin(elapsedTime * 2 * Math.PI * SHAKE_FREQUENCY) * SHAKE_MAGNITUDE),
			    			startPos.y
	    			);
			    	
			    	if (elapsedTime > SHAKE_DURATION) {
			    		shakeTimer.stop();
			    		this.setLocation(startPos);
			    		this.repaint();
			    		
			    		questionPanel.removeAll();
			    	}
			    });
			    shakeTimer.start();
			}
		}
	}
	
	public void displaySingleChoiceQuestion(String question, ArrayList<String> answers, Function<Integer, Boolean> evalAnswerFunc) {
		questionPanel.setText(question);
		answerPanel.addSingleChoiceAnswers(answers, evalAnswerFunc, this);
		
		questionPanel.repaint();
		answerPanel.repaint();
	}	
}
