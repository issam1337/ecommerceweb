

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ page import="org.issam.ecommerceweb.controller.admin.AdminProductServlet" %>
<%@include file="header.jsp" %>


<%@include file="slidebar.jsp" %>

<div class="col-sm-6 padding-right">

    <h3 color="#A5E3B9">New Card</h3>

    <div class="table-responsive cart_info">

        <form action="ChangeCards" method="get"> 
            <div class="product-information"
                 <br>

                <label>Cards</label>
                <select name="cards">
                    <option> 50 </option>
                    <option>100</option>
                    <option> 200</option>
                    <option>500</option>
                </select>



                <label>Number Of Cards</label>

                <input type="number" name="numberofcard" placeholder="enter number" required min="1" class="input-field"  />



                <input type="submit" value="Add"  class="btn btn-default" >

                </form>
            </div>



    </div>
</div>
</div>
</section>


<%@include file="footer.jsp" %>


