
google.load("visualization", "1", {
    packages: ["corechart"]
});

function draw_day(f) {
    //console.log(f);
    var rowArray = [];
    var num_puntos = 0;
    for (var i = 0; i < f.length; i++) {
        rowArray.push([f[i].day, f[i].num_puntos]);
        num_puntos += f[i].num_puntos;
    }
    ;

    $('#num_puntos').html(num_puntos);
    //console.log(rowArray);
   // drawChart();

    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Day');
        data.addColumn('number', 'Num de puntos registrados');
        data.addRows(rowArray);
        var chart = new google.visualization.AreaChart(document.getElementById('draw_day'));

        var options = {
            height: 300,
            title: 'Numero de Puntos de Registrados por Dia',
            titleTextStyle: {
                color: 'black',
                fontSize: 14},
            hAxis: {
                title: 'Dia',
                titleTextStyle: {
                    color: '#404040',
                    fontSize: 9
                },
                textStyle: {fontSize: 9}

            },
            vAxis: {
                title: 'Num de Puntos',
                titleTextStyle: {
                    color: '#404040',
                    fontSize: 9
                },
                textStyle: {fontSize: 9}
            },
            legend: 'none',/*
            chartArea: {
                left: 25,
                top: 60,
                width: "80%",
                height: "70%"
            },*/
            backgroundColor: 'transparent',
            colors: ['#E55C3C']
        };

        chart.draw(data, options);
    }
 
}
;

function  draw_month(f) {

    //console.log(f);
    var rowArray = [];
    for (var i = 0; i < f.length; i++) {
        rowArray.push([f[i].month, f[i].num_puntos]);
    }
    ;
    //console.log('por mes');
   // console.log(rowArray);

    drawChart();

    function drawChart() {


        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Mes');
        data.addColumn('number', 'Num Puntos por Mes');
        data.addRows(rowArray);
        var options = {
            //height: 200,
            title: 'Numero de Puntos  Registrados por Mes',
            titleTextStyle: {
                color: 'black',
                fontSize: 14},
            colors: ['#E55C3C'],
            legend: 'none',
            hAxis: {
                title: 'Mes',
                titleTextStyle: {
                    color: '#404040',
                    fontSize: 9
                },
                textStyle: {fontSize: 9}

            },
            vAxis: {
                title: 'Num de Puntos',
                titleTextStyle: {
                    color: '#404040',
                    fontSize: 9
                },
                textStyle: {fontSize: 9}
            },             
            chartArea: {
                left: 85,
                top: 25,
                width: "600px",
                height: "250px"
            }
        };
        var chart = new google.visualization.ColumnChart(document.getElementById('draw_month'));
        chart.draw(data, options);
    }
}
