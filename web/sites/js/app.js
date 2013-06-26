var map_id = 'examples.map-vyofok3q',
//var map_id = 'examples.map-y7l23tes',
        markerLayer,
        features = [],
        features_summary,
        interaction,
        map = mapbox.map('map');

map.addLayer(mapbox.layer().id(map_id));

//mm_puntos_pen(mapData);
mm_puntos_delincuencia(mapData);
menu_pendientes();


window.setTimeout(function() {
    mm_date_day_delincuencia(draw_day);
    mm_date_month_delincuencia(draw_month);
}, 1000);



map.centerzoom({
    lat: -9.455,
    lon: -75.127
}, 6);

map.setZoomRange(0, 18);

function mapData(f) {
    features = f;
    console.log(f);
    markerLayer = mapbox.markers.layer().features(features);
    markerLayer.factory(function(m) {


        //var elem = mapbox.markers.simplestyle_factory(m);
        // var elem = simplestyle_factory_rub(m); 

        var elem = document.createElement('div');
        elem.className = 'mmg mmg-first';

        MM.addEvent(elem, 'click', function(e) {
            map.ease.location({
                lat: m.geometry.coordinates[1],
                lon: m.geometry.coordinates[0]
            }).zoom(map.zoom()).optimal();
        });
        return elem;
    });

    interaction = mapbox.markers.interaction(markerLayer);
    map.interaction.auto();
    map.addLayer(markerLayer);
    map.ui.zoomer.add();
    map.ui.zoombox.add();
    map.ui.hash.add();

    interaction = mapbox.markers.interaction(markerLayer);
    map.addLayer(markerLayer);
    interaction.formatter(function(feature) {
        var o = '<h4 class="popover-geo-title">' + feature.tipo + '</h4>' +
                '<div class="popover-geo-fecha">' + feature.fecha + '</div>' +
                '<p>' + feature.descripcion + '</p>';

        var img = '<img style="height: auto; width:100px;   margin-right: 25px;" src="' + feature.img + '">';

        var a_button = '<a  role="button" class="btn btn-success"  href="#detail" onclick="call_detail(\'' + feature.idpunto + '\')"> Más Detalle</a>';

        if (feature.img.replace(/\s/g, "") === 'null') {

            return o + a_button;
        } else {

            return o + img + a_button;
        }
    });

    $('#map').removeClass('loading');

//Seleccion
    $('#layer').on('click', 'li', function(e) {
        alert('eee');
        var id = $(this).attr('id') + "";
        markerLayer.filter(function(features) {
            if (features.properties.from === id) {
                return true;
            }
        });
    });
}
;


function call_detail(id) {
    //console.log(id);
//
    $('.click').trigger('click');
    $('#popover').css({
        'width': '820px',
        'margin-top': '-5%',
        'margin-left': function() {
            return -($(this).width() / 2);
        }
    });
    var f = _.find(features, function(feature) {
        console.log(feature.idpunto);
        return feature.idpunto === id
    });
    console.log(f);

    $('.tipo').text(f.tipo);
    $('.hora').text(f.hora);
    $('.fecha').text(f.fecha);
    $('.usuario').text(f.usuario);
    $('.description').text(f.descripcion);

    $('.de_conocimiento').text(f.de_conocimiento);
    if (f.img.replace(/\s/g, "") != 'null') {
        $('.img').attr('src', f.img);
    }



    // $('#id_punto').val(f.idpunto);

}
;








$(document).on('ready', function() {

    $('#estadisticas').css({
        'width': '90%',
        'margin-top': '-5%',
        'margin-left': function() {
            return -($(this).width() / 2);
        },
        'height': ' 95%',
        'text-align': 'center'

    });

    /* $('a[href="#estadisticas"]').click(function(e) {
     window.setTimeout(function() {
     mm_stadistic(stadistis_p_pen);
     mm_stadistic_month(stadistic_p_pen_month);
     }, 1000);
     
     });*/





    $('.select_pentiente').click(function() {
        var id = $(this).attr('id');
        markerLayer.filter(function(features) {
            //console.log(features.fecha.replace(/\s/g, "") + ' - ' + id);
            if (features.fecha.replace(/\s/g, "") === id) {
                return true;
            }
        });
    });


    $('#slide-toggle').toggle(function() {
        $('.content_menu').hide(500);

        $('.slide-toggle').css('background-position', '5px 15px');

    }, function() {
        $('.content_menu').show(500);

        $('.slide-toggle').css('background-position', '-84px 15px');


    });



    $('.slide-toggle').click(function() {

        //alert('ff');

        $('#slide-toggle').show();
    });
});


simplestyle_factory_rub = function(feature) {
    var sizes = {
        small: [30, 50],
        medium: [30, 70],
        large: [35, 90]
    };
    var fp = feature.properties || {};
    var size = fp['marker-size'] || 'medium';
    var symbol = fp['marker-symbol'];
    var d = document.createElement('img');
    d.width = sizes[size][0];
    d.height = sizes[size][1];
    d.className = 'simplestyle-marker';
    d.alt = fp.title || '';
    d.src = 'https://dl.dropboxusercontent.com/u/43116811/icon-tur/peru.png';
    var ds = d.style;
    ds.position = 'absolute';
    ds.clip = 'rect(auto auto ' + (sizes[size][1] * 0.75) + 'px auto)';
    ds.marginTop = -((sizes[size][1]) / 2) + 'px';
    ds.marginLeft = -(sizes[size][0] / 2) + 'px';
    ds.cursor = 'pointer';
    ds.pointerEvents = 'all';
    return d;
};