package privatemoviecollection.be;

import java.util.List;

/**
 *
 * @author DKE
 */
public class CatMovie{
    private int id;
    private int categoryId;
    private int movieId;
    private Movie m;
    
    public CatMovie(int id, int categoryId, int movieId){
        this.id = id;
        this.categoryId = categoryId;
        this.movieId = movieId;
    }
    
    public CatMovie (int id, String name, List<String> categories){
        m = new Movie();
        //m.getName() = name;
        //m.getCategoriesList() = categories;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getCategoryId(){
        return categoryId;
    }
    
    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }
    
    public int getMovieId(){
        return movieId;
    }
    
    public void setMovieId(int movieId){
        this.movieId = movieId;
    }
}