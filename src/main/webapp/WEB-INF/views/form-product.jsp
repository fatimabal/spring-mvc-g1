<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<form action="${pageContext.request.contextPath}/product" method="post">
  <input type="hidden" name="id" value="${product.id}">
  <label for="">Libelle</label>
  <input type="text" name="libelle"  value="${product.libelle}" >
  <label for="">Prix</label>
  <input type="text" name="prix"  value="${product.prix}">

  <button type="submit">Enregistrer</button>
</form>
