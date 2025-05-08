package co.edu.unisimon.integration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "ESTUDIANTES", schema = "DB2ADMIN")
public class Estudiante {

    @Id
    @Column(name = "ESTCODIGO", nullable = false, precision = 14, scale = 0)
    private BigDecimal estCodigo;

    @Column(name = "ESTIDE", length = 20)
    private String estIde;

    @Column(name = "ESTTIPOIDE", nullable = false)
    private Short estTipoIde;

    @Column(name = "ESTNOMBRE1", nullable = false, length = 50)
    private String estNombre1;

    @Column(name = "ESTNOMBRE2", length = 50)
    private String estNombre2;

    @Column(name = "ESTAPELLIDO1", nullable = false, length = 50)
    private String estApellido1;

    @Column(name = "ESTAPELLIDO2", length = 50)
    private String estApellido2;

}

