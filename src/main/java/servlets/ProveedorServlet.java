package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProveedorDAO;
import model.Proveedor;

public class ProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProveedorDAO proveedorDAO = new ProveedorDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String Command = request.getParameter("Command");
			if(Command==null) Command = "List";
			switch(Command) {
			case "Create":
				createProveedor(request, response);
				break;
			case "Update":
				updateProveedor(request, response);
				break;
			case "Delete":
				deleteProveedor(request, response);
				break;
			default:
				listProveedores(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void listProveedores(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher rd = request.getRequestDispatcher("Mantenimiento Proveedores.jsp");
		rd.forward(request, response);
	}

	private void deleteProveedor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idproveedor = Integer.parseInt(request.getParameter("idproveedor"));
		proveedorDAO.deleteProveedor(idproveedor);
		request.setAttribute("message", "Proveedor Nro. " + idproveedor + " eliminado.");
		listProveedores(request, response);
	}

	private void updateProveedor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idproveedor = Integer.parseInt(request.getParameter("idproveedor"));
		String nombre_rs = request.getParameter("nombre_rs");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		Proveedor proveedor = new Proveedor();
		proveedor.setIdproveedor(idproveedor);
		proveedor.setNombre_rs(nombre_rs);
		proveedor.setTelefono(telefono);
		proveedor.setEmail(email);
		proveedorDAO.updateProveedor(proveedor);
		request.setAttribute("message", "Proveedor '" + nombre_rs + "' actualizado.");
		listProveedores(request, response);
	}

	private void createProveedor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nombre_rs = request.getParameter("nombre_rs");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		Proveedor proveedor = new Proveedor();
		proveedor.setNombre_rs(nombre_rs);
		proveedor.setTelefono(telefono);
		proveedor.setEmail(email);
		
		proveedorDAO.createProveedor(proveedor);
		request.setAttribute("message", "Proveedor '" + nombre_rs + "' registrado.");
		listProveedores(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
