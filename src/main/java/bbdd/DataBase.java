package bbdd;
import processing.core.PImage;

import java.sql.*;

public class DataBase {

        // Variable de connexió a la BBDD
        Connection c;

        // Variable de consulta
        Statement query;

        // Dades de connexió (user, password, nom de la base de dades)
        String user, password, databaseName;

        // Estat de la connexió
        public boolean connectat = false;

        public DataBase(String user, String password, String databaseName){
            this.user = user;
            this.password = password;
            this.databaseName = databaseName;
        }

        public void connect(){
            try {
                c = DriverManager.getConnection("jdbc:mysql://localhost:8889/"+databaseName, user, password);
                query = c.createStatement();
                System.out.println("Connectat a la BBDD! :) ");
                connectat = true;
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }

        // Retorna el número de files d'una taula
        public int getNumRowsTaula(String nomTaula){
            try {
                ResultSet rs = query.executeQuery( "SELECT COUNT(*) AS n FROM "+ nomTaula );
                rs.next();
                int numRows = rs.getInt("n");
                return numRows;
            }
            catch(Exception e) {
                System.out.println(e);
                return 0;
            }
        }

    // Retorna el número de files que retornaria una query SELECT qualsevol amb valor "n"
    public int getNumRowsQuery(String q){
        try {
            ResultSet rs = query.executeQuery( q);
            rs.next();
            return rs.getInt("n");
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }

        // Retorna el número de columnes d'una taula de la base de dades
        public int getNumColsTaula(String nomTaula){
            try {
                String q = "SELECT count(*) as n FROM information_schema.columns WHERE table_name ='"+ nomTaula +"' AND table_schema='"+databaseName+"'";
                System.out.println(q);
                ResultSet rs = query.executeQuery( q);
                rs.next();
                int numCols = rs.getInt("n");
                return numCols;
            }
            catch(Exception e) {
                System.out.println(e);
                return 0;
            }
        }

        // Retorna les dades d'una taula en concret
        public String[][] getInfoTaulaEdificio(){
            int numFiles = getNumRowsTaula("EDIFICIO");
            int numCols  = 8;
            String[][] info = new String[numFiles][numCols];
            try {
                ResultSet rs = query.executeQuery( "SELECT * FROM EDIFICIO");
                int nr = 0;
                while (rs.next()) {
                    info[nr][0] = String.valueOf(rs.getInt("ID_EDIFICIO"));
                    info[nr][1] = rs.getString("DESCRIPCION");
                    info[nr][2] = String.valueOf(rs.getFloat("POSX"));
                    info[nr][3] = String.valueOf(rs.getFloat("POSY"));
                    info[nr][4] = rs.getString("USUARIO");
                    info[nr][5] = String.valueOf(rs.getInt("ESTILO"));
                    info[nr][6] = String.valueOf(rs.getInt("MATERIAL"));
                    info[nr][7] = String.valueOf(rs.getInt("TIPOLOGIA"));
                    nr++;
                }
                return info;
            }
            catch(Exception e) {
                System.out.println(e);
                return null;
            }
        }

        public String[][] getEstilosEdificios(){
            int numFiles = getNumRowsTaula("ESTILO");
            int numCols = 2;
String[][] estilos = new String[numFiles][numCols];
try{
    ResultSet rs = query.executeQuery("SELECT * FROM ESTILO");
    int nr = 0;
    while(rs.next()){
        estilos[nr][0] = String.valueOf(rs.getString("ID_ESTILO"));
        estilos[nr][1] = String.valueOf(rs.getString("NOMBRE_ESTILO"));
        nr++;
    }
    return estilos;
} catch (Exception e) {
    System.out.println(e);
    return null;
}
        }

    public String[][] getMaterialesEdificios(){
        int numFiles = getNumRowsTaula("MATERIAL");
        int numCols = 2;
        String[][] estilos = new String[numFiles][numCols];
        try{
            ResultSet rs = query.executeQuery("SELECT * FROM MATERIAL");
            int nr = 0;
            while(rs.next()){
                estilos[nr][0] = String.valueOf(rs.getString("ID_MATERIAL"));
                estilos[nr][1] = String.valueOf(rs.getString("NOMBRE_MATERIAL"));
                nr++;
            }
            return estilos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] getTipologiaEdificios(){
        int numFiles = getNumRowsTaula("TIPOLOGIA");
        int numCols = 2;
        String[][] estilos = new String[numFiles][numCols];
        try{
            ResultSet rs = query.executeQuery("SELECT * FROM TIPOLOGIA");
            int nr = 0;
            while(rs.next()){
                estilos[nr][0] = String.valueOf(rs.getString("ID_TIPOLOGIA"));
                estilos[nr][1] = String.valueOf(rs.getString("NOMBRE_TIPOLOGIA"));
                nr++;
            }
            return estilos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


        public String[] getImagenesEdificio(String idEDIFICIO){
            int numFiles = getNumRowsQuery("SELECT COUNT(*) AS n FROM imagen img, edificio ed WHERE img.EDIFICIO=ed.ID_EDIFICIO AND ed.ID_EDIFICIO = '"+idEDIFICIO+"'");
            String[] info = new String[numFiles];
            try {
                ResultSet rs = query.executeQuery( "SELECT img.NOMBRE_IMAGEN AS imagen FROM imagen img, edificio ed WHERE img.EDIFICIO=ed.ID_EDIFICIO AND ed.ID_EDIFICIO = '"+idEDIFICIO+"'");
                int nr = 0;
                while (rs.next()) {
                    info[nr]= rs.getString("imagen");
                    nr++;
                }
                return info;
            }
            catch(Exception e) {
                System.out.println(e);
                return null;
            }

        }

        // Retorna les dades de la columna NOM de la taula UNITAT
        public String[] getColumnaNomTaulaUnitat(){
            int numFiles = getNumRowsTaula("unitat");
            String[] info = new String[numFiles];
            try {
                ResultSet rs = query.executeQuery( "SELECT nom FROM unitat ORDER BY nom ASC");
                int nr = 0;
                while (rs.next()) {
                    info[nr] = rs.getString("nom");
                    nr++;
                }
                return info;
            }
            catch(Exception e) {
                System.out.println(e);
                return null;
            }
        }

        // Retorna el valor de la Columna NUMERO de la taula UNITAT per aquella fila amb NOM
        public String getNumeroFromTaulaUnitat(String nom)  {
            try {
                ResultSet rs = query.executeQuery( "SELECT numero FROM unitat WHERE nom = '"+nom+"'");
                rs.next();
                return String.valueOf(rs.getInt("numero"));
            }
            catch(Exception e) {
                System.out.println(e);
                return null;
            }
        }


        // INSERTS

        // Inserta les dades a la taula Unitat

        void insertInfoTaulaUnitat(String num, String nom){
            try {
                String sNom = nom.replace("\'", "\\'");
                String q = "INSERT INTO unitat (numero, nom) VALUES ('" + num + "','" + sNom + "')";
                System.out.println(q);
                query.execute(q);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }


        // UPDATES

        // Actualitza les dades a la taula Unitat

        void updateInfoTaulaUnitat(String id, String num, String nom){
            try {
                String q = "UPDATE unitat SET numero='"+num+"', nom='"+nom+"' WHERE numero='"+id+"'";
                System.out.println(q);
                query.execute(q);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }

        // DELETES

        // Esborra la fila de la taula Unitat amb el número concret
        void deleteInfoTaulaUnitat(String id){
            try {
                String q = "DELETE FROM unitat WHERE numero='"+id+"'";
                System.out.println(q);
                query.execute(q);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
    }

