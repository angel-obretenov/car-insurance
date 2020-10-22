// const $target = $('#email1');
// $target.click(function () {
//     alert("WTFXD")
// });

// $.post('/index',
//     { username: `tom` },
//     function (data) {
//         console.log(data);
//     }).fail(function () {
//     console.log(`idk man`);
// })

// $(document).ready(function () {
//     $(`#brand_id`).on('change', 'select', function () {
//         $.ajax({
//             url: '/api/model/brandId/',
//             type: 'GET',
//             data: {'id': $('#brand_id').val()},
//             success: function (data) {
//                 console.log(data);
//
//                 $.each(data, function (key, modelName) {
//                     var option = new Option(modelName, modelName);
//
//                     $(option).html(modelName);
//
//                     $('#model_id').append(option);
//                 })
//             }
//         })
//     });
//     $('#brand_id').trigger('change');
// })

// close to work, doesnt see the thymeleaf options tho

// $(document).ready(function($) {
//     alert('Jquery Working');
// });

// $(`#brand_id`).on('change', function (){
//     alert('test');
// })


// $('#brand_id').trigger('change');

// $(document).ready(function () {
//     $(`#brand_id`).on('change', function () {
//         let val = $(this).val();
//         console.log(val);
//         let sub = $('#model_id');
//
//         $('option', sub).filter(function () {
//             if ($(this).attr('data-brandid') === val
//                 || $(this).attr('value') === '-1'
//             ) {
//                 $(this).show();
//             } else {
//                 $(this).hide();
//             }
//         });
//     });
//     $('#brand_id').trigger('change');
// })

// $(document).ready(function () {
//     $(`#brand_id`).on('change', function () {
//         let val = $(this).val();
//
//         let json = [];
//         $.getJSON('/api/model/brandId/' + val, {
//             ajax: 'true',
//             function(data) {
//                 json = data;
//                 data.int.charAt(3)
//             }
//         });
//
//         $('#model_id')
//             .append($("<option></option>")
//                 .attr("value=", json[index]["id"])
//                 .text(json[index]["name"]));
//     })
// });

// $(document).ready(function() {
//     // $('#model_id').prop('disabled', true);
//     $('#brand_id')
//         .append($("<option></option>")
//             .attr("value", "")
//             .text(""));
//     $.each(json, function(index) {
//         let brandId = -1;
//         $('#brand_id').select(function () {
//             brandId = $('#brand_id').val();
//         })
//         var json = [];
//         $.ajax({
//             url: '/api/model/brandid/' + brandId,
//             method: 'GET',
//             success:
//                 function (data) {
//                    json = data;
//                 }
//         });
//
//         $('#brand_id')
//             .append($("<option></option>")
//                 .attr("value=", json[index]["value"])
//                 .text(json[index]["title"]));
//     });
//     $('#brand_id').change(function() {
//         process($('#model_id').children(":selected").html());
//     });
// });

//maybe idk
// $(document).ready(function () {
//     let brandId = -1;
//     $('#brand_id').select(function () {
//         brandId = $('#brand_id').val();
//     })
//
//     if (!(brandId === -1)) {
//         $('model_id').hide();
//         if ($('#model_id.option').getAttribute('data-brandid') === brandId) {
//         }
//         $('#model_id').getAttribute('data-brandid').show();
//     }
// });

// var callback = function (data) {
//
//     // Get select
//     var select = document.getElementById('mySelect');
//
//     // Add options
//     for (var i in data) {
//         $(select).append('<option value=' + data[i] + '>' + data[i] + '</option>');
//     }
//
//     // Set selected value
//     $(select).val(data[1]);
// }


// let $
// ('#model_id').getAttribute('data-brandid')

// })

// $(document).ready(function () {
//     $('#brand_id').change(function () {
//             let brandId = $(this).val();
//
//             $.ajax({
//                 url: '/api/model/brandId/' + brandId,
//                 type: "GET",
//                 datatype: "JSON",
//                 success: function (data) {
//                     let html = '<option value="">City</option>';
//                     let len = data.length;
//                     // alert(data)
//
//                     for (let i = 0; i < len; i++) {
//                         html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
//                     }
//
//                     html += '</option>'
//                     $('#model_id').html(html)
//                 }
//             })
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


// $.ajax({
//     url: '/index',
//     method: 'POST',
//     success:
//         function (data) {
//             console.log(data);
//         }
// });


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

})












