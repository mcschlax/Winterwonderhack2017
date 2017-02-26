package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ModPane 
{
	/**
	 * Returns the splitplane containing left and right vboxs for each side.
	 * @param root the primarystage, also needs the image view for the fractal.
	 * @return the splitpane
	 */
	public static SplitPane get(Stage root, ImageView fractalView)
	{
		//TODO fuck divider panes and there weird positioning
		SplitPane pane = new SplitPane();
		pane.setMinSize(100, root.getHeight());
		
		VBox left = new VBox(20);
		left.setMinWidth(100);
		left.setPrefWidth(300);
		left.setMaxWidth(800);
		
		Label title = new Label("Modification Pane");
		title.setPadding(new Insets(20));
		title.setFont(new Font("Arial", 20));

		left.getChildren().add(title);

		VBox right = new VBox(5);
		right.setMinWidth(100);

        ScrollPane scrollPane = new ScrollPane(fractalView);

		right.getChildren().add(scrollPane);
		
		pane.getItems().addAll(left, right);
		pane.setDividerPositions(0,0.2F);

		return pane;
	}
}
