package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.Table;

@Table(name = "Usuario")
public class Usuario {
    @Id(strategy = Id.IDENTITY)
    @Column
    private int id;
}
