var vorige = function () {
    window.history.back();
};
var hideWhenPlayAsGuest = function () {
    $("#loginform").hide();
    
    
};

var showWhenNotPlayAsGuest = function () {
    $("#loginform").show();
    
};

var aantalspelers;

$(document).ready(function ()
{

    var requestaantalspelers = $.ajax({cache: false,
        dataType: "text",
        url: "/ProgrameerProjectDomnion/ServletGameplay",
        data: {operation: 'aantalSpelersDoorgeven'
        }

    });

    requestaantalspelers.done(function (data) {
        var obj = JSON.parse(data);
        aantalspelers = obj.aantalSpelersDoorgeven;
    });

    $(".checkYes").on("click", hideWhenPlayAsGuest);
    $(".checkNo").on("click", showWhenNotPlayAsGuest);
    //$(".back").on("click", vorige);
    $(".next").click(function () {

        var request = $.ajax({
            url: "/ProgrameerProjectDomnion/ServletGameplay",
            data: {operation: 'login',
                gebruikersnaam: $("#gebruikersnaam").val(),
                wachtwoord: $("#wachtwoord").val(),
                aantalspelers: aantalspelers,
                gast:$("input[type=radio]:checked").val(),
                doorTeSturenGebruiker1: $(".gebruikersnaam1").val(),
                doorTeSturenGebruiker2: $(".gebruikersnaam2").val(),
                doorTeSturenGebruiker3: $(".gebruikersnaam3").val(),
                doorTeSturenGebruiker4: $(".gebruikersnaam4").val()
            }});

        request.done(function (data) {
            var obj = data;//weg

            $("#toonGegevens").html("gebruikersnaam is " + obj.gebruikersnaam + " en wachtwoord is " + obj.wachtwoord);
            console.log(obj);

            if (obj.success === "true")
            {
                if (obj.teller < aantalspelers + 1) {

                    window.location.href = "login" + obj.teller + ".html";//link nog aanpassen

                } else {

                    window.location.href = "selecteerKaarten.html";

                }
                

            } else if ($("#gebruikersnaam").val() === "") {
                window.alert('Vul een gebruikersnaam in.');
            } else if ($("#wachtwoord").val() === "") {
                window.alert("Vul een wachtwoord in.");
            } else {
                window.alert("Deze gebruiker is al ingelogd, log in met een andere speler a.u.b.");
            }

        });
        request.fail(function (jqXHR, textStatus) {
            alert(jqXHR.status + ' ' + textStatus);
        });
    });




});


