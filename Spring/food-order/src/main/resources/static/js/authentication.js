$(document).ready(function () {
    toastr.options.timeOut = 2000;

    $.validator.addMethod("vietnamesePhone", function (value, element) {
        return this.optional(element) || /^0[0-9]{9}/i.test(value);
    }, "SDT phải là dãy 10 ký tụ số bắt đầu bằng số 0(mặc định");

    $.validator.addMethod("passwordFormat", function (value, element) {
        return this.optional(element) || /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/i.test(value);
    }, "Mật khẩu tối thiểu 8 ký tự, ít nhất một chữ cái, một số và một ký tự đặc biệt");

    const signupValidator = $('#sign-up-form').validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            'userName': {
                required: true,
                maxlength: 100
            },
            "phone": {
                required: true,
                vietnamesePhone: true,
                maxlength: 10
            },
            'password': {
                required: true,
                passwordFormat: true
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
            'password': {
                required: "Password is required",
                passwordFormat: "Password must be less than 8 characters"
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

        const RequestBody = {};
        for (let i = 0; i < signupData.length; i++) {
            RequestBody[signupData[i].name] = signupData[i].value;
        }


        $.ajax({
            url: "/authentication/signup",
            type: "POST",
            data: JSON.stringify(requestBody), // dữ liệu được gửi vào trong body của HTTP message lên backend
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
});