(function () {
    $.ajax({
        type: "GET",
        url: 'ProfeDiagramas',
        dataType: 'json',
        success: function (json) {
            $.each(json, function (index, element) {

            });
        }
    });
});