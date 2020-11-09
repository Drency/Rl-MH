//Imports
import java.time.format.DateTimeFormatter;  
import javafx.scene.control.ColorPicker;
import java.time.LocalDateTime; 
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * Constructor class for each match.
 * Extends Rectangle class
 */
public class Match extends Rectangle{

    //Global vars
    String hScore, aScore;
    double x, y, w, h;
    String date;
    boolean win = false;

    /**
     * Constructor for the Match object
     * @params x = x pos, y= y pos, w = width of box, h = height of box, score1 = your score, score2 = their score
     */
    public Match(double x, double y, double w, double h, String score1, String score2){
        super(x, y, w, h);
        this.hScore = score1;
        this.aScore = score2;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        this.date = dtf.format(now);
        Color fillColor = Color.RED;
        
        //Sets color based on if you win or lose.
        if(Integer.parseInt(hScore) > Integer.parseInt(aScore)){
            win=true;
            fillColor = Color.GREEN;
        }
        
        this.setFill(fillColor);
    }

    //Empty constructor
    public Match(){

    }


    //GET and SET methods.
    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getHScore(){
        return this.hScore;
    }

    public void setHScore(String hScore){
        this.hScore = hScore;
    }

    public String getAScore(){
        return this.aScore;
    }

    public void setAScore(String aScore){
        this.aScore = aScore;
    }
}