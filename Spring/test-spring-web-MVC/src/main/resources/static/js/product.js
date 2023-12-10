$(document).ready(function () {

    toastr.options.timeOut = 2000; // 2.0s

    let updateProductId = -1;

    const validator = $("#create-product-form").validate({

        onfocusout: false,
        onkeyup: false,
        onclick: false,

        rules: {
            "name": {
                required: true,
                maxlength: 255
            },
            "price": {
                required: true,
                minLength:0
            },
            "description": {
                required: true,
                maxlength: 1000
            },
            "image": {
                required: true,
            }

        },
        messages: {
            "name": {
                required: "Product name is required",
                maxlength: "Product name must be less than 255 characters"
            },
            "price": {
                required: "Student address is required",
                minLength: "Positive numbers"
            },
            "description": {
                required: "Product name is required",
                maxlength: "Product name must be less than 1000 characters"
            },
            "image": {
                required: "image is required",
                maxlength: "image must be less than 10 characters"
            }

        },
    });

    $(".create-product-btn").click(function () {
        $("#product-creation-modal").modal("show");
    });

    $("#save-product-btn").click(function () {
        const isValidForm = $("#create-product-form").valid();
        if (!isValidForm) {
            return;
        }


        const formData = $('#create-product-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }


        $.ajax({
            url: "/admin",
            type: "POST",
            data: JSON.stringify(requestBody),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Create a new product successfully");
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



    $(".update-product-modal-open").click(function () {

        updateProductId = parseInt($(event.currentTarget).attr("product-id"));
        $.ajax({
            url: "/admin/" + updateProductId,
            type: "GET",
            success: function (data) {
                console.log(data);


                $("#update-product-form #name").val(data.name);
                $("#update-product-form #price").val(data.price);
                $("#update-product-form #description").val(data.description);
                $("#update-product-form #image").val(data.image);

                $("#product-update-modal").modal('show');
            },
            error: function (err) {
                console.error(err);
                toastr.success("Đã có lỗi xảy ra");
            }
        })

    });

    $("#update-product-btn").click(function () {
        const formData = $('#update-product-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }


        $.ajax({
            url: "/admin/" + updateProductId,
            type: "PUT",
            data: JSON.stringify(requestBody),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Update a product successfully");
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

    $('#product-creation-modal').on('hidden.bs.modal', function () {
        $('#create-product-form').trigger("reset");
        validator.resetForm();
    });
});