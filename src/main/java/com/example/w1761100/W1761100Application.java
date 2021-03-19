package com.example.w1761100;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.w1761100.Rest.plm;
@SpringBootApplication
public class W1761100Application extends Application{

	public static void main(String[] args) {
		//loading data from previous runs
		plm.loadFromFile();
		SpringApplication.run(W1761100Application.class, args);
		launch(args);
	}
	public void start(Stage primaryStage) throws Exception {
		System.out.println("\nWelcome to Premier League Manager");
		//console menu launch
		plm.cliMenu();
	}
}
