/* 
 * Made and owned by Egon De Baene.
 */

//initilize
$("document").ready(function () {
    //onclicks

    var geselecteerd = $(".geselecteerdefotos");
    var selecteer = $(".slide");
    selecteer.on("click", dropImage);
    //geselecteerd.on("click", resetImage);
    $(".next").click(function(){
        var request = $.ajax({ cache: false,
            dataType: "text",
            url: "/ProgrameerProjectDomnion/SelecteerKaarten",
            data: { operation: 'selecteerKaarten',
                kaarten: geselecteerdeKaarten
                    }
            
    });
    
    request.done(function(data){
        var obj = JSON.parse(data);
        
        window.alert(obj.lijstVanKaarten);
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
        $('.geselecteerdeKaarten').append("<div class='geselecteerdefotos'><div class='slide' id='"+naamFoto+"' style='float: left; list-style: none; position: relative; width: 155.4px; margin-right: 50px;'>,<img alt='"+naamFoto+"' src='assets/images/kaarten/"+naamFoto+".jpg'></div></div>");
        $('.hiddendiv').append(this);
        geselecteerdeKaarten.push(naamFoto);
        console.log(naamFoto);
        console.log(geselecteerdeKaarten);
        
        
    }
};

var upImage = function () {
        naamFoto = $(this).attr("id");
        

};

var print = function () {
    window.alert(geselecteerdeKaarten);
};

//
//var burgeradd = function () {
//    $('.toppings').append("<img src='images/" + this.text + ".svg'/>");
//};