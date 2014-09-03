/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#loading").hide();
    $('#user').keypress(function(event) {
        if (event.keyCode == 13) {
            dataLoader(event);
            return false;
        }
    });
    $('#submit').click(function(event) {
        dataLoader(event);
    });
});

function dataLoader(event) {
    $(".spinner-background").slideDown("slow");
    $(".spinner").slideDown("slow");
    var username = $('#user').val();
    $.ajax({
        url: 'actionservlet',
        data: {user: username},
//        timeout: 300,
        beforeSend: function() {
        },
        success: function(responseText) {
            $('.intro-content').html(responseText.profile);
            $('.page-inner').html(responseText.details);

        },
        error: function() {
            $('.intro-content').html("<div class=\"profile\"><img src=\"img/default.jpg\" alt=\"profile\"></div><h1><span style=\"color:red\">Oops.!</span><span style=\"color:black\">Something went wrong</span></h1>");
        },
        complete: function() {
            $(".spinner-background").hide();
            $(".spinner").hide();
//                            alert("finished");
        }
    });
}

