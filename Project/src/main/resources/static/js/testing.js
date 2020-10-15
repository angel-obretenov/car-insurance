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
// $(function () {
//     $(`#brand_id`).on('change', function (){
//         let val = $(this).val();
//         let sub = $('#model_id');
//         $('option', sub).filter(function () {
//             if ($(this).attr('data-brandid') == val
//                 || $(this).attr('value') === -1){
//                 $(this).show();
//             } else {
//                 $(this).hide();
//             }
//         });
//     });
//     $('#brand_id').trigger('change');
// })

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


// $(document).ready(function (){
//     const carDto = {};
//     $('#submit1').click(function (){
//         carDto.brandId = $('#brand_id').val();
//         carDto.modelId = $('#model_id').val();
//         carDto.cubicCapacity = $('#cubics').val();
//         carDto.registrationDate = $('#reg_date').val();
//         carDto.driversAge = $('#driver_age').val();
//
//         var carDtoObj = JSON.stringify(carDto);
//         $.ajax({
//             url: '/index',
//             method: 'POST',
//             data: carDtoObj,
//             contentType: 'application/json; charset = urf-8',
//             success: function (){
//                 alert('Saved succsfly!');
//             },
//             error: function (error){
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
