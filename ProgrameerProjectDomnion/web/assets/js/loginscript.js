var spelers = [];
var opslaanGebruikersnaam = function(){
    var spelersGegevens = {
        
            naam: $("#gebruikersnaam").val()
    };
    
    var spelerToevoegen ={
        speler: spelersGegevens
    };
    if(spelers.length <4){
        if ($("#gebruikersnaam").val() !== ""){
            spelers.push(spelerToevoegen);
            }
    } else{
        window.alert("er mogen maximum 4 spelers geselcteerd worden");
    }
    if(typeof (Storage) !== 'undefined'){
        
        localStorage.setItem("gebruikers", JSON.stringify(spelers));
    };
};


var vorige = function(){
    window.history.back();
};
$(document).ready(function()
{       
    $(".next").click(opslaanGebruikersnaam);
    $(".back").on("click",vorige);
    $(".next").click(function() {   
        var request = $.ajax({ cache: false,
            dataType: "text",
            url: "/ProgrameerProjectDomnion/ProjectDominion",
            data: { operation: 'login',
                gebruikersnaam: $("#gebruikersnaam").val(),
                wachtwoord: $("#wachtwoord").val()}
        });
             
        request.done(function(data){
            var obj = JSON.parse(data);
             
            $("#toonGegevens").html("gebruikersnaam is " + obj.gebruikersnaam + " en wachtwoord is " + obj.wachtwoord);
            console.log(obj);
            if(obj.success)
            {
               window.location.href="speler1.html";//link nog aanpassen
            }else if($("#gebruikersnaam").val()===""){
                window.alert("vul een gebruikersnaam in");
            }else if($("#wachtwoord").val()===""){
                window.alert("vul een wachtwoord in");
            }else{
                window.alert("deze gebruiker bestaat nog niet of het wachtwoord is incorrect");
            }       
        });
        request.fail(function (jqXHR, textStatus) {
            alert(jqXHR.status + ' ' + textStatus);
        });
    });
});