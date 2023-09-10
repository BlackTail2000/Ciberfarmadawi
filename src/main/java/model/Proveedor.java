package model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_proveedor")
@Data
@NoArgsConstructor
public class Proveedor {
	
	@Id
	private int idproveedor;
	private String nombre_rs;
	private String telefono;
	private String email;
	
	@OneToMany(mappedBy = "proveedor")
	private List<Producto> productos;
	
	public Producto addProducto(Producto producto) {
		this.getProductos().add(producto);
		producto.setProveedor(this);
		
		return producto;
	}
	
	public Producto removeProducto(Producto producto) {
		this.getProductos().remove(producto);
		producto.setProveedor(null);
		
		return producto;
	}
}
