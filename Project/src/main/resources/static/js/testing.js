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



$(document).ready(function (){
    const carDto = {};
    $('#submit1').click(function (){
        carDto.brandId = $('#brand_id').val();
        carDto.modelId = $('#model_id').val();
        carDto.cubicCapacity = $('#cubics').val();
        carDto.registrationDate = $('#reg_date').val();
        carDto.driversAge = $('#driver_age').val();

        var carDtoObj = JSON.stringify(carDto);
        $.ajax({
            url: '/index',
            method: 'POST',
            data: carDtoObj,
            contentType: 'application/json; charset = urf-8',
            success: function (){
                alert('Saved succsfly!');
            },
            error: function (error){
                alert('something happened which i dont know what it is :) ' + error);
            }
        })
    })
})



// $.ajax({
//     url: '/index',
//     method: 'POST',
//     success:
//         function (data) {
//             console.log(data);
//         }
// });
