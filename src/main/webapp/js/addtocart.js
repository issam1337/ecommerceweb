$(document).ready(function () {


    if ("userID" in window)
        getInitProductNumber(userID);

    //add in home & shop page
    $('.add-to-cart').click(function () {
        var id = $(this).attr('id');
        addProduct(id, 1);
    });

    //add from product details
    $('.add-to-my').click(function () {
        var id = $(this).attr('id');
        var qaunty = $("#quan").val();
        addProduct(id, qaunty);
    });
    
    $(".cart_quantity_down").click(function(){
        var id = $(this).attr('id').substring(2);
        //check if number of product greater than zero 
        if($("#V_"+id).val() > 1 )
            reduceProduct(id);
    });
    
    $(".cart_quantity_up").click(function(){
        var id = $(this).attr('id').substring(2);
        increaseProduct(id);
    });
 
    //------------------ function on page ---------------------


    function addProduct(id, qaunty) {
        $.ajax({
            url: 'addCart', //servlet url
            type: 'GET',
            data: {"productID": id, "qaunty": qaunty},
            success: (data) => {
                if (data.redirect) {
                    // data.redirect contains the string URL to redirect to
                    window.location.href = data.redirect;
                }else{
                    $("#number").html(data);
                    showNotification('product add to your cart','success');
                }
                
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert("error");
                if (thrownError.redirect.length) {
                    window.location.replace(thrownError.redirect);
                } else {
                    alert('There was an error processing your request, please try again');
                }
            }
        });
    }

    function getInitProductNumber(id) {
        $.ajax({
            url: 'getCartCount',
            type: 'GET',
            data: {"id": id},
            success: (data) => {
                $("#number").html(data);
            }
        });
    }
    

    function reduceProduct(id){
       $.ajax({
            url: 'ReduceQuantity',
            type: 'GET',
            data: {"id": id},
            success: (data) => {
                if (data.redirect) {
                    window.location.href = data.redirect;
                }else{
                    $("#number").html(data);
                    showNotification('product Quantity decrease from your cart','success');
                    reCalculateDecrease(id);
                }
                
            }
        });
    }

    function increaseProduct(id){
        $.ajax({
            url: 'increaseQuantity',
            type: 'GET',
            data: {"id": id},
            success: (data) => {
                if (data.redirect) {
                    window.location.href = data.redirect;
                }else{
                    $("#number").html(data);
                    showNotification('product Quantity increase to your cart','success');
                    reCalculateIncrease(id);
                }
                
            }
        });
    }
    

    function reCalculateDecrease(id){
        var price = parseInt($("#P_"+id).text().substring(1));
        $("#V_"+id).val($("#V_"+id).val()-1);
        var pecies = parseInt($("#V_"+id).val());
        $("#T_"+id).text("$"+( pecies*price) );
        

        var total = parseInt($("#subTotal").text().substring(1));
        $("#subTotal").text("$"+(total - price));
        $("#total").text("$"+(total - price));
        
    }

    function reCalculateIncrease(id){
        var price = parseInt($("#P_"+id).text().substring(1));
        var pecies = parseInt($("#V_"+id).val()) + 1 ;
        $("#V_"+id).val(pecies);
        $("#T_"+id).text("$"+( pecies*price) );
        var total = parseInt($("#subTotal").text().substring(1));
        $("#subTotal").text("$"+(total + price));
        $("#total").text("$"+(total + price));
    }
    
});
