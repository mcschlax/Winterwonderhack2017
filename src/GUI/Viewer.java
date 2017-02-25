package GUI; /**
 * Created by steve on 2/25/17.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
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
        MenuItem newFlame = new MenuItem("New");
        MenuItem loadFlame = new MenuItem("Load Existing");
        MenuItem saveFlame = new MenuItem("Save");
        MenuItem export = new MenuItem("Export to image");
        menu.getItems().addAll(newFlame, loadFlame, saveFlame, export);

        newFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        loadFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        saveFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        export.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));

        Menu view = new Menu("View");

        Menu upload = new Menu("Upload");
        MenuItem uploadAWS = new MenuItem("Upload to AWS");
        upload.getItems().addAll(uploadAWS);

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
        SplitPane mod = ModPane.get(primaryStage, new ImageView()); //Temporary blank for testing
        root.setCenter(mod);

        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setMaximized(true);
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
