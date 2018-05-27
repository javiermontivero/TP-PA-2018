package tppagrupo7.xpress.executor.impl;

import org.junit.*;
import tppagrupo7.xpress.domain.Query;
import tppagrupo7.xpress.util.*;

import static org.junit.Assert.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SQLExecutorImplTest extends TestSuiteWithDBAccess {

    private SQLExecutorImpl executor = new SQLExecutorImpl(getConnection());
    @Test
    public void executeForSingleRow_simpleClass_OK() {
        Query<UsuarioSinReferencias> query = new Query<>();
        query.setQuery("SELECT * FROM USUARIO WHERE id = 5;");
        query.setExpectedType(UsuarioSinReferencias.class);
        Optional<UsuarioSinReferencias> user = executor.executeForSingleRow(query);

        assertTrue(user.isPresent());
        assertEquals(5,user.get().getId());
        assertEquals("javi",user.get().getUsername());
        assertEquals("12345",user.get().getPassword());
    }

    @Test
    public void executeForSingleRow_simpleClassNoResults_FAIL() {
        Query<UsuarioSinReferencias> query = new Query<>();
        query.setQuery("SELECT * FROM USUARIO WHERE id = 115;");
        query.setExpectedType(UsuarioSinReferencias.class);
        Optional<UsuarioSinReferencias> user = executor.executeForSingleRow(query);

        assertFalse(user.isPresent());
    }

    @Test
    public void executeForSingleRow_classWithManyToOne_OK(){
        Query<UsuarioSinRoles> query = new Query<>();
        query.setExpectedType(UsuarioSinRoles.class);
        query.setQuery("SELECT * FROM USUARIO WHERE id = 5;");

        Optional<UsuarioSinRoles> user = executor.executeForSingleRow(query);

        assertTrue(user.isPresent());
        assertEquals(5,user.get().getId());
        assertEquals("javi",user.get().getUsername());
        assertEquals("12345",user.get().getPassword());
        Persona persona = user.get().getPersona();
        assertEquals(10,persona.getId());
        assertEquals("Javier",persona.getName());
        assertEquals("Cochabanba 500",persona.getAddress());
    }

    @Test
    public void execute_OK(){
        Query<UsuarioSinReferencias> query = new Query<>();
        query.setQuery("SELECT * FROM USUARIO;");
        query.setExpectedType(UsuarioSinReferencias.class);
        List<UsuarioSinReferencias> users = executor.execute(query);

        assertEquals(3,users.size());
        assertEquals("javi",users.get(0).getUsername());
        assertEquals("fede",users.get(1).getUsername());
        assertEquals("jorge",users.get(2).getUsername());
    }

    @BeforeClass
    public static void createTables() throws SQLException {
        createTable("CREATE TABLE USUARIO " +
                "(id INT PRIMARY KEY," +
                " username VARCHAR(20) NOT NULL," +
                " password VARCHAR(20) NOT NULL," +
                " persona_id INT)");
        insertRows("INSERT INTO USUARIO (id,username,password,persona_id) VALUES (5,'javi','12345',10)");
        insertRows("INSERT INTO USUARIO (id,username,password,persona_id) VALUES (9,'fede','12345',15)");
        insertRows("INSERT INTO USUARIO (id,username,password,persona_id) VALUES (16,'jorge','12345',10)");

        createTable("CREATE TABLE PERSONA (id INT PRIMARY KEY, name VARCHAR(20) NOT NULL, address VARCHAR(40) NOT NULL);");
        insertRows("INSERT INTO PERSONA (id, name, address) VALUES (10, 'Javier', 'Cochabamba 500')");
        insertRows("INSERT INTO PERSONA (id, name, address) VALUES (19, 'Martin', 'Sarmiento 1500')");

    }

    @AfterClass
    public static void drop() throws SQLException, IOException, ClassNotFoundException {
        destroy();
    }
}
