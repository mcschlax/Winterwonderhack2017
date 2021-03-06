package GUI; /**
 * Created by steve on 2/25/17.
 */

import Base.Fractal;
import Base.Point;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Viewer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        ModPane mpane = new ModPane(primaryStage);

        //Main Menu Options
        Menu menu = new Menu("File");
        MenuItem newFlame = new MenuItem("New");
        MenuItem loadFlame = new MenuItem("Load Existing");
        MenuItem saveFlame = new MenuItem("Save");
        MenuItem saveAsFlame = new MenuItem("Save As...");
        MenuItem export = new MenuItem("Export to image");
        MenuItem quit = new MenuItem("Quit");
        menu.getItems().addAll(newFlame, loadFlame, saveFlame, saveAsFlame, export, quit);

        //Key shortcuts for Main Menu
        newFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        loadFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));  
        saveFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveAsFlame.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+S"));
        export.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        quit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));

        //Actions for Main Menu
        newFlame.setOnAction(e -> {
            mpane.setFractal(generate(1080, 1920, 100000, 1,1,1));
        });
        mpane.getRandom().setOnAction(e -> {
            mpane.newRands();
        });
        mpane.getRegen().setOnAction(e -> {
            double[] d = mpane.getRands();
            mpane.setFractal(generate(1080, 1920, 100000, d[0], d[1], d[2]));
        });

        quit.setOnAction(e -> {
            System.exit(0);
        });

        //View Options
        Menu view = new Menu("View");

        //Upload Options
        Menu upload = new Menu("Upload");
        MenuItem uploadAWS = new MenuItem("Upload to AWS");
        upload.getItems().addAll(uploadAWS);

        //About Options
        Menu about = new Menu("About");
        MenuItem aboutItem = new MenuItem("About");
        about.getItems().addAll(aboutItem);

        //Key shortcuts for About
        aboutItem.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
        aboutItem.setOnAction(e -> {
            aboutWindow(primaryStage).show();
        });

        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(menu, view, upload, about);
        root.setTop(menubar);

        root.setCenter(mpane);

        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Flam3 Fractals");
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    private WritableImage generate(int XRES, int YRES, int ITER, double VAR0, double VAR1, double VAR2) {

        WritableImage image = new WritableImage(YRES, XRES);

        Map params = new HashMap<String, String>();
        if (XRES != 0) params.put("XRES", Integer.toString(XRES));
        if (YRES != 0) params.put("YRES", Integer.toString(YRES));
        if (ITER != 0) params.put("ITER", Integer.toString(ITER));
        if (VAR0 != 0) params.put("VAR0", Double.toString(VAR0));
        if (VAR1 != 0) params.put("VAR1", Double.toString(VAR1));
        if (VAR2 != 0) params.put("VAR2", Double.toString(VAR2));

        Point[][] fpoints = Fractal.createFractal(params);
        for (int r = 0; r < YRES; r++) {
            for (int c = 0; c < XRES; c++) {
                double rV = fpoints[r][c].C().R();
                double gV = fpoints[r][c].C().G();
                double bV = fpoints[r][c].C().B();
                Color col = new Color(rV, gV, bV, 1);
                image.getPixelWriter().setColor(r, c, col);
            }
        }

        return image;

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
