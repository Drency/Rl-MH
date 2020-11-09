//Imports
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
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.beans.binding.Bindings;
import java.io.*;
import java.util.*;
import java.lang.Integer;
import java.awt.Insets;
/**
 * Main
 * Creates BorderPane to hold elements and uses methods: buttonClicked & addCenter
 */
public class Main extends Application{

    //Global var
    ArrayList<Match> gameList = new ArrayList<Match>();
     ArrayList<Integer> intList = new ArrayList<Integer>();
    BorderPane pane;
    ScrollPane centerPane;
    Scanner leser;
    final String filnavn = "history.txt";
    String[] matchList;

    //Main launching application
    public static void main(String[] args){
        Application.launch(args);
        
    }

    //Starts the stage and creates all the elements
    public void start(Stage primaryStage){
        
        pane = new BorderPane();
        Button addGame = new Button("Add match");
        // Button editGame = new Button("Edit game");

        //Getting matches from txt file
        try {
            File fil = new File(filnavn);
            leser = new Scanner(fil);
            String linje = "";
            do{
                
                linje = leser.nextLine();
                matchList =linje.split(",");
                if(matchList.length == 3){
                    Match m = new Match(50, 0, 550, 100, matchList[0], matchList[1]);
                    m.setDate(matchList[2]);
                    matchList = new String[0];
                    gameList.add(m);
                }
                
            } while( leser.hasNextLine() );
            
            leser.close();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }

        setRight();
        pane.setLeft(addGame); 

        //Onclick listener
        addGame.setOnMouseClicked(e ->{
            buttonClicked(addGame.getText().toString());
        });

        //Initializing scrollpane for center of the BorderPane
        centerPane = new ScrollPane();
        
        //Adding central part.
        addCenter();

        //Creating scene and adding inn the pane to display to user
        Scene scene = new Scene(pane, 800, 900);

        primaryStage.setTitle("Match history");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    //Button clicked handler
    public void buttonClicked(String name){
        String hScore = JOptionPane.showInputDialog(null, "Your goals: ");
        while(Integer.parseInt(hScore) < 0){
            hScore= JOptionPane.showInputDialog(null, "You cant have negative goals.");

        }
        String aScore = JOptionPane.showInputDialog(null, "Opponents goals: ");
        while(Integer.parseInt(aScore) < 0){
            aScore= JOptionPane.showInputDialog(null, "Opponents cant have negative goals.");
        }
        //Creates match object
        Match m = new Match(50, 0, 550, 100, hScore, aScore);

        gameList.add(0, m);

        //Updates Center
        addCenter();

        //Udates file
        updateFile(m);

        setRight();

    }

    //Adds components to the senter via a stackpane for text and object. inside a VBox container
    public void addCenter(){

        VBox box = new VBox();
        centerPane.setContent(null);
        ArrayList<Match> mL = new ArrayList<Match>();
        for(int i = 0; i<gameList.size();i++){
            gameList.get(i).setY((i*100)+20);
            mL.add(gameList.get(i));
            
            box.getChildren().add(new StackPane(mL.get(i),new Text( mL.get(i).getHScore() + " : " + mL.get(i).getAScore()
             + "\n" + mL.get(i).getDate())));
        }

        box.styleProperty().bind(Bindings.concat("-fx-font-size: 30"));    
        centerPane.setContent(box);
        pane.setCenter(centerPane);

    }

    //Writes the newly created object to a file
    public void updateFile(Match m){

        try {
            File fil = new File(filnavn);
            PrintWriter skriver = new PrintWriter(filnavn);

            for(int i = 0; i< gameList.size(); i++){
                skriver.println(gameList.get(i).getHScore() + "," + gameList.get(i).getAScore() + "," + gameList.get(i).getDate());
            }

            skriver.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void setRight(){
        int won = 0;
        for(Match m : gameList){
            if(Integer.parseInt(m.getHScore()) > Integer.parseInt(m.getAScore())){
                won++;
            }
        }
        VBox g = new VBox(5);
        
        Text antMatch = new Text();
        Text gWon = new Text();
        Text gLoss = new Text();

        antMatch.styleProperty().bind(Bindings.concat("-fx-font-size: 20"));
        antMatch.setText("Matches played: \n" + Integer.toString(gameList.size()));

        gWon.styleProperty().bind(Bindings.concat("-fx-font-size: 20;"));
        gWon.setFill(Color.GREEN);
        gWon.setText("\n Matches won: \n" + Integer.toString(won));

        gLoss.styleProperty().bind(Bindings.concat("-fx-font-size: 20;"));
        gLoss.setFill(Color.RED);
        gLoss.setText("\n Games lost: \n" + (gameList.size() - won));
        
        g.getChildren().addAll(antMatch, gWon, gLoss);
        pane.setRight(g);
    }
}