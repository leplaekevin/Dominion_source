var vorige = function(){
    window.history.back();
};
$(document).ready(function()
{       
    $(".back").on("click",vorige);
    $(".next").click(function() {
        
        var request = $.ajax({ cache: false,
            dataType: "text",
            url: "/ProgrameerProjectDomnion/ProjectDominion",
            data: { operation: 'registreren',
                gebruikersnaam: $("#gebruikersnaam").val(),
                wachtwoord: $("#wachtwoord").val(),
                checkwachtwoord: $("#checkwachtwoord").val()}
        });
        
        request.done(function(data){
            var obj = JSON.parse(data);
             
            $("#toonGegevens").html("gebruikersnaam is " + obj.gebruikersnaam + " en wachtwoord is " + obj.wachtwoord);
            console.log(obj);
            if(obj.success)
            {
               window.location.href="index.html";
            } else if($("#gebruikersnaam").val() === ""){
                window.alert("geef een gebruikersnaam op");
            } else if($("#wachtwoord").val() === ""){
                window.alert("vul een wachtwoord in");
            } else if($("#wachtwoord").val() !== $("#checkwachtwoord").val()){
                window.alert("de wachtwoorden komen niet overeen");
            } else{
                window.alert("deze gebruiker bestaat al");
            }
        });
        request.fail(function (jqXHR, textStatus) {
            alert(jqXHR.status + ' ' + textStatus);
        });
    });   
});