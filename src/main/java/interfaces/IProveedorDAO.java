package interfaces;

import java.util.List;

import model.Proveedor;

public interface IProveedorDAO {
	List<Proveedor> findAll();
	void createProveedor(Proveedor proveedor);
	void updateProveedor(Proveedor proveedor);
	void deleteProveedor(int idproveedor);
}
