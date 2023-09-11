package interfaces;

import java.util.List;

import model.Categoria;

public interface ICategoriaDAO {
	List<Categoria> findAll();
	void createCategoria(Categoria categoria);
	void updateCategoria(Categoria categoria);
	void deleteCategoria(int idcategoria);
}
