$('.menu').click(function(){
    // $('.menu').css('background-color','#ff5e1a');
    if ($('#leftM').slideDown){
        $('#leftM').slideUp("slow");
    }

    $('#rightM').slideToggle("slow");
});


// $('.content').mouseleave(function(){
//     $('.menu').css('background-color','#ff782e');
//     $('.dropDown').slideUp("slow", function(){
//         $(this).fadeOut(2000);
//     });
// });

$('.menuleft').click(function(){
    // $('.menu').css('background-color','#ff5e1a');
    if ($('#rightM').slideDown){
        $('#rightM').slideUp("slow");
    }
    $('#leftM').slideToggle("slow");
});

