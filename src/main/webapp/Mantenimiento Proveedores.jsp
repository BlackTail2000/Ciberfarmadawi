<%@page import="dao.ProveedorDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Proveedor, dao.ProveedorDAO" %>
<%
  ProveedorDAO proveedorDAO = new ProveedorDAO();

  @SuppressWarnings("unchecked")
  List<Proveedor> proveedores = (List<Proveedor>) request.getAttribute("proveedores");
  if(proveedores==null){
	  proveedores = proveedorDAO.findAll();
  }
  
  String message = (String) request.getAttribute("message");
  if(message==null){
	  message = "";
  }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mantenimiento de Proveedores</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="p-5 bg-primary-subtle">
  <div class="p-5 border border-5 border-info rounded bg-white">
    <h1>Listado de Proveedores</h1>
    <hr>
    <button class="btn btn-outline-success my-4" data-bs-toggle="modal" data-bs-target="#modal-create"><i class="bi bi-database-add"></i> Registrar Proveedor</button>
    <div class="modal fade" id="modal-create" tabindex="-1" aria-labellebdy="modal-titleC" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modal-titleC">Registrar Nuevo Proveedor</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="ProveedorServlet">
            <input type="hidden" name="Command" value="Create">
            <div class="modal-body">
              <div class="mb-3">
                <label class="form-label" for="nombre_rs">Razón Social: </label>
                <input type="text" class="form-control" id="nombre_rs" name="nombre_rs" required autocomplete="off">
              </div>
              <div class="mb-3">
                <label class="form-label" for="telefono">Teléfono: </label>
                <input type="text" class="form-control" id="telefono" name="telefono" required autocomplete="off">
              </div>
              <div class="mb-3">
                <label class="form-label" for="email">Email: </label>
                <input type="email" class="form-control" id="email" name="email" required autocomplete="off">
              </div>
            </div>
            <div class="modal-footer">
              <button class="btn btn-success" type="submit"><i class="bi bi-database-add"></i> Registrar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <table class="table table-hover">
      <tr>
        <th>ID</th>
        <th>Razón Social</th>
        <th>Teléfono</th>
        <th>Email</th>
        <th colspan="2">Acciones</th>
      </tr>
      <%
        int count = 1;
        for(Proveedor item: proveedores){
      %>
      <tr>
        <th><%=item.getIdproveedor() %></th>
        <td><%=item.getNombre_rs() %></td>
        <td><%=item.getTelefono() %></td>
        <td><%=item.getEmail() %></td>
        <td><button class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#modal-update<%=count%>"><i class="bi bi-pencil"></i> Actualizar</button></td>
        <td><button class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#modal-delete<%=count%>"><i class="bi bi-trash"></i> Eliminar</button></td>
      </tr>
      <%
          count++;
        }
      %>
    </table>
    <div class="text-primary">
      <div class="text-center"><%=message %></div>
    </div>
    <%
      count = 1;
      for(Proveedor item: proveedores){
    %>
    <div class="modal fade" id="modal-update<%=count%>" tabindex="-1" aria-labelledby="modal-titleU<%=count%>" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modal-titleU<%=count%>">Actualizar Proveedor Nro. <%=item.getIdproveedor() %></h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="ProveedorServlet">
            <input type="hidden" name="idproveedor" value="<%=item.getIdproveedor()%>">
            <input type="hidden" name="Command" value="Update">
            <div class="modal-body">
              <div class="mb-3">
                <label class="form-label" for="nombre_rs">Razón Social: </label>
                <input class="form-control" id="nombre_rs" name="nombre_rs" type="text" required autocomplete="off" value="<%=item.getNombre_rs()%>">
              </div>
              <div class="mb-3">
                <label class="form-label" for="telefono">Teléfono: </label>
                <input class="form-control" id="telefono" name="telefono" type="text" required autocomplete="off" value="<%=item.getTelefono()%>">
              </div>
              <div class="mb-3">
                <label class="form-label" for="email">Email: </label>
                <input class="form-control" id="email" name="email" type="email" required autocomplete="off" value="<%=item.getEmail()%>">
              </div>
            </div>
            <div class="modal-footer">
              <button class="btn btn-primary m-auto"><i class="bi bi-pencil"></i> Actualizar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="modal fade" id="modal-delete<%=count%>" tabindex="-1" aria-labelledby="modal-titleD<%=count%>" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modal-titleD<%=count%>">¿Eliminar al Proveedor Nro. <%=item.getIdproveedor()%>?</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="ProveedorServlet">
            <input type="hidden" name="idproveedor" value="<%=item.getIdproveedor()%>">
            <input type="hidden" name="Command" value="Delete">
            <div class="modal-body d-flex justify-content-around">
              <button class="btn btn-success" type="submit"><i class="bi bi-check"></i> Sí</button>
              <button class="btn btn-danger" type="button" data-bs-dismiss="modal" aria-label="Close"><i class="bi bi-x"></i> No</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <%
        count++;
      }
    %>
  </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</html>