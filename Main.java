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
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.beans.binding.Bindings;
import java.io.*;
import java.util.*;
import java.lang.Integer;
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

    //Main launching application
    public static void main(String[] args){
        Application.launch(args);
        
    }

    //Starts the stage and creates all the elements
    public void start(Stage primaryStage){
        
        pane = new BorderPane();
        Button addGame = new Button("Add match");
        Button editGame = new Button("Edit game");

        //Getting matches from txt file
        try {
            
            File fil = new File(filnavn);
            leser = new Scanner(fil);
            String linje = "";
            do{
                
                linje = leser.nextLine();
                String[] matchList =linje.split(", *");
                for(String s : matchList){
                    intList.add(Integer.parseInt(s));
                }
                
            } while( leser.hasNextLine() );
            
            leser.close();
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }
        //String nyttNavn = showInputDialog("Skriv inn et navn: \n Eks: Fornavn Etternavn");

        ArrayList<Integer> aScoreList = new ArrayList<Integer>();
        ArrayList<Integer> hScoreList = new ArrayList<Integer>();
    
        int scoreH, scoreA;

        for (int x = 0; x<intList.size(); x+=2){
            hScoreList.add(intList.get(x));
        }

        for(int w = 1; w<intList.size(); w+=2){
            aScoreList.add(intList.get(w));
        }

        System.out.println(aScoreList + " : " + hScoreList);
        for(int y = 0; y < hScoreList.size();y++){
            gameList.add(new Match(50, 0, 600, 100, hScoreList.get(y), aScoreList.get(y)));
        }


        System.out.println("Gamelist from scratch: " + gameList);

        pane.setLeft(addGame);
        pane.setRight(editGame);

        //Onclick listener
        addGame.setOnMouseClicked(e ->{
            buttonClicked(addGame.getText().toString());
        });

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

        //Updates Center
        addCenter();

        updateFile(m);

    }

    //Adds components to the senter via a stackpane for text and object. inside a VBox container
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

    public void updateFile(Match m){

        try {
            File fil = new File(filnavn);
        PrintWriter skriver = new PrintWriter(filnavn);

        for(int i = 0; i< gameList.size(); i++){
            
            skriver.println(gameList.get(i).getHScore() + "," + gameList.get(i).getAScore());
        }

        skriver.close();
        } catch (Exception e) {
            //TODO: handle exception
        }

    }
}