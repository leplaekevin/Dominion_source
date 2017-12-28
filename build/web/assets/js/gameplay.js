var kaartnaam = 'error';
var playedCoins = false;
var gameMustEnd = false;
$(document).ready(function () {

    $("#voorraad").hover(function () {
        $("#voorraad span").css("zIndex", -10);
    }, function () {
        $("#voorraad span").css("zIndex", 10);
    });
    
     $("#puntenEnMunten").hover(function () {
        $("#puntenEnMunten span").css("zIndex", -10);
    }, function () {
        $("#puntenEnMunten span").css("zIndex", 10);
    });


    for (i = 0; i < 3; i++) {

        fillDrawDeck("back");
    }
    fillFoldDeck("back");
    voorraadaantal = 0;
    var request = $.ajax({cache: false,
        dataType: "text",
        url: "/ProgrameerProjectDomnion/ServletGameplay",
        data: {
            operation: 'load'
        }
    });

    request.done(function (data) {

        var obj = JSON.parse(data);
        for (var i = 0; i < obj.aantalInHand - 1; i++) { //wrm - 1????
            fillHand(eval("obj.hand" + i));
        }

        fillCopper(parseInt(obj.aantalkoper));
        fillSilver(parseInt(obj.aantalzilver));
        fillGold(parseInt(obj.aantalgoud));
        fillEstate(parseInt(obj.aantallandgoed));
        fillDuchy(parseInt(obj.aantalhertogdom));
        fillProvince(parseInt(obj.aantalprovincie));
        fillCurse(parseInt(obj.aantalvloek));



        fillVoorraad(obj.kaartnaam1);
        fillVoorraad(obj.kaartnaam2);
        fillVoorraad(obj.kaartnaam3);
        fillVoorraad(obj.kaartnaam4);
        fillVoorraad(obj.kaartnaam5);
        fillVoorraad(obj.kaartnaam6);
        fillVoorraad(obj.kaartnaam7);
        fillVoorraad(obj.kaartnaam8);
        fillVoorraad(obj.kaartnaam9);
        fillVoorraad(obj.kaartnaam10);

        fillVoorraadCount("kaart1", obj.kaart1);
        fillVoorraadCount("kaart2", obj.kaart2);
        fillVoorraadCount("kaart3", obj.kaart3);
        fillVoorraadCount("kaart4", obj.kaart4);
        fillVoorraadCount("kaart5", obj.kaart5);
        fillVoorraadCount("kaart6", obj.kaart6);
        fillVoorraadCount("kaart7", obj.kaart7);
        fillVoorraadCount("kaart8", obj.kaart8);
        fillVoorraadCount("kaart9", obj.kaart9);
        fillVoorraadCount("kaart10", obj.kaart10);

        fillAankooppunten(obj.aankooppunten);
        fillActiepunten(obj.actiepunten);
        fillExtramunten(obj.munten);
        obj.aantalspelers;
        switch (obj.aantalspelers) {
            case 2:
                $("#speler3").hide();
                $("#speler4").hide();

                break;
            case 3:
                $("#speler4").hide();
                break;

        }
        selectPlayer(obj.spelernr + 1);


    });
    request.fail(function (jqXHR, textStatus) {
        alert(jqXHR.status + ' ' + textStatus);
    });


});

var fillHand = function (kaart) {
    if (kaart !== "goud" && kaart !== "koper" && kaart !== "zilver" && kaart !== "tuinen" &&
            kaart !== "vloek" && kaart !== "hertogdom" && kaart !== "provincie" && kaart !== "landgoed")
    {
        $('#hand').append("<img src='assets/images/kaarten/" + kaart + ".jpg' onclick=\"playActionCard('" + kaart + "')\"class='" + kaart + "'>");
    } else {
        $('#hand').append("<img src='assets/images/kaarten/" + kaart + ".jpg'  class='" + kaart + "'>");
    }

};

var fillspeelveld = function (kaart) {
    $('#speelveld').append("<img src='assets/images/kaarten/" + kaart + ".jpg'  class='" + kaart + "'>");
};

var fillFoldDeck = function (kaart) {
    $('#folddeck').append("<img src='assets/images/kaarten/" + kaart + ".jpg' class=''>");
};

var fillDrawDeck = function (kaart) {

    $('#drawdeck').append("<img src='assets/images/kaarten/" + kaart + ".jpg' class=''>");
};

var selectPlayer = function (speler, aantalspelers) {

    document.getElementById('speler' + speler).style.height = "75px";
    //speler -1;
    if (speler !== 1) {
        speler = speler - 1;
        document.getElementById('speler' + speler).style.height = "50px";
    } else {

        document.getElementById('speler' + aantalspelers).style.height = "50px";

    }

};

var fillVoorraad = function (kaart) {
    voorraadaantal++;
    $('#voorraad').append("<img src='assets/images/kaarten/" + kaart + ".jpg' id='" + kaart + "' class='hand' onclick=\"take('" + kaart + "')\"/><span id='kaart" + voorraadaantal + "'>0</span>");
};

