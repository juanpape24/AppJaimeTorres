/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionjaimetorres2.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author USUARIO
 */
public class BD {

    public BD() {
    }

    public Connection connectDatabase(String host, String port, String database,
            String user, String password) {
        String url = "";
        try {
            // We register the PostgreSQL driver
            // Registramos el driver de PostgresSQL
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(url,user, password);
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error al conectar con la base de datos de PostgreSQL (" + url + "): " + sqle);
            return null;
        }
    }
    
    public void getPinturasbyArtista(Connection conn, String idArtista){
        try{
             Statement st = conn.createStatement();
             String query= String.format("select * from pinturas inner join artistas on codigo_artista=artistas.codigo where artistas.codigo='%s'", idArtista);
             ResultSet result = st.executeQuery(query);
             while(result.next()){
                 String codigo = result.getString("codigo");
                 int precio = result.getInt("precio");
                 String fecha = result.getString("fecha");
                 System.out.println(codigo+" | "+precio+" | "+fecha);
             }
             result.close();
             st.close();
        }
        catch(Exception ex){
            System.out.println("Ocurrió un error al ejecutar la consulta: "+ex.getMessage());
        }
       
    }
    
    public void getArtistabyPintura(Connection conn, String idPintura){
        try{
             Statement st = conn.createStatement();
             String query= String.format("select * from artistas inner join pinturas on codigo_artista=artistas.codigo where pinturas.codigo='%s'", idPintura);
             ResultSet result = st.executeQuery(query);
             while(result.next()){
                 String codigo = result.getString("codigo");
                 String nombre = result.getString("nombre")+" "+result.getString("apellido");
                 System.out.println(codigo+" | "+nombre);
             }
             result.close();
             st.close();
        }
        catch(Exception ex){
            System.out.println("Ocurrió un error al ejecutar la consulta: "+ex.getMessage());
        }
       
    }
    
    public void getPrecioPinturasMuseo(Connection conn, String idMuseo){
        try{
             Statement st = conn.createStatement();
             String query= String.format("select sum(precio) from pinturas inner join artistas on codigo_artista=artistas.codigo inner join museos on codigo_museo=museos.codigo where codigo_museo='%s'", idMuseo);
             ResultSet result = st.executeQuery(query);
             while(result.next()){
                 long precio = result.getLong("sum");
                 System.out.println("El precio total de las pinturas del museo "+idMuseo+" es: "+precio);
             }
             result.close();
             st.close();
        }
        catch(Exception ex){
            System.out.println("Ocurrió un error al ejecutar la consulta: "+ex.getMessage());
        }
       
    }
    
    public void getArtistas(Connection conn){
        try{
             Statement st = conn.createStatement();
             String query= "select * from artistas";
             ResultSet result = st.executeQuery(query);
             while(result.next()){
                 String codigo = result.getString("codigo");
                 String nombre = result.getString("nombre")+" "+result.getString("apellido");
                 System.out.println(codigo+". "+nombre);
             }
             result.close();
             st.close();
        }
        catch(Exception ex){
            System.out.println("Ocurrió un error al ejecutar la consulta: "+ex.getMessage());
        }
       
    }
    
    public void getPinturas(Connection conn){
        try{
             Statement st = conn.createStatement();
             String query= "select * from pinturas";
             ResultSet result = st.executeQuery(query);
             while(result.next()){
                 String codigo = result.getString("codigo");
                 int precio = result.getInt("precio");
                 String fecha = result.getString("fecha");
                 System.out.println(codigo+" | "+precio+" | "+fecha);
             }
             result.close();
             st.close();
        }
        catch(Exception ex){
            System.out.println("Ocurrió un error al ejecutar la consulta: "+ex.getMessage());
        }
       
    }
    
    public void getMuseos(Connection conn){
        try{
             Statement st = conn.createStatement();
             String query= "select * from museos";
             ResultSet result = st.executeQuery(query);
             while(result.next()){
                 String codigo = result.getString("codigo");
                 String nombre = result.getString("nombre");
                 System.out.println(codigo+". "+nombre);
             }
             result.close();
             st.close();
        }
        catch(Exception ex){
            System.out.println("Ocurrió un error al ejecutar la consulta: "+ex.getMessage());
        }
       
    }
    
    public void CloseConnection(Connection conn){
        try{
            conn.close();
        }
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }
}
