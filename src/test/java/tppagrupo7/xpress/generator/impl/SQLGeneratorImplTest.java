package tppagrupo7.xpress.generator.impl;

import org.junit.Test;
import tppagrupo7.xpress.domain.Query;
import tppagrupo7.xpress.util.NotAnnotatedClass;
import tppagrupo7.xpress.util.Usuario;
import tppagrupo7.xpress.util.UsuarioSinReferencias;
import tppagrupo7.xpress.util.UsuarioSinRoles;

import static org.junit.Assert.*;

public class SQLGeneratorImplTest {

    private SQLGeneratorImpl generator = new SQLGeneratorImpl();

    @Test
    public void selectByIdTest_onlyColumnAnnotation_success(){
        Query<UsuarioSinReferencias> usuarioQuery = generator.selectById(UsuarioSinReferencias.class, 12);
        assertEquals("SELECT * FROM Usuario WHERE id = 12;".toUpperCase(), usuarioQuery.getQuery());
        assertEquals(UsuarioSinReferencias.class, usuarioQuery.getExpectedType());
    }

    @Test
    public void selectAllTest_onlyColumnAnnotation_success(){
        Query<UsuarioSinReferencias> userQuery = generator.selectAll(UsuarioSinReferencias.class);
        assertEquals("SELECT * FROM Usuario;".toUpperCase(), userQuery.getQuery());
        assertEquals(UsuarioSinReferencias.class, userQuery.getExpectedType());
        Query<NotAnnotatedClass> query = generator.selectAll(NotAnnotatedClass.class);
        assertEquals("SELECT * FROM NotAnnotatedClass;".toUpperCase(), query.getQuery());
        assertEquals(NotAnnotatedClass.class, query.getExpectedType());
    }

    @Test
    public void selectByIdTest_manyToOneAnnotation_success(){
        Query<UsuarioSinRoles> userQuery = generator.selectById(UsuarioSinRoles.class,12);

        assertEquals(
                ("SELECT * FROM USUARIO u " +
                 "INNER JOIN PERSONA p ON p.id = u.persona_id " +
                 "WHERE id = 12;").toUpperCase(),
                userQuery.getQuery());
        assertEquals(UsuarioSinRoles.class,userQuery.getExpectedType());
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
