package tppagrupo7.xpress.generator.impl;

import org.junit.Test;
import tppagrupo7.xpress.domain.Query;
import tppagrupo7.xpress.util.NotAnnotatedClass;
import tppagrupo7.xpress.util.Usuario;

import static org.junit.Assert.*;

public class SQLGeneratorImplTest {

    private SQLGeneratorImpl generator = new SQLGeneratorImpl();

    @Test
    public void selectByIdTest_success(){
        Query<Usuario> usuarioQuery = generator.selectById(Usuario.class, 12);
        assertEquals("SELECT * FROM Usuario WHERE id = 12;".toUpperCase(), usuarioQuery.getQuery());
        assertEquals(Usuario.class, usuarioQuery.getExpectedType());
    }

    @Test
    public void selectAllTest_success(){
        Query<Usuario> userQuery = generator.selectAll(Usuario.class);
        assertEquals("SELECT * FROM Usuario;".toUpperCase(), userQuery.getQuery());
        assertEquals(Usuario.class, userQuery.getExpectedType());
        Query<NotAnnotatedClass> query = generator.selectAll(NotAnnotatedClass.class);
        assertEquals("SELECT * FROM NotAnnotatedClass;".toUpperCase(), query.getQuery());
        assertEquals(NotAnnotatedClass.class, query.getExpectedType());
    }

    @Test
    public void selectByIdTest_fail_noId(){
        try{
            generator.selectById(NotAnnotatedClass.class,12);
            fail();
        }catch (RuntimeException e){
            assertEquals("Cannot find id field in class NotAnnotatedClass.",e.getMessage());
        }
    }

}
