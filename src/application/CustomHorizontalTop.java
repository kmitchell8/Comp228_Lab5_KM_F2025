package application;


import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CustomHorizontalTop extends VBox {
	
	
	private final HBox topHbox;
	
	public CustomHorizontalTop() {
		super();//VBox inheritance
		
		//Hbox Layout
	this.topHbox = new HBox(); //hbox(integer value that sets spacing between children)//creates new Hbox in constructor
	//to assign to topHbox
	topHbox.setPadding(new Insets(0,0,0,0));
	topHbox.setAlignment(Pos.CENTER);
	topHbox.setStyle("");//sets the background colour possibly other settings ("-fx-background-color: #336699;")
	
	//HBox properties
	/*
	Label setTitle = new Label("Favourite Sport: MOST");
	setTitle.setFont(Font.font(BASELINE_OFFSET_SAME_AS_HEIGHT)); //other options Font.font("Arial", FontWeight.BOLD, 20)
	setTitle.setStyle("");//"-fx-text-fill: white;"
	*/
	String imageLocation = getClass().getResource("resources/images/banner.png").toExternalForm();
	ImageView image = new ImageView(imageLocation);//https://www.pexels.com/photo/blue-orange-black-green-white-adidas-soccer-ball-on-green-field-47354/
	//set width*height image
	image.setFitWidth(500); 
    image.setFitHeight(125); 
    image.setPreserveRatio(true);
	//add image to Hbox
	topHbox.getChildren().addAll(image);
	
	
	//add to Vbox 
	this.getChildren().add(topHbox);
	//return 
	}

	
	/*
	private HBox createBottomHBox() {
		LocalDate date = LocalDate.now();
		//System.out.println(LocalDate.now());
		
		return date;
	}
	*/
}