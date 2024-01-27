const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
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
                    "asc"
                ]
            ]
        })
    );
});

$(function (checkbox, id) {
    var enable = checkbox.is(":checked");

    $.ajax({
        url: userAjaxUrl + id,
        type: "POST",
        data: "enable=" + enable
    }).done(function () {
        checkbox.closest("tr").attr("data-userEnable", enable);
        successNoty(enable ? "Enable" : "Disable");
    }).fail(function () {
        $(checkbox).prop("checked", !enable);
    });
});