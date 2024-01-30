// const mealsAjaxUrl = "profile/meals";
//
// var ctx = {
//     ajaxUrl: mealsAjaxUrl
// };
//
// $(function () {
//     ctx = {
//         ajaxUrl: "profile/meals",
//         datatableApi: $("#datatable").DataTable({
//             "paging": false,
//             "info": true,
//             "columns": [
//                 {
//                     "data": "dateTime"
//                 },
//                 {
//                     "data": "description"
//                 },
//                 {
//                     "data": "calories"
//                 },
//                 {
//                     "defaultContent": "Edit",
//                     "orderable": false
//                 },
//                 {
//                     "defaultContent": "Delete",
//                     "orderable": false
//                 }
//             ],
//             "order": [
//                 [
//                     0,
//                     "desc"
//                 ]
//             ]
//         }),
//         updateTable: updateFiltered
//     };
//     makeEditable();
// });
//
// function updateFiltered() {
//     $.ajax({
//         type: "GET",
//         url: "/profile/meals/filter",
//         data: $("#filter").serialize()
//     }).done(updateTableByData())
// }
//
// $(function () {
//     $("#refreshButton").click(function () {
//         $.ajax({
//             url: mealsAjaxUrl,
//             cache: false,
//             success: function (html) {
//                 $("#filter").html(html);
//             }
//         });
//     });
// });




const mealAjaxUrl = "profile/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: mealAjaxUrl + "filter",
            data: $("#filter").serialize()
        }).done(updateTableByData);
    }
};

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return date.replace('T',  '').substring(0, 16);
                        }
                        return date;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (!data.enabled){
                    $(row).attr("data-meal-excess", data.excess)
                }
            }
        })
    );
});