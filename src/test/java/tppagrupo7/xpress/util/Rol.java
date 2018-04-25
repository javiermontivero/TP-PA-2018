package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.Table;

@Table(name = "ROL")
public class Rol {
    @Id(strategy = Id.IDENTITY)
    @Column
    private int idRol;
    
    @Column
    private String descripcion;
    
    @OneToMany(mappedBy="ROL")
    private List<UsuarioRol> usuarios;
    
    @OneToMany(mappedBy="ROL")
    private List<RolAplicacion> aplicaciones;
    
    public Rol(){
    }
}