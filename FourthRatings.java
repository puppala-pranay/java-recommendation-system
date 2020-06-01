
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {

    
       private double getAverageByID(String ID,int minimalRaters){
        double total = 0;
        int count = 0;
    for(Rater rr : RaterDatabase.getRaters()){
    if(rr.hasRating(ID)){
    count++;
    total = total+rr.getRating(ID);
    }
    }
    
    if(count>=minimalRaters){return total/count;}
    
    return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> arr = new ArrayList<Rating>();
        ArrayList<String> moviesIDs = MovieDatabase.filterBy(new TrueFilter());
    for(String id : moviesIDs){
    
    double avgrating = getAverageByID(id, minimalRaters);
    if(avgrating>0.0){
    Rating rat = new Rating(id,avgrating);
    arr.add(rat);
    }
    }
    return arr;
    }
    
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
    ArrayList<Rating> arr = new ArrayList<Rating>();
        ArrayList<String> moviesIDs = MovieDatabase.filterBy(filterCriteria);
    for(String id : moviesIDs){
    
    double avgrating = getAverageByID(id, minimalRaters);
    if(avgrating>0.0){
    Rating rat = new Rating(id,avgrating);
    arr.add(rat);
    }
    }
    return arr;
    }
    
    private double dotProduct(Rater me , Rater r){
      double dotP = 0; 
    for(String movID : me.getItemsRated()){
    if(r.hasRating(movID)){dotP = dotP + (me.getRating(movID)-5)*(r.getRating(movID)-5);}
    }
    return dotP;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
    Rater me = RaterDatabase.getRater(id);
    ArrayList<Rating> arr = new ArrayList<Rating>();
    for(Rater r : RaterDatabase.getRaters()){
    if(me==r){continue;}
    double dotP = dotProduct(me,r);
    if(dotP<=0){continue;}
    Rating rat = new Rating(r.getID(),dotP);
    arr.add(rat);
    }
    Collections.sort(arr,Collections.reverseOrder());
    return arr;
    }
    
    private double getSimilarAverageByID(int num_sim,ArrayList<Rating> s_Id ,String movie_ID,int minimalRaters){
        double total = 0;
        int count = 0;
        
    for(Rater rr : RaterDatabase.getRaters()){
        boolean  b= false;
        int k=0;
        for(int i =0;i<num_sim;i++){
            Rating rat = s_Id.get(i);
        if(rr.getID().equals(rat.getItem())){b = true;
        k=i;}
        }
    if(b && rr.hasRating(movie_ID)){
    count++ ; 
    total = total+rr.getRating(movie_ID)*s_Id.get(k).getValue();
    }
    }
    
    if(count>=minimalRaters){return total/count;}
    
    return 0.0;
    }
    
    public ArrayList<Rating> getSimilarRatings(String raterId,int numSimilarRaters,int minimalRaters){
       
        ArrayList<Rating>similarities_Id =  getSimilarities(raterId);
         ArrayList<Rating> arr = new ArrayList<Rating>();
        ArrayList<String> moviesIDs = MovieDatabase.filterBy(new TrueFilter());
    for(String id : moviesIDs){
    
    double avgrating = getSimilarAverageByID(numSimilarRaters,similarities_Id,id, minimalRaters);
    if(avgrating>0.0){
    Rating rat = new Rating(id,avgrating);
    arr.add(rat);
    }
    }
    Collections.sort(arr);
    return arr;
  }
  
  public ArrayList<Rating> getSimilarRatingsByFilter(String raterId,int numSimilarRaters,int minimalRaters,Filter f){
       
        ArrayList<Rating>similarities_Id =  getSimilarities(raterId);
         ArrayList<Rating> arr = new ArrayList<Rating>();
        ArrayList<String> moviesIDs = MovieDatabase.filterBy(f);
        System.out.printf("\n no of movies after filtering = %d\n",moviesIDs.size());
    for(String id : moviesIDs){
    
    double avgrating = getSimilarAverageByID(numSimilarRaters,similarities_Id,id, minimalRaters);
    if(avgrating>0.0){
    Rating rat = new Rating(id,avgrating);
    arr.add(rat);
    }
    }
    Collections.sort(arr);
    return arr;
  }
}
