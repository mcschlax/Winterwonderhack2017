package GUI;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ModPane 
{
	public SplitPane get(Stage root)
	{
		SplitPane pane = new SplitPane();
		pane.setMinSize(100, root.getHeight());
		//pane.setBorder(new Border(new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
		return pane;
	}
}
