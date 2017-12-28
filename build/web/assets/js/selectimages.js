/* 
 * Made and owned by Egon De Baene.
 */

//initilize
$("document").ready(function () {
    //onclicks
    
    var selecteer = $(".slide");
    selecteer.on("click", dropImage);
    
    $(".next").click(function () {
        if ($("li.inline input[type=radio]:checked").size() > 0) {
            var request = $.ajax({cache: false,
                dataType: "text",
                url: "/ProgrameerProjectDomnion/ServletGameplay",
                data: {operation: 'selecteerKaarten',
                    doorTegevenDeck: $("li.inline input[type=radio]:checked").attr("id")}});
        } else {
            if (geselecteerdeKaarten.length < 10) {
                alert("Je moet 10 kaarten selecteren");
            }else{
                var request = $.ajax({cache: false,
                    dataType: "text",
                    url: "/ProgrameerProjectDomnion/ServletGameplay",
                    data: {operation: 'selecteerKaarten',
                        doorTeSturenKaart1: $("div.geselecteerdeFotos div:nth-child(1)").attr("id"), // todo: niet op id, want veranderbaar
                        doorTeSturenKaart2: $("div.geselecteerdeFotos div:nth-child(2)").attr("id"),
                        doorTeSturenKaart3: $("div.geselecteerdeFotos div:nth-child(3)").attr("id"),
                        doorTeSturenKaart4: $("div.geselecteerdeFotos div:nth-child(4)").attr("id"),
                        doorTeSturenKaart5: $("div.geselecteerdeFotos div:nth-child(5)").attr("id"),
                        doorTeSturenKaart6: $("div.geselecteerdeFotos div:nth-child(6)").attr("id"),
                        doorTeSturenKaart7: $("div.geselecteerdeFotos div:nth-child(7)").attr("id"),
                        doorTeSturenKaart8: $("div.geselecteerdeFotos div:nth-child(8)").attr("id"),
                        doorTeSturenKaart9: $("div.geselecteerdeFotos div:nth-child(9)").attr("id"),
                        doorTeSturenKaart10: $("div.geselecteerdeFotos div:nth-child(10)").attr("id")
                    }

                });
            }
        }

        request.done(function () {
            window.location.href = "gameplay.html";
        });
        request.fail(function (jqXHR, textStatus) {
            alert(jqXHR.status + ' ' + textStatus);
        });
    });


}
);
var i = 0;
var geselecteerdeKaarten = [];

var dropImage = function () {
    $("li.inline input[type=radio]:checked").attr('checked',false);
       
    i++;
    if (i > 10) {
        window.alert("Er zijn reeds 10 kaarten geselecteerd!");
    } else {

        naamFoto = $(this).attr("id");
        $('.geselecteerdeFotos').append("<div class='selected' id='" + naamFoto + "' style='float: left; list-style: none; position: relative; width: 155.4px; margin-right: 50px;'>,<img src='assets/images/kaarten/" + naamFoto + ".jpg'></div>");
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
