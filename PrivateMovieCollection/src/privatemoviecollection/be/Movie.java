package privatemoviecollection.be;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;

=======
>>>>>>> parent of 145f406... Add files via upload
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
    
<<<<<<< HEAD
    public Movie(int id, String name, int personalRating,
            int imdbRating, String lastview, String filelink, String categoriesList){
=======
    public Movie(int id, String name, int rating,
            String lastview, String filelink){
>>>>>>> parent of 145f406... Add files via upload
        this.id = id;
        this.name = name;
        this.personalRating = personalRating;
        this.imdbRating = imdbRating;
        this.lastview = lastview;
        this.filelink = filelink;
<<<<<<< HEAD
        this.categoriesList = Arrays.asList(categoriesList.split("_"));
=======
>>>>>>> parent of 145f406... Add files via upload
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
<<<<<<< HEAD
    
    public String getCategoriesList(){
        String result = "";
        for(String categorieslist : categoriesList)
            result += categorieslist + "_";
        return result.substring(0, result.length() - 1);
    }
    
    public void setCategoriesList(String categoriesList){
        this.categoriesList = Arrays.asList(categoriesList.split("_"));
    }
=======
>>>>>>> parent of 145f406... Add files via upload
}