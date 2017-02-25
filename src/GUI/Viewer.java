package GUI; /**
 * Created by steve on 2/25/17.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Viewer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        Menu menu = new Menu("File");
        Menu view = new Menu("View");
        Menu upload = new Menu("Upload");

        Menu about = new Menu("About");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        aboutItem.setOnAction(e -> {
            aboutWindow(primaryStage).show();
        });
        about.getItems().addAll(aboutItem);

        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(menu, view, upload, about);

        root.setTop(menubar);
        SplitPane mod = ModPane.get(primaryStage);
        root.setCenter(mod);

        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    private Stage aboutWindow(Stage primary) {

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primary);

        VBox aboutBox = new VBox(20);
        aboutBox.getChildren().add(new Text("Winter WonderHack 2017!"));
        Scene scene = new Scene(aboutBox, 200, 300);
        dialog.setScene(scene);

        return dialog;

    }
}
