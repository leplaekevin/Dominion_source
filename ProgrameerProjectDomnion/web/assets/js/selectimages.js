/* 
 * Made and owned by Egon De Baene.
 */

//initilize
$("document").ready(function () {
    //onclicks

    var selecteer = $(".slide");
    selecteer.on("click", dropImage);
    
    $(".next").click(function(){
        var request = $.ajax({ cache: false,
            dataType: "text",
            url: "/ProgrameerProjectDomnion/gameplay",
            data: { operation: 'gameplay',
                geselecteerdeKaarten: JSON.parse(geselecteerdeKaarten)}
            
    });
    
    request.done(function(data){
        var obj = JSON.parse(data);
        
        $("#test").html("de kaarten zijn " + obj.geselecteerdeKaarten);
        console.log(obj.arrayGeselecteerdeKaarten);
    });
    request.fail(function(jqXHR, textStatus){
      alert(jqXHR.status + ' ' + textStatus);  
    });
    });

});
var i = 0;
var geselecteerdeKaarten = [];


//var resetImage = function () {
//    $('.slider1').show(this);
//    this.remove();
//};
var dropImage = function () {
    i++;
    if (i > 10) {
        window.alert("er zijn reeds 10 kaarten geselecteerd");
    } else {
        
        naamFoto = $(this).attr("id");
        $('.geselecteerdeFotos').append("<div class='selected' id='"+naamFoto+"' style='float: left; list-style: none; position: relative; width: 155.4px; margin-right: 50px;'>,<img src='assets/images/kaarten/"+naamFoto+".jpg'></div>");
        $('.hiddendiv').append(this);
        $(this).remove();
        geselecteerdeKaarten.push(naamFoto);
        console.log(naamFoto);
        console.log(geselecteerdeKaarten);
        
        
        
    }
};

var newImages = function () {
        geselecteerdeKaarten.length = 0;
        console.log(geselecteerdeKaarten);
        location.reload(true);
        
};

/*


var upImage = function () {
        naamFoto = $(this).attr("id");
        $(this).remove(".geselecteerdeKaarten");
        $(this).remove(".hiddendiv");
        $('.slider1').append("<div class='slide' id='"+naamFoto+"' style='float: left; list-style: none; position: relative; width: 155.4px; margin-right: 50px;'>,<img src='assets/images/kaarten/"+naamFoto+".jpg'></div>");
        console.log(naamFoto); 
        
        

};
*/