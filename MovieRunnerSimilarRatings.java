
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {

    public void printAverageRatings(){
    
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        System.out.printf("\n no of movies : %d\n",MovieDatabase.size());
        System.out.printf("no of raters = %d\n",RaterDatabase.size());
        
        ArrayList<Rating> arr = sr.getAverageRatings(1);
        System.out.printf("\nfound %d movies\n",arr.size());
        Collections.sort(arr);
        for(Rating rat : arr){
        System.out.printf("\n %4.2f   %s ",rat.getValue(), MovieDatabase.getTitle(rat.getItem()));
        
        }
        
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
    
         FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        System.out.printf("\n no of movies : %d\n",MovieDatabase.size());
        System.out.printf("no of raters = %d\n",RaterDatabase.size());
        
        AllFilters al = new AllFilters();
        al.addFilter(new YearAfterFilter(1980));
        al.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> arr = sr.getAverageRatingsByFilter(1,al);
        System.out.printf("\nfound %d movies\n",arr.size());
        Collections.sort(arr);
        for(Rating rat : arr){
        System.out.printf("\n %4.2f   %s  %d",rat.getValue(), MovieDatabase.getTitle(rat.getItem()),MovieDatabase.getYear(rat.getItem()));
        System.out.printf("\n Genre ==  %s\n",MovieDatabase.getGenres(rat.getItem()));
        }
        
        
    }
    
    public void printSimilarRatings(){
    
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.printf("\n no of movies : %d\n",MovieDatabase.size());
        System.out.printf("no of raters = %d\n",RaterDatabase.size());
        System.out.println("enter the rater id");
        Scanner sc = new Scanner(System.in);
        String rater_id = sc.nextLine();
        ArrayList<Rating> arr = sr.getSimilarRatings("65",20,5);
        System.out.printf("\nfound %d movies\n",arr.size());
        Collections.sort(arr);
        for(Rating rat : arr){
        System.out.printf("\n %4.2f   %s ",rat.getValue(), MovieDatabase.getTitle(rat.getItem()));
        
        }
    }
    
    public void printSimilarRatingsByGenre(){
    
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.printf("\n no of movies : %d\n",MovieDatabase.size());
        System.out.printf("no of raters = %d\n",RaterDatabase.size());
        GenreFilter f = new GenreFilter("Action");
        ArrayList<Rating> arr = sr.getSimilarRatingsByFilter("65",20,5,f);
        System.out.printf("\nfound %d movies\n",arr.size());
        Collections.sort(arr);
        for(Rating rat : arr){
        System.out.printf("\n %4.2f   %s ",rat.getValue(), MovieDatabase.getTitle(rat.getItem()));
        
        }
    }
    
    public void printSimilarRatingsByDirector(){
    
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.printf("\n no of movies : %d\n",MovieDatabase.size());
        System.out.printf("no of raters = %d\n",RaterDatabase.size());
        DirectorsFilter f = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        ArrayList<Rating> arr = sr.getSimilarRatingsByFilter("1034",10,3,f);
        System.out.printf("\nfound %d movies\n",arr.size());
        Collections.sort(arr);
        for(Rating rat : arr){
        System.out.printf("\n %4.2f   %s ",rat.getValue(), MovieDatabase.getTitle(rat.getItem()));
        
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
    
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.printf("\n no of movies : %d\n",MovieDatabase.size());
        System.out.printf("no of raters = %d\n",RaterDatabase.size());
        AllFilters al = new AllFilters();
        al.addFilter(new GenreFilter("Adventure"));
        al.addFilter(new MinutesFilter(100,200));
        ArrayList<Rating> arr = sr.getSimilarRatingsByFilter("65",10,5,al);
        System.out.printf("\nfound %d movies\n",arr.size());
        Collections.sort(arr);
        for(Rating rat : arr){
        System.out.printf("\n %4.2f   %s ",rat.getValue(), MovieDatabase.getTitle(rat.getItem()));
        
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
    
        FourthRatings sr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.printf("\n no of movies : %d\n",MovieDatabase.size());
        System.out.printf("no of raters = %d\n",RaterDatabase.size());
        AllFilters al = new AllFilters();
        al.addFilter(new YearAfterFilter(2000));
        al.addFilter(new MinutesFilter(80,100));
        ArrayList<Rating> arr = sr.getSimilarRatingsByFilter("65",10,5,al);
        System.out.printf("\nfound %d movies\n",arr.size());
        Collections.sort(arr);
        for(Rating rat : arr){
        System.out.printf("\n %4.2f   %s ",rat.getValue(), MovieDatabase.getTitle(rat.getItem()));
        
        }
    }
}
