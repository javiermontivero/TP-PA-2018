package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.*;

import java.util.List;


@Table(name = "USUARIO")
public class UsuarioSinReferencias {
    @Id(strategy = Id.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public UsuarioSinReferencias(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


