package GUI; /**
 * Created by steve on 2/25/17.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
        MenuItem saveAsFlame = new MenuItem("Save As...");
        MenuItem export = new MenuItem("Export to image");
        menu.getItems().addAll(newFlame, loadFlame, saveFlame, saveAsFlame, export);

        newFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        loadFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        saveFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveAsFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+S"));
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
        primaryStage.setTitle("Flam3 Fractals");
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    private Stage aboutWindow(Stage primary) {

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primary);
        dialog.setTitle("About: Flam3 Fractals");
        
        VBox aboutBox = new VBox(10);
        Label aText = new Label("Flame3 Fractals");
        aText.setFont(new Font("Arial", 20));
        aText.setTextAlignment(TextAlignment.CENTER);
        aText.setPadding(new Insets(15,15,0,12));
        
        Label bText = new Label("Winter Wonder Hack 2017 (Hackathon MTU)");
        bText.setFont(new Font("Arial", 18));
        bText.setTextAlignment(TextAlignment.CENTER);
        bText.setPadding(new Insets(0,12,12,12));
        
        Label author = new Label("Authors: ");
        author.setFont(new Font("Arial", 16));
        author.setTextAlignment(TextAlignment.CENTER);
        author.setPadding(new Insets(0,0,0,12));
        
        Label gui = new Label("GUI: Steve Bertolucci, Domenic Portuesi");
        gui.setFont(new Font("Arial", 16));
        gui.setTextAlignment(TextAlignment.CENTER);
        gui.setPadding(new Insets(0,0,0,12));
        
        Label engine = new Label("Engine: Mark Schlax, Justin Williams");
        engine.setFont(new Font("Arial", 16));
        engine.setTextAlignment(TextAlignment.CENTER);
        engine.setPadding(new Insets(0,0,0,12));
        
        aboutBox.getChildren().addAll(aText,bText,author,gui,engine);
        Scene scene = new Scene(aboutBox, 400, 225);
        dialog.setScene(scene);

        return dialog;

    }
}
