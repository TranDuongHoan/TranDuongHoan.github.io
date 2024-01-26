$(document).ready(() => {

    toastr.options.timeOut = 2500; // 2.5s

    //hiển thị tên người dùng khi chỉnh sửa hồ sơ
    const userInfo = JSON.parse(localStorage.getItem('user-info'));
    const fullName = userInfo ? userInfo.fullName : null;

    if (fullName) {
        $('#profile-fullName').val(fullName);
    }

    // //thực hiện chức năng đổi mật khẩu
    // $.validator.addMethod("passwordFormat", function (value, element) {
    //     return this.optional(element) || /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{10,}$/i.test(value);
    // }, "Mật khẩu tối thiểu 10 ký tự, ít nhất một chữ cái, một số và một ký tự đặc biệt");

    //validate form đổi mật khẩu
    const changePasswordValidator = $('#change-password-form').validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            'oldPassword': {
                required: true
            },
            'newPassword': {
                required: true
                // passwordFormat: true
            },
            "renewPassword": {
                required: true,
                equalTo: '#newPassword'
            }
        },
        messages: {
            'oldPassword': {
                required: "Password is required",
            },
            'newPassword': {
                required: "New Password is required",
                // passwordFormat: "Password must be less than 10 characters"
            },
            "renewPassword": {
                required: "New Password is required",
                equalTo: "The re-entered password must match the new password"
            }
        }
    });

    //gọi api đổi mật khẩu
    $('#change-password-btn').click(async () => {
        //validate
        const isValidForm = $('#change-password-form').valid();
        if (!isValidForm) {
            return;
        }
        //lấy dữ liệu từ form
        const changePasswordData = $('#change-password-form').serializeArray();
        if (!changePasswordData || changePasswordData.length === 0) {
            return;
        }
        console.log(changePasswordData)
        //chuyển dữ liệu từ object sang json
        const requestBody = {};
        for (let i = 0; i < changePasswordData.length; i++) {
            requestBody[changePasswordData[i].name] = changePasswordData[i].value;
        }
        requestBody["email"] = email;
        console.log(requestBody)
        //call api lên backend
        await $.ajax({
            type: "PUT",
            url: "/profile/change_password",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(requestBody),
            success: function (data) {
                toastr.success("Thay đổi mật khẩu thành công!");
                console.log(data);
            },
            error: function (error) {
                toastr.error("Đã có lỗi xảy ra, vui lòng thử lại sau!");
                console.log(error);
            }
        });
    });


    //Quên mật khẩu
    //validate form quên mật khẩu
    const forgotPasswordValidator = $('#forgot-password-form').validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            'email': {
                required: true,
                maxlength: 50
            }
        },
        messages: {
            'email': {
                required: "Email is required",
                maxlength: "Email must be less than 50 characters"
            }
        }
    });

    //gọi api quên mật khẩu
    $('#forgot-password-btn').click(async () => {
        //validate
        const isValidForm = $('#forgot-password-form').valid();
        if (!isValidForm) {
            return;
        }
        //lấy dữ liệu từ form
        const emailData = $('#forgot-password-form').serializeArray();
        if (!emailData || emailData.length === 0) {
            return;
        }
        console.log(emailData)
        //chuyển dữ liệu từ object sang json
        const requestBody = {};
        for (let i = 0; i < emailData.length; i++) {
            requestBody[emailData[i].name] = emailData[i].value;
        }
        console.log(requestBody)
        //call api lên backend
        await $.ajax({
            type: "POST",
            url: "/profile/forgot_password",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(requestBody),
            success: function (data) {
                toastr.success("Vui lòng kiểm tra email để lấy OTP!");
                console.log(data);
                setTimeout(() => {
                    window.location.href = "http://localhost:8080/verification";
                }, 3000);
            },
            error: function (error) {
                toastr.error("Đã có lỗi xảy ra, vui lòng thử lại sau!");
                console.log(error);
            }
        });
    });

    //validate form quên mật khẩu
    const verificationValidator = $('#verification-form').validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            'otp': {
                required: true
            },
            'newPassword': {
                required: true
                // passwordFormat: true
            },
            "renewPassword": {
                required: true
                // equalTo: '#newPassword'
            }
        },
        messages: {
            'otp': {
                required: "OTP bắt buộc"
            },
            'newPassword': {
                required: "New Password is required",
                passwordFormat: "Password must be less than 10 characters"
            },
            "renewPassword": {
                required: "New Password is required"
                // equalTo: "The re-entered password must match the new password"
            }
        }
    });

    //gọi api quên mật khẩu
    $('#verification-btn').click(async () => {
        //validate
        const isValidForm = $('#verification-form').valid();
        if (!isValidForm) {
            return;
        }
        //lấy dữ liệu từ form
        const forgetPasswordData = $('#verification-form').serializeArray();
        if (!forgetPasswordData || forgetPasswordData.length === 0) {
            return;
        }

        //chuyển dữ liệu từ object sang json
        const requestBody = {};
        for (let i = 0; i < forgetPasswordData.length; i++) {
            requestBody[forgetPasswordData[i].name] = forgetPasswordData[i].value;
        }
        requestBody["otp"] = requestBody["otp-1"] + requestBody["otp-2"] +requestBody["otp-3"] +requestBody["otp-4"];


        //call api lên backend
        await $.ajax({
            type: "PUT",
            url: "/profile/verification",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(requestBody),
            success: function (data) {
                toastr.success("Đặt lại mật khẩu thành công!");
                setTimeout(() => {
                    window.location.href = "http://localhost:8080";
                }, 3000);
            },
            error: function (error) {
                toastr.error("Đã có lỗi xảy ra, vui lòng thử lại sau!");
            }
        });
    });

});