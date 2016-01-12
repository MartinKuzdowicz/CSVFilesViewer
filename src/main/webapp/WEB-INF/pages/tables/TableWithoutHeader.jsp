<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-bordered table-striped">

	<c:forEach items="${recordsTableDTO.listOfRows}" var="rowWithData">
		<tr>
			<c:forEach items="${rowWithData}" var="record">
				<td>${record}</td>
			</c:forEach>

		</tr>

	</c:forEach>

</table>