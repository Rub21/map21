function mm_puntos_delincuencia(callback) {
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for';
    }
    var url = 'http://192.168.1.52:8080/map21/SListPuntos_Delincuencia?callback=callback';
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });

    function response(x) {
        console.log(x)

        for (var i = 0; i < x.length; i++) {
            //Properties
            x[i]['properties'] = {};
            x[i].properties['marker-size'] = 'small';
            if (x[i].estado) {
                x[i].properties['marker-color'] = '#E55C3C';
            } else {
                x[i].properties['marker-color'] = '#659E51';
            }

            if (x[i].img.replace(/\s/g, "") != 'null') {

                if (x[i].reg_de.replace(/\s/g, "") == 'w') {
                    x[i].img = '../imagenesDB/' + x[i].img;
                }

            }


            features.push(x[i]);
        }
        //console.log(features);
        return callback(features);
    }
}
;


function mm_date(callback) {
    var url = 'http://localhost:8080/map21/SListar_Date_Delincuencia?callback=callback';
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


function mm_date_day_delincuencia(callback) {

    var url = 'http://localhost:8080/map21/SListar_Date_Delincuencia_Day?callback=callback';
    //console.log(url);
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });

    function response(x) {
        console.log("mm_date_day_delincuencia");
        console.log(x);
        return callback(x);
    }
    ;
}
;


function mm_date_month_delincuencia(callback) {

    var url = 'http://localhost:8080/map21/SListar_Date_Delincuencia_Month?callback=callback';
    //console.log(url);
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });

    function response(x) {
        console.log("mm_date_month_delincuencia");
        console.log(x);
        return callback(x);
    }
    ;
}
;

