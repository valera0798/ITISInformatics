var hasSuccessClass = "has-success";
var hasErrorClass = "has-error";
var placeholderHasErrorClass = "wrong-field";

var errorWrongEmail = "Email is wrong!";
var errorInvalidEmail = "* Email is invalid! Example: example@service.domen";
var errorWrongPassword = "Password is wrong!";
var errorWrongEmailPassword = "* The email or password is incorrect!";

var placeholderEmail = "Enter email here";
var placeholderPassword = "Enter password here";

function validateEmail(email) {
    var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(email);
}

function formFilledCorrectly() {
    return $("#div-email").hasClass(hasSuccessClass) &&
        $("#div-password").hasClass(hasSuccessClass);
}
function enableCompleteAction(isFilledCorrectly) {
    if (isFilledCorrectly) {
        $('#btn-sign-in').attr('disabled',false);
    } else {
        $('#btn-sign-in').attr('disabled',true);
    }
}

$(document).ready(function(){
    enableCompleteAction(formFilledCorrectly());

    $("#email").focusout(function(){
        if (($(this).val() === '') || !validateEmail($("#email").val())) {
            $("#div-email").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#email").attr("placeholder", errorWrongEmail);
            $("#email").addClass(placeholderHasErrorClass);
            if (($(this).val() !== '') && !validateEmail($("#email").val())) {
                $("#error-email-invalid").text(errorInvalidEmail);
            }
        }
        else {
            $("#div-email").removeClass(hasErrorClass).addClass(hasSuccessClass);
            $("#email").attr("placeholder", placeholderEmail);
            $("#email").removeClass(placeholderHasErrorClass);
            $("#error-email-invalid").text("");
        }
        enableCompleteAction(formFilledCorrectly());
    });
    $("#password").focusout(function(){
        if ($(this).val() === '') {
            $("#div-password").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#password").attr("placeholder", errorWrongPassword);
            $("#password").addClass(placeholderHasErrorClass);
        }
        else {
            $("#div-password").removeClass(hasErrorClass).addClass(hasSuccessClass);
            $("#password").attr("placeholder", placeholderPassword);
            $("#password").removeClass(placeholderHasErrorClass);
        }
        enableCompleteAction(formFilledCorrectly());

    });

    $("#btn-sign-in").on("click", function () { // при нажатии на кнопку, асинхронно проверить наличие пользователя в БД
        $.ajax({
            url: "/login",
            method: "POST",
            data: {
                email: $("#email").val(),
                password: $("#password").val()
            },
            success: function (result) {              // url новой страницы
                if (result !== "NONEXISTENT") {
                    window.location = result;     // отправка GET-метода LoginServlet с параметром = id
                } else {
                    $("#error-email-password-incorrect").text(errorWrongEmailPassword);
                }
            }
        });
    });
});