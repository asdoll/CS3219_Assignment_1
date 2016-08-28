package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UI extends Application {
    
    Stage mainStage = new Stage();
    GridPane grid = new GridPane();
    abstructDataTypeSolution.Controller ADT;
    sharedDataSolution.main SD;
    Text message = new Text();
    
    
    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle("CS3219 Assignment 1 - KWIC");
        try {
			setupPanel();
		} catch (IOException e) {
			
		}
        mainStage.show();
    }
    
    private void setupPanel() throws IOException {
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setMinSize(360, 180);
        
        final Text inputFilenameText = new Text("Enter input file path");
        grid.add(inputFilenameText, 0, 0);
        
        final TextField inputFilenameField = new TextField("input.txt");
        grid.add(inputFilenameField, 0, 1);
        
        final Text ignoreFilenameText = new Text("Enter ignore file path");
        grid.add(ignoreFilenameText, 0, 2);
        
        final TextField ignoreFilenameField = new TextField("ignore.txt");
        grid.add(ignoreFilenameField, 0, 3);
        
        final Text outputFilenameText = new Text("Enter the output file path and choose method");
        grid.add(outputFilenameText, 0, 4);
        
        final TextField outputFilenameField = new TextField("output.txt");
        grid.add(outputFilenameField, 0, 5);
        
        final HBox buttons = new HBox();
        
        final Button submitButton1 = new Button();
        submitButton1.setText("Solution 1: Abstruct Data Type Solution");
        submitButton1.setMinWidth(180);
        submitButton1.setAlignment(Pos.BOTTOM_CENTER);
        
        final Button submitButton2 = new Button();
        submitButton2.setText("Solution 2: Shared Data Solution");
        submitButton2.setMinWidth(180);
        submitButton2.setAlignment(Pos.BOTTOM_CENTER);
        
        buttons.getChildren().addAll(submitButton1, submitButton2);
        grid.add(buttons, 0, 6);
        
        final Text instructions = new Text("Input format: \n"
                + "First line contains all words to ignore\n"
                + "Second line onwards each contain one title");
        grid.add(instructions, 0, 7);
        
        grid.add(message, 0, 8);
        
        mainStage.setScene(new Scene(grid));
        
        submitButton1.setOnAction(e -> {
        	processADT(inputFilenameField.getText(),ignoreFilenameField.getText(), outputFilenameField.getText());
        });
        
        submitButton2.setOnAction(e -> {
        	processSD(inputFilenameField.getText(),ignoreFilenameField.getText(), outputFilenameField.getText());
        });
        
       
    }
    
    private void processADT(String inputFilename, String ignoreFilename, String outputFilename) {
			
				try {
					ADT.controller(inputFilename,ignoreFilename, outputFilename);
					 message.setText("Abstruct Data Type Solution processed");
				} catch (IOException e) {
					message.setText("Warning:Input/Ignore/Output path error!");
				}

    }
    
    private void processSD(String inputFilename,String ignoreFilename, String outputFilename) {
    	try {
			SD.controller(inputFilename,ignoreFilename, outputFilename);
			message.setText("Shared Data Solution processed");
		} catch (IOException e) {
			message.setText("Warning:Input/Ignore/Output path error!");
		}
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}