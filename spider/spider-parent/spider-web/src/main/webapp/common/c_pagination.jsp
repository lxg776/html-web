<%@page language="java" pageEncoding="UTF-8"%>

<div class="pagination pagination-centered">
	<ul>


		<c:if test="${page.pageNum gt 1}">
			<li><a href="javascript:void(0);"
				onclick="getDataList(${page.pageNum -1})">Prev</a></li>
		</c:if>


		<c:if test="${page.pageNum -5 le 0}">
			<c:forEach begin="1" end="${page.pageNum-1}" var="index">
				<li><a href="javascript:void(0);"
					onclick="getDataList(${index})">${index}</a></li>
			</c:forEach>
		</c:if>

		<c:if test="${page.pageNum -5 gt 0}">
			<c:forEach begin="${page.pageNum-5}" end="${page.pageNum-1}"
				var="index">
				<li><a href="javascript:void(0);"
					onclick="getDataList(${index})">${index}</a></li>
			</c:forEach>
		</c:if>

		<li><a href="javascript:void(0);"
			onclick="getDataList(${page.pageNum})" class="active">${page.pageNum}</a></li>

		<c:if test="${page.pageNum le page.pages}">
			<c:if test="${page.pageNum +5 le page.pages}">
				<c:forEach begin="${page.pageNum +1}" end="${page.pageNum +5}"
					var="index">
					<li><a href="javascript:void(0);"
						onclick="getDataList(${index})">${index}</a></li>
				</c:forEach>
			</c:if>
			<c:if test="${page.pageNum +5 gt page.pages}">
				<c:forEach begin="${page.pageNum+1}" end="${page.pages}" var="index">
					<li><a href="javascript:void(0);"
						onclick="getDataList(${index})">${index}</a></li>
				</c:forEach>
			</c:if>
		</c:if>

		<c:if test="${page.pageNum lt page.pages}">
			<li><a href="javascript:void(0);"
				onclick="getDataList(${page.pageNum +1})">Next</a></li>
		</c:if>
	</ul>
</div>

