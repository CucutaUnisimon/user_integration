package co.edu.unisimon.integration.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PERSONA", schema = "DOCENTE")
@Data
public class Persona {

    @Id
    @Column(name = "CEDULA", length = 10, nullable = false)
    private String cedula;

    @Column(name = "NOMBRE1", length = 20)
    private String nombre1;

    @Column(name = "NOMBRE2", length = 20)
    private String nombre2;

    @Column(name = "APELLIDO1", length = 20)
    private String apellido1;

    @Column(name = "APELLIDO2", length = 20)
    private String apellido2;

    @Column(name = "DIRECCION", length = 500)
    private String direccion;

    @Column(name = "CORREOINST", length = 50)
    private String correoInst;

    @Column(name = "CORREOPER", length = 50)
    private String correoPer;

    @Column(name = "CORREOMICROSOFT", length = 50)
    private String correoMicrosoft;
}
