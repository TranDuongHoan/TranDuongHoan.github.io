$(document).ready(function () {

    toastr.options.timeOut = 2000; // 2.0s
    let deleteStudentId = -1;

    $(".create-student-btn").click(function () {
        $("#student-creation-modal").modal("show");
    });

    $("#save-student-btn").click(function () {
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

    $(".delete-student-btn").click(function (event){
        deleteStudentId = parseInt($(event.currentTarget).attr("student-id"));
        $("#student-delete-modal").modal("show");
    });

    $("#delete-student-btn").click(function (){
        $.ajax({
            url: "/students/" + deleteStudentId,
            type: "DELETE",
            success:function (data){
                toastr.success("Xóa sinh viên thành công");
                setTimeout(()=>{
                    location.reload();
                }, 1000);
            },
            error: function (err){
                toastr.success("Xóa sinh viên không thành công, vui lòng thử lại")
            }
        })
    });

    $(".update-student-modal-open").click(function (){
        //call api len java va lay du lieu
        const updateStudentId = parseInt($(event.currentTarget).attr("student-id"));
        $.ajax({
            url: "/students/" + updateStudentId,
            type: "GET",
            success:function (data){
                console.log(data);
            },
            error: function (err){
                console.error(err);
                toastr.success("Đã có lỗi xảy ra");
            }
        })
        $("#student-update-modal").modal('show');
    });

});