package model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_categorias")
@Data
@NoArgsConstructor
@NamedQuery(name = "Categoria.findAll", query = "Select c From Categoria c")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcategoria;
	private String descripcion;
	
	@OneToMany(mappedBy = "categoria")
	private List<Producto> productos;
	
	public Producto addProducto(Producto producto) {
		this.getProductos().add(producto);
		producto.setCategoria(this);
		
		return producto;
	}
	
	public Producto removeProducto(Producto producto) {
		this.getProductos().remove(producto);
		producto.setCategoria(null);
		
		return producto;
	}
}
