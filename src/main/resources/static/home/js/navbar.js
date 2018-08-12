/**
 * 
 */
$(document).scroll(function() { 
    scroll_start = $(this).scrollTop();
    if(scroll_start > offset.top) {
        $('#mynav').css('background-color', '#f0f0f0');
    } else {
        $('#mynav').css('background-color', 'transparent');
    }
});