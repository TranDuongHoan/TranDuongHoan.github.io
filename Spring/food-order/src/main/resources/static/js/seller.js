$(document).ready(function () {
    toastr.options.timeOut = 2500; //2.5s

    let chosenFile = [];
    const defaultImg = "/images/default.png";

    //validate
    const validatorAdminSide = $("#seller-form").validate({
        onfocusout: false, //khi sự kiện này xảy ra thì không validate
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
            "image": {
                required: true,
            },
            "rating": {
                required:true
            }
        },
        messages: {
            "name": {
                required: "Name is required",
                maxlength: "Name must be less than 255 characters"
            },
            "address": {
                required: "Student address is required",
                maxlength: "Student address must be less than 255 characters"
            },
            "image": {
                required: "Image is required"
            },
            "rating": {
                required: "Rating is required"
            }
        }
    });

    $('#seller-img-show').click(() => {
        $('#seller-image').click();
    });

    //open modal to create a seller
    $('.create-seller-btn').click(function () {
        $('#seller-img-show').attr('src', defaultImg);
        $('#seller-modal #save-seller-btn').attr("action-type", "CREATE");
        $('#seller-modal').modal('show');
    });

    $('#seller-image').change(function (event) {
        const tempFiles = event.target.files;
        if (!tempFiles || tempFiles.length === 0) {
            return;
        }
        chosenFile = tempFiles[0];

        const imageBlob = new Blob([chosenFile], {type: chosenFile.type});
        const imageUrl = URL.createObjectURL(imageBlob);
        $('#seller-img-show').attr("src", imageUrl);
    });

    //open modal to update a seller
    $('.update-seller-btn').click(async function (event) {
        //call api lên java và lấy dữ liệu
        const updatesellerId = parseInt($(event.currentTarget).attr("seller-id"));
        let seller = null;
        await $.ajax({
            url: "/admin/sellers/" + updatesellerId,
            type: "GET",
            success: function (data) {
                seller = data;
            },
            error: function (err) {
                toastr.warning(data.responseJSON.error);
                toastr.warning("There have been errors, please try again!");
            }
        });

        if (!seller) {
            toastr.error("There have been errors, please try again!")
            return;
        }
        //đổ dữ liệu vào form
        $('#seller-form #name').val(seller.name);
        $('#seller-form #price').val(seller.price);
        $('#seller-form #description').val(seller.description);
        $('#seller-form #image').val(seller.image);

        $('#seller-modal #save-seller-btn').attr('action-type', "UPDATE");
        $('#seller-modal #save-seller-btn').attr("seller-id", updatesellerId);
        $('#seller-modal').modal("show");
    });

    //create or update student
    $('#save-seller-btn').click(function (event) {
        //validate
        const isValidForm = $('#seller-form').valid();
        if (!isValidForm) {
            return;
        }

        const actionType = $(event.currentTarget).attr("action-type");
        const sellerId = $(event.currentTarget).attr("seller-id");
        //lấy dữ liệu từ form
        const Data = $('#seller-form').serializeArray();
        if (!Data || Data.length === 0) {
            return;
        }
        //chuyển dữ liệu từ dạng object sang json
        const requestBody = {};
        for (let i = 0; i < Data.length; i++) {
            requestBody[Data[i].name] = Data[i].value;
        }
        const method = actionType === "CREATE" ? "POST" : "PUT";
        if (method === "PUT") {
            requestBody["id"] = sellerId;
        }
        const formData = new FormData();
        formData.append("image", chosenFile, chosenFile.name);
        formData.append("sellerRequest", JSON.stringify(requestBody));
        //call api lên backend
        $.ajax({
            url: "/admin/sellers",
            type: method,
            data: formData, //dữ liệu được gửi vào trong body của HTTP
            contentType: false, //NEEDED, DON'T OMIT THIS
            processData: false, //NEEDED, DON'T OMIT THIS
            success: function (data) {
                toastr.success((method === "CREATE" ? "Create" : "Update") + "a new seller successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("There have been errors, please try again!");
            }
        });
        $("#seller-modal #save-seller-btn").attr("action-type", "");
        $('#seller-modal #save-seller-btn').attr("seller-id", "");
    });

    //reset form
    $('#seller-modal').on('hidden.bs.modal', function () {
        $("#seller-modal #save-seller-btn").attr("action-type", "");
        $('#seller-modal #save-seller-btn').attr("seller-id", "");
        $('#seller-form').trigger("reset");
        $('#seller-form input').removeClass("error");
        validatorAdminSide.resetForm();
    });


    //set url mới khi thẻ select thay đổi
    $('#seller-page-size').change(function (event) {
        const pageSize = event.target.value;
        window.location.href = ('/admin?pageSize=' + pageSize + '&currentPage=0');
    });


});