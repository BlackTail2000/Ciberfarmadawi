package model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_usuarios")
@Data
@NoArgsConstructor
public class Usuario {
	
	@Id
	private int cod_usua;
	private String nom_usua;
	private String ape_usua;
	private String usr_usua;
	private String cla_usua;
	private Date fna_usua;
	private int est_usua;
	
	@ManyToOne
	@JoinColumn(name = "idtipo")
	private Tipo tipo;
	
	@OneToMany(mappedBy = "usuario")
	private List<CabBoleta> cabBoletas;
	
	public CabBoleta addCabBoleta(CabBoleta cabBoleta) {
		this.getCabBoletas().add(cabBoleta);
		cabBoleta.setUsuario(this);
		
		return cabBoleta;
	}
	
	public CabBoleta removeCabBoleta(CabBoleta cabBoleta) {
		this.getCabBoletas().remove(cabBoleta);
		cabBoleta.setUsuario(null);
		
		return cabBoleta;
	}
}
