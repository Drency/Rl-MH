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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.beans.binding.Bindings;

public class Main extends Application{

    ArrayList<Match> gameList = new ArrayList<Match>();
    BorderPane pane;
    ScrollPane centerPane;

    public static void main(String[] args){
        Application.launch(args);
        
    }

    public void start(Stage primaryStage){
        
        pane = new BorderPane();
        Button addGame = new Button("Add match");
        Button editGame = new Button("Edit game");

        pane.setLeft(addGame);
        pane.setRight(editGame);

        addGame.setOnMouseClicked(e ->{
            buttonClicked(addGame.getText().toString());
        });

        centerPane = new ScrollPane();
        // centerPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        
        addCenter();

        Scene scene = new Scene(pane, 800, 900);

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

        Match m = new Match(50, 0, 600, 100, hScore, aScore);

        gameList.add(0, m);

        addCenter();

    }

    public void addCenter(){

        VBox box = new VBox();
        centerPane.setContent(null);
        // System.out.println("Running");
        ArrayList<Match> mL = new ArrayList<Match>();
        for(int i = 0; i<gameList.size();i++){
            gameList.get(i).setY((i*100)+20);
            mL.add(gameList.get(i));

            StackPane sPane = new StackPane();
            // System.out.println("Array: " + mL);
            
            

            //sPane.getChildren().addAll(mL.get(i), new Text(mL.get(i).getHScore() + " : " + mL.get(i).getAScore()));
            box.getChildren().add(new StackPane(mL.get(i),new Text( mL.get(i).getHScore() + " : " + mL.get(i).getAScore()
             + "\n" + mL.get(i).getDate())));
        }

        box.styleProperty().bind(Bindings.concat("-fx-font-size: 30"));    
        // box.getChildren().setFontSize(20);
        centerPane.setContent(box);
        //centerPane.setAlignment(Pos.CENTER);
        pane.setCenter(centerPane);

    }
}