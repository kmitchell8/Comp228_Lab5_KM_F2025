package application;

//import java.time.LocalDate;

//import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
//import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.binding.Bindings;

public class CustomGridPane /* extends GridPane */ {

	private final GridPane gridPane; // redundant if GridPane class is inherited
	/*
	 * private Boolean isFullNameValid = false; private Boolean isContactNumbValid =
	 * false; private Boolean isSalaryValid = false; //private Boolean
	 * isEducationSelected = false; //private Boolean isDateValid = false;
	 */
	private SimpleBooleanProperty isFullNameValid = new SimpleBooleanProperty(false);
	private SimpleBooleanProperty isContactNumbValid = new SimpleBooleanProperty(false);
	private SimpleBooleanProperty isSalaryValid = new SimpleBooleanProperty(false);
	//private SimpleBooleanProperty isEducationSelected = new SimpleBooleanProperty(false);
	//private SimpleBooleanProperty isDateValid = new SimpleBooleanProperty(false); 

	public CustomGridPane() {

		// super(); //needed for inheritance
		// create new grid pane
		this.gridPane = new GridPane(); // also redundant when using inheritance (all gridPane objects will be
										// converted to "this" to align with the GridPane class

		/*
		 * pane.setCenter(Embed the code for the Gridpane - employment form. );
		 */

		// Column 0 takes 25% of the available width
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(25);
		col1.setHgrow(Priority.ALWAYS);// ensures that the column resizes when window is resized

		// Column 1 takes 25% of the available width
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(25);
		col2.setHgrow(Priority.ALWAYS);

		// Column 0 takes 25% of the available width
		ColumnConstraints col3 = new ColumnConstraints();
		col1.setPercentWidth(25);
		col1.setHgrow(Priority.ALWAYS);// ensures that the column resizes when window is resized

		// Column 1 takes 25% of the available width
		ColumnConstraints col4 = new ColumnConstraints();
		col2.setPercentWidth(25);
		col2.setHgrow(Priority.ALWAYS);

		// Add the constraints to the GridPane
		gridPane.getColumnConstraints().addAll(col1, col2, col3, col4);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gridPane.setHgap(5.5);
		gridPane.setVgap(5.5);
		// Place nodes in the pane

		int row = 0;// use row counter for better organisation

		// row 0
		gridPane.add(new Label("Personal Information"), 0, row, 2, 1); // (col index, row index, col span, row span)

		// row 1
		row = row + 2;
		gridPane.add(new Label("Full Name"), 0, row, 1, 1);
		TextField fullNameField = new TextField();// Name the text fields for use later in code
		gridPane.add(fullNameField, 1, row, 3, 1);

		// Name length Validation
		fullNameField.textProperty().addListener((obs, oldValue, newValue) -> {
			// 1. Length constraint (max 50)
			if (newValue.length() > 50) {
				fullNameField.setText(oldValue); // Revert to old value if too long
			}

			/*
			 * if(!newValue.trim().isEmpty()&&newValue.length()<=50&&newValue.matches(
			 * "[a-zA-Z\\s]+")) { isFullNameValid=true; }
			 */
			boolean isValid = !newValue.trim().isEmpty() && newValue.length() <= 50 && newValue.matches("[a-zA-Z\\s]+");

			isFullNameValid.set(isValid);
		});

		// row 2
		row = row + 2;
		gridPane.add(new Label("Current Address"), 0, row, 1, 1);
		TextField currentAddField = new TextField();// Name the text fields for use later in code
		gridPane.add(currentAddField, 1, row, 3, 1);

		// row 3
		row = row + 2;
		Label contactLabel = new Label("Contact Number");
		contactLabel.setWrapText(true);
		gridPane.add(contactLabel, 0, row, 1, 2);
		TextField contactNumbField = new TextField();// Name the text fields for use later in code
		gridPane.add(contactNumbField, 1, row, 1, 1);

		// contact number validation

		contactNumbField.textProperty().addListener((obs, oldValue, newValue) -> {
			// Only allow digits (0-9)
			if (!newValue.matches("\\d*")) {
				contactNumbField.setText(newValue.replaceAll("[^\\d]", ""));
			}
			// Length constraint (exactly 10 digits)
			if (newValue.length() > 10) {
				contactNumbField.setText(oldValue); // Revert to old value if too long
			}

			/*if (newValue.matches("\\d{10}")) {
				isContactNumbValid = true;
			}*/
			isContactNumbValid.set(newValue.matches("\\d{10}"));
		});
		Label emailLabel = new Label("Email Address");
		emailLabel.setWrapText(true);
		gridPane.add(emailLabel, 2, row, 1, 2);
		TextField emailField = new TextField();// Name the text fields for use later in code
		gridPane.add(emailField, 3, row, 1, 1);

		// row 4
		row = row + 2;
		Label educationLabel = new Label("Highest Educational Attainment");
		educationLabel.setWrapText(true);
		gridPane.add(educationLabel, 0, row, 1, 2);
		ChoiceBox<String> educationField = new ChoiceBox<>();// drop down menu box
		educationField.getItems().addAll("", "College Diploma", "Bachelors", "Masters");
		educationField.setValue("");// default value
		gridPane.add(educationField, 1, row, 1, 1);

		/*
		 * educationField.valueProperty().addListener((obs, oldValue, newValue) -> {
		 * if(newValue != null && !newValue.isEmpty()){ isEducationSelected=true; } });
		 */

		Label genderLabel = new Label("Gender");
		genderLabel.setWrapText(true);
		// gridPane.add(genderLabel, 2, row, 1, 1);
		ToggleGroup genderGroup = new ToggleGroup();
		RadioButton rbMale = new RadioButton("Male");
		RadioButton rbFemale = new RadioButton("Female");
		RadioButton rbOther = new RadioButton("Other");
		rbMale.setToggleGroup(genderGroup);
		rbFemale.setToggleGroup(genderGroup);
		rbOther.setToggleGroup(genderGroup);
		// preselect a button
		// rbMale.setSelected(true);
		HBox genderHbox = new HBox(5, rbMale, rbFemale, rbOther);// 5 is the spacing between radio buttons
		// gridPane.add(new HBox(5,rbYes,rbNo,rbNotSure), 1,6);
		VBox genderVbox = new VBox(1);
		genderVbox.getChildren().add(genderLabel);
		genderVbox.getChildren().add(genderHbox);
		gridPane.add(genderVbox, 2, row, 2, 2);

		// row 5
		row = row + 2;
		gridPane.add(new Label("Employment Eligibility"), 0, row, 2, 1);

		// row 6
		row = row + 2;
		Label dateLabel = new Label("Date Available");
		// dateLabel.setWrapText(true);
		gridPane.add(dateLabel, 0, row, 1, 2);
		DatePicker dateAvailableField = new DatePicker();// Date Picker
		dateAvailableField.setValue(LocalDate.now());
		gridPane.add(dateAvailableField, 1, row, 1, 2);
		Label positionLabel = new Label("Desired Position");
		// dateLabel.setWrapText(true);
		gridPane.add(positionLabel, 2, row, 1, 2);
		TextField position = new TextField();// Name the text fields for use later in code
		gridPane.add(position, 3, row, 1, 1);

		// row 7
		row = row + 2;
		Label salaryLabel = new Label("Desired Salary");
		gridPane.add(salaryLabel, 0, row, 1, 1);
		TextField salaryField = new TextField();// Name the text fields for use later in code
		gridPane.add(salaryField, 1, row, 3, 1);

		// salary validator
		salaryField.textProperty().addListener((obs, oldVal, newVal) -> {
			String cleanedValue = newVal.replaceAll("[^0-9.]", "");

			final String salaryPattern = "^\\d{0,8}(\\.\\d{0,2})?$";

			if (!cleanedValue.matches(salaryPattern)) {
				salaryField.setText(oldVal);
			} else if (!newVal.equals(cleanedValue)) {
				salaryField.setText(cleanedValue);
			}

			/*if (!newVal.trim().isEmpty() && newVal.matches(salaryPattern)) {
				isSalaryValid = true;
			}*/
			isSalaryValid.set(!newVal.trim().isEmpty() && newVal.matches(salaryPattern));
		});

		// row 8
		row = row + 2;
		Label authorisedLabel = new Label("Are you legally authorized to work in the country?");
		// gridPane.add(authorisedLabel, 0, row, 4, 1);
		ToggleGroup authorisedGroup = new ToggleGroup();
		RadioButton rbYesAuth = new RadioButton("Yes");
		RadioButton rbNoAuth = new RadioButton("No");
		rbYesAuth.setToggleGroup(authorisedGroup);
		rbNoAuth.setToggleGroup(authorisedGroup);
		// preselect a button
		// rbYesAuth.setSelected(true);
		HBox authorisedHbox = new HBox(5, rbYesAuth, rbNoAuth);// 5 is the spacing between radio buttons
		// gridPane.add(new HBox(5,rbYes,rbNo,rbNotSure), 1,6);
		// gridPane.add(authorisedHbox, 0, row, 1, 1);
		VBox authorisedVbox = new VBox(1);
		authorisedVbox.getChildren().add(authorisedLabel);
		authorisedVbox.getChildren().add(authorisedHbox);
		gridPane.add(authorisedVbox, 0, row, 2, 1);

		// row 9
		row = row + 2;
		Label relativesLabel = new Label("Do you have reliatives working for our company?");
		gridPane.add(relativesLabel, 0, row, 4, 1);
		ToggleGroup relativesGroup = new ToggleGroup();
		RadioButton rbYesRel = new RadioButton("Yes");
		RadioButton rbNoRel = new RadioButton("No");
		rbYesRel.setToggleGroup(relativesGroup);
		rbNoRel.setToggleGroup(relativesGroup);
		// preselect a button
		// // rbYesAuth.setSelected(true);
		HBox relativesHbox = new HBox(5, rbYesRel, rbNoRel);// 5 is the spacing between radio buttons
		// gridPane.add(new HBox(5,rbYes,rbNo,rbNotSure), 1,6);
		// gridPane.add(relativesHbox, 0, row, 1, 1);
		VBox relativesVbox = new VBox(1);
		relativesVbox.getChildren().add(relativesLabel);
		relativesVbox.getChildren().add(relativesHbox);
		gridPane.add(relativesVbox, 0, row, 2, 1);

		// row 10
		row = row + 2;
		gridPane.add(new Label("If yes, plese explain furter"), 0, row, 2, 1);
		TextField explanation = new TextField();// Name the text fields for use later in code
		gridPane.add(explanation, 1, row, 3, 1);

		BooleanBinding isFormValid = Bindings.and(isFullNameValid, isContactNumbValid)
                .and(isSalaryValid)
                /*.and(isEducationSelected)
                .and(isDateValid)*/;
		// row 11
		row++;
		Label agreementLabel = new Label(
				"By submitting this application, you agree " + "to adhere to company policies and provide accurate"
						+ "information throughout the employment process");
		agreementLabel.setWrapText(true);
		// gridPane.add(agreementLabel, 0, row, 2, 4);

		/*Boolean isFormValid = false;
		if (isFullNameValid && isContactNumbValid && isSalaryValid) {
			isFormValid = true;
		}*/
		
		// Create Submit button
		Button submitApplicationButton = new Button("Submit");
		submitApplicationButton.disableProperty().bind(isFormValid.not());//binding the button/form to the validation
		submitApplicationButton.setOnAction(event -> {

			// Server Connection
			String url = "jdbc:mysql://localhost:3306/Final_Fall2025_KevonMitchell";
			String username = "root";
			String password = "";

			// All queries
			String applicantInsertQry = "INSERT INTO final_applicant (fullname,contactnumb,education,available_date,salary) "
					+ "VALUES (?, ?, ?,?,?)";
			String employmentInsertQry = "INSERT INTO final_employment (fullname,contactnumb,education,salary) "
					+ "VALUES (?, ?, ?,?)";

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
					PreparedStatement pst = conn.prepareStatement(applicantInsertQry);
				    PreparedStatement pst2 = conn.prepareStatement(employmentInsertQry);) {

				pst.setString(1, fullNameField.getText());
				pst.setString(2, contactNumbField.getText());
				pst.setObject(3, educationField.getValue());
				pst.setObject(4, dateAvailableField.getValue());
				pst.setString(5, salaryField.getText());
				
				pst2.setString(1, fullNameField.getText());
				pst2.setString(2, contactNumbField.getText());
				pst2.setObject(3, educationField.getValue());
				pst2.setString(4, salaryField.getText());

				// Execute update query
				pst.executeUpdate();
				pst2.executeUpdate();
				/*
				 * st.close(); //close the statement conn.close();//close the connection
				 */ // not necessary with a try block
			}

			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Application Sent");

		});

