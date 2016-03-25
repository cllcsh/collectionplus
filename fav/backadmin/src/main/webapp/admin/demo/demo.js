
//        //仪表盘
//$("#test").speedometer();

//$('#speedometer').click(function(){
//    $('#test').speedometer({ percentage: Math.round(Math.random()*100)|| 0 });
//});
function speedometer(){
    $('#test').speedometer({ percentage: Math.round(Math.random()*100)|| 0 });
}
 





$("img[test='showRelation']").click(function(){
    
});


/**
 * 饼状图
 * @param div 显示区域
 * @param data 数据
 */
function showPie(div ,data){
//                alert($(div).width());
    var data = [
        {age: '2000', population: 2704659},
        {age: '2001', population: 4499890},
        {age: '2002', population: 2159981},
        {age: '2003', population: 3853788},
        {age: '2004', population: 14106543},
        {age: '2005-2008', population: 8819342},
        {age: '2008-2013', population: 6124631}
    ];


    var width = $(div).width(),
    height = $(div).height(),
    radius = Math.min(width, height) / 2;

    var color = d3.scale.ordinal()
    .range(["#98abc5", "#8a89a6", "#7b6888", "#6b486b", "#a05d56", "#d0743c", 
        "#ff8c00","blue"]);

    var arc = d3.svg.arc()
    .outerRadius(radius - 10)
    .innerRadius(0);

    var pie = d3.layout.pie()
    .sort(null)
    .value(function(d) { return d.population; });

    var svg = d3.select(div).append("svg")
    .attr("width", width)
    .attr("height", height)
    .append("g")
    .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

    data.forEach(function(d) {
        //alert(d);
        d.population = +d.population;

    });

    var g = svg.selectAll(".arc")
    .data(pie(data))
    .enter().append("g")
    .attr("class", "arc");

    g.append("path")
    .attr("d", arc)
    .style("fill", function(d) { return color(d.data.age); });

    g.append("text")
    .attr("transform", function(d) { return "translate(" + arc.centroid(d) + ")"; })
    .attr("dy", ".35em")
    .style("text-anchor", "middle")
    .text(function(d) { return d.data.age; });
}



/**
 * 柱状图
 * @param div 显示区域
 * @param data 数据
 */

function showBar(div, data){

    var data = [
        {age: 'A', population: 0.27046},
        {age: 'B', population: 0.44998},
        {age: 'C', population: 0.21599},
        {age: 'D', population: 0.38537},
        {age: 'E', population: 0.14106},
        {age: 'F', population: .02288},
        {age: 'G', population: 0.44106},
        {age: 'H', population: 0.22106}
    
    ];
    var margin = {top: 20, right: 20, bottom: 30, left: 40},
    width = $(div).width() - margin.left - margin.right,
    height = $(div).height() - margin.top - margin.bottom;

    var formatPercent = d3.format(".0%");

    var x = d3.scale.ordinal()
    .rangeRoundBands([0, width], .1);

    var y = d3.scale.linear()
    .range([height, 0]);

    var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

    var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left")
    .tickFormat(formatPercent);

    var svg = d3.select(div).append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    data.forEach(function(d) {
        d.population = +d.population;
    });
 
 
    x.domain(data.map(function(d) { return d.age; }));
    y.domain([0, d3.max(data, function(d) { return d.population; })]);

    svg.append("g")
    .attr("class", "x axis")
    .attr("transform", "translate(0," + height + ")")
    .call(xAxis);

    svg.append("g")
    .attr("class", "y axis")
    .call(yAxis)
    .append("text")
    .attr("transform", "rotate(-90)")
    .attr("y", 6)
    .attr("dy", ".71em")
    .style("text-anchor", "end");

    svg.selectAll(".bar")
    .data(data)
    .enter().append("rect")
    .attr("class", "bar")
    .attr("x", function(d) { return x(d.age); })
    .attr("width", x.rangeBand())
    .attr("y", function(d) { return y(d.population); })
    .attr("height", function(d) { return height - y(d.population); });
}



