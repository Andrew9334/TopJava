const mealsAjaxUrl = "meals/";

const ctx = {
    ajaxUrl: mealsAjaxUrl
};

$(function () {
    makeEditable(
        $("#datatable".DataTable({
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
                "order":[
                    [
                        0,
                        "asc"
                    ]
                ]

            })
        )
    );
});

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