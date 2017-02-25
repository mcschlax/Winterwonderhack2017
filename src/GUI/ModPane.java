package GUI;

import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ModPane 
{
	public static SplitPane get(Stage root)
	{
		SplitPane pane = new SplitPane();
		pane.setMinSize(100, root.getHeight());
		//pane.setBorder(new Border(new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
		VBox left = new VBox(20);

		Label title = new Label("Modification Pane");
		title.setFont(new Font("Arial", 20));

		left.getChildren().add(title);

		ImageView fractalView = new ImageView();

		pane.getItems().addAll(left, fractalView);
		pane.setDividerPosition(0, 0.5f);

		return pane;
	}
}
