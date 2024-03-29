
<%@ page import="org.issam.ecommerceweb.controller.user.Shop" %>
<%-- include header file --%> 
<%@include file="header.jsp" %>


<%@include file="slidebar.jsp" %>

<div class="col-sm-9 padding-right">
    <div class="features_items"><!--features_items-->
        <h2 class="title text-center">Features Items</h2>



        <c:if test="${!empty requestScope.allProducts}">

            <c:forEach items="${requestScope.allProducts}" var="product">

                <div class="col-sm-4">
                    <div class="product-image-wrapper">
                        <div class="single-products">
                            <div class="productinfo text-center">
                                <img src="${product.photo}" alt="" />
                                <h2>$${product.price}</h2>
                                <p>${product.name}</p>
                                <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                            </div>
                            <div class="product-overlay">
                                <div class="overlay-content">
                                    <h2>$${product.price}</h2>
                                    <p>${product.name}</p>
                                    <a href="#" id="${product.productId}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                </div>
                            </div>
                        </div>
                        <div class="choose">
                            <ul class="nav nav-pills nav-justified">
                                <li><a href="Product?id=${product.productId}"><i class="fa fa-plus-square"></i>View Details</a></li>
                            </ul>
                        </div>
                    </div>
                </div>


            </c:forEach>
        </c:if>

        <!-- End Sara features_items-->

    </div><!--features_items-->






    <ul class="pagination">
        <c:if test="${not empty query}">
            <c:set var="stringQuery" value="&cate=${query}"/>
        </c:if>

        <c:if test="${currentPage != 1}">
             <li><a href="Shop?page=${currentPage - 1}${stringQuery}">&laquo;</a></li>
	</c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage == i}">
                    <li class="active"><a href="">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                    <li><a href="Shop?page=${i}${stringQuery}">${i}</a></li>

                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li><a href="Shop?page=${currentPage + 1}${stringQuery}">&raquo;</a></li>
	</c:if>
    </ul>
</div>
</div>
</div>
</section>

<%@include file="footer.jsp" %>
