package GUI;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ModPane extends SplitPane
{
	Stage root;
	Button random;
	Button regen;
	ImageView fractalView;

	public ArrayList<TextField> textFields = new ArrayList<TextField>();


	public ModPane(Stage r)
	{
		this.root = r;

		//SplitPane pane = new SplitPane();
		this.setMinSize(100, root.getHeight());

		VBox left = new VBox(20);
		left.setMinWidth(200);
		left.setPrefWidth(300);
		left.setMaxWidth(800);

		Label title = new Label("Modification Pane");
		title.setPadding(new Insets(20,20,5,20));
		title.setFont(new Font("Arial", 20));

		Label spacer = new Label("");

		left.getChildren().addAll(title,spacer);

		HBox buttons = new HBox();
		buttons.setPadding(new Insets(20,20,20,20));

		random = new Button("Randomize");
		random.setPadding(new Insets(10,12,10,12));

		regen = new Button("Regenerate Image");
		regen.setPadding(new Insets(10,12,10,12));

		buttons.getChildren().addAll(random, regen);
		left.getChildren().add(buttons);

		for(int i = 0; i < 3; i++)
		{
			HBox fields = new HBox();

			TextField t = new TextField();
			t.setPrefWidth(45);
			t.setPadding(new Insets(5));
			textFields.add(t);

			Label l = new Label("V" + i);
			l.setPadding(new Insets(0, 5, 0, 20));

			fields.getChildren().addAll(l,t);
			left.getChildren().add(fields);
		}

		VBox right = new VBox(5);
		right.setMinWidth(100);

		fractalView = new ImageView();

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(fractalView);
		right.getChildren().add(scrollPane);

		this.getItems().addAll(left, right);
		this.setDividerPositions(0,0.2F);

	}

	public void setFractal(WritableImage fractal) {
		fractalView.setImage(fractal);
	}

	public Button getRandom() {
		return this.random;
	}

	public Button getRegen() {
		return this.regen;
	}

	public void newRands() {

		for(TextField f : textFields)
		{
			Random rand = new Random();
			f.setText(Double.toString(rand.nextDouble()));
		}

	}

	public double[] getRands() {
		int numfuns = 3;
		double[] rands = new double[numfuns];
		for (int i = 0; i < numfuns; i++) {
			rands[i] = Double.parseDouble(textFields.get(i).getText());
		}
		return rands;
	}

}
