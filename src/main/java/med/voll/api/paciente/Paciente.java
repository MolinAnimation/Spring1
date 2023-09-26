package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Table(name = "pacientes")
// marca la clase como enrtidad jpa (almacenable en base de datos)
@Entity(name = "Paciente")

// genera codico lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor
// lombok genera metodos equals y hashcode para comparar en base al id
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.documento = datos.documento();
        this.telefono = datos.telefono();
        this.direccion = new Direccion(datos.direccion());

    }

}
