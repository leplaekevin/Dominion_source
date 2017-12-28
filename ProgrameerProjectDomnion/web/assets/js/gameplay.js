$(document).ready(function () {
//    for (i = 0; i < 5; i++) {
//        fillHand("back");
//    }
    for (i = 0; i < 3; i++) {
        fillFoldDeck("back");
        fillDrawDeck("back");
    }
    for (i = 0; i < 10; i++) {
        fillVoorraad("back");
    }
    fillAankooppunten(3);
    fillActiepunten(3);
    fillExtramunten(3);
    fillCopper(3);
    fillSilver(3);
    fillGold(3);
    fillEstate(3);
    fillDuchy(3);
    fillProvince(3);
    fillCurse(3);

    selectPlayer("2");


    var request = $.ajax({cache: false,
        dataType: "text",
        url: "/ProgrameerProjectDomnion/ServletGameplay",
        data: {
            operation: 'load'
        }
    });
   request.done(function(data){
            var obj = JSON.parse(data);
        fillHand(obj.hand0);
        fillHand(obj.hand1);
        fillHand(obj.hand2);
        fillHand(obj.hand3);
        fillHand(obj.hand4);

    });
    request.fail(function (jqXHR, textStatus) {
        alert(jqXHR.status + ' ' + textStatus);
    });


});

var fillHand = function (kaart) {

    $('#hand').append("<img src='assets/images/kaarten/" + kaart + ".jpg' class='hand'>");

};

var fillFoldDeck = function (kaart) {
    $('#folddeck').append("<img src='assets/images/kaarten/" + kaart + ".jpg' class='hand'>");
};

var fillDrawDeck = function (kaart) {
    $('#drawdeck').append("<img src='assets/images/kaarten/" + kaart + ".jpg' class='hand'>");
};

var selectPlayer = function (speler) {
    document.getElementById('speler' + speler).style.height = "75px";
};

var fillVoorraad = function (kaart) {
    $('#voorraad').append("<img src='assets/images/kaarten/" + kaart + ".jpg' id='" + kaart + "' class='hand' onclick='take(" + kaart + ")'/><span id='counter" + kaart + "'>0</span>");
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
    //TODO
};
var fillVoorraadCount = function (kaart, aantal) {
    //TODO
    $("'#counter" + kaart + "'").text(aantal);
};