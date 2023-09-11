package interfaces;

import java.util.List;

import model.Tipo;

public interface ITipoDAO {
	List<Tipo> findAll();
	void createTipo(Tipo tipo);
	void updateTipo(Tipo tipo);
	void deleteTipo(int idtipo);
}
