$(document).ready(function () {

    toastr.options.timeOut = 2000; // 2.0s
    let deleteStudentId = -1;
    let updateStudentId = -1;

    $.validator.addMethod("vietnamesePhone", function (value, element) {
        return this.optional(element) || /^0[0-9]{9}/i.test(value);
    }, "SDT phải là dãy 10 ký tụ số bắt đầu bằng số 0(mặc định");

    $.validator.addMethod("birthday", function (value, element) {
        const birthYear = new Date(value).getFullYear();
        const currentYear = new Date().getFullYear();
        return this.optional(element) || currentYear - birthYear > 18;

    }, "Sinh viên phải quá 18 tuổi");

    $.validator.addMethod("pastDate", function (value, element) {
        const birthday = new Date(value);
        birthday.setHours(0);
        birthday.setMinutes(0);
        birthday.setSeconds(0);

        const today = new Date();
        today.setSeconds(0);
        today.setMinutes(0);
        today.setHours(0);

        return this.optional(element) || (birthday - today < 0) || birthday === today;

    }, "Sinh viên phải sinh ở quá khứ");

    const validator = $("#create-student-form").validate({

        onfocusout: false,
        onkeyup: false,
        onclick: false,

        rules: {
            "name": {
                required: true,
                maxlength: 255
            },
            "address": {
                required: true,
                maxlength: 255
            },
            "phone": {
                required: true,
                vietnamesePhone: true,
                maxlength: 10
            },
            "className": {
                required: true,
                maxlength: 10
            },
            "birthday": {
                required: true,
                birthday: true,
                pastDate: true
            }
        },
        messages: {
            "name": {
                required: "Student name is required",
                maxlength: "Student name must be less than 255 characters"
            },
            "address": {
                required: "Student address is required",
                maxlength: "Student address must be less than 255 characters"
            },
            "phone": {
                required: "Student phone is required",
                maxlength: "Student phone must be less than 10 characters",
                vietnamesePhone: "SDT phải là dãy 10 ký tụ số bắt đầu bằng số 0"
            },
            "className": {
                required: "ClassName is required",
                maxlength: "ClassName must be less than 10 characters"
            },
            "birthday": {
                required: "Birthday is required",
                birthday: "Sinh viên phải quá 18 tuổi",
                pastDate: "Sinh viên phải sinh ở quá khứ"
            }
        },
    });

    $(".create-student-btn").click(function () {
        $("#student-creation-modal").modal("show");
    });

    $("#save-student-btn").click(function () {
        const isValidForm = $("#create-student-form").valid();
        if (!isValidForm) {
            return;
        }
        // const name = $("#name").val();
        // const address = $("#address").val();

        const formData = $('#create-student-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }

        // call api lên backend
        $.ajax({
            url: "/students",
            type: "POST",
            data: JSON.stringify(requestBody), // dữ liệu được gửi vào trong body của HTTP message lên backend
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Create a new student successfully");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                console.log(err);
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại");
            }
        });

    });

    $(".delete-student-btn").click(function (event) {
        deleteStudentId = parseInt($(event.currentTarget).attr("student-id"));
        $("#student-delete-modal").modal("show");
    });

    $("#delete-student-btn").click(function () {
        $.ajax({
            url: "/students/" + deleteStudentId,
            type: "DELETE",
            success: function (data) {
                toastr.success("Xóa sinh viên thành công");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.success("Xóa sinh viên không thành công, vui lòng thử lại")
            }
        })
    });

    $(".update-student-modal-open").click(function () {
        //call api len java va lay du lieu
        updateStudentId = parseInt($(event.currentTarget).attr("student-id"));
        $.ajax({
            url: "/students/" + updateStudentId,
            type: "GET",
            success: function (data) {
                console.log(data);

                //đổ dữ liệu vào form -- jQuery
                $("#update-student-form #name").val(data.name);
                $("#update-student-form #address").val(data.address);
                $("#update-student-form #phone").val(data.phone);
                $("#update-student-form #className").val(data.className);

                $("#student-update-modal").modal('show');
            },
            error: function (err) {
                console.error(err);
                toastr.success("Đã có lỗi xảy ra");
            }
        })

    });

    $("#update-student-btn").click(function () {
        const formData = $('#update-student-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }

        // call api lên backend
        $.ajax({
            url: "/students/" + updateStudentId,
            type: "PUT",
            data: JSON.stringify(requestBody), // dữ liệu được gửi vào trong body của HTTP message lên backend
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Update a student successfully");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                console.log(err);
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại");
            }
        });

    });

    $('#student-creation-modal').on('hidden.bs.modal', function () {
        $('#create-student-form').trigger("reset");
        validator.resetForm();
    });

    $("#page-size").change(function (event){
       const pageSize = event.target.value;
       window.location.href = '/students?pageSize=' + pageSize + '&currentPage=0';
    });
});