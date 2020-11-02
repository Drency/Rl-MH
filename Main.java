import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javax.swing.JOptionPane;
import java.lang.Integer.*;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.geometry.Pos;


public class Main extends Application{

    ArrayList<Match> gameList = new ArrayList<Match>();

    public static void main(String[] args){
        Application.launch(args);
        
    }

    public void start(Stage primaryStage){
        
        BorderPane pane = new BorderPane();
        Button addGame = new Button("Add match");
        Button editGame = new Button("Edit game");

        pane.setLeft(addGame);
        pane.setRight(editGame);

        addGame.setOnMouseClicked(e ->{
            buttonClicked(addGame.getText().toString());
        });

        Group centerGrp = new Group();

        HBox center = new HBox();
        center.setSize(200, 200);
        // center.setAlignment(Pos.CENTER);
        center.setStyle("-fx-background-color: green");
        Label score = new Label();
        score.setText("Du vant");

        center.getChildren().add(score);
        HBox center1 = new HBox();
        // center1.setAlignment(Pos.CENTER);
        center1.setStyle("-fx-background-color: red");
        Label score1 = new Label();
        score1.setText("Du tapte");

        center1.getChildren().add(score1);

        centerGrp.getChildren().addAll(center, center1);

        pane.setCenter(centerGrp);

        Scene scene = new Scene(pane, 800, 600);

        primaryStage.setTitle("Match history");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void buttonClicked(String name){
        int hScore = Integer.parseInt(JOptionPane.showInputDialog(null, "Your goals: "));
        while(hScore < 0){
            hScore= Integer.parseInt(JOptionPane.showInputDialog(null, "You cant have negative goals."));

        }
        int aScore = Integer.parseInt(JOptionPane.showInputDialog(null, "Opponents goals: "));
        while(aScore < 0){
            aScore= Integer.parseInt(JOptionPane.showInputDialog(null, "Opponents cant have negative goals."));
        }

        Match m = new Match(hScore, aScore);

        gameList.add(m);

    }
}