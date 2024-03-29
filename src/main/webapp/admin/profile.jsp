

<%@include file="header.jsp" %>


<%@include file="slidebar.jsp" %>



<c:choose>
    <c:when test="${empty userInfo.photo}">
        <c:set var="photo" value="../upload/profile.jpg"/>
        <c:set var="photoscr" value="upload/profile.jpg"/>
    </c:when>
    <c:otherwise>
        <c:set var="photo" value="../${userInfo.photo}"/>
        <c:set var="photoscr" value="${userInfo.photo}"/>
    </c:otherwise>
</c:choose>

<div class="col-sm-9 padding-right">
    <div class="product-details"><!--product-details-->
        <div class="col-sm-4">
            <div class="view-product">
                <img src="${photo}"  id="imageView" alt="" />
            </div>
        </div>
            
        <div class="col-sm-8">
           
            
            <div class="product-information">
                <form action="AdminProfile" method="post" id="editProfileForm" enctype="multipart/form-data" onsubmit="return validateSignup();">	
                    <h2>${userInfo.userName}</h2>
                    <p>Account Setting</p>
                    <label>Name</label>
                    <input type="text" placeholder="Name"  class="input-field"  id="editUserName" value="${userInfo.userName}" disabled required/>
                    <input type="hidden" name="username" value="${userInfo.userName}"/>
                    <label>E-mail</label>
                    <input type="email" placeholder="E-mail"  class="input-field" name="email" id="editEmail" value="${userInfo.email}" required/>
                    <label>Password</label>
                    <input type="Password" placeholder="Password"  class="input-field" name="password" id="SignupPassword" required/>
                    <label>Confirm Password</label>
                    <input type="Password" placeholder="Confirm Password"  class="input-field" id="SignupConfirmPassword" required/>

                    <label>Address</label>
                    <input type="text" placeholder="Address"  class="input-field" name="address" id="editAddress" value="${userInfo.address}" />
                    <label>Job</label>
                    <input type="text" placeholder="Job"  class="input-field" name="job" id="editJob" value="${userInfo.job}"/>
                    <label>Credit Card</label>
                    <input type="text" placeholder="Credit Card"  class="input-field" name="creaditCard" id="SignupCreditCard" value="${userInfo.creditCard}" required/>
                    <input type="hidden" name="id" value="${userInfo.userId}" />
                    <input type="hidden" name="photo" value="${photoscr}"/>

                    <label>Image</label>
                    <input type="file" name="image" id="image" accept="image/*"><br/>
                    <button type="submit" class="btn btn-default" id="editSubmitBtn">Update</button>
                </form>
            </div>
        </div>





    </div>
</div>
</div>
</section>



<%@include file="footer.jsp" %>
