package model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_det_boleta")
@Data
@NoArgsConstructor
public class DetBoleta {

	@EmbeddedId
	private DetBoletaPK id;
	private int cantidad;
	private BigDecimal preciovta;
	
	@ManyToOne
	@JoinColumn(name = "num_bol")
	private CabBoleta cabBoleta;
	@ManyToOne
	@JoinColumn(name = "id_prod")
	private Producto producto;
}
