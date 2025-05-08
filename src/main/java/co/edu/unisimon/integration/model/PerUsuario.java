package co.edu.unisimon.integration.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "PER_USUARIOS", schema = "DB2ADMIN")
public class PerUsuario {

    @Id
    @Column(name = "USU_USUARIO", nullable = false, length = 60)
    private String usuUsuario;

    @Column(name = "USU_PASSWORD", length = 100)
    private String usuPassword;

    @Column(name = "USU_CORREO", length = 100)
    private String usuCorreo;

    @Column(name = "USU_CODIGO", precision = 14, scale = 0)
    private BigDecimal usuCodigo;

    @Column(name = "USU_IDENTIFICACION", length = 100)
    private String usuIdentificacion;

    @Column(name = "USU_FECHA_PASSWORD", length = 100)
    private String usuFechaPassword;

    @Column(name = "USU_ULTIMA_IP", length = 100)
    private String usuUltimaIp;

    @Column(name = "USU_ESTADO", precision = 1, scale = 0)
    private BigDecimal usuEstado;

    @Column(name = "USU_PASSWD_UC")
    @Temporal(TemporalType.DATE)
    private Date usuPasswdUc;

    @Column(name = "USU_CAMBIOCLAVE", nullable = false)
    private Integer usuCambioclave;

    @Column(name = "USU_DOMINIOUSUARIO", length = 100)
    private String usuDominiousuario;

    @Column(name = "USU_MICROSOFT", length = 60)
    private String usuMicrosoft;

    // Getters y Setters
}

