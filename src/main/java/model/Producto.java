package model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_productos")
@Data
@NoArgsConstructor
public class Producto {
	
	@Id
	private String id_prod;
	private String des_prod;
	private int stk_prod;
	private BigDecimal pre_prod;
	private byte est_prod;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name = "idproveedor")
	private Proveedor proveedor;
	
	@OneToMany(mappedBy = "producto")
	private List<DetBoleta> detBoletas;
	
	public DetBoleta addDetBoleta(DetBoleta detBoleta) {
		this.getDetBoletas().add(detBoleta);
		detBoleta.setProducto(this);
		
		return detBoleta;
	}
	
	public DetBoleta removeDetBoleta(DetBoleta detBoleta) {
		this.getDetBoletas().remove(detBoleta);
		detBoleta.setProducto(null);
		
		return detBoleta;
	}
}
