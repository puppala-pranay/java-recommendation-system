
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename){
     FileResource fr = new FileResource("data/"+filename);
     CSVParser parser = fr.getCSVParser();
     ArrayList <Movie> arr = new ArrayList<Movie>();
     for(CSVRecord record : parser){
        String id = record.get("id");
        String title = record.get("title");
        String year = record.get("year");
        String country = record.get("country");
        String genre = record.get("genre");
        String director = record .get("director");
        int min = Integer.parseInt(record.get("minutes").trim());
        String poster = record .get("poster");
        Movie mv = new Movie(id,title,year,genre,director,country,poster,min);
        arr.add(mv);
        }
      return arr;
    }
    
    public void testLoadMovies(){
    ArrayList<Movie> arr = loadMovies("ratedmoviesfull.csv");
    int size = arr.size();
    int comedy=0;
    int length=0;
    int largestno=0;
    String director = null;
    
    HashMap<String,Integer> hm = new HashMap<String,Integer>();
    
    System.out.println("no of movies = "+size);
    for(Movie mv : arr){
    if(size<50){System.out.println(mv);}
    if(mv.getGenres().indexOf("Comedy")>0){comedy++;}
    if(mv.getMinutes()>150){length++;}
    if(hm.containsKey(mv.getDirector())){hm.replace(mv.getDirector(),hm.get(mv.getDirector())+1);}
    else{hm.put(mv.getDirector(),1);}
    }
    
    for(String dir :hm.keySet()){
    if(hm.get(dir)>largestno){largestno = hm.get(dir);
        director = dir;
    }
    }
    System.out.println("the largest no of movies by a director = "+largestno);
    for(String dir :hm.keySet()){
    if(hm.get(dir)==largestno){System.out.println(dir);}
    }
    System.out.printf("no of comedy movies = %d\n",comedy);
    System.out.printf("no of movies with 150+ length = %d\n",length);
        
        
    }
    
    public ArrayList<Rater> loadRaters(String filename){
    
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser =  fr.getCSVParser();
        ArrayList<Rater> arr = new ArrayList<Rater>();
        for(CSVRecord record : parser){
        String raterid = record.get("rater_id");
        boolean b = false;
        int required =0;
         
        for(int i = 0;i<arr.size();i++){
        if(arr.get(i).getID().equals(raterid)){b = true;
        required = i;}
        }
        
        if(b){
        arr.get(required).addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
        }else{
        Rater rr = new EfficientRater(raterid);
        rr.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
        arr.add(rr);
        }
        
        
        
        
        
        }
    
        return arr;
    }
    
    public void testLoadRaters(){
    ArrayList<Rater>arr =  loadRaters("ratings.csv");
    int size = arr.size();
    String req_rater="2";
    System.out.println("No of raters = "+size);
    int  maxno_ratings=0;
    int no_of_maxraters=0;
    String part_movie = "1798709";
    int no_ratings_partmovie = 0;
   HashSet<String> movies_ids = new HashSet<String>(); 
    
    for(Rater rr : arr){
        String id = rr.getID();
         int no_ratings = rr.numRatings();
    System.out.println(id+"  "+ no_ratings);
    if(rr.getID().equals(req_rater)){System.out.printf("\nthe no of ratings by %s is %d\n",req_rater,no_ratings);}
    if(no_ratings>maxno_ratings){
    maxno_ratings = no_ratings;
    }
    
    // ArrayList<String> ratingitems = rr.getItemsRated();
    // for(String str : ratingitems){
    // System.out.println("movieID = "+str +" rating = "+ rr.getRating(str));
    
    // }
    
    
    }
    System.out.printf("\ndetails of raters with maxratings ie %d :\n",maxno_ratings);
    for(Rater rr : arr){
        String id = rr.getID();
        
         int no_ratings = rr.numRatings();
         if(no_ratings==maxno_ratings){
            no_of_maxraters++;
            System.out.println(id);
            }
            
         if(rr.hasRating(part_movie)){no_ratings_partmovie++;}
         
         ArrayList<String> movies_inrater = rr.getItemsRated();
         for(String movie_id : movies_inrater){
            if(!movies_ids.contains(movie_id)){movies_ids.add(movie_id);}
             
             
            }
         
         
         
        }
        
        System.out.printf("\n no of ratings for %s is %d",part_movie,no_ratings_partmovie);
        System.out.printf("\n no of movies = %d",movies_ids.size());
    
    }
    
    
    
}
