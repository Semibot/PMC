package privatemoviecollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatemoviecollection.be.Category;

/**
 *
 * @author DKE
 */
public class CategoryDAO{
    SQLServerDataSource ds;
    
    public CategoryDAO(){
        ds = new SQLServerDataSource();
        ds.setDatabaseName("MyPrivateMovieCollection");
        ds.setUser("CS2018B_3");
        ds.setPassword("CS2018B_3");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }
    
    //Crud Create
    public Category createCategory(int id, String name) throws SQLException{
        
        Category c = null;
        try(Connection conn = ds.getConnection()){
            String sql = "INSERT INTO Category(name) "
                    + "VALUES(?)";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            c = new Category(id, name);
            
            int createdRows = stmt.executeUpdate();
            
            if(createdRows == 0){
                throw new SQLException("Creating category failed, no rows created.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    c.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Creating category failed, no ID obtained.");
                }
            }
        }
        return c;
    }
    
    //cRud Read
    public Category getCategory(int id){
        
        try(Connection conn = ds.getConnection()){
            PreparedStatement pstmt =
                    conn.prepareStatement("SELECT * FROM Category WHERE id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int idc = rs.getInt("id");
                String name = rs.getString("name");
                Category c = new Category(idc, name);
                return c;
            }
        }catch(SQLServerException ex){
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //crUd Update
    public void updateCategory(Category c) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "UPDATE Category SET name=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, c.getId());
            
            int updatedRows = stmt.executeUpdate();
            
            if(updatedRows == 0){
                throw new SQLException("Updating category failed, no rows updated.");
            }
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    c.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Updating category failed, no ID obtained.");
                }
            }
        }
    }
    
    //cruD Delete
    public void deleteCategory(Category c) throws SQLException{
        
        try(Connection conn = ds.getConnection()){
            String sql = "DELETE FROM Category WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, c.getId());
            
            int deletedRows = stmt.executeUpdate();
            
            if(deletedRows == 0){
                throw new SQLException("Deleting category failed, no rows deleted.");
            }
            
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    c.setId((int) generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Deleting category failed, no ID obtained.");
                }
            }
        }
    }
    
    public List<Category> getAllCategories(){
        
        List<Category> cateGories = new ArrayList();
        try(Connection conn = ds.getConnection()){
            String sqlStatement = "SELECT * FROM Category";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category c = new Category(id, name);
                cateGories.add(c);
            }
        }catch(SQLServerException ex){
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cateGories;
    }
}