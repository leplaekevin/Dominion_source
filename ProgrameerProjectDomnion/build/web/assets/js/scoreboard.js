/* 
 * Made and owned by Egon De Baene.
 */

$("document").ready(function () {

    var loadScore = $.ajax({cache: false,
        dataType: "text",
        url: "/ProgrameerProjectDomnion/ServletGameplay",
        data: {operation: 'scoreboard'
        }

    });

    loadScore.done(function (data) {
        var obj = JSON.parse(data);
        $('#spelerrank1').append("<td>1</td><td>" + obj.gebruiker1 + "</td><td>" + obj.score1 + "</td>");
        $('#spelerrank2').append("<td>2</td><td>" + obj.gebruiker2 + "</td><td>" + obj.score2 + "</td>");
        if (obj.aantalSpelers > 2) {
            $('#spelerrank3').append("<td>3</td><td>" + obj.gebruiker3 + "</td><td>" + obj.score3 + "</td>");
        }
        if (obj.aantalSpelers > 3) {
            $('#spelerrank4').append("<td>4</td><td>" + obj.gebruiker4 + "</td><td>" + obj.score4 + "</td>");
        }

    });
    
});