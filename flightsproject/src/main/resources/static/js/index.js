$(document).ready(function () {


    $('#userForm').submit(function (e) {
        e.preventDefault();

        var username = $('#username').val();

        $.ajax({
            method: 'GET',
            url: '/users/username/' + username
        }).done(function (data) {
            console.log(data);
            $('#greet').text('Hello ' + data.username);
        });


    });

    
    generateFlights();
});


function generateFlights() {
    $.ajax({
        method: 'GET',
        url: '/flights'
    }).done(function (data) {
        
        $.each(data, function (i, flight) {
            var $option = $("<option/>", {
                value: i,
                text: flight.destination
            });

            $('#flights').append($option);

        });
    })
}