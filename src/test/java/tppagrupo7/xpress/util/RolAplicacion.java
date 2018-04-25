package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.Table;

@Table(name = "ROL_APLICACION")
public class RolAplicacion {
    @Id(strategy = Id.IDENTITY)
    @Column
    private int idRolAplicacion;
    
    @ManyToOne
    private Rol rol;
    
    @Column
    private Aplicacion aplicacion;
    
    public RolAplicacion(){
    }
}
