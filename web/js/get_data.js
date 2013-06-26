function mm_puntos_p(callback) {
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for';
    }
    var url = 'http://localhost:8080/map21/SListarPuntosPendientes?callback=callback';
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });

    function response(x) {

        var features = [];
        console.log(x);

        for (var i = 0; i < x.length; i++) {
            //Properties
            console.log(x[i].tipo);
            x[i]['properties'] = {};
            x[i].properties['marker-size'] = 'small';
            x[i].properties['marker-color'] = '#D64937';
            
            if (x[i].tipo.replace(/\s/g, "") == 'w') {
                x[i].url_img = 'imagenesDB/' + x[i].url_img;
            }
            features.push(x[i]);
 //console.log(x[i].tipo);
        }
        //console.log(features);
        return callback(features);
    }
}
;

function mm_puntos_a(callback) {
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for';
    }
    var url = 'http://localhost:8080/map21/SListarPuntosAtendidos?callback=callback';
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });
    function response(x) {
        console.log(x)
        var features = [];

        for (var i = 0; i < x.length; i++) {
            //Properties
            console.log(x[i].tipo);
            x[i]['properties'] = {};
            x[i].properties['marker-size'] = 'small';
            x[i].properties['marker-color'] = '#659E51';
            if (x[i].tipo.replace(/\s/g, "") == 'w') {
                x[i].url_img = 'imagenesDB/' + x[i].url_img;
            }
            features.push(x[i]);
        }
        //console.log(features);
        return callback(features);
    }
}
;


function mm_puntos(callback) {
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for';
    }
    var url = 'http://localhost:8080/map21/SListarPuntos?callback=callback';
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });
    function response(x) {
        console.log(x)
        var features = [];

        for (var i = 0; i < x.length; i++) {
            //Properties
            x[i]['properties'] = {};
            x[i].properties['marker-size'] = 'small';
            if (x[i].estado) {
                x[i].properties['marker-color'] = '#E55C3C';
            } else {
                x[i].properties['marker-color'] = '#659E51';
            }

            if (x[i].tipo = 'w') {
                x[i].url_img = 'imagenesDB/' + x[i].url_img;
            }
            features.push(x[i]);
        }
        //console.log(features);
        return callback(features);
    }
}
;

function mm_date(callback) {
    var url = 'http://localhost:8080/map21/SListarDate?callback=callback';
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });
    function response(x) {
        return callback(x);
    }
}
;