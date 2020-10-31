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
        }
    );
    $('#brand_id').trigger('change');
});

    $('.policy_picture').click(function () {
        let img = $(this).attr("src");
        let appear_image = "<img id='appear_image' onclick='closeImage()' src='" + img + "' >";
        appear_image = appear_image.concat("<div id='close_image'>Click on image to close</div>");
        $("body").append(appear_image);
        $('#appear_image').hide();
        $('#close_image').hide();
        $('#appear_image').show(1000)
        $('#close_image').show(1000)
    })
    function closeImage() {
        $('#appear_image').remove();
        $('#close_image').remove();
    }



// $('#search_input').change(function () {
//     let filter = $(this).val().toUpperCase();
//
// })

// $(document).ready(function () {
//     $('#service-nav').click(
//         function () {
//             if ($(""))
//         }
//     )
// })

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
        if ($("#brand_id").next(".validation").length === 0) {
            $("#brand_id").after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select brand</div>");
        }
        e.preventDefault();
        $('#brand_id').focus();
        focusSet = true;
    } else {
        $("#brand_id").next(".validation").remove();
    }
    //FOR MODEL SELECT
    if ($('#model_id').val() === -1 || $('#model_id').val() === null || $('#model_id').val() === '' || $('#model_id').val() === '-1') {
        if ($("#model_id").next(".validation").length === 0) {
            $("#model_id").after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select model</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#model_id').focus();
        }
    } else {
        $("#model_id").next(".validation").remove();
    }
    //FOR CUBICS
    if (!$('#cubics').val() || $('#cubics').val() === '') {
        if ($("#cubics").next(".validation").length === 0) {
            $("#cubics").after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please fill cubic capacity</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#cubics').focus();
        }
    } else {
        $("#cubics").next(".validation").remove();
    }
    if ($('#cubics').val() < 100 || $('#cubics').val() > 1300) {
        if ($("#cubics").next(".validation").length === 0) {
            $("#cubics").after("<div class='validation' style='color:red;margin-bottom: 20px;'>Maximum cubic capacity is 1300!</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#cubics').focus();
        }
    } else {
        $("#cubics").next(".validation").remove();
    }
    //FOR REGSITRATION DATE
    if (!$('#reg_date').val()) {
        if ($("#reg_date").next(".validation").length === 0) {
            $("#reg_date").after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please select registraton date.</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#reg_date').focus();
        }
    } else {
        $("#reg_date").next(".validation").remove();
    }
    //FOR DRIVERS AGE

    if (!$('#driver_age').val()) {
        if ($("#driver_age").next(".validation").length == 0) {
            $("#driver_age").after("<div class='validation' style='color:red;margin-bottom: 20px;'>Please fill drivers age</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#driver_age').focus();
        }
    } else {
        $("#driver_age").next(".validation").remove();
    }

    if ($('#driver_age').val() < 18 || $('#driver_age').val() > 65) {
        if ($("#driver_age").next(".validation").length == 0) {
            $("#driver_age").after("<div class='validation' style='color:red;margin-bottom: 20px;'>Drivers age should be between 18 and 65</div>");
        }
        e.preventDefault();
        if (!focusSet) {
            $('#driver_age').focus();
        }
    } else {
        $("#driver_age").next(".validation").remove();
    }
})


// $(document).ready(function () {
//
//     $("#submitButton").click(function (e) {
//         var formInvalid = false;
//         $('#simulate_form input').each(function () {
//             if ($(this).val() === '') {
//                 formInvalid = true;
//                 e.preventDefault();
//             }
//         });
//
//         if (formInvalid) {
//             $('#redirect_msg').show();
//
//         } else $('#redirect_msg').hide();
//     });
//
// });
