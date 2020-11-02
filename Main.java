import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javax.swing.JOptionPane;
import java.lang.Integer.*;


public class Main extends Application{
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

        Scene scene = new Scene(pane, 800, 600);

        primaryStage.setTitle("Match history");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public Match buttonClicked(String name){
        int hScore = Integer.parseInt(JOptionPane.showInputDialog(null, "Your goals: "));
        while(hScore < 0){
            hScore= Integer.parseInt(JOptionPane.showInputDialog(null, "You cant have negative goals."));

        }
        int aScore = Integer.parseInt(JOptionPane.showInputDialog(null, "Opponents goals: "));
        while(aScore < 0){
            aScore= Integer.parseInt(JOptionPane.showInputDialog(null, "Opponents cant have negative goals."));
        }

        Match m = new Match(hScore, aScore);

        return m;

    }
}