/* 
 Made by Duncan
 */

$("document").ready(function () {
    //onclicks
    $("#2").click(function () {
        $(this).toggleClass("selectable");
        $("#3, #4").removeClass("selectable");
    });

    $("#3").click(function () {
        $(this).toggleClass("selectable");
        $("#2, #4").removeClass("selectable");
    });

    $("#4").click(function () {
        $(this).toggleClass("selectable");
        $("#3, #2").removeClass("selectable");
    });

    $(".selected").on("click", selecteerAantal);
    $(".selected").click(function () {

        var request = $.ajax({cache: false,
            dataType: "text",
            url: "/ProgrameerProjectDomnion/ServletGameplay",
            data: {operation: 'keuzeAantalSpelers',
                gekozenAantalSpelers: spelers
            }

        });

        request.done(function (data) {
            var obj = JSON.parse(data);

        });
    });
});





var selecteerAantal = function () {

    spelers = $(this).attr("id");
    console.log(spelers);
    spelers = parseInt(spelers);

};




