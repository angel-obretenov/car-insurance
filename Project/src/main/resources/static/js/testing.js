$(document).ready(function () {
    $('#brand_id').change(
        function () {
            $.getJSON('/api/model/brandId/' + $(this).val(), {
                ajax: 'true'
            }, function (data) {
                var html = '<option value="">Select Model</option>';
                var len = data.length;
                for (var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].id + '">'
                        + data[i].name + ' Year: ' + data[i].year + '</option>';
                }
                html += '</option>';

                $('#model_id').html(html);
                $('#model_id').trigger('contentChanged');
            });
        });
    $('#brand_id').trigger('change');
});
// $(document).ready(function () {
//     const carDto = [];
//     $('#submit1').click(function () {
//         carDto.brandId = $('#brand_id').val();
//         carDto.modelId = $('#model_id').val();
//         carDto.cubicCapacity = $('#cubics').val();
//         carDto.registrationDate = $('#reg_date').val();
//         carDto.driversAge = $('#driver_age').val();
//
//         let carDtoObj = JSON.stringify(carDto);
//         $.ajax({
//             url: '/',
//             method: 'POST',
//             data: carDtoObj,
//             contentType: 'application/json; charset = urf-8',
//             success: function () {
//                 alert('Saved succsfly!');
//             },
//             error: function (error) {
//                 alert('something happened which i dont know what it is :) ' + error);
//             }
//         })
//     })
// })

// Fields verification, prevents pressing submit if not selected all

$('#simulate_form').on('submit', function (e) {
    let focusSet = false;
    //FOR BRAND SELECT
    if ($('#brand_id').val() === '-1') {
        if ($("#brand_id").parent().next(".validation").length == 0) {
            $("#brand_id").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select brand</div>");
        }
        e.preventDefault();
        $('#brand_id').focus();
        focusSet = true;
    } else {
        $("#brand_id").parent().next(".validation").remove();
    }
    //FOR MODEL SELECT
    if ($('#model_id').val() === '-1') {
        if ($("#model_id").parent().next(".validation").length == 0) {
            $("#model_id").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select model</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#model_id').focus();
        }
    } else {
        $("#model_id").parent().next(".validation").remove();
    }
    //FOR CUBICS
    if (!$('#cubics').val()) {
        if ($("#cubics").parent().next(".validation").length == 0) {
            $("#cubics").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please fill cubic capacity</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#cubics').focus();
        }
    } else {
        $("#cubics").parent().next(".validation").remove();
    }
    if ($('#cubics').val() > 1300) {
        if ($("#cubics").parent().next(".validation").length == 0) {
            $("#cubics").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Maximum cubic capacity is 1300!</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#cubics').focus();
        }
    } else {
        $("#cubics").parent().next(".validation").remove();
    }
    //FOR REGSITRATION DATE
    if (!$('#reg_date').val()) {
        if ($("#reg_date").parent().next(".validation").length == 0) {
            $("#reg_date").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select registraton date.</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#reg_date').focus();
        }
    } else {
        $("#reg_date").parent().next(".validation").remove();
    }
    //FOR DRIVERS AGE

    if (!$('#driver_age').val()) {
        if ($("#driver_age").parent().next(".validation").length == 0) {
            $("#driver_age").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please fill drivers age</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#driver_age').focus();
        }
    } else {
        $("#driver_age").parent().next(".validation").remove();
    }

    if ($('#driver_age').val() < 18 || $('#driver_age').val() > 65 ) {
        if ($("#driver_age").parent().next(".validation").length == 0) {
            $("#driver_age").parent().after("<div class='validation' style='color:red;margin-bottom: 20px;'>Drivers age should be between 18 and 65</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#driver_age').focus();
        }
    } else {
        $("#driver_age").parent().next(".validation").remove();
    }

})




