package application;

//import java.time.LocalDate;

//import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class CustomGridPane /* extends GridPane */ {

	private final GridPane gridPane; // redundant if GridPane class is inherited

	public CustomGridPane() {
		// super(); //needed for inheritance
		// create new grid pane
		this.gridPane = new GridPane(); // also redundant when using inheritance (all gridPane objects will be
										// converted to "this" to align with the GridPane class

		/*
		 * pane.setCenter(Embed the code for the Gridpane - employment form. );
		 */

		// Column 0 takes 50% of the available width
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(50);
		col1.setHgrow(Priority.ALWAYS);// ensures that the column resizes when window is resized

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

		int row = 0;// use row counter for better organisation

		// row 0
		gridPane.add(new Label("First Name*:"), 0, row);
		gridPane.add(new Label("Last Name*:"), 1, row);

		// row 1
		row++;
		TextField firstNameField = new TextField();//Name the text fields for use later in code
		TextField lastNameField = new TextField();
		gridPane.add(firstNameField, 0, row);
		gridPane.add(lastNameField, 1, row);

		// row 2
		row++;
		gridPane.add(new Label("Email:"), 0, row);

		// row 3
		row++;
		TextField emailField = new TextField();
		gridPane.add(emailField, 0, row);

		// row 4
		row++;
		gridPane.add(new Label("Portfolio website:"), 0, row);

		// row 5
		row++;
		gridPane.add(new TextField(), 0, row, 2, 1);

		// row 6
		row++;
		gridPane.add(new Label("Position you are applying for*:"), 0, row);

		// row 7
		row++;
		gridPane.add(new TextField(), 0, row, 2, 1);

		// row 8
		row++;
		gridPane.add(new Label("Salary requirements:"), 0, row);
		gridPane.add(new Label("When can you start:"), 1, row);

		// row 9
		row++;
		gridPane.add(new TextField(), 0, row);
		gridPane.add(new TextField(), 1, row);

		// row 10
		row++;
		gridPane.add(new Label("Phone*:"), 0, row);
		gridPane.add(new Label("Fax:"), 1, row);

		// row 11
		row++;
		gridPane.add(new TextField(), 0, row);
		gridPane.add(new TextField(), 1, row);

		// row 12
		row++;
		gridPane.add(new Label("Are you willling to relocate:"), 0, row);

		// row 13
		row++;
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
		gridPane.add(relocateHbox, 0, row);

		// row 14
		row++;
		gridPane.add(new Label("Last company you worked for:"), 0, row);

		// row 15
		row++;
		gridPane.add(new TextField(), 0, row, 2, 1);

		// row 16
		row++;
		gridPane.add(new Label("Reference/Comments/Questions:"), 0, row);

		// row 17
		row++;
		gridPane.add(new TextArea(), 0, row, 2, 1);

		// row 18
		row++;
		// Create Submit button
		Button submitApplicationButton = new Button("Submit");
		submitApplicationButton.setOnAction(event -> {

			// Server Connection
			String url = "jdbc:mysql://localhost:3306/week9_LabWork_KevonMitchell";
			String username = "root";
			String password = "";

			// All queries
			String insertQry = "INSERT INTO application_form (firstname, lastname, email) VALUES (?, ?, ?)";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
				try (	
						// loads db driver
						Connection conn = DriverManager.getConnection(url, username, password);
						
						// Create a statement
						PreparedStatement pst = conn.prepareStatement(insertQry);){
					
						pst.setString(1, firstNameField.getText());
						pst.setString(2, lastNameField.getText());
						pst.setString(3, emailField.getText());

						// Execute update query
						pst.executeUpdate();
					/*st.close(); //close the statement
					conn.close();//close the connection*/ //not necessary with a try block
				}
				

			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			System.out.println("Application Sent");

		});

		// Create Read button
		Button readApplicationButton = new Button("Read");
		readApplicationButton.setOnAction(event -> {
			// Server Connection
			String url = "jdbc:mysql://localhost:3306/week9_LabWork_KevonMitchell";
			String username = "root";
			String password = "";

			// All queries
			String selectQry = "SELECT * FROM application_form WHERE lastname = ?";

				
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
				try (	
						// loads db driver
						Connection conn = DriverManager.getConnection(url, username, password);
						
						// Create a statement
						PreparedStatement pst = conn.prepareStatement(selectQry);){
						pst.setString(1, "Testlastname1");

						// Execute select query
						try(ResultSet rs = pst.executeQuery()) {

					// Process the results
					while (rs.next()) {
						String firstName = rs.getString("firstname");
						System.out.println(firstName);
						firstNameField.setText(rs.getString("firstname")) ;
						lastNameField.setText(rs.getString("lastname")) ;
						emailField.setText(rs.getString("email")) ;
					
					}
					/*st.close(); //close the statement
					conn.close();//close the connection*/ //not necessary with a try block
				}
				}

			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("Application Read");
		});

		/*
		 * //Add buttons to grid gridPane.add(submitApplicationButton, 0, row,1,1);
		 * gridPane.add(readApplicationButton, 1, row,1,1);
		 * 
		 * //center the button s GridPane.setHalignment(submitApplicationButton,
		 * HPos.CENTER); GridPane.setHalignment(readApplicationButton, HPos.CENTER);
		 */

		// alternate solution
		submitApplicationButton.setMaxWidth(Double.MAX_VALUE);// additional styling to ensure buttons are the same size
		readApplicationButton.setMaxWidth(Double.MAX_VALUE);// set width to any size, does not have to be max
		HBox.setHgrow(submitApplicationButton, Priority.ALWAYS);// additional styling to ensure buttons are the same
																// size
		HBox.setHgrow(readApplicationButton, Priority.ALWAYS);
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);// aligns buttons in box
		buttonBox.getChildren().addAll(submitApplicationButton, readApplicationButton);

		gridPane.add(buttonBox, 0, row, 2, 1);// adds Hbox with both buttons to gridPane
		GridPane.setHalignment(buttonBox, HPos.CENTER);

		// this.getChildren().add(gridPane);

	}

	// getter method to return custom grid pane object to add to the border pane
	// node when it's not inherited from GridPane class
	public GridPane getGridPane() {
		return this.gridPane;
	}

}
