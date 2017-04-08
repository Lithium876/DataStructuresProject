package application;
import javafx.fxml.FXML;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class MainController {

	@FXML
	private Label MyMessage;
	
	public void generateRandom(ActionEvent event)
	{
	Random rand = new Random();
	int myRand = rand.nextInt(50) + 1;
	//System.out.println(Integer.toString(myRand));
	//MyMessage.setText(Integer.toString(myRand));
	
		
	}
}
