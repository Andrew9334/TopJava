var ctx;
var mealAjaxUrl = "profile/meals/";

function updateFiltered() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

$(function () {
    ctx = {
        ajaxUrl: mealAjaxUrl,
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


// https://ru.stackoverflow.com/questions/382620/%D0%9E%D1%87%D0%B8%D1%81%D1%82%D0%BA%D0%B0-%D1%84%D0%BE%D1%80%D0%BC%D1%8B-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-js
function clearFilter() {
    document.getElementById('filter').reset();
    $.get(mealAjaxUrl, updateTableByData);
}