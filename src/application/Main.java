package application;

import java.time.LocalDate;

import javafx.application.Application;
//import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		// Place nodes in the pane

		pane.setTop(new CustomHorizontalTop());//adds custom horizontal top object to top border pane
		pane.setRight(new CustomPane("Right"));
		pane.setLeft(new CustomPane("Left"));
		pane.setCenter(new CustomGridPane().getGridPane());// embed gridPane into the center pane//child of Border pane
		
		//create new grid pane
		
		
		/*
		 * pane.setCenter(Embed the code for the Gridpane - employment form. );
		 */

		/*
		GridPane gridPane = new GridPane();

		// Column 0 takes 50% of the available width
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(50);
		col1.setHgrow(Priority.ALWAYS);//ensures that the column resizes when window is resized

		// Column 1 takes 50% of the available width
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(50);
		col2.setHgrow(Priority.ALWAYS);

		// Add the constraints to the GridPane
		gridPane.getColumnConstraints().addAll(col1, col2);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gridPane.setHgap(5.5);
		gridPane.setVgap(5.5);
		// Place nodes in the pane
		gridPane.add(new Label("First Name*:"), 0, 0);
		gridPane.add(new TextField(), 0, 1);

		gridPane.add(new Label("Last Name*:"), 1, 0);
		gridPane.add(new TextField(), 1, 1);
		gridPane.add(new Label("Email:"), 0, 2);
		gridPane.add(new TextField(), 0, 3);
		gridPane.add(new Label("Portfolio website:"), 0, 4);
		gridPane.add(new TextField(), 0, 5, 2, 1);
		gridPane.add(new Label("Position you are applying for*:"), 0, 6);
		gridPane.add(new TextField(), 0, 7, 2, 1);
		gridPane.add(new Label("Salary requirements:"), 0, 8);
		gridPane.add(new TextField(), 0, 9);
		gridPane.add(new Label("When can you start:"), 1, 8);
		gridPane.add(new TextField(), 1, 9);
		gridPane.add(new Label("Phone*:"), 0, 10);
		gridPane.add(new TextField(), 0, 11);
		gridPane.add(new Label("Fax:"), 1, 10);
		gridPane.add(new TextField(), 1, 11);
		gridPane.add(new Label("Are you willling to relocate:"), 0, 12);
		// create radio buttons and radio button toggle group
		ToggleGroup relocateGroup = new ToggleGroup();
		RadioButton rbYes = new RadioButton("Yes");
		RadioButton rbNo = new RadioButton("No");
		RadioButton rbNotSure = new RadioButton("Not Sure");
		rbYes.setToggleGroup(relocateGroup);
		rbNo.setToggleGroup(relocateGroup);
		rbNotSure.setToggleGroup(relocateGroup);
		// preselect a button
		rbNo.setSelected(true);
		HBox relocateHbox = new HBox(5, rbYes, rbNo, rbNotSure);// 5 is the spacing between radio buttons
		// gridPane.add(new HBox(5,rbYes,rbNo,rbNotSure), 1,6);
		gridPane.add(relocateHbox, 0, 13);
		gridPane.add(new Label("Last company you worked for:"), 0, 14);
		gridPane.add(new TextField(), 0, 15, 2, 1);
		gridPane.add(new Label("Reference/Comments/Questions:"), 0, 16);
		gridPane.add(new TextArea(), 0, 17, 2, 1);

		// Create button
		Button sendApplicationButton = new Button("Send Application");

		sendApplicationButton.setOnAction(event -> {
			System.out.println("Application Sent");
		});
		
		//Add button to grid
		gridPane.add(sendApplicationButton, 0, 18,2,1);
		//center the button
		GridPane.setHalignment(sendApplicationButton, HPos.CENTER);	
		
		pane.setCenter(gridPane);// embed gridPane into the center pane//child of Border pane
		
		*/
		

		HBox bottomHbox = new HBox();
		bottomHbox.setPadding(new Insets(0, 0, 0, 0));
		bottomHbox.setAlignment(Pos.CENTER);
		// bottomHbox.setStyle("");//sets the background colour possibly other settings
		// ("-fx-background-color: #336699;")
		//gets todays date, adds it to a label and then to an hbox to embed in in bottom boder pane
		LocalDate date = LocalDate.now();
		Label todayLabel = new Label(date.toString());
		bottomHbox.getChildren().addAll(todayLabel);
		
		//adds hbox date the bottom section of the border pane
		pane.setBottom(bottomHbox);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Application Form"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

	}

	public static void main(String[] args) {
		launch(args);
	}
}