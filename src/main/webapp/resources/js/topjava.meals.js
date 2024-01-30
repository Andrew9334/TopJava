var mealsAjaxUrl = "profile/meals";

var ctx = {
    ajaxUrl: mealsAjaxUrl
};

$(function () {
    ctx = {
        ajaxUrl: "profile/meals",
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        }),
        updateTable: updateFiltered
    };
    makeEditable();
});

function updateFiltered() {
    $.ajax({
        type: "GET",
        url: "/profile/meals/filter",
        data: $("#filter").serialize()
    }).done(updateTableByData())
}

$(function () {
    $("#refreshButton").click(function () {
        $.ajax({
            url: mealsAjaxUrl,
            cache: false,
            success: function (html) {
                $("#filter").html(html);
            }
        });
    });
});