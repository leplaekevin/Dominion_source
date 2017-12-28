var vorige = function(){
    window.history.back();
};
$(document).ready(function()
{       
    $(".back").on("click",vorige);
    $(".next").click(function() {
        
        var request = $.ajax({ 
            url: "/ProgrameerProjectDomnion/ServletGameplay",
            data: { operation: 'registreren' ,
                gebruikersnaam: $("#gebruikersnaam").val(),
                wachtwoord: $("#wachtwoordregistratie").val(),
                checkwachtwoord: $("#checkwachtwoord").val()
        }});
        
        request.done(function(data){
//            var obj = JSON.parse(data);
             console.log(data);
             var obj = data;//weg
            $("#toonGegevens").html("gebruikersnaam is " + obj.gebruikersnaam + " en wachtwoord is " + obj.wachtwoord);
            console.log(obj);
            console.log(obj.registratieSuccess);
            if(obj.registratieSuccess=== "true")
            {
                window.alert("registratrie is gelukt");
                //automatis laten doorgaan
               window.location.href="index.html";
            } else if($("#gebruikersnaam").val() === ""){
                window.alert("Vul een gebruikersnaam in.");
            } else if($("#wachtwoordregistratie").val() === ""){
                window.alert("Vul een wachtwoord in.");
            } else if($("#wachtwoordregistratie").val() !== $("#checkwachtwoord").val()){
                window.alert("De wachtwoorden komen niet overeen.");
            } else{
                window.alert("Deze gebruiker bestaat al.");
            }
        });
        request.fail(function (jqXHR, textStatus) {
            console.log(jqXHR.status, textStatus);
            alert(jqXHR.status + ' ' + textStatus);
        });
    });   
});