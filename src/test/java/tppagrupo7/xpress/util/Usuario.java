package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.*;

import java.util.List;


@Table(name = "USUARIO")
public class Usuario {
    @Id(strategy = Id.IDENTITY)
    @Column
    private int id;
    
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

