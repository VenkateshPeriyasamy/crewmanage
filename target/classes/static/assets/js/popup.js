$(document).ready(function(){

$(".open-popup").click(function(event){
$("#popup_default").show();
});

$(".popup-close").click(function(){
  $("#popup_default").hide();
});

$(".open-popup-edit").click(function(event){
$("#edit_popup_default").show();
});

$(".popup-close-edit").click(function(){
  $("#edit_popup_default").hide();
});

});