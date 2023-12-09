

$(document).ready(function () {
    $(function () {
        $.scrollUp({
            scrollName: 'scrollUp',
            scrollDistance: 300,
            scrollFrom: 'top',
            scrollSpeed: 300,
            easingType: 'linear',
            animation: 'fade',
            animationSpeed: 200,
            scrollTrigger: false,
            scrollText: '<i class="fa fa-angle-up"></i>',
            scrollTitle: false,
            scrollImg: false,
            activeOverlay: false,
            zIndex: 2147483647
        });
    });

    //to read photo from client 
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#imageView').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }


    $("#image").change(function () {
        readURL(this);
    });
    

    activeClass();
    
});

/*price range*/

 $('#sl2').slider();

	var RGBChange = function() {
	  $('#RGB').css('background', 'rgb('+r.getValue()+','+g.getValue()+','+b.getValue()+')')
};


    function activeClass(){
        var link = window.location.href;
        if(link.includes("Shop")){
            $("#shopBtn").addClass("active");
            $("#homeBtn").removeClass("active");
        }else if(link.includes("index.jsp") || link.endsWith("yourCart/")){
            $("#homeBtn").addClass("active");
            $("#shopBtn").removeClass("active");
        }else{
            $("#homeBtn").removeClass("active");
            $("#shopBtn").removeClass("active");
        }
    }
