package tppagrupo7.xpress.generator.impl;

import org.junit.Assert;
import org.junit.Test;
import tppagrupo7.xpress.util.NotAnnotatedClass;
import tppagrupo7.xpress.util.Usuario;

import java.util.List;

public class SQLGeneratorImplTest {

    private SQLGeneratorImpl generator = new SQLGeneratorImpl();

    @Test
    public void selectByIdTest_success(){
        Assert.assertEquals("SELECT * FROM Usuario WHERE id = 12;".toUpperCase(),generator.selectById(Usuario.class,12).getQuery());
    }

    @Test
    public void selectAllTest_success(){
        Assert.assertEquals("SELECT * FROM Usuario;".toUpperCase(),generator.selectAll(Usuario.class).getQuery());
        Assert.assertEquals("SELECT * FROM NotAnnotatedClass;".toUpperCase(),generator.selectAll(NotAnnotatedClass.class).getQuery());
    }

    @Test
    public void selectByIdTest_fail_noId(){
        try{
            generator.selectById(NotAnnotatedClass.class,12);
            Assert.fail();
        }catch (RuntimeException e){
            Assert.assertEquals("Cannot find id field in class NotAnnotatedClass.",e.getMessage());
        }
    }

}
