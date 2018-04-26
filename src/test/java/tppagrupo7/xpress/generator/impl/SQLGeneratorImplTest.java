package tppagrupo7.xpress.generator.impl;

import org.junit.Assert;
import org.junit.Test;
import tppagrupo7.xpress.util.NotAnnotatedClass;
import tppagrupo7.xpress.util.Usuario;

public class SQLGeneratorImplTest {

    private SQLGeneratorImpl generator = new SQLGeneratorImpl();

    @Test
    public void selectByIdTest_success(){
        Assert.assertEquals("SELECT * FROM Usuario WHERE id = 12;",generator.selectById(Usuario.class,12));
    }

    @Test
    public void selectAllTest_success(){
        Assert.assertEquals("SELECT * FROM Usuario;",generator.selectAll(Usuario.class));
        Assert.assertEquals("SELECT * FROM NotAnnotatedClass;",generator.selectAll(NotAnnotatedClass.class));
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
    
    public static void main(){
    	//recuperar 1 usuario
    	Usuario usr = Xpress.find(Usuario.class,1);
    	
    	//recuperar varios usuarios
    	List<Usuario> usr_lst = Xpress.findAll(Usuario.class);
    	
    	
    }
}
