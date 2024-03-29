
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.issam.ecommerceweb.controller.admin.AdminProductServlet" %>
<%@include file="header.jsp" %>


<%@include file="slidebar.jsp" %>

<div class="col-sm-9 padding-right">

    <h2>Slidebar

    </h2>




    <div class="table-responsive cart_info">
        <table class="table table-condensed">
            <thead>

                <tr class="cart_menu">
                    <td class="image">Item</td>
                    
                    <td class="price">Tittle</td>
                    <td class="quantity">productId</td>
                    <td></td>
                </tr>
            </thead>
            <tbody>
                <c:if test="${!empty requestScope.allslidertsAdmin}">

                    <c:forEach items="${requestScope.allslidertsAdmin}" var="product">
                        <tr>
                            <td class="cart_product">
                                <a href=""><img src="../${product.image}" alt=""></a>
                            </td>
                            <td class="cart_description">
                                <p>${product.title}</p>
                                
                            </td>
                            <td class="cart_price">
                                <p>${product.productId}</p>
                            </td>
                           
                            <td class="cart_delete">
                                <a class="cart_quantity_delete" href="DeleteSlider?id=${product.id}"><i class="fa fa-times"></i></a>
                               
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>


            </tbody>
        </table>
    </div>

</div>
</div>
</div>
</section>

<%@include file="footer.jsp" %>

<%@include file="notify.jsp" %>
