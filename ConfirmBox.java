//package com.fxml;

import javafx.stage.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ConfirmBox {
	static boolean answer;

	public static boolean display(String title, String message) {
		// creates a new stage
		Stage window = new Stage();
		Scene scene;
		Label label1;

		// creating yes and no button
		Button yes = new Button("Yes");
		Button no = new Button("No");

		yes.setOnAction(e -> {
			answer = true;
			window.close();
		});

		no.setOnAction(e -> {
			answer = false;
			window.close();
		});
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);

		label1 = new Label(message);

		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label1, yes, no);

		scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return answer;

	}
}
