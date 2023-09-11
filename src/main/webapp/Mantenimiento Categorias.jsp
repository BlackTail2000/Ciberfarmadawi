<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.List, model.Categoria, dao.CategoriaDAO" %>
<%
  CategoriaDAO categoriaDAO = new CategoriaDAO();
  
  @SuppressWarnings("unchecked")
  List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
  if(categorias==null){
	  categorias = categoriaDAO.findAll();
  }
  
  String message = (String) request.getAttribute("message");
  if(message==null){
	  message = "";
  }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mantenimiento de Categorías</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="p-5 bg-primary-subtle">
  <div class="p-5 border border-5 border-info rounded bg-white">
    <h1>Listado de Categorías</h1>
    <hr>
    <button class="btn btn-outline-success my-4" data-bs-toggle="modal" data-bs-target="#modal-create"><i class="bi bi-database-add"></i> Registrar Categoría</button>
    <div class="modal fade" id="modal-create" tabindex="-1" aria-labelledby="modal-titleC" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modal-titleC">Registrar Nueva Categoría</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="CategoriaServlet">
            <input type="hidden" name="Command" value="Create">
            <div class="modal-body">
              <div class="mb-3">
                <label class="form-label" for="descripcion">Descripción: </label>
                <input type="text" class="form-control" id="descripcion" name="descripcion" required autocomplete="off">
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
        <th>Descripción</th>
        <th colspan="2">Acciones</th>
      </tr>
      <%
        int count = 1;
        for(Categoria item: categorias){
      %>
      <tr>
        <th><%=item.getIdcategoria() %></th>
        <td><%=item.getDescripcion() %></td>
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
      for(Categoria item: categorias){
    %>
    <div class="modal fade" id="modal-update<%=count%>" tabindex="-1" aria-labelledby="modal-titleU<%=count%>" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="modal-titleU<%=count%>">Actualizar Categoría Nro. <%=item.getIdcategoria() %></h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="CategoriaServlet">
            <input type="hidden" name="idcategoria" value="<%=item.getIdcategoria()%>">
            <input type="hidden" name="Command" value="Update">
            <div class="modal-body">
              <div class="mb-3">
                <label class="form-label" for="descripcion">Descripción: </label>
                <input class="form-control" id="descripcion" name="descripcion" type="text" required autocomplete="off" value="<%=item.getDescripcion()%>">
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
            <h5 class="modal-title" id="modal-titleD<%=count%>">¿Eliminar la Categoría Nro. <%=item.getIdcategoria() %>?</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="CategoriaServlet">
            <input type="hidden" name="idcategoria" value="<%=item.getIdcategoria()%>">
            <input type="hidden" name="Command" value="Delete">
            <div class="modal-body d-flex justify-content-around">
              <button class="btn btn-success" type="submit"><i class="bi bi-check"></i> Sí</button>
              <button class="btn btn-danger" type="button" data-bs-dismiss="modal" aria-label="Close"> No</button>
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