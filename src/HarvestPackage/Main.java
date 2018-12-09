package HarvestPackage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

//import javafx.application.Application;

/**
 * @author RYANF This program is intended to present the user with a window to
 *         enter in field information, and then build a map used to grab the
 *         data. The code below is building the UI, labels, buttons, etc.
 *
 */
public class Main extends Application {
	Button button;
	Stage stage;

	public static void main(String[] args) {
		// Lauch the program.
		launch(args);

	}

	@Override
	// This is the build the stage for the first box. The user will enter in various
	// information, and build a field from it.
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Frasch Harvest");
		// Various Boxes to fill the border layout.
		VBox vbFields = new VBox();
		HBox hFields = new HBox();
		VBox vbFieldsLeft = new VBox();
		HBox warningTop = new HBox();

		// Various labels for the boxes.
		Label fieldName = new Label("Field Name");
		Label startingPass = new Label("Starting Pass");
		Label endingPass = new Label("Last Pass");
		Label startingRange = new Label("Starting Range");
		Label endingRange = new Label("Last Range");
		Label harvestPattern = new Label("Harvest Pattern");
		Label warning = new Label("");

		// Setting the warning to tell the user to enter a number for passes and ranges.
		warning.setPadding(new Insets(5, 5, 5, 5));
		warning.setTextFill(Color.web("white"));
		warning.setStyle("-fx-font: 12 arial;");
		warningTop.setStyle("-fx-background-color: #336699;");
		warningTop.getChildren().addAll(warning);
		warningTop.setAlignment(Pos.CENTER);

		// Lays out the verticle box on left side.
		vbFieldsLeft.getChildren().addAll(fieldName, startingPass, endingPass, startingRange, endingRange,
				harvestPattern);
		vbFieldsLeft.setPadding(new Insets(10, 12, 15, 12));
		vbFieldsLeft.setSpacing(20);
		vbFieldsLeft.setStyle("-fx-background-color: #336697;");
		fieldName.setTextFill(Color.web("white"));
		startingPass.setTextFill(Color.web("white"));
		endingPass.setTextFill(Color.web("white"));
		startingRange.setTextFill(Color.web("white"));
		endingRange.setTextFill(Color.web("white"));
		harvestPattern.setTextFill(Color.web("white"));

		// Lays out bottom Hbox, with the 'create' button.
		hFields.setPadding(new Insets(10, 12, 15, 12));
		hFields.setSpacing(10);
		hFields.setStyle("-fx-background-color: #336699;");
		button = new Button("Create");
		button.setPrefSize(100, 20);

		// button.setOnAction(this);
		hFields.setAlignment(Pos.CENTER);
		hFields.getChildren().addAll(button);

		vbFields.setPadding(new Insets(10, 12, 15, 12));
		vbFields.setSpacing(10);
		vbFields.setStyle("-fx-background-color: #336698;");
		TextField fieldN = new TextField();
		TextField startingP = new TextField();
		TextField endingP = new TextField();
		TextField startingR = new TextField();
		TextField endingR = new TextField();
		ChoiceBox<String> harvestP = new ChoiceBox<>();
		// Gets the items for the dropdown.
		harvestP.getItems().addAll("Serpentine", "Regular", "Circular");
		// To set default value of dropdown.
		harvestP.setValue("Serpentine");

		// Sets labels and textfields in the container
		vbFields.getChildren().addAll(fieldN, startingP, endingP, startingR, endingR, harvestP);

		// New pane to display warning at top.
		BorderPane border = new BorderPane();
		border.setBottom(hFields);
		border.setCenter(vbFields);
		border.setLeft(vbFieldsLeft);
		border.setTop(warningTop);

		Scene scene = new Scene(border, 260, 300);
		primaryStage.setScene(scene);
		primaryStage.show();

		// lamda expression for handling button click.
		button.setOnAction(e -> {
			boolean box1 = isInt(startingP, startingP.getText());
			boolean box2 = isInt(endingP, endingP.getText());
			boolean box3 = isInt(startingR, startingR.getText());
			boolean box4 = isInt(endingR, endingR.getText());
			if (box1 == true && box2 == true && box3 == true && box4 == true) {

				String fname = String.valueOf(fieldN.getText());
				String sp = Integer.valueOf(startingP.getText()).toString();
				String ep = Integer.valueOf(endingP.getText()).toString();
				String sr = Integer.valueOf(startingR.getText()).toString();
				String er = Integer.valueOf(endingR.getText()).toString();
				String hp = String.valueOf(harvestP.getValue());
				try {
					iowaField(fname, sp, ep, sr, er, hp);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				warning.setText("Error: Passes and ranges must be numbers");
			}
		});

	}

	/**
	 * @param input
	 *            the user's input
	 * @param message
	 *            to pull from
	 * @returns true or false. If its not an int, false. If it is, true. This is to
	 *          check if the input from a use is an int or not. It resets boxes to
	 *          red if they are not red.
	 */
	private boolean isInt(TextField input, String message) {
		try {
			int a = Integer.parseInt(input.getText());
			input.setStyle("-fx-text-box-border: white");
			input.setStyle("-fx-background-color: white");
			return true;
		} catch (NumberFormatException e) {
			input.setStyle("-fx-text-box-border: red ; -fx-border-width: 10px ;");
			input.setStyle("-fx-background-color: #f08080");
			return false;

		}
	}

	/**
	 * @param input
	 *            the user's input
	 * @param message
	 *            to pull from
	 * @returns true or false. If double, true. if not, false. To check users input
	 *          for the harvest Screen.
	 */
	private boolean isDouble(TextField input, String message) {
		try {
			double a = Double.parseDouble(input.getText());
			return true;
		} catch (NumberFormatException e) {
			input.setStyle("-fx-text-box-border: red ; -fx-border-width: 10px ;");
			input.setStyle("-fx-background-color: #f08080");
			return false;
		}
	}

