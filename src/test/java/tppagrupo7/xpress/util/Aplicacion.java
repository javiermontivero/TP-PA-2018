package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.Table;

@Table(name = "APLICACION")
public class Aplicacion {
    @Id(strategy = Id.IDENTITY)
    @Column
    private int idAplicacion;
    
    @Column 
    private String descripcion;
    
    @OneToMany(mappedBy="APLICACION")
    private List<RolAplicacion> roles; 
    
    public Aplicacion(){
    }
}
