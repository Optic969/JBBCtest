package tests;

import databaseConnect.JDBCConnection;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Check connection to DB test and sending requests")
public class TestDatabase extends TestsSetups {

    @Test
    @Order(1)
    @DisplayName("Check connection to DB ")
    public void testConnection() {
        Assertions.assertNotNull(JDBCConnection.connectToDB());
    }

    @Test
    @Order(2)
    @DisplayName("Check creation of table in DB")
    public void testCreateTable() {
        String query = "CREATE TABLE lapenko (" + "id int(6) NOT NULL,"
                + "first_name VARCHAR(45) NOT NULL,"
                + "last_name VARCHAR(45) NOT NULL,"
                + "discipline VARCHAR(45),"
                + "PRIMARY KEY (id))";
        JDBCConnection.createTable(query);
    }

    @Test
    @Order(3)
    @DisplayName("Sending INSERT requests")
    public void testInsertRequest() throws SQLException {
        String query = "INSERT INTO test.lapenko (id, first_name, last_name, discipline ) VALUES ('10', 'Richard', 'Sopogov', 'English')";
        JDBCConnection.insertIntoDB(query);
        String selectQuery = "SELECT discipline FROM test.lapenko WHERE id=10";
        ResultSet rs = JDBCConnection.selectFromDB(selectQuery);
        rs.first();
        Assertions.assertEquals(rs.getString("discipline"), "English");
    }

    @Test
    @Order(4)
    @DisplayName("Sending INSERT requests")
    public void testInsertRequest1() throws SQLException {
        String query = "INSERT INTO test.lapenko (id, first_name, last_name, discipline ) VALUES ('20', 'Tatiana', 'Vosmiglazova', 'Lottery')";
        JDBCConnection.insertIntoDB(query);
        String selectQuery = "SELECT discipline FROM test.lapenko WHERE id=20";
        ResultSet rs = JDBCConnection.selectFromDB(selectQuery);
        rs.first();
        Assertions.assertEquals(rs.getString("discipline"), "Lottery");
    }

    @Test
    @Order(5)
    @DisplayName("Sending INSERT requests")
    public void testInsertRequest2() throws SQLException {
        String query = "INSERT INTO test.lapenko (id, first_name, last_name, discipline ) VALUES ('30', 'Igor', 'Ivanov', 'Builder')";
        JDBCConnection.insertIntoDB(query);
        String selectQuery = "SELECT discipline FROM test.lapenko WHERE id=30";
        ResultSet rs = JDBCConnection.selectFromDB(selectQuery);
        rs.first();
        Assertions.assertEquals(rs.getString("discipline"), "Builder");
    }

    @Test
    @Order(6)
    @DisplayName("Sending UPDATE requests")
    public void testUpdateRequest() throws SQLException {
        String query = "UPDATE test.lapenko SET last_name = 'Katamaranov' WHERE id=30";
        JDBCConnection.updateInDB(query);
        String selectQuery = "SELECT last_name FROM test.lapenko WHERE id=30";
        ResultSet rs = JDBCConnection.selectFromDB(selectQuery);
        rs.first();
        Assertions.assertEquals(rs.getString("last_name"), "Katamaranov");
    }

    @Test
    @Order(7)
    @DisplayName("Sending SELECT JOIN requests. Check last name ")
    public void testSelectRequest_checkLastName() throws SQLException {
        String query = "SELECT first_name, last_name FROM test.lapenko WHERE id=30";
        ResultSet rs = JDBCConnection.selectFromDB(query);
        rs.first();
        Assertions.assertEquals(rs.getString("first_name"), "Igor");
    }

    /*@Test
    @Order(8)
    @DisplayName("Sending DELETE requests")
    public void testDeleteRequest() throws SQLException {
        String query = "DELETE FROM test.professor WHERE id=10";
        JDBCConnection.deleteFromDB(query);
    }*/

    @Test
    @Order(9)
    @DisplayName("Check creation of table in DB")
    public void testCreateTable1() {
        String query = "CREATE TABLE people (" + "id int(6) NOT NULL,"
                + "first_name VARCHAR(45) NOT NULL,"
                + "last_name VARCHAR(45) NOT NULL,"
                + "discipline VARCHAR(45),"
                + "PRIMARY KEY (id))";
        JDBCConnection.createTable(query);
    }

    @Test
    @Order(10)
    @DisplayName("Sending INSERT requests")
    public void testInsertRequest3() throws SQLException {
        String query = "INSERT INTO test.people (id, first_name, last_name, discipline ) VALUES ('10', 'Igor', 'Ivanov', 'Music')";
        JDBCConnection.insertIntoDB(query);
        String selectQuery = "SELECT last_name FROM test.people WHERE id=10";
        ResultSet rs = JDBCConnection.selectFromDB(selectQuery);
        rs.first();
        Assertions.assertEquals(rs.getString("last_name"), "Ivanov");
    }

    @Test
    @Order(11)
    @DisplayName("Sending INSERT requests")
    public void testInsertRequest4() throws SQLException {
        String query = "INSERT INTO test.people (id, first_name, last_name, discipline ) VALUES ('20', 'Ivan', 'Petrov', 'Builder')";
        JDBCConnection.insertIntoDB(query);
        String selectQuery = "SELECT last_name FROM test.people WHERE id=20";
        ResultSet rs = JDBCConnection.selectFromDB(selectQuery);
        rs.first();
        Assertions.assertEquals(rs.getString("last_name"), "Petrov");
    }

    @Test
    @Order(12)
    @DisplayName("Sending INSERT requests")
    public void testInsertRequest5() throws SQLException {
        String query = "INSERT INTO test.people (id, first_name, last_name, discipline ) VALUES ('30', 'Galina', 'Pupkina', 'Builder')";
        JDBCConnection.insertIntoDB(query);
        String selectQuery = "SELECT last_name FROM test.people WHERE id=30";
        ResultSet rs = JDBCConnection.selectFromDB(selectQuery);
        rs.first();
        Assertions.assertEquals(rs.getString("last_name"), "Pupkina");
    }

    @Test
    @Order(13)
    @DisplayName("Sending SELECT JOIN requests")
    public void testSelectWithJoinRequest_CheckFistName() throws SQLException {
        String query = "SELECT * FROM test.lapenko l INNER JOIN test.people p ON l.id=p.id";
        ResultSet rs = JDBCConnection.selectFromDB(query);
        rs.last();
    }
}
