package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.Table;



@Table(name = "USUARIO")
public class Usuario {
    @Id(strategy = Id.IDENTITY)
    @Column
    private int idUsuario;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @ManyToOne
    private Persona persona;
    
    @OneToMany(mappedBy="USUARIO")
    private List<UsuarioRol> roles;
    
    public Usuario(){
    }
}

