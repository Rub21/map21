$(function() {

    map_pendientes();
    menu_pendientes();

    $('#button_submit').click(function(e) {
        e.preventDefault();
        var ajaxdata = $("#id_punto").val();
        console.log(ajaxdata);
        var value = 'id_punto=' + ajaxdata;

        $.ajax({
            url: "Servlet_Actualizar",
            //type: "post",
            data: value,
            cache: false,
            success: function(data) {
                $("#id_punto").val('');
                map_pendientes();
                $('#mensaje_alert').html('<div class="alert alert-success"> <strong>Eliminaición Satisfactoria!</strong> ...</div>');
            }
        });

    });


    $('#mySwitch input:checkbox').click(
            function(e) {
                alert($(this).is(':checked'))
            }
    );
    $('#uniqueID').click(function() {
        var state = ($('#uniqueID .b1').is('.btn-primary') ? 0 : 1);
        $('#uniqueID').attr('data-state', state);
        $('#uniqueID .b1').toggleClass('btn-primary');
        $('#uniqueID .b0').toggleClass('btn-primary');

        if (state === 1)
        {

            //alert('puntos Pendi');
            map_pendientes();
            menu_pendientes();

        } else {

            //alert('puntos aten');
            map_atendidos();
            menu_pendientes();
        }

    });

});


