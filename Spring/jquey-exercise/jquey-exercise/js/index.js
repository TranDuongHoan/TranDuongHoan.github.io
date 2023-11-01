$(document).ready(function () {

    let checkedRows = [];
    let students = JSON.parse(data);

    function initData() {
        $("#btn-update").prop("disabled", true);
        $("#btn-delete").prop("disabled", true);

        renderTable();
    }

    function renderTable() {
        $("tbody").empty();
        for (let i = 0; i < students.length; i++) {
            const sinhVien = students[i];
            const row = "<tr><td><input type='checkbox' id=" + sinhVien.id + "></td><td>" + sinhVien.name + "</td><td>" + sinhVien.birthday + "</td><td>" + sinhVien.phone + "</td><td>" + sinhVien.hometown + "</td></tr>";
            $("tbody").append($(row));
        }
    }

    initData();

    $("input[type='checkbox']").change(function () {
        const studentId = $(this).attr("id");
        if ($(this).is(':checked')) {
            checkedRows.push(parseInt(studentId));
        } else {
            // const temp = [];
            // for (let i = 0; i < checkedRows.length; i++) {
            //     if (checkedRows[i] === studentId) {
            //         continue;
            //     }
            //     temp.push(checkedRows[i]);
            // }
            // checkedRows = temps;
            checkedRows = checkedRows.filter(row => row !== studentId);
        }

        if (checkedRows.length > 0) {
            $("#btn-update").prop("disabled", false);
            $("#btn-delete").prop("disabled", false);
        } else {
            $("#btn-update").prop("disabled", true);
            $("#btn-delete").prop("disabled", true);
        }
    });

    $("#btn-delete").click(function () {
        if (checkedRows.length < 1) {
            alert("Vui lòng chọn bản ghi trước khi xóa");
            return;
        }
        const confirmation = confirm("Bạn có chắc chắn muốn xóa dữ liệu vừa chọn?");
        if (!confirmation) {
            return;
        }
        students = students.filter(sv => !checkedRows.includes(sv.id));
        renderTable();
    });

    $("#btn-save").click(function () {
        const name = $("#name").val();
        const birthday = $("#birthday").val();
        const phone = $("#phone").val();
        const hometown = $("#hometown").val();

        // validate dữ liệu
        if (name === null || name === undefined || name.trim() === "") {
            $("#name-error").html("Tên bắt buộc nhập");
            return;
        }
        if (name.length > 100) {
            $("#name-error").html("Tên không vượt quá 100 ký tự");
            return;
        }

        // 1990-12-01 --> 01/12/1990
        const birthdayStrElements = birthday.split("-"); // ['1990', '12','01']

        students.push({
            id: students.length + 1,
            name,
            birthday: birthdayStrElements[birthdayStrElements.length - 1] + "/" + birthdayStrElements[1] + "/" + birthdayStrElements[0],
            phone,
            hometown
        });
        renderTable();
        resetForm();
        resetError();
        alert("Bạn đã thêm mới thành công!");
    });

    function resetForm() {
        $("#name").val("");
        $("#birthday").val("");
        $("#phone").val("");
        $("#hometown").val("");
    }

    function resetError() {
        $("#name-error").html("");
        $("#birthday").val("");
        $("#phone").val("");
        $("#hometown").val("");
    }

});