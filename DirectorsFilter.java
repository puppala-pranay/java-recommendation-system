
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class DirectorsFilter implements Filter {

    private String direc ;
    private String[] list_direc;
    
    public DirectorsFilter(String directors){
    direc = directors;
    list_direc = direc.split(",");
    }
    
    public boolean satisfies(String Id){
        String movie_direcs = MovieDatabase.getDirector(Id);
    for(int k = 0 ; k < list_direc.length;k++){
    
    if(movie_direcs.indexOf(list_direc[k])>=0){return true;}
    }
    return false;
    }
}