		/*
		 * //Add buttons to grid gridPane.add(submitApplicationButton, 0, row,1,1);
		 * gridPane.add(readApplicationButton, 1, row,1,1);
		 * 
		 * //center the button s GridPane.setHalignment(submitApplicationButton,
		 * HPos.CENTER); GridPane.setHalignment(readApplicationButton, HPos.CENTER);
		 */

		// Add buttons to grid - alternate solution
		submitApplicationButton.setMaxWidth(Double.MAX_VALUE);// additional styling to ensure buttons are the same size
		submitApplicationButton.setPrefWidth(200);
		submitApplicationButton.setPrefHeight(40);
		submitApplicationButton.setMinWidth(150);
		submitApplicationButton.setMinHeight(40);
		agreementLabel.setMaxWidth(Double.MAX_VALUE);// set width to any size, does not have to be max
		// HBox.setHgrow(submitApplicationButton, Priority.ALWAYS);// additional styling
		// to ensure buttons are the same
		// size
		// HBox.setHgrow(agreementLabel, Priority.ALWAYS);
		HBox agreementBox = new HBox(10);
		agreementBox.setAlignment(Pos.CENTER);// aligns buttons in box
		agreementBox.getChildren().addAll(agreementLabel, submitApplicationButton);

		gridPane.add(agreementBox, 0, row, 4, 2);// adds Hbox with both buttons to gridPane
		GridPane.setHalignment(agreementBox, HPos.CENTER);

		// this.getChildren().add(gridPane);
	}

	// getter method to return custom grid pane object to add to the border pane
	// node when it's not inherited from GridPane class
	public GridPane getGridPane() {
		return this.gridPane;
	}

}
