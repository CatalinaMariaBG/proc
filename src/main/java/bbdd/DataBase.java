package bbdd;

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
                Class.forName("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection("jdbc:mysql://localhost:8889/"+databaseName+ "?useTimezone=true&serverTimezone=UTC", user, password);
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


    public String[] getInfoTotalEdificios(String nom){
        int llargaria = 8;
        String[] info = new String[llargaria];
        try {
            String q = "SELECT * FROM EDIFICIO WHERE DESCRIPCION = '"+nom+"'";
            System.out.println(q);
            ResultSet rs = query.executeQuery(q);
            rs.next();
                info[0] = String.valueOf(rs.getInt("ID_EDIFICIO"));
                info[1] = nom;
                info[2] = String.valueOf(rs.getFloat("POSX"));
                info[3] = String.valueOf(rs.getFloat("POSY"));
                info[4] = rs.getString("USUARIO");
                //Estil
                info[5] = getNomEstil(rs.getInt("ESTILO"));
                //Tipologia
                info[6] = getNomTipologia(rs.getInt("TIPOLOGIA"));
                //Material
                info[7] = getNomMaterial(rs.getInt("MATERIAL"));

            for(int i = 0; i<info.length; i++) {
                System.out.println(info[i]);
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int getIDEdificio(String nom){
            try{
                String q = "SELECT EDIFICIO.ID_EDIFICIO FROM EDIFICIO WHERE EDIFICIO.DESCRIPCION = '"+nom+"'";
                ResultSet rs = query.executeQuery(q);
                rs.next();
                return rs.getInt("ID_EDIFICIO");
            }
            catch (Exception e){
                System.out.println(e);
                return -1;
            }
    }

    public String getImagenEdificio(int idEdificio){
        try {
            String q = "SELECT IMAGEN.NOMBRE_IMAGEN FROM IMAGEN WHERE IMAGEN.EDIFICIO ='"+idEdificio+"'";
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getString("NOMBRE_IMAGEN");
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String getImageName(int idImage){
        try {
            Statement query = c.createStatement();
            String q = "SELECT IMAGEN.NOMBRE_IMAGEN FROM IMAGEN WHERE ID_IMAGEN ='"+idImage+"'";
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getString("NOMBRE_IMAGEN");
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] getInfoMapaEdificios(){
        String qN = "SELECT COUNT(*) AS n FROM EDIFICIO ed, IMAGEN img WHERE ed.ID_EDIFICIO=img.EDIFICIO AND img.MAPA='S'";
        System.out.println(qN);
        int numFiles = getNumRowsQuery(qN);
        int numCols  = 5;
        String[][] info = new String[numFiles][numCols];
        try {
            String q = "SELECT  ed.ID_EDIFICIO AS ID, ed.DESCRIPCION AS NOMBRE, ed.POSX AS LNG, ed.POSY AS LAT, img.NOMBRE_IMAGEN AS IMAGEN " +
                    " FROM EDIFICIO ed, IMAGEN img " +
                    " WHERE ed.ID_EDIFICIO = img.EDIFICIO AND img.MAPA = 'S' " +
                    " ORDER BY ed.ID_EDIFICIO ASC";
            ResultSet rs = query.executeQuery( q);
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("ID"));
                info[nr][1] = rs.getString("NOMBRE");
                info[nr][2] = String.valueOf(rs.getFloat("LNG"));
                info[nr][3] = String.valueOf(rs.getFloat("LAT"));
                info[nr][4] = rs.getString("IMAGEN");
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] getQualitiesEdificios(String nomEdificio){
        int numFiles = 1;
        int numCols  = 3;
        String[][] info = new String[numFiles][numCols];
        try {
            ResultSet rs = query.executeQuery( "SELECT * FROM EDIFICIO WHERE DESCRIPCION = '"+nomEdificio+"'");
            int nr = 0;
            while (rs.next()) {
                info[nr][0] = String.valueOf(rs.getInt("ESTILO"));
                info[nr][1] = String.valueOf(rs.getInt("MATERIAL"));
                info[nr][2] = String.valueOf(rs.getInt("TIPOLOGIA"));
                nr++;
            }
            return info;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public String[] getImagenesEdificio(String idEDIFICIO){
        int numFiles = getNumRowsQuery("SELECT COUNT(*) AS n FROM IMAGEN img, EDIFICIO ed WHERE img.EDIFICIO=ed.ID_EDIFICIO AND ed.ID_EDIFICIO = '"+idEDIFICIO+"'");
        String[] info = new String[numFiles];
        try {
            ResultSet rs = query.executeQuery( "SELECT img.NOMBRE_IMAGEN AS IMAGEN FROM IMAGEN img, EDIFICIO ed WHERE img.EDIFICIO=ed.ID_EDIFICIO AND ed.ID_EDIFICIO = '"+idEDIFICIO+"'");
            int nr = 0;
            while (rs.next()) {
                info[nr]= rs.getString("IMAGEN");
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

    public String[][] getMapaQualitats(){
            int numFiles = getNumRowsTaula("EDIFICIO");
            int numCols = 4;
            String[][] info = new String[numFiles][numCols];
        try{
            ResultSet rs = query.executeQuery("SELECT * FROM EDIFICIO");
            int nr = 0;
            while(rs.next()){
                info[nr][0] = String.valueOf(rs.getString("ID_EDIFICIO"));
                info[nr][1] = getNomEstil(rs.getInt("ESTILO"));
                info[nr][2] = getNomMaterial(rs.getInt("MATERIAL"));
                info[nr][3] = getNomTipologia(rs.getInt("TIPOLOGIA"));
                nr++;
            }
            return info;
        } catch (Exception e) {
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

        public int getIDEstil(String nomEstil){
            try{
                ResultSet rs = query.executeQuery("SELECT ID_ESTILO FROM ESTILO WHERE ESTILO.NOMBRE_ESTILO = '"+nomEstil+"'");
                rs.next();
                return rs.getInt("ID_ESTILO");
            }catch(Exception e) {
                System.out.println(e);
                return -1;
            }
        }

    public String getNomEstil(int id){
        try{
            String q = "SELECT NOMBRE_ESTILO FROM ESTILO WHERE ESTILO.ID_ESTILO = '"+id+"'";
            System.out.println(q);
            Statement query = c.createStatement();
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getString("NOMBRE_ESTILO");
        }catch(Exception e) {
            System.out.println(e);
            return "null";
        }
    }

    public int getIDMaterial(String nomMaterial){
        try{
            String q = "SELECT ID_MATERIAL FROM MATERIAL WHERE MATERIAL.NOMBRE_MATERIAL = '"+nomMaterial+"'";
            System.out.println(q);
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("ID_MATERIAL");
        }catch(Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public String getNomMaterial(int id){
            try{
                String q = "SELECT NOMBRE_MATERIAL FROM MATERIAL WHERE MATERIAL.ID_MATERIAL = '"+id+"'";
                System.out.println(q);
                Statement query = c.createStatement();
                ResultSet rs = query.executeQuery(q);
                rs.next();
                return rs.getString("NOMBRE_MATERIAL");
            }
            catch(Exception e) {
                System.out.println(e);
                return "null";
            }
        }

    public int getIDTipologia(String nomTipologia){
        try{
            String q = "SELECT ID_TIPOLOGIA FROM TIPOLOGIA WHERE TIPOLOGIA.NOMBRE_TIPOLOGIA = '"+nomTipologia+"'";
            System.out.println(q);
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("ID_TIPOLOGIA");
        }catch(Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public String getNomTipologia(int id){
        try{
            String q = "SELECT NOMBRE_TIPOLOGIA FROM TIPOLOGIA WHERE TIPOLOGIA.ID_TIPOLOGIA = '"+id+"'";
            System.out.println(q);
            Statement query = c.createStatement();
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getString("NOMBRE_TIPOLOGIA");
        }catch(Exception e) {
            System.out.println(e);
            return "null";
        }
    }

    public String[][] getUsuaris(){
        int numFiles = getNumRowsTaula("USUARIO");
        int numCols = 2;
        String[][] usuarios = new String[numFiles][numCols];
        try{
            ResultSet rs = query.executeQuery("SELECT * FROM USUARIO");
            int nr = 0;
            while(rs.next()){
                usuarios[nr][0] = rs.getString("ID_USUARIO");
                usuarios[nr][1] = rs.getString("PASSWORD");
                nr++;
            }
            return usuarios;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] getProyectos(){
        int numFiles = getNumRowsTaula("PROYECTO");
        int numCols = 3;
        String[][] proyectos = new String[numFiles][numCols];
        try{
            Statement query = c.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM PROYECTO");
            int nr = 0;
            while(rs.next()){
                proyectos[nr][0] = rs.getString("DESCRIPCION");
                proyectos[nr][1] = rs.getString("FECHA_FINAL");
                proyectos[nr][2] = String.valueOf(getNumMuros(rs.getInt("ID_PROYECTO")));
                nr++;
            }
            return proyectos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String[][] getSelectProyectos(){
        int numFiles = getNumRowsTaula("PROYECTO");
        int numCols = 2;
        String[][] proyectos = new String[numFiles][numCols];
        try{
            Statement query = c.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM PROYECTO");
            int nr = 0;
            while(rs.next()){
                proyectos[nr][0] = rs.getString("ID_PROYECTO");
                proyectos[nr][1] = rs.getString("DESCRIPCION");
                nr++;
            }
            return proyectos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int getNumProyectos(){
        try{
            Statement query = c.createStatement();
            String q = "SELECT COUNT(*) AS n FROM PROYECTO";
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("n");
        }catch(Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public int getNumMuros(int idProyecto){
        try{
            String q = "SELECT COUNT(*) AS n FROM MURO_PROYECTO m, PROYECTO p WHERE m.PROYECTO_ID=p.ID_PROYECTO AND p.ID_PROYECTO = '"+idProyecto+"'";
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("n");
        }catch(Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public int getNumProyectosUser(String user){
            try{
                String q = "SELECT COUNT(*) AS n FROM PROYECTO WHERE USUARIO = '"+user+"'";
                ResultSet rs = query.executeQuery(q);
                rs.next();
                return rs.getInt("n");
            }catch(Exception e) {
                System.out.println(e);
                return -1;
            }
    }

    public int IdProyecto(String name){
        try{
            Statement query = c.createStatement();
            String q = "SELECT ID_PROYECTO FROM PROYECTO WHERE DESCRIPCION = '"+name+"'";
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("ID_PROYECTO");
        }catch(Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public String[][] infoProyecto(String name){
            int id = IdProyecto(name);
        int numFiles = getNumRowsQuery("SELECT COUNT(*) AS n FROM MURO_PROYECTO WHERE PROYECTO_ID ='"+id+"'");
        int numCols = 3;
        String[][] proyectos = new String[numFiles][numCols];
        try{
            Statement query = c.createStatement();
            ResultSet rs = query.executeQuery("SELECT ORDEN o FROM MURO_PROYECTO WHERE PROYECTO_ID = '"+id+"'");
            int nr = 0;
            while(rs.next()){
                proyectos[nr][0] = String.valueOf(nr);
                proyectos[nr][1] = name;
                proyectos[nr][2] = rs.getString("o");
                nr++;
            }
            printArray2D(proyectos);
            return proyectos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String imageCreation(String orden){
        try {
            Statement query = c.createStatement();
            String q = "SELECT IMAGEN_ID FROM MURO_PROYECTO WHERE ORDEN ='"+orden+"'";
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return getImageName(rs.getInt("IMAGEN_ID"));
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

        public void insertProject(String nom, String date, String user){
            int c = getNumRowsTaula("PROYECTO") +1;
            try{
                String q = "INSERT INTO PROYECTO (ID_PROYECTO, DESCRIPCION, FECHA_FINAL, USUARIO) VALUES('"+c+"', '"+nom+"', '"+date+"', '"+user+"')";
                query.execute(q);
            }catch(Exception e) {
                System.out.println(e);
            }
        }

        public void insertInfoTaulaEdifici(String nom, float x, float y, String user, String estil, String material, String tipologia){
            int c = getNumRowsTaula("EDIFICIO") + 1;
            int estilID = getIDEstil(estil);
            int materialID = getIDMaterial(material);
            int tipologiaID = getIDTipologia(tipologia);
            try{
                String q = "INSERT INTO EDIFICIO (ID_EDIFICIO, DESCRIPCION, POSX, POSY, USUARIO, ESTILO, MATERIAL, TIPOLOGIA) VALUES ('"+c+"', '"+nom+"', '"+x+"', " +
                        "'"+y+"', '"+user+"', '"+estilID+"', '"+materialID+"', '"+tipologiaID+"')";
                System.out.println(q);
                query.execute(q);
            }catch(Exception e) {
                System.out.println(e);
            }
        }

        public void insertImageEdificio(String edificio, String nomImatge){
            int c = getNumRowsTaula("IMAGEN") + 1;
            int ed = getIDEdificio(edificio);
            try{
                String q = "INSERT INTO IMAGEN (ID_IMAGEN, NOMBRE_IMAGEN, EDIFICIO, MAPA) VALUES ('"+c+"', '"+nomImatge+"','"+ed+"','S')";
                System.out.println(q);
                query.execute(q);
            }catch(Exception e) {
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

        public void updateinfoMapa(int ed){
            try {
                String q = "UPDATE IMAGEN SET MAPA ='N' WHERE EDIFICIO ='"+ed+"'";
                query.execute(q);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }

        public void resetInfoMapa(){
            try {
                String q = "UPDATE IMAGEN SET MAPA = 'S'";
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

        public void deleteEdificio(String nom){
            try {
                String q = "DELETE FROM EDIFICIO WHERE DESCRIPCION ='"+nom+"'";
                System.out.println(q);
                query.execute(q);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }

        public void deleteImagen(String ed){
            int edificioID = getIDEdificio(ed);
            try {
                String q = "DELETE FROM IMAGEN WHERE EDIFICIO ='"+edificioID+"'";
                System.out.println(q);
                query.execute(q);
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }



        public void printArray2D(String[][] info){
            for(int f=0; f<info.length; f++){
                for(int c=0; c<info[f].length; c++){
                    System.out.print(info[f][c]+ "\t");
                }
                System.out.println();
            }
        }
    }

