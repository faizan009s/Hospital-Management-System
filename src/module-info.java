module HMS_New {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
    

	requires com.jfoenix;
	requires javafx.graphics;
	requires de.jensd.fx.glyphs.fontawesome;
    
	requires javafx.base;
	exports application;


	 
	
	opens application to javafx.graphics, javafx.fxml;
}
