package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.OneToMany;
import tppagrupo7.xpress.annotation.Table;

@Table(name = "ROL_APLICACION")
public class RolAplicacion {
    @Id(strategy = Id.IDENTITY)
    @Column
    private int idRolAplicacion;
    
    @OneToMany(mappedBy = "rol")
    private Rol rol;
    
    @OneToMany(mappedBy = "aplicacion")
    private Aplicacion aplicacion;
    
    public RolAplicacion(){
    }
}
