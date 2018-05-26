package tppagrupo7.xpress.executor.impl;

import org.junit.*;
import tppagrupo7.xpress.domain.Query;
import tppagrupo7.xpress.util.TestSuiteWithDBAccess;
import tppagrupo7.xpress.util.UsuarioSinReferencias;
import static org.junit.Assert.*;
import java.io.IOException;
import java.sql.SQLException;

public class SQLExecutorImplTest extends TestSuiteWithDBAccess {

    private SQLExecutorImpl executor = new SQLExecutorImpl(getConnection());
    @Test
    public void findByIdTest_OK() {
        Query<UsuarioSinReferencias> query = new Query<>();
        query.setQuery("SELECT * FROM USUARIO WHERE id = 5;");
        query.setExpectedType(UsuarioSinReferencias.class);
        UsuarioSinReferencias user = executor.executeForSingleRow(query);

        assertEquals(5,user.getId());
        assertEquals("javi",user.getUsername());
        assertEquals("12345",user.getPassword());
    }


    @BeforeClass
    public static void createTables() throws SQLException {
        createTable("CREATE TABLE USUARIO " +
                "(id INT PRIMARY KEY," +
                "username VARCHAR(20) NOT NULL," +
                "password VARCHAR(20) NOT NULL);");
        insertRows("INSERT INTO USUARIO(id,username,password) VALUES (5,'javi','12345')");
        insertRows("INSERT INTO USUARIO(id,username,password) VALUES (7,'fede','54321')");
        insertRows("INSERT INTO USUARIO(id,username,password) VALUES (9,'jorge','22577')");
    }

    @AfterClass
    public static void drop() throws SQLException, IOException, ClassNotFoundException {
        destroy();
    }
}
