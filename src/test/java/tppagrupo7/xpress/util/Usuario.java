package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.*;

import java.util.List;


@Table(name = "USUARIO")
public class Usuario {
    @Id(strategy = Id.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="username")
    private String username;
    
    @Column(name="password")
    private String password;
    
    @ManyToOne
    private Persona persona;
    
    @OneToMany(mappedBy="ID_USUARIO")
    private List<UsuarioRol> roles;
    
    public Usuario(){
    }
}