	/**
	 * @param fieldName
	 *            name of the field
	 * @param startingP
	 *            staring pass
	 * @param endingP
	 *            ending pass
	 * @param startingR
	 *            staring range
	 * @param endingR
	 *            ending range
	 * @param hPattern
	 *            what pattern is being used for harvest.
	 * @return true after completoin.
	 * @throws FileNotFoundException
	 */
	public boolean iowaField(String fieldName, String startingP, String endingP, String startingR, String endingR,
			String hPattern) throws FileNotFoundException {

		// This final is to normalize the moisture on the plot to 15 percent. Used to
		// calculate normalize yield
		final double MOISTURE_NORMALIZING_VALUE = 15;

		// Creating new field with user input.
		fieldDefinition thisField = new fieldDefinition(fieldName, Integer.parseInt(startingP),
				Integer.parseInt(endingP), Integer.parseInt(startingR), Integer.parseInt(endingR), hPattern);

		// To output data to a file.
		PrintWriter outputStream = new PrintWriter(fieldName + ".txt");

		// Building the new window.
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Field Name :  " + fieldName + "   Harvest Pattern:  " + hPattern);
		window.setWidth(500);
		window.setHeight(450);

		// Building the various containers for labels and whatnot.
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color: #336698;");
		HBox hBoxBottom = new HBox();
		hBoxBottom.setPadding(new Insets(40, 12, 15, 12));
		hBoxBottom.setSpacing(20);
		VBox vBoxLeft = new VBox();
		vBoxLeft.setPadding(new Insets(10, 12, 15, 12));
		vBoxLeft.setSpacing(0);
		VBox fieldPlots = new VBox();
		fieldPlots.setStyle("-fx-background-color: #336698;");
		fieldPlots.setAlignment(Pos.CENTER_LEFT);

		/*
		 * To create button array. This is an unimplemented button grid I had wanted to
		 * complete, but I didn't have enough time to the proper layout.
		 * fieldPlots.setPadding(new Insets(5)); fieldPlots.setHgap(5);
		 * fieldPlots.setVgap(5);
		 * 
		 * for (int r = 0; r < thisField.getTotalPassesPerField(); r++) { for (int c =
		 * 0; c < thisField.getTotalRangesPerPass(); c++) { int number = 0; Button
		 * button = new Button(String.valueOf(number)); fieldPlots.add(button, c, r); }
		 * }
		 * 
		 * ScrollPane scrollPane = new ScrollPane(fieldPlots);
		 * 
		 * // This was just to see if I could get this on the screen. //
		 * fieldPlots.add(button, 1, 1); // Goes in grid
		 */

		// Goes in VBox, the previous plot nums
		Label prevPlot = new Label("Previous Plot");
		Label prevPass = new Label("Pass");
		Label ppNum = new Label("0");
		Label prevRange = new Label("Range");
		Label prNum = new Label("0");

		// Goes in VBoxleft, current plot nums
		Label currPlot = new Label("Current Plot");
		Label currPass = new Label("Pass");
		Label cpNum = new Label(startingP);
		Label currRange = new Label("Range");
		Label crNum = new Label(startingR);

		// Goes in VBox Left, next plot labels and nums
		Label nextPlot = new Label("Next Plot");
		Label nextPass = new Label("Pass");
		Label npNum = new Label("0");
		Label nextRange = new Label("Range");
		Label nrNum = new Label("0");

		// Goes in the HBox bottom
		Label weight = new Label("Weight(lbs)");
		Label moisture = new Label("Moisture");
		TextField wtData = new TextField();
		wtData.setMaxWidth(50);
		TextField msData = new TextField();
		msData.setMaxWidth(50);
		Button advancePlot = new Button("Advance");

		// Goes in VBox center;
		Label link1 = new Label();
		link1.setStyle("-fx-font: 20 arial;");
		Label link2 = new Label();
		link1.setStyle("-fx-font: 20 arial;");
		Label link3 = new Label();
		link1.setStyle("-fx-font: 20 arial;");

		// To update the next plot numbers from the next pass and range on the labels
		// telling the user what the next plot is.
		npNum.textProperty().bind(new SimpleIntegerProperty(thisField.getNextPass()).asString());
		nrNum.textProperty().bind(new SimpleIntegerProperty(thisField.getNextRange()).asString());
		thisField.nextNextPlot();
		npNum.textProperty().bind(new SimpleIntegerProperty(thisField.getNextPass()).asString());
		nrNum.textProperty().bind(new SimpleIntegerProperty(thisField.getNextRange()).asString());
		thisField.nextNextPlot();

		// Lamda action if 'advanceplot is clicked. If its clicked, it changes labels,
		// advances the plot, writes to a text file. This is by far the worst piece of
		// code I wrote... I know it can be better, making new methods and whatnot.
		advancePlot.setOnAction(e -> {
			// If the wtBox and msBox are doubles, turn true or false.
			boolean wtBox = isDouble(wtData, wtData.getText());
			boolean msBox = isDouble(msData, msData.getText());

			// If these are true, then do this.
			if (wtBox == true && msBox == true) {
				if (thisField.getRunningPlotNum() == thisField.getPlotNum()) {
					wtData.setVisible(false);
					msData.setVisible(false);

				}
				// To correct the weight of the plot entered, standardized to a moisture of 15
				// percent.
				double correctedWeight = Double.parseDouble(wtData.getText());
				double correctedMoisturePercentageChange = MOISTURE_NORMALIZING_VALUE
						- Double.parseDouble(msData.getText());

				if (correctedMoisturePercentageChange > 0) {
					correctedWeight = correctedWeight * ((100 + correctedMoisturePercentageChange) / 100);
				} else if (correctedMoisturePercentageChange < 0) {
					correctedWeight = correctedWeight * ((100 + correctedMoisturePercentageChange) / 100);
				}
				// Add this corrected weight to the queue.
				thisField.myQueue.add(thisField.getRunningPlotNum(), correctedWeight);

				// If the running plot number is less then the plot amount for the field.
				// Add corrected weight to 2D array
				if (thisField.getRunningPlotNum() <= thisField.getPlotNum()) {
					thisField.fieldArray[thisField.getCurrPass() - thisField.getStartP()][thisField.getCurrRange()
							- thisField.getStartR()] = correctedWeight;
					thisField.myQueue.add(thisField.getRunningPlotNum(), correctedWeight);
					// If the plotNum is less than or equal to running plot number. disarm the
					// button, and set the textfields to not visible.
				} else if (thisField.getPlotNum() == thisField.getRunningPlotNum()) {
					advancePlot.disarm();

					window.close();
				}
				// Prints the data to a text file..
				// To pull the array data into the binary tree, which will then print a ranked
				// listing of the plots. This could be used to select which seed to keep for
				// plant breeding.

				outputStream.println("wt: " + wtData.getText() + ", ms: " + msData.getText() + "\n");
				// clears the buttons
				wtData.clear();
				msData.clear();

				thisField.fieldTree.print();
				// To set the previous plot labels to the current, then advance plot
				link3.setText(link2.getText());
				link2.setText(link1.getText());
				link1.setText(thisField.getMyQueue().peek());

				ppNum.textProperty().bind(new SimpleIntegerProperty(thisField.getCurrPass()).asString());
				prNum.textProperty().bind(new SimpleIntegerProperty(thisField.getCurrRange()).asString());
				thisField.nextPlot();

				// Update the current plot with the new current plot nums.
				cpNum.textProperty().bind(new SimpleIntegerProperty(thisField.getCurrPass()).asString());
				crNum.textProperty().bind(new SimpleIntegerProperty(thisField.getCurrRange()).asString());

				npNum.textProperty().bind(new SimpleIntegerProperty(thisField.getNextPass()).asString());
				nrNum.textProperty().bind(new SimpleIntegerProperty(thisField.getNextRange()).asString());
				thisField.nextNextPlot();

			}

		});
		// Setting all labels color, stype, size
		prevPlot.setTextFill(Color.web("white"));
		prevPass.setTextFill(Color.web("white"));
		ppNum.setTextFill(Color.web("white"));
		prevRange.setTextFill(Color.web("white"));
		prNum.setTextFill(Color.web("white"));

		nextPlot.setTextFill(Color.web("white"));
		nextPass.setTextFill(Color.web("white"));
		npNum.setTextFill(Color.web("white"));
		nrNum.setTextFill(Color.web("white"));
		nextRange.setTextFill(Color.web("white"));

		currPlot.setTextFill(Color.web("white"));
		currPlot.setPadding(new Insets(30, 0, 0, 0));
		currPlot.setStyle("-fx-font: 24 arial;");
		currPass.setTextFill(Color.web("white"));
		currPass.setStyle("-fx-font: 18 arial");

		cpNum.setTextFill(Color.web("white"));
		cpNum.setStyle("-fx-font: 18 arial");
		currRange.setTextFill(Color.web("white"));
		currRange.setStyle("-fx-font: 18 arial");
		crNum.setTextFill(Color.web("white"));
		crNum.setStyle("-fx-font: 18 arial");

		link1.setTextFill(Color.web("white"));
		link2.setTextFill(Color.web("white"));
		link3.setTextFill(Color.web("white"));

		weight.setTextFill(Color.web("white"));
		moisture.setTextFill(Color.web("white"));

		hBoxBottom.getChildren().addAll(weight, wtData, moisture, msData, advancePlot);

		vBoxLeft.getChildren().addAll(prevPlot, prevPass, ppNum, prevRange, prNum, nextPlot, nextPass, npNum, nextRange,
				nrNum, currPlot, currPass, cpNum, currRange, crNum);
		fieldPlots.getChildren().addAll(link1, link2, link3);
		border.setBottom(hBoxBottom);
		border.setLeft(vBoxLeft);
		border.setCenter(fieldPlots);

		Scene scene = new Scene(border, 400, 400);
		window.setScene(scene);
		window.showAndWait();
		// Prints the binary tree after you exit the window.
		int j;
		for (int i = 0; i < thisField.getTotalPassesPerField(); i++) {
			for (j = 0; j < thisField.getTotalRangesPerPass(); j++) {
				thisField.fieldTree.insert(thisField.fieldArray[i][j], thisField.getStartP() + i,
						thisField.getStartR() + j);
			}
		}
		thisField.fieldTree.print();
		outputStream.close();
		return true;
	}

}
