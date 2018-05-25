package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.ManyToOne;
import tppagrupo7.xpress.annotation.Table;

@Table(name = "USUARIO_ROL")
public class UsuarioRol {
    @Id(strategy = Id.IDENTITY)
    @Column
    private int idUsuarioRol;
    
    @Column
    private Rol rol;
    
    @ManyToOne
    private Usuario usuario;
    
    public UsuarioRol(){
    }
    
}
