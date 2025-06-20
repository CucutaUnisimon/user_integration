package co.edu.unisimon.integration.model.aula.posgrado;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "per_usuarios", schema = "unisimon")
@Data
public class PerUsuarioPosgrado {

    @Id
    @Column(name = "usu_usuario", length = 60, nullable = false)
    private String usuario;

    @Column(name = "usu_password", length = 100)
    private String password;

    @Column(name = "usu_correo", length = 100)
    private String correo;

    @Column(name = "usu_codigo", precision = 14, scale = 0)
    private BigDecimal codigo;

    @Column(name = "usu_identificacion", length = 100)
    private String identificacion;

    @Column(name = "usu_fecha_password", length = 100)
    private String fechaPassword;

    @Column(name = "usu_estado")
    private Integer estado;

    @Column(name = "usu_microsoft", length = 60)
    private String microsoft;

    @Column(name = "usu_sede")
    private Integer sede = 1;

    @Column(name = "usu_nombre_completo", length = 250)
    private String nombreCompleto;

}

