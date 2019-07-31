$(document).ready(function(){
    $('.edit-button').on('click',function(event){
        
        event.preventDefault();
        var href=$(this).attr('href');
        var text=$(this).attr('text');
        
        
            $.get(href,function(flightDetails,status){
                $('.edit #id').val(flightDetails.id);
                $('.edit #flightNo').val(flightDetails.flightNo);
                $('.edit #flightType').val(flightDetails.flightType);
                $('.edit #airline').val(flightDetails.airline);
                $('.edit #flightModelNo').val(flightDetails.flightModelNo);
                $('.edit #flightCapacity').val(flightDetails.flightCapacity);
                        
            });
            $('.edit').modal();
      
    });
    
    $('#canceledit').on('click',function(){
        
        $('.edit').modal('hide');
        });
        
    
});