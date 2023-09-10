package model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_cab_boleta")
@Data
@NoArgsConstructor
public class CabBoleta {

	@Id
	private int num_bol;
	private Date fch_bol;
	
	@ManyToOne
	@JoinColumn(name = "cod_usua")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "cabBoleta")
	private List<DetBoleta> detBoletas;
	
	public DetBoleta addDetBoleta(DetBoleta detBoleta) {
		this.getDetBoletas().add(detBoleta);
		detBoleta.setCabBoleta(this);
		
		return detBoleta;
	}
	
	public DetBoleta removeDetBoleta(DetBoleta detBoleta) {
		this.getDetBoletas().remove(detBoleta);
		detBoleta.setCabBoleta(null);
		
		return detBoleta;
	}
}