var fillAankooppunten = function (aantal) {
    $('#aankooppunten').text(aantal);
};
var fillActiepunten = function (aantal) {
    $('#actiepunten').text(aantal);
};
var fillExtramunten = function (aantal) {
    $('#extramunten').text(aantal);
};
var drawCard = function () {
    $('#hand').append("<img src='assets/images/kaarten/" + "back" + ".jpg' class='hand'>").show('slow');

};
var fillCopper = function (aantal) {
    $('#copper').text(aantal);
};
var fillSilver = function (aantal) {
    $('#silver').text(aantal);
};
var fillGold = function (aantal) {
    $('#gold').text(aantal);
};
var fillEstate = function (aantal) {
    $('#estate').text(aantal);
};
var fillDuchy = function (aantal) {
    $('#duchy').text(aantal);
};
var fillCurse = function (aantal) {
    $('#curse').text(aantal);
};
var fillProvince = function (aantal) {
    $('#province').text(aantal);
};
var take = function (kaart) {
    if (playedCoins) {
        var take = $.ajax({cache: false,
            dataType: "text",
            url: "/ProgrameerProjectDomnion/ServletGameplay",
            data: {
                operation: 'take',
                kaartnaam: kaart
            }
        });

        take.done(function (data) {

            var obj = JSON.parse(data);
            $("#log").prepend(obj.melding + "<br>");
            fillVoorraadCount("kaart1", obj.kaart1);
            fillVoorraadCount("kaart2", obj.kaart2);
            fillVoorraadCount("kaart3", obj.kaart3);
            fillVoorraadCount("kaart4", obj.kaart4);
            fillVoorraadCount("kaart5", obj.kaart5);
            fillVoorraadCount("kaart6", obj.kaart6);
            fillVoorraadCount("kaart7", obj.kaart7);
            fillVoorraadCount("kaart8", obj.kaart8);
            fillVoorraadCount("kaart9", obj.kaart9);
            fillVoorraadCount("kaart10", obj.kaart10);


            fillCopper(parseInt(obj.aantalkoper));
            fillSilver(parseInt(obj.aantalzilver));
            fillGold(parseInt(obj.aantalgoud));
            fillEstate(parseInt(obj.aantallandgoed));
            fillDuchy(parseInt(obj.aantalhertogdom));
            fillProvince(parseInt(obj.aantalprovincie));
            fillCurse(parseInt(obj.aantalvloek));


            fillAankooppunten(obj.aankooppunten);
            fillActiepunten(obj.actiepunten);
            fillExtramunten(obj.munten);
            //$("#log").prepend(obj.legInAflegDeck + "<br>");
            if (obj.legInAflegDeck) {
                fillFoldDeck(obj.aflegdeck);
            }
        });
    } else {
        $("#log").prepend("Speel uw munten eerst!<br>");
    }

};
var fillVoorraadCount = function (kaartnr, aantal) {
    $('#' + kaartnr).text(aantal);
};
var endturn = function () {
    playedCoins = false;
    var endturn = $.ajax({cache: false,
        dataType: "text",
        url: "/ProgrameerProjectDomnion/ServletGameplay",
        data: {
            operation: 'endturn'
        }

    });
    endturn.done(function (data) {

        var obj = JSON.parse(data);
        var spelernr = obj.spelernr;
        $("#log").prepend(obj.beurtbeindigt + "<br>");
        $("#log").prepend(obj.naamActiveSpeler + " is aan de beurt." + "<br>");


        selectPlayer(spelernr + 1, obj.aantalspelers);


        $("#hand").html("");
        $("#folddeck").html("");
        $("#speelveld").html("");
        fillFoldDeck("back");
        if (obj.legInAflegDeck) {
            fillFoldDeck(obj.aflegdeck);
        }
        for (var i = 0; i < obj.aantalInHand - 1; i++) { //wrm - 1????
            fillHand(eval("obj.hand" + i));
        }

        fillAankooppunten(obj.aankooppunten);
        fillActiepunten(obj.actiepunten);
        fillExtramunten(obj.munten);
        //$("#log").prepend(obj.spelernr + 1);
        if (obj.gameMoetEindigen) {
            endgame();
            gameMustEnd = true;
        }

    });
};
var playcoins = function () {
    playedCoins = true;
    $("#hand .koper,#hand .goud, #hand .zilver").fadeTo("slow", 0.8, function () {
        // Animation complete.
    });
    $('#hand .koper,#hand .goud,#hand .zilver').animate({
        'marginBottom': "+=30px" //moves up
    });
};
var endgame = function () {

    var endgame = $.ajax({cache: false,
        dataType: "text",
        url: "/ProgrameerProjectDomnion/ServletGameplay",
        data: {
            operation: 'endGame'
        }

    });
    endgame.done(function (data) {
        if (gameMustEnd) {
            window.alert("Game werd beëindigd.");
            window.location.assign("eindscore.html");
        } else {
            if (confirm("Wilt u de game beëindigd?")) {
                window.location.assign("eindscore.html");
            }
        }
    });
};

var playActionCard = function (kaartnaam) {

    var actioncard = $.ajax({cache: false,
        dataType: "text",
        url: "/ProgrameerProjectDomnion/ServletGameplay",
        data: {
            operation: 'playcard',
            kaartnaam: kaartnaam
        }

    });
    actioncard.done(function (data) {
        var obj = JSON.parse(data);
        //$("#log").prepend(obj.speelveld0);
        $("#hand").html("");

        for (var i = 0; i < obj.aantalInHand - 1; i++) { //wrm - 1????
            fillHand(eval("obj.hand" + i));
        }
        $("#speelveld").html("");
        $("#log").prepend(obj.meldingActieKaart + "<br>");

        for (var i = 0; i < obj.aantalInSpeelveld; i++) {
            fillspeelveld(eval("obj.speelveld" + i));
        }

        fillAankooppunten(obj.aankooppunten);
        fillActiepunten(obj.actiepunten);
        fillExtramunten(obj.munten);

    });

};