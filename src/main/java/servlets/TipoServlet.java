package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TipoDAO;
import model.Tipo;

public class TipoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TipoDAO tipoDAO = new TipoDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String Command = request.getParameter("Command");
			if(Command==null) Command = "List";
			
			switch(Command) {
			case "Create":
				createTipo(request, response);
				break;
			case "Update":
				updateTipo(request, response);
				break;
			case "Delete":
				deleteTipo(request, response);
				break;
			default:
				listTipos(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createTipo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String descripcion = request.getParameter("descripcion");
		Tipo tipo = new Tipo();
		tipo.setDescripcion(descripcion);
		
		tipoDAO.createTipo(tipo);
		request.setAttribute("message", "Tipo '" + descripcion + "' registrado.");
		listTipos(request, response);
	}
	
	private void listTipos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher rd = request.getRequestDispatcher("Mantenimiento Tipos.jsp");
		rd.forward(request, response);
	}
	
	private void updateTipo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idtipo = Integer.parseInt(request.getParameter("idtipo"));
		String descripcion = request.getParameter("descripcion");
		Tipo tipo = new Tipo();
		tipo.setIdtipo(idtipo);
		tipo.setDescripcion(descripcion);
		
		tipoDAO.updateTipo(tipo);
		request.setAttribute("message", "Tipo '" + descripcion + "' actualizado.");
		listTipos(request, response);
	}

	private void deleteTipo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idtipo = Integer.parseInt(request.getParameter("idtipo"));
		
		tipoDAO.deleteTipo(idtipo);
		request.setAttribute("message", "Tipo Nro. " + idtipo + " eliminado.");
		listTipos(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
