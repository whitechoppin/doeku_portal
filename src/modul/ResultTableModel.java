package modul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vincent
 */
public class ResultTableModel extends AbstractTableModel {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private int numofrow;
    private String query;

    private boolean connstat = false;

    public ResultTableModel(String driver, String url, String username,
        String password, String query) throws ClassNotFoundException,
        SQLException {
        this.query = query;
        Class.forName(driver);

        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement();
        
        connstat = true;
           setQuery(query);
    }

    @Override
    public Class getColumnClass(int column) throws IllegalStateException {
        if(! connstat)
            throw new IllegalStateException("Not Connected to Database");

        try {
            String classname = rsmd.getColumnClassName(column + 1);

            return Class.forName(classname);
        } catch(ClassNotFoundException cnfex) {
        } catch(SQLException sqlex) { }

        return Object.class;
    }

    @Override
    public int getRowCount() throws IllegalStateException {
        if(! connstat)
            throw new IllegalStateException("Not Connected to Database");
            return numofrow;
    }

    @Override
    public int getColumnCount() throws IllegalStateException {
        if(! connstat)
            throw new IllegalStateException("Not Connected to Database");

        try {
            return rsmd.getColumnCount();
        } catch(SQLException sqlex) { }

        return 0;
    }

    @Override
    public String getColumnName(int column) throws IllegalStateException {
        if(! connstat)
            throw new IllegalStateException("Not Connected to Database");

        try {
            return rsmd.getColumnName(column + 1);
        } catch(SQLException sqlex) { }

        return "";
    }
    
    @Override
    public Object getValueAt(int row, int column) throws IllegalStateException {
        if(! connstat)
            throw new IllegalStateException("Not Connected to Database");

        try {
            rs.absolute(row + 1);

            return rs.getObject(column + 1);
        } catch(SQLException sqlex) { }

        return "";
    }

    public PreparedStatement setPreparedStatement(String query) throws
            IllegalStateException, SQLException {
        PreparedStatement ps;
        
        if(! connstat)
            throw new IllegalStateException("Not Connected to Database");
        
        ps = conn.prepareStatement(query);
        
        return ps;
    }
    
    public ResultSet getResultSet() throws 
            IllegalStateException {
         if(! connstat)
            throw new IllegalStateException("Not Connected to Database");
         
         return rs;
    }
    
   public void setQuery(String query) throws SQLException,IllegalStateException {

        if(! connstat)
            throw new IllegalStateException("Not Connected to Database");

        rs = stmt.executeQuery(query);
        rsmd = rs.getMetaData();

        rs.last();
        numofrow = rs.getRow();

        //fireTableStructureChanged();
        fireTableDataChanged();
    }

    public void disconnect() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException sqlex) {
        } finally {
            connstat = false;
        }
    }
}
