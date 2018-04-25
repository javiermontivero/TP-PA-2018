package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.Table;

@Table(name = "PERSONA")
public class Persona {
    @Id(strategy = Id.IDENTITY)
    @Column
    private int idPersona;
    
    @Column
    private String nombre;
    
    @Column
    private String direccion;
    
    @Column
    private Date fechaAlta;
    
    public Persona(){
    }
}