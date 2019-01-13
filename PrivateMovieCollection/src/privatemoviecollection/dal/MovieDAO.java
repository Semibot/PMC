package privatemoviecollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatemoviecollection.be.Movie;

/**
 *
 * @author DKE
 */
public class MovieDAO{
    SQLServerDataSource ds;
    
    public MovieDAO(){
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyPrivateMovieCollection");
        ds.setUser("CS2018B_3");
        ds.setPassword("CS2018B_3");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
    
    //Crud Create
    public Movie createMovie(int id, Movie m) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "INSERT INTO Movie(name, rating,"
                 + " lastview, filelink, categories) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, m.getName());
            stmt.setInt(2, m.getRating());
            stmt.setString(3, m.getLastview());
            stmt.setString(4, m.getFilelink());
            stmt.setString(5, String.valueOf(m.getCategoriesList()));
            
            int createdRows = stmt.executeUpdate();
            
            if(createdRows == 0){
                throw new SQLException("Creating movie failed, no rows created.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    m.setId((int)generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Creating movie failed, no ID obtained");
                }
            }
        }
        return m;
    }
    
    //cRud Read
    public Movie getMovie(int id){
        
        try(Connection conn = ds.getConnection()){
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM Movie WHERE id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int ids = rs.getInt("id");
                String name = rs.getString("name");
                int rating = rs.getInt("rating");
                String listview = rs.getString("listview");
                String filelink = rs.getString("filelink");
                String categories = rs.getString("categories");
                Movie m = new Movie(ids, name, rating,
                listview, filelink, Arrays.asList(categories));
                return m;
            }
        }catch(SQLServerException ex){
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //crUd Update
    public void updateMovie(Movie m) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "UPDATE Movie SET name=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, m.getName());
            stmt.setInt(2, m.getId());
            
            int updatedRows = stmt.executeUpdate();
            
            if(updatedRows == 0){
                throw new SQLException("Updating movie failed, no rows updated.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    m.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Updating a movie failed, no song updated.");
                }
            }
        }
    }
    
    //cruD Delete
    public void deleteMovie(Movie m) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "DELETE FROM Movie WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, m.getId());
            
            int deletedRows = stmt.executeUpdate();
            
            if(deletedRows == 0){
                throw new SQLException("Deleting a movie failed, no rows deleted.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    m.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Deleting a movie failed, no ID obtained.");
                }
            }
        }
    }
    
    public List<Movie> getAllMovies(){
        
        List<Movie> mov = new ArrayList();
        try(Connection conn = ds.getConnection()){
            String sqlStatement = "SELECT * FROM Movie";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int rating = rs.getInt("rating");
                String lastview = rs.getString("lastview");
                String filelink = rs.getString("filelink");
                String categories = rs.getString("categories");
                Movie m = new Movie(id, name, rating,
                        lastview, filelink, Arrays.asList(categories));
                mov.add(m);
            }
        }catch(SQLServerException ex){
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mov;
    }
}