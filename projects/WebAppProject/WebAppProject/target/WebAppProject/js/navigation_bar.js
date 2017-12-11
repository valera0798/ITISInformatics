var hasSuccessClass = "has-success";
var hasErrorClass = "has-error";
var placeholderHasErrorClass = "wrong-field";

var errorEmptyName = "Name can not be empty!";
var errorEmptyText = "Text can not be empty!";

var messagePostAddedSuccessfully = "Post has been added successfully.";

function formFilledCorrectly() {
    return ($("#div-name").hasClass(hasSuccessClass) &&
        $("#div-text").hasClass(hasSuccessClass));
}

function enableCompleteAction(isFilledCorrectly) {
    if (isFilledCorrectly) {
        $('#btn-add').attr('disabled',false);
    } else {
        $('#btn-add').attr('disabled',true);
    }
}

function validateFieldForEmptiness(field, container, errorMessage) {
    if (field.val() === '') {
        container.removeClass(hasSuccessClass).addClass(hasErrorClass);
        field.addClass(placeholderHasErrorClass);
        field.attr('placeholder', errorMessage);
    }
    else {
        container.removeClass(hasErrorClass).addClass(hasSuccessClass);
        field.removeClass(placeholderHasErrorClass);
        field.attr('placeholder', '');
    }
    enableCompleteAction(formFilledCorrectly());
}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}

$(document).ready(function () {
    // search
    $("#search-line").on('input', function () {
        $.ajax({
            url: "search",
            method: "GET",
            data: {
                keyWord: $("#search-line").val()
            },
            success: function (responseXml) {
                $("#posts-container").empty();
                $("#posts-container").html($(responseXml).find("data").html());
            }
        });
        // $.ajax({
        //     url: "/search",
        //     data: {
        //         keyWord: $(this).val()
        //     },
        //     success: function (responseJson) {
        //         var $container = $("#container");
        //         $container.empty();
        //         $.each(responseJson, function (index, post) {
        //             var block = $("<div></div>").addClass("top-margin");
        //             block.attr('id', post.id);
        //             var postCard = $("<div></div>").addClass("post-card");
        //                 var layout = $("<div></div>").addClass("col-md-8 col-md-offset-2");
        //                     var row = $("<div></div>").addClass("row");
        //                         var panel = $("<div></div>").addClass("panel panel-default");
        //                             var heading = $("<div></div>").addClass("panel-heading");
        //                                 var title = $("<h4></h4>").addClass("display-4").text(post.name);
        //                             var body = $("<div></div>").addClass("panel-body");
        //                                 var text = $("<p></p>").text(post.text);
        //                             var footer = $("<div></div>").addClass("panel-footer");
        //                                 var author = $("<a></a>").addClass("text-primary")
        //                                     .attr('href', "user_profile?id=" + post.owner.id)
        //                                     .text(post.owner.email);
        //             author.appendTo(footer);
        //             text.appendTo(body);
        //             title.appendTo(heading);
        //             panel
        //                 .append(heading)
        //                 .append(body)
        //                 .append(footer);
        //             panel
        //                 .appendTo(row)
        //                 .appendTo(layout)
        //                 .appendTo(postCard)
        //                 .appendTo(block)
        //                 .appendTo($container);
        //         })
        //     }
        // });
    });

    $("#search-line").trigger('input');

    // add
    if (localStorage.getItem(messagePostAddedSuccessfully) == 'true') {
        localStorage.setItem(messagePostAddedSuccessfully, false);
        $.notify(messagePostAddedSuccessfully, {
            className: 'info',
            globalPosition: 'top right'
        });
    }
    enableCompleteAction(formFilledCorrectly());

    $("#name").focusout(function(){
        validateFieldForEmptiness($("#name"), $("#div-name"), errorEmptyName);
    });
    $("#text").focusout(function(){
        validateFieldForEmptiness($("#text"), $("#div-text"), errorEmptyText);
    });

    $('#add-post').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });

    $('#btn-add').on('click', function (e) {
        e.preventDefault();
        $.ajax({
            url: "add",
            method: "POST",
            data: {
                name: $("#name").val(),
                text: $("#text").val(),
                userId: getUrlParameter("id")
            },
            success: function (status) {
                if (status === "SUCCESS") {
                    $('#add-post').modal('hide');
                    localStorage.setItem(messagePostAddedSuccessfully, true);
                    location.reload();
                } else if (status === "FAILURE") {
                    $.notify("Post with entered data can not be created.", {
                        className:'error',
                        globalPosition: 'top right'
                    });
                }
            }
        });
    });
});