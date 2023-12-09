
<%@ page import="org.issam.ecommerceweb.controller.user.IndexProductServlet" %>

<%@include file="header.jsp" %>


<%@include file="slider.jsp" %>



<%@include file="slidebar.jsp" %>
<jsp:include page="/IndexProductServlet"/>
<div class="col-sm-9 padding-right">
    <div class="features_items">
        <h2 class="title text-center">New Items</h2>

        <c:if test="${!empty requestScope.limitedProducts}">

            <c:forEach items="${requestScope.limitedProducts}" var="product">

                <div class="col-sm-4">
                    <div class="product-image-wrapper">
                        <div class="single-products">
                            <div class="productinfo text-center">
                                <img src=${product.photo} alt="" />
                                <h2>$${product.price}</h2>
                                <p>${product.name}</p>
                                <a class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                            </div>
                            <div class="product-overlay">
                                <div class="overlay-content">
                                    <h2>$${product.price}</h2>
                                    <p>${product.name}</p>
                                    <a  id="${product.productId}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                </div>
                            </div>
                            <img src="images/home/new.png" class="new" alt="" />
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

    </div>
</div>
</div>
</div>
</section>



<%@include file="footer.jsp" %>
