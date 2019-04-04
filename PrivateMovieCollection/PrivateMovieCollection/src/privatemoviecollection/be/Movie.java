package privatemoviecollection.be;

import java.util.List;

/**
 *
 * @author DKE
 */
public class Movie{
    private int id;
    private String name;
    private int personalRating;
    private int imdbRating;
    private String filelink;
    private String lastview;
    private List<String> categoriesList;
    
    public Movie(){
        
    }
    
    public Movie(int id, String name, int personalRating,
            int imdbRating, String lastview, String filelink, List<String> categoriesList){
        this.id = id;
        this.name = name;
        this.personalRating = personalRating;
        this.imdbRating = imdbRating;
        this.lastview = lastview;
        this.filelink = filelink;
        this.categoriesList = categoriesList;
    }
    
    @Override
    public String toString(){
        return name +"\t\t\t\t"+ imdbRating +"\t\t\t"+
          personalRating +"\t\t\t\t" + lastview;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPersonalRating(){
        return personalRating;
    }

    public void setPersonalRating(int personalRating){
        this.personalRating = personalRating;
    }
    
    public int getImdbRating(){
        return imdbRating;
    }
    
    public void setImdbRating(int imdbRating){
        this.imdbRating = imdbRating;
    }

    public String getFilelink(){
        return filelink;
    }

    public void setFilelink(String filelink){
        this.filelink = filelink;
    }

    public String getLastview(){
        return lastview;
    }

    public void setLastview(String lastview){
        this.lastview = lastview;
    }
    
    public List<String> getCategoriesList(){
        return categoriesList;
    }
    
    public void setCategoriesList(List<String> categoriesList){
        this.categoriesList = categoriesList;
    }
}