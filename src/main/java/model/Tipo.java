package model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_tipos")
@Data
@NoArgsConstructor
@NamedQuery(name = "Tipo.findAll", query = "Select t From Tipo t")
public class Tipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtipo;
	private String descripcion;
	
	@OneToMany(mappedBy = "tipo")
	private List<Usuario> usuarios;
	
	public Usuario addUsuario(Usuario usuario) {
		this.getUsuarios().add(usuario);
		usuario.setTipo(this);
		
		return usuario;
	}
	
	public Usuario removeUsuario(Usuario usuario) {
		this.getUsuarios().remove(usuario);
		usuario.setTipo(null);
		
		return usuario;
	}
}
