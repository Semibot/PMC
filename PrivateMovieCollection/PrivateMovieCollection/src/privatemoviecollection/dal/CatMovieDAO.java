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
import privatemoviecollection.be.CatMovie;

/**
 *
 * @author DKE
 */
public class CatMovieDAO {
    SQLServerDataSource ds;
    
    public CatMovieDAO(){
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyPrivateMovieCollection");
        ds.setUser("CS2018B_3");
        ds.setPassword("CS2018B_3");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
    
    //Crud Create
    public CatMovie createCatMovie(int id, CatMovie cm) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "INSERT INTO CatMovie(categoryId, movieId)"
                 + " VALUES(?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, cm.getCategoryId());
            stmt.setInt(2, cm.getMovieId());
            
            int createdRows = stmt.executeUpdate();
            
            if(createdRows == 0){
                throw new SQLException("Creating catmovie failed, no rows created.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    cm.setId((int)generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Creating catmovie failed, no ID obtained");
                }
            }
        }
        return cm;
    }
    
    //cRud Read
    public CatMovie getCatMovie(int id){
        
        try(Connection conn = ds.getConnection()){
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM CatMovie WHERE id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int ids = rs.getInt("id");
                int categoryId = rs.getInt("categoryId");
                int movieId = rs.getInt("movieId");
                CatMovie cm = new CatMovie(ids, categoryId, movieId);
                return cm;
            }
        }catch(SQLServerException ex){
            Logger.getLogger(CatMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(CatMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //crUd Update
    public void updateCatMovie(CatMovie cm) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "UPDATE CatMovie SET categoryId=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, cm.getCategoryId());
            stmt.setInt(2, cm.getMovieId());
            
            int updatedRows = stmt.executeUpdate();
            
            if(updatedRows == 0){
                throw new SQLException("Updating catmovie failed, no rows updated.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    cm.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Updating a catmovie failed, no catmovie updated.");
                }
            }
        }
    }
    
    //cruD Delete
    public void deleteCatMovie(CatMovie cm) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "DELETE FROM CatMovie WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, cm.getId());
            
            int deletedRows = stmt.executeUpdate();
            
            if(deletedRows == 0){
                throw new SQLException("Deleting a catmovie failed, no rows deleted.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    cm.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Deleting a catmovie failed, no ID obtained.");
                }
            }
        }
    }
    
    public List<CatMovie> getAllCatMovies(){
        
        List<CatMovie> cmov = new ArrayList();
        try(Connection conn = ds.getConnection()){
            String sqlStatement = "SELECT id, name, "
            +"categories FROM Movie INNER JOIN CatMovie"
               +" ON Movie.id = CatMovie.movieId";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next()){
                int ids = rs.getInt("id");
                String name = rs.getString("name");
                String categories = rs.getString("categories");
                CatMovie cm = new CatMovie(ids, name, Arrays.asList(categories));
                cmov.add(cm);
            }
        }catch(SQLServerException ex){
            Logger.getLogger(CatMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(CatMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cmov;
    }
}