var hasSuccessClass = "has-success";
var hasErrorClass = "has-error";
var placeholderHasErrorClass = "wrong-field";

var errorEmptyEmail = "Email can not be empty!";
var errorInvalidEmail = "* Email is invalid! Example: example@service.domen";
var errorEmptyPassword = "Password can not be empty!";
var errorDifferentPasswords = "* Passwords are different!";
var errorEmailIsBusy = "* The email address you have used is already registered.";

var placeholderEmail = "Enter email here";
var placeholderPassword = "Enter password here";

function validateEmail(email) {
    var pattern = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(email);
}
function validatePasswords(password, passwordRepetition) {
    return password.valueOf() == passwordRepetition.valueOf();
}
function formFilledCorrectly() {
    return $("#div-email").hasClass(hasSuccessClass) &&
        $("#div-password").hasClass(hasSuccessClass) &&
        $("#div-password-repetition").hasClass(hasSuccessClass);
}
function enableCompleteAction(isFilledCorrectly) {
    if (isFilledCorrectly) {
        $('#btn-sign-up').attr('disabled',false);
    } else {
        $('#btn-sign-up').attr('disabled',true);
    }
}

$(document).ready(function(){
    enableCompleteAction(formFilledCorrectly());

    $("#email").focusout(function(){
        if (($(this).val() === '') || !validateEmail($("#email").val())) {
            $("#div-email").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#email").attr("placeholder", errorEmptyEmail);
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
            $("#password").attr("placeholder", errorEmptyPassword);
            $("#password").addClass(placeholderHasErrorClass);
        }
        else {
            $("#div-password").removeClass(hasErrorClass).addClass(hasSuccessClass);
            $("#password").attr("placeholder", placeholderPassword);
            $("#password").removeClass(placeholderHasErrorClass);
        }
        enableCompleteAction(formFilledCorrectly());
    });
    $("#password-repetition").focusout(function(){
        if ($(this).val() === '') {
            $("#div-password-repetition").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#password-repetition").attr("placeholder", errorEmptyPassword);
            $("#password-repetition").addClass(placeholderHasErrorClass);
        } else
        if (!validatePasswords($("#password").val(), $("#password-repetition").val())) {
            $("#div-password").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#div-password-repetition").removeClass(hasSuccessClass).addClass(hasErrorClass);
            $("#error-passwords-different").text(errorDifferentPasswords);
        }
        else {
            $("#div-password").removeClass(hasErrorClass).addClass(hasSuccessClass);
            $("#div-password-repetition").removeClass(hasErrorClass).addClass(hasSuccessClass);
            $("#password").attr("placeholder", placeholderPassword);
            $("#password").removeClass(placeholderHasErrorClass);
            $("#password-repetition").attr("placeholder", placeholderPassword);
            $("#password-repetition").removeClass(placeholderHasErrorClass);
            $("#error-passwords-different").text("");
        }
        enableCompleteAction(formFilledCorrectly());
    });

    $("#btn-sign-up").on("click", function () {
        $.ajax({
            url: "/registration",
            method: "POST",
            data: {
                email: $("#email").val(),
                password: $("#password").val(),
                gender: $("#gender option:selected").text(),
                country: $("#country option:selected").text()
            },
            success: function (result) {
                if (result !== "EMAILISBUSY") {
                    window.location = result;     // отправка GET-метода LoginServlet с параметром = id
                } else {
                    $("#div-email").removeClass(hasSuccessClass).addClass(hasErrorClass);
                    $("#error-email-is-busy").text(errorEmailIsBusy);
                }
            }
        });
    })
});