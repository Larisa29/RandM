package PaooGame;
import java.sql.*;

//creez conexiunea la baza de date prin intermediul conexiunii si pot sa execut statementuri de creare, inserare, stergere,actualizare etc
/*! \class database
    \brief Clasa care ne permite crearea obiectelor ce faciliteaza conectarea la o baza de date SQLite
           Metodele  baza de date "setari.db".
 */
public class database {
    Connection c;
    Statement stmt;
    ResultSet rs;

     /*! \fn public database()
        \brief Constructorul de initializare al clasei database in care se indica driverul si baza de date la care ma conectez
     */

    public database()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:setari.db");
            stmt = c.createStatement();


            /*String sql = "CREATE TABLE SETARI " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " INT volum_Meniu NOT NULL, " +
                    " INT volum_Joc)";*/

            /*ResultSet rs = stmt.executeQuery( "SELECT * FROM SETARI;" );
            while ( rs.next() ) {
                int id = rs.getInt("ID");
                int menuvol = rs.getInt("Menu_Volume");
                int jocvol = rs.getInt("Joc_Volume");

                System.out.println( "ID = " + id );
                System.out.println( "Menu vol = " + menuvol );
                System.out.println( "Joc vol = " + jocvol );
                System.out.println();
                System.out.println("afisare tabela");

            }
            rs.close();
            stmt.close();
            c.close();*/

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }



    /*! \fn public void  UpdateMenuVolume(int new_volume) throws  SQLException
     \brief Functie ce scrie in baza de date volumul muzicii din timpul jocului.Volumul e ales de utilizator.
     \param new_volume este intregul care reprezinta unitatea la care utilizatorul doreste sa se auda sunetul
  */
    public void UpdateMenuVolume(int new_volume) throws  SQLException {
        String instruction="UPDATE SETARI set Menu_Volume ="+new_volume+" WHERE ID=1;";
        stmt.executeUpdate(instruction);
    }


    /*! \fn public int getMenuVolume() throws SQLException
       \brief Functie de extragere a volumului sunetului din meniu , volum extras din  baza de date.
    */
    public int getMenuVolume() throws SQLException
    {
        rs=stmt.executeQuery("SELECT * FROM SETARI;");
        return rs.getInt("Menu_Volume");
    }
    /*! \fn public void UpdateJocVolume(int new_volume) throws  SQLException {
     \brief Functie ce scrie in baza de date volumul muzicii din timpul jocului.Volumul e ales de utilizator.
     \param new_volume este intregul care reprezinta unitatea la care utilizatorul doreste sa se auda sunetul
  */
    public void UpdateJocVolume(int new_volume) throws  SQLException {
        String sql="UPDATE SETARI set Joc_Volume ="+new_volume+" WHERE ID=1;";
        stmt.executeUpdate(sql);
    }


    /*! \fn public int getJocVolume() throws SQLException
       \brief Functie de extragere a volumului sunetului din timpul jocului , volum extras din  baza de date.
    */
    public int getJocVolume() throws SQLException {
        rs=stmt.executeQuery("SELECT * FROM SETARI;");
        return rs.getInt("Joc_Volume");
    }


    /*! \fn public int getNivel1Activ() throws SQLException {
        \brief Metoda extrage valoarea flagului care indica daca trebuie incarcata harta 1 sau nu (din baza de date).
     */
    public int getNivel1Activ() throws SQLException {
        int x = 0;
        rs=stmt.executeQuery("SELECT * FROM SAVE;");
        while(rs.next())
        {
            x=rs.getInt("IN_NIVEL1");
        }
        return x;
    }
    /*! \fn public int getNivel2Activ() throws SQLException {
            \brief Metoda extrage valoarea flagului care indica daca trebuie incarcata harta 2 sau nu (din baza de date).
         */
    public int getNivel2Activ() throws SQLException {
        int x = 0;
        rs=stmt.executeQuery("SELECT * FROM SAVE;");
        while(rs.next())
        {
            x=rs.getInt("IN_NIVEL2");
        }
        return x;
    }


}

