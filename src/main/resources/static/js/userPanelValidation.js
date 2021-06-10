var options = {
    'onKeyPress': function (cep, e, field, options) {
        var masks = ['00000', '0000Z'];
        mask = (cep == '0000') ? masks[1] : masks[0];
        $('#zipcode').mask(mask, options);
    },
    'clearIfNotMatch': true,
    'translation': {
        'Z': {
            pattern: /[1-9]/
        }
    }
};

$('#postalCode').mask("00-000", options);

$('#houseNumber').mask("000");

$(function () {
    $('#city').keydown(function (e) {
        if (e.shiftKey || e.ctrlKey || e.altKey) {
            e.preventDefault();
        } else {
            var key = e.keyCode;
            if (!((key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
                e.preventDefault();
            }
        }
    });
});
$(function () {
    $('#country').keydown(function (e) {
        if (e.shiftKey || e.ctrlKey || e.altKey) {
            e.preventDefault();
        } else {
            var key = e.keyCode;
            if (!((key == 8) || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))) {
                e.preventDefault();
            }
        }
    });
});