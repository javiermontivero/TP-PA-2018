package tppagrupo7.xpress.util;

import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.Table;

@Table(name = "PERSONA")
public class Persona {
    @Id(strategy = Id.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
