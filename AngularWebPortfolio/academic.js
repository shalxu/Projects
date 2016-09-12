app.controller('AcademicController', function ($animate,$scope) {

Highcharts.chart('container', {

                     chart: {
                        
            type: 'scatter',
            zoomType: 'xyz'
        },
        title: {
            text: "Shal's GPA Breakdown at University of Denver"
        },
        subtitle: {
            text: '2013-2017'
        },
        xAxis: {
            title: {
                enabled: true,
                text: 'Year'
            },
            startOnTick: true,
            endOnTick: true,
            tickInterval:1,
            showLastLabel: true
        },
        yAxis: {
            title: {
                text: 'Grades converted to GPA'
            },
            min:1,
            max:4
        },
        legend: {
            layout: 'vertical',
            align: 'left',
            verticalAlign: 'top',
            x: 100,
            y: 200,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
            borderWidth: 1
        },
        plotOptions: {
            scatter: {
                 dataLabels: {
                    format: "{point.name}",
                    enabled: true
                },
                marker: {
                    radius: 5,
                    states: {
                        hover: {
                            enabled: true,
                            lineColor: 'rgb(100,100,100)'
                        }
                    }
                },
                states: {
                    hover: {
                        marker: {
                            enabled: false
                        }
                    }
                },
                tooltip: {
                    pointFormat: '<b>{point.name}</b><br>Grade: {point.y}'
                }
            }
        },
        series: [{
            name: 'Psychology',
            color: 'rgba(223, 83, 83, .5)',
            data: [ 
            {"x":2013,"y":4,"name":"AP Psychology"},
            {"x":2013.9,"y":4,"name":"Psychology Research Assistant"},
            {"x":2014.3,"y":4,"name":"Psychology Research Assistant"},
            {"x":2014.45,"y":4,"name":"Developmental Psychology"},
            {"x":2014.7,"y":4,"name":"Intro to Statistics"},{"x":2014.75,"y":4,"name":"Social Psychology"},
            {"x":2015.9,"y":4,"name":"Abnormal Psychology"},
            {"x":2015.3,"y":4,"name":"Peer Counseling"}, {"x":2015.35,"y":4,"name":"Research Methods"},
            {"x":2015.7,"y":4,"name":"Cognitive Neuroscience"}]

        }, {
            name: 'Computer Science',
            color: 'rgba(119, 152, 191, .5)',
            data: [{"x":2014,"y":4,"name":"Intro to Computer Science I"},
            {"x":2014.35,"y":4,"name":"Intro to Computer Science II"},{"x":2014.25,"y":4,"name":"Discrete Structures"},
            {"x":2014.5,"y":4,"name":"Intro to Computer Science III"},
            {"x":2014.65,"y":4,"name":"Algorithms & Data Structures"},
            {"x":2015,"y":4,"name":"Intro to System Programming"},{"x":2014.9,"y":4,"name":"Computer Organization"},
            {"x":2015.4,"y":4,"name":"Software Tools"},
            {"x":2015.75,"y":4,"name":"Programming Languages"},
            {"x":2016.3,"y":4,"name":"Computer Graphics"},{"x":2016.25,"y":4,"name":"Software Engineering"},{"x":2016.2,"y":4,"name":"Software Testing"},{"x":2016.35,"y":4,"name":"Web Development"}]
        },{
            name: 'Music',
            color: 'rgba(153, 255, 51, .5)',
            data: [{"x":2013.7,"y":4,"name":"Women's Chorus"},
            {"x":2014.55,"y":4,"name":"Vocal Private Instruction"},
            {"x":2014.65,"y":3.7,"name":"Music Theory I"},{"x":2014.6,"y":4,"name":"Aural Skills I"},
            {"x":2015.05,"y":3.7,"name":"Music Theory II"},{"x":2015.1,"y":3.7,"name":"Aural Skills II"},
            {"x":2015.25,"y":4,"name":"Vocal Private Instruction"},
            {"x":2015.8,"y":4,"name":"Women's Chorus"}, {"x":2015.65,"y":4,"name":"Foundations of Musicology"},
            {"x":2016.4,"y":4,"name":"Renaissance Music"}]

        },{
            name: 'Math',
            color: 'rgba(204, 102, 255, .5)',
            data: [ {"x":2013.05,"y":4,"name":"AP Calculus"},{"x":2015.6,"y":4,"name":"Intro to Linear Algebra"}]

        },{
           name: 'Electives',
            color: 'rgba(255, 204, 0, .5)',
            data: [{"x":2013.75,"y":4,"name":"Criminology"},{"x":2013.65,"y":4,"name":"Freshman Seminar"},{"x":2013.8,"y":4,"name":"Freshman Writing - International Students"},{"x":2013.6,"y":4,"name":"Ecology I"},
            {"x":2014.05,"y":4,"name":"Ecology II"},{"x":2014.95,"y":4,"name":"Intro to Sociology"},{"x":2014.1,"y":3.3,"name":"Freshman Writing I"},
            {"x":2014.2,"y":4,"name":"Ecology III"},{"x":2014.4,"y":3.7,"name":"Freshman Writing II"},
             {"x":2015.2,"y":4,"name":"Food in East Asian History"},
             {"x":2016.15,"y":3.7,"name":"Irish Studies"}]

        }]
    });
});
