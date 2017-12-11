var hasSuccessClass = "has-success";
var hasErrorClass = "has-error";
var placeholderHasErrorClass = "wrong-field";

var errorEmptyEmail = "Email can not be empty!";
var errorInvalidEmail = "* Email is invalid! Example: example@service.domen";
var errorEmptyPassword = "Password can not be empty!";
var errorSamePasswords = "* New and old passwords are same!";

var placeholderEmail = "Enter email here";
var placeholderPassword = "Enter password here";

var messageDataChangedSuccessfully = "Data has been changed successfully.";
var messagePostDeletedSuccessfully = "Post has been deleted successfully.";

function validateEmail(email) {
    var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (email === '') return true;
    else return pattern.test(email);
}
function formFilledCorrectly() {
    return ($("#div-email").hasClass(hasSuccessClass) || !$("#div-email").hasClass(hasErrorClass)) &&
        $("#div-old-password").hasClass(hasSuccessClass) &&
        $("#div-new-password").hasClass(hasSuccessClass);
}
function enableCompleteAction(isFilledCorrectly) {
    if (isFilledCorrectly) {
        $('#btn-change').attr('disabled',false);
    } else {
        $('#btn-change').attr('disabled',true);
    }
}

$(document).ready(function () {
    if (localStorage.getItem(messageDataChangedSuccessfully) == 'true') {
        localStorage.setItem(messageDataChangedSuccessfully, false);
        $.notify(messageDataChangedSuccessfully, {
            className: 'info',
            globalPosition: 'top right'
        });
    }

    if (localStorage.getItem(messagePostDeletedSuccessfully) == 'true') {
        localStorage.setItem(messagePostDeletedSuccessfully, false);
        $.notify(messagePostDeletedSuccessfully, {
            className: 'info',
            globalPosition: 'top right'
        });
    }

    enableCompleteAction(formFilledCorrectly());

    // email field validation
    $("#email").focusout(function(){
        if (!validateEmail($("#email").val())) {
            $("#div-email").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#email").addClass(placeholderHasErrorClass);
            $("#error-email-invalid").text(errorInvalidEmail);
        }
        else {
            if ($(this).val() !== '') {
                $("#div-email").removeClass(hasErrorClass).addClass(hasSuccessClass);
                $("#email").attr("placeholder", placeholderEmail);
            }
            $("#email").removeClass(placeholderHasErrorClass);
            $("#error-email-invalid").text("");
        }
        enableCompleteAction(formFilledCorrectly());
    });
    $("#old-password").focusout(function(){
        if ($(this).val() === '') {
            $("#div-old-password").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#old-password").attr("placeholder", errorEmptyPassword);
            $("#old-password").addClass(placeholderHasErrorClass);
        }
        else {
            $("#div-old-password").removeClass(hasErrorClass).addClass(hasSuccessClass);
            $("#old-password").attr("placeholder", placeholderPassword);
            $("#old-password").removeClass(placeholderHasErrorClass);
        }
        enableCompleteAction(formFilledCorrectly());
    });
    $("#new-password").focusout(function(){
        if ($(this).val() === '') {
            $("#div-new-password").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#new-password").attr("placeholder", errorEmptyPassword);
            $("#new-password").addClass(placeholderHasErrorClass);
        }
        else {
            $("#div-new-password").removeClass(hasErrorClass).addClass(hasSuccessClass);
            $("#new-password").attr("placeholder", placeholderPassword);
            $("#new-password").removeClass(placeholderHasErrorClass);
        }
        if ($(this).val() === $("#old-password").val() && $(this).val() !== '') {
            $("#div-new-password").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#div-old-password").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#new-password").attr("placeholder", errorSamePasswords);
            $("#new-password").addClass(placeholderHasErrorClass);
            $("#error-passwords-same").text(errorSamePasswords);
        } else if ($(this).val() !== $("#old-password").val()) {
            $("#div-new-password").removeClass(hasErrorClass).addClass(hasSuccessClass);
            $("#div-old-password").removeClass(hasErrorClass).addClass(hasSuccessClass);
            $("#new-password").attr("placeholder", placeholderPassword);
            $("#new-password").removeClass(placeholderHasErrorClass);
            $("#error-passwords-same").text("");
        }
        enableCompleteAction(formFilledCorrectly());
    });

    // configure dropdown menu
    $('.dropdown-toggle').dropdown();

    $(".dropdown-toggle").on("hide.bs.dropdown", function () {
        $(".caret").removeClass("caret-up");
    });
    $(".dropdown-toggle").on("show.bs.dropdown", function () {
        $(".caret").addClass("caret-up");
        $(".fa-gear").addClass("fa-spin");
    });
    $(".dropdown-toggle").hover(
        function () {
            $(".fa-gear").addClass("fa-spin");
        }, function () {
            $(".fa-gear").removeClass("fa-spin");
        }
    )

    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });
    $('#change-profile').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });

    // calling ChangeUserServlet to handle action
    $('#btn-change').on('click', function (e) {
        e.preventDefault();
        $("#email").val("default");
        $.ajax({
            url: "change",
            method: "POST",
            data: {
                changes: $("#change-profile-form").serialize()
            },
            success: function (status) {
                $("#email").val("");
                if (status === "SUCCESS") {
                    $('#change-profile').modal('hide');
                    localStorage.setItem(messageDataChangedSuccessfully, true);
                    location.reload();
                } else if (status === "BAD_EMAIL") {
                    $.notify("Email has already been using.", {
                        className:'error',
                        globalPosition: 'top right'
                    });
                } else if (status === "BAD_OLD_PASSWORD") {
                    $.notify("Your current password was entered incorrectly.", {
                        className:'error',
                        globalPosition: 'top right'
                    });
                }
            }
        });
    });

    // delete post
    $(".delete-post").on('click', function (e) {
        e.preventDefault();
        var postId = $(this).attr('id');
        $.ajax({
            url: "delete_post",
            method: "POST",
            data: {
                postId: postId
            },
            success: function (status) {
                if (status === "SUCCESS") {
                    $('#add-post').modal('hide');
                    localStorage.setItem(messagePostDeletedSuccessfully, true);
                    location.reload();
                } else if (status === "FAILURE") {
                    $.notify("Post have not deleted.", {
                        className:'error',
                        globalPosition: 'top right'
                    });
                }
            }
        });
    })
});