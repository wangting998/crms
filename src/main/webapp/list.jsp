<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户列表</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
	<p>${testDate}</p>
  <body>
<h3 align="center">客户列表</h3>
<%--<span>${cookie.name.value}</span>--%>
<table border="1" width="70%" align="center">
	<tr>
		<th>客户姓名</th>
		<th>性别</th>
		<th>生日</th>
		<th>手机</th>
		<th>邮箱</th>
		<th>描述</th>
		<th>操作</th>
	</tr>
	<c:forEach var="item" items="${list.beanList}">
	<tr>
		<%--遍历 List--%>
		<td>${item.cname}</td>
		<td>${item.gender}</td>
		<td>${item.birthday}</td>
		<td>${item.cellphone}</td>
		<td>${item.email}</td>
		<td>${item.description}</td>
		<td>
			<a href="<c:url value='/load?cid=${item.cid}'/>">编辑</a>
			<a href="<c:url value='/delete?cid=${item.cid}'/>">删除</a>
		</td>
	</tr>
	</c:forEach>
</table>
	<%--<div class="page-icon">
		<a href="#">&lt;上一页&gt;</a>
		<!-- 数字1 代表当前页面-->
		<a href="#"><span class="currentPage">1</span></a>
		<a href="#">2</a>
		<a href="#">3</a>
		<a href="#">4</a>
		<a href="#">5</a>
		<a href="#">6</a>
		<a href="#">7</a>
		<a href="#">8</a>
		<a href="#">9</a>
		<a href="#">10</a>
		<a href="#" class="nextPage">&lt;下一页&gt;</a>
	</div>--%>

	<div align="center">
		第${list.pc}页/共${list.tp}页
		<a href="${list.url}&pc=1">首页</a>
		<c:choose>
			<c:when test="${list.pc eq 1}">上一页</c:when>
			<c:otherwise>
				<a href="${list.url}&pc=${list.pc-1}">上一页</a>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${list.tp<=10}">
				<c:set var="begin" value="1"/>
				<c:set var="end" value="${list.tp}"/>
			</c:when>
			<c:otherwise>
				<c:set var="begin" value="${list.pc-4}"/>
				<c:set var="end" value="${list.pc+5}"/>
				<c:choose>
					<c:when test="${begin<1}">
						<c:set var="begin" value="1"/>
						<c:set var="end" value="10"/>
					</c:when>
					<c:when test="${end>list.tp}">
						<c:set var="begin" value="${list.tp-9}"/>
						<c:set var="end" value="${list.tp}"/>
					</c:when>
				</c:choose>
			</c:otherwise>
		</c:choose>
		<c:forEach begin="${begin }" end="${end }" var="i">
			<c:choose>
				<c:when test="${i==list.pc}">${i}</c:when>
				<c:otherwise>
					<a href="${list.url}&pc=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${list.pc eq list.tp}">下一页</c:when>
			<c:otherwise>
				<a href="${list.url}&pc=${list.pc+1}">下一页</a>
			</c:otherwise>
		</c:choose>

		<a href="${list.url}&pc=${list.tp}">尾页</a>
	</div>
  </body>
</html>
