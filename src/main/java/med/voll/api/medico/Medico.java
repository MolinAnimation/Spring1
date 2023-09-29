package med.voll.api.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

//especifica la tabla de la base de datos
@Table(name = "medicos")
// marca la clase como enrtidad jpa (almacenable en base de datos)
@Entity(name = "Medico")

// genera codico lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor
// lombok genera metodos equals y hashcode para comparar en base al id
@EqualsAndHashCode(of = "id")

public class Medico {
    // generacion automatica del id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private boolean activo;
    private String documento;
    // indica que el campo especialidad se almacene como cadena de texto en lugar
    // que como el indice del enum
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    /*
     * indica que la informacion del objeto direccion
     * se alamcenara como parte de la tabla y no como una tabla separada
     * es decir los campos de direccion como calle ciudad... pasaran a ser
     * parte de la tabla medicos y va acompaniada de la etiqueta @Embeddable
     */
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.telefono = datosRegistroMedico.telefono();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());

    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null) {
            this.nombre = datosActualizarMedico.nombre();
        }

        if (datosActualizarMedico.documento() != null) {
            this.documento = datosActualizarMedico.documento();
        }

        if (datosActualizarMedico.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }

}
