$(document).ready(function () {
    toastr.options.timeOut = 2000;

    $.validator.addMethod("emailFormat", function (value, element) {
        return this.optional(element) || /^[A-Za-z0-9+_.-]+@(.+)$/i.test(value);
    }, "Vui lòng nhập email hợp lệ");

    $.validator.addMethod("vietnamesePhone", function (value, element) {
        return this.optional(element) || /^0[0-9]{9}/i.test(value);
    }, "SDT phải là dãy 10 ký tụ số bắt đầu bằng số 0(mặc định");

    // $.validator.addMethod("passwordFormat", function (value, element) {
    //     return this.optional(element) || /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]$/i.test(value);
    // }, "Mật khẩu tối đa 10 ký tự, ít nhất một chữ cái, một số và một ký tự đặc biệt");

    const signupValidator = $('#sign-up-form').validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            'name': {
                required: true,
                maxlength: 100
            },
            "phone": {
                required: true,
                vietnamesePhone: true,
                maxlength: 10
            },
            'email': {
                required: true,
                emailFormat: true,
                maxlength: 50
            },
            'password': {
                required: true,
                // passwordFormat: true
            }
        },
        messages: {
            'userName': {
                required: "Username is required",
                maxlength: "Username must be less than 100 characters"
            },
            "phone": {
                required: "Mobie number is required",
                maxlength: "Mobie number must be less than 10 characters",
                vietnamesePhone: "Mobie number must be a sequence of 10 digits starting with 0"
            },
            'email': {
                required: "Email is required",
                emailFormat: "Please enter a valid email address",
                maxlength: "Email must be less than 50 characters"
            },
            'password': {
                required: "Password is required"
            }
        }
    });


    $('#sign-up-btn').click(function () {

        const isValidForm = $('#sign-up-form').valid();
        if (!isValidForm) {
            return;
        }

        const signupData = $('#sign-up-form').serializeArray();
        if (!signupData || signupData.length === 0) {
            return;
        }

        const requestBody = {};
        for (let i = 0; i < signupData.length; i++) {
            requestBody[signupData[i].name] = signupData[i].value;
        }




        $.ajax({
            url: "/authentication/signup",
            type: "POST",
            data: JSON.stringify(requestBody),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Đăng ký thành công");
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



    const loginValidator = $('#log-in-form').validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            "phone": {
                required: true,
                vietnamesePhone: true,
                maxlength: 10
            },
            'password': {
                required: true,
                // passwordFormat: true,
            }
        },
        messages: {
            "phone": {
                required: "Mobie number is required",
                maxlength: "Mobie number must be less than 10 characters",
                vietnamesePhone: "Mobie number must be a sequence of 10 digits starting with 0"
            },
            'password': {
                required: "Password is required"
                // passwordFormat: "Password must be less than 10 characters"
            }
        }
    });


    $('#log-in-btn').click(async function (event) {

        const isValidForm = $('#log-in-form').valid();
        if (!isValidForm) {
            return;
        }

        const loginData = $('#log-in-form').serializeArray();
        if (!loginData || loginData.length === 0) {
            return;
        }

        const requestBody = {};
        for (let i = 0; i < loginData.length; i++) {
            requestBody[loginData[i].name] = loginData[i].value;
        }

        await $.ajax({
            type: "POST",
            url: "/authentication/login",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(requestBody),
            success: function (data) {
                toastr.success("Đăng nhập thành công!");
                localStorage.setItem("access-token", data.jwt);
                localStorage.setItem("refresh-token", data.refreshToken);
                const userInfo = {
                    roles: data.roles,
                    phone: data.phone,
                    email: data.email,
                    userName: data.userName,
                    avatar: data.avatar
                };
                localStorage.setItem("user-info", JSON.stringify(userInfo));
                setTimeout(() => {
                    if (data.roles == 'ADMIN') {
                        window.location.href = "http://localhost:8080/courses/analysis";
                    } else {
                        window.location.href = "http://localhost:8080";
                    }
                }, 1500);
            },
            error: function (error) {
                toastr.error("Đã có lỗi xảy ra, vui lòng thử lại sau!");
                console.log(error);
            }
        });

    });





})