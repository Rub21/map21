
/*jQuery.validator.setDefaults({
 debug: true,
 success: "valid"
 });*/
$(document).ready(function() {
    $("#formulario").validate({
        rules: {
            usuario: {required: true, maxlength: 50},
            fecha: {required: true, },
            hora: {required: true, },        
            des: {required: true, maxlength: 100},       
            lat: {required: true, number: true},
            lon: {required: true, number: true}
            //imagen:{required: true}
        },
        messages: {
            usuario: "Por favor, ingrese el Usuario",
            fecha: "Campo requerido.",
            hora: "Campo requerido.",
            des: "Campo requerido,  Escriba menos de 100 caracteres.",         
            lat: "Por favor, ingrese numeros",
            lon: "Por favor, ingrese numeros"
            //imagen:"Campo Requerido"
        }

    });
});
