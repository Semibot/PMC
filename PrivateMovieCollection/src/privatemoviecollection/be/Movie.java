package privatemoviecollection.be;

/**
 *
 * @author DKE
 */
public class Movie{
    private int id;
    private String name;
    private int rating;
    private String filelink;
    private String lastview;
    
    public Movie(int id, String name, int rating,
            String lastview, String filelink){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.lastview = lastview;
        this.filelink = filelink;
    }
    
    @Override
    public String toString(){
        return name + "\t\t\t\t\t" + rating +
                "\t\t\t\t\t\t" + lastview;
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

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
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
}