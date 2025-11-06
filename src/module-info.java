module Lab5_Fx_Wk8 {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
}