import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

public class Match{

    int hScore, aScore;
    String date;
    boolean win = false;

    public Match(int score1, int score2){
        this.hScore = score1;
        this.aScore = score2;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        this.date = dtf.format(now);
        if(hScore > aScore){
            win=true;
        }
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