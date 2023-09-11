package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import model.Categoria;

public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String Command = request.getParameter("Command");
			if(Command==null) Command = "List";
			switch(Command) {
			case "Create":
				createCategoria(request, response);
				break;
			case "Update":
				updateCategoria(request, response);
				break;
			case "Delete":
				deleteCategoria(request, response);
				break;
			default:
				listCategorias(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void listCategorias(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher rd = request.getRequestDispatcher("Mantenimiento Categorias.jsp");
		rd.forward(request, response);
	}

	private void deleteCategoria(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idcategoria = Integer.parseInt(request.getParameter("idcategoria"));
		categoriaDAO.deleteCategoria(idcategoria);
		request.setAttribute("message", "Categoría Nro. " + idcategoria + " eliminada.");
		listCategorias(request, response);
	}

	private void updateCategoria(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idcategoria = Integer.parseInt(request.getParameter("idcategoria"));
		String descripcion = request.getParameter("descripcion");
		Categoria categoria = new Categoria();
		categoria.setIdcategoria(idcategoria);
		categoria.setDescripcion(descripcion);
		categoriaDAO.updateCategoria(categoria);
		request.setAttribute("message", "Categoría '" + descripcion + "' actualizada.");
		listCategorias(request, response);
	}

	private void createCategoria(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String descripcion = request.getParameter("descripcion");
		Categoria categoria = new Categoria();
		categoria.setDescripcion(descripcion);
		
		categoriaDAO.createCategoria(categoria);
		request.setAttribute("message", "Categoría '" + descripcion + "' registrada.");
		listCategorias(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
