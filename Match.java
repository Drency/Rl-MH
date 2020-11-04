import java.time.format.DateTimeFormatter;  
import javafx.scene.control.ColorPicker;
import java.time.LocalDateTime; 
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Match extends Rectangle{

    int hScore, aScore;
    double x, y, w, h;
    String date;
    boolean win = false;

    public Match(double x, double y, double w, double h, int score1, int score2){
        super(x, y, w, h);
        this.hScore = score1;
        this.aScore = score2;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        this.date = dtf.format(now);
        Color fillColor = Color.RED;
        if(hScore > aScore){
            win=true;
            fillColor = Color.GREEN;
        }
        
        this.setFill(fillColor);
    }

    public Match(){

    }


    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public int getHScore(){
        return this.hScore;
    }

    public void setHScore(int hScore){
        this.hScore = hScore;
    }

    public int getAScore(){
        return this.aScore;
    }

    public void setAScore(int aScore){
        this.aScore = aScore;
    }

}