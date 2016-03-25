function fixcs2shapeadd(shapesarray)
{
    MapInitAddShape(shapesarray);
    MapControl1.AttachEvent('onchangeview',OnMapChanged);
}

function OnMapChanged()
{
    MapInitAddShape(shapefixcs2);
}

function MapInitAddShape(shapesarray)
{   
    var i=0;
    jQuery.timer(1, function(timer) {
        var log = parseFloat(shapesarray[i][0]);
        var lat = parseFloat(shapesarray[i][1]);
        var html = shapesarray[i][3];
        var shapescale = parseFloat(shapesarray[i][2]);
        if(isNaN(shapescale))
        {
            shapescale = 0.0000125;
        }
        else if(shapescale>0.0002)
        {
            shapescale = 0.0000125;
        }
        var scale  = MapControl1.GetMapScale();
        var MapRect = MapControl1.GetViewBounds();
        var width = (MapRect.rightTop.x - MapRect.leftBottom.x);
        var height = (MapRect.rightTop.y - MapRect.leftBottom.y);//alert('shapescale:'+shapescale+',mapscale:'+scale+',log:'+log+',lat:'+lat+',html:'+html);
        if ((shapescale <= scale) && MapRect.leftBottom.x-width<log && log<MapRect.rightTop.x+width && MapRect.leftBottom.y-height<lat && lat<MapRect.rightTop.y+height)
        {   
            if (shapeinmap.length==0)
            {
                shapeinmap.push(i);
                MapControl1.CustomLayer.InsertMark(i,log,lat,16,16,html);
            }
            else
            {
                var bIn = false;
                for(var j=0;j<shapeinmap.length;j++)
                {
                    if(shapeinmap[j]==i)
                    {
                        bIn = true;
                    }
                }
                if(!bIn)
                {
                    shapeinmap.push(i);
                    MapControl1.CustomLayer.InsertMark(i,log,lat,16,16,html);
                }
            }
        }
        else// if (shapescale > scale)
        {
            if (shapeinmap.length!=0)
            {
                var bIn = false;
                for(var j=0;j<shapeinmap.length;j++)
                {
                    if(shapeinmap[j]==i)
                    {
                        bIn = true;
                        shapeinmap.splice(j,1);
                    }
                }
                if(bIn)
                {
                    MapControl1.CustomLayer.RemoveMark(i);
                }
            }
        }
        i++;
        if (i==shapesarray.length)
        {  
            timer.stop();
        }
    });
}

var AddShapDB = function (){
    if (fixcs!="")
    {
        var ParamsArray = fixcs.split(',');//alert(ParamsArray);
        
        var last_shapdb = -1;
        jQuery.timer(1, function(timer) {
            last_shapdb++;
            if(last_shapdb < ParamsArray.length)
            {
                var Param = ParamsArray[last_shapdb].split(':');
                if (Param.length >= 3)
                {
                    var log = parseFloat(Param[0]);
                    var lat = parseFloat(Param[1]);
                    var html;
                    for (var i = 2; i < Param.length; i++)
                    {
                        if (i == 2)
                        {
                            html = Param[2].toString();
                        }
                        else
                            html += ':' + Param[i].toString();
                    }
                    MapControl1.CustomLayer.InsertMark(ParamsArray[last_shapdb],log,lat,16,16,html);
                }      
            }
            else
            {
                last_shapdb = -1;
                timer.stop();
            }      
        }); 
    }
    if (fixcs2!="")
    {
        var ParamsArray2 = fixcs2.split(',');
        
        for (var i=0;i<ParamsArray2.length;i++)
        {
            var Param2 = ParamsArray2[i].split(':');
            if (Param2.length >= 4)
            {
                var logstr = Param2[0];
                var latstr = Param2[1];
                var scalestr = Param2[2];
                var html2;
                for (var j = 3; j < Param2.length; j++)
                {
                    if (j == 3)
                    {
                        html2 = Param2[3].toString();
                    }
                    else
                        html2 += ':' + Param2[j].toString();
                }
                shapefixcs2.push(new Array(logstr,latstr,scalestr,html2));
            }
        }
        fixcs2shapeadd(shapefixcs2);
    }
    
    ////针对保险e通添加的在页面打点
    if (Insurance_ECs !="")
    {
        var ParamsArray = Insurance_ECs.split(',');//alert(ParamsArray);
        
        var last_shapdb = -1;
        jQuery.timer(1, function(timer) {
            last_shapdb++;
            if(last_shapdb < ParamsArray.length)
            {
                var Param = ParamsArray[last_shapdb].split(':');
                if (Param.length >= 3)
                {
                    var log = parseFloat(Param[0]);
                    var lat = parseFloat(Param[1]);
                    var html;
                    for (var i = 2; i < Param.length; i++)
                    {
                        if (i == 2)
                        {
                            html = Param[2].toString();
                        }
                        else
                            html += ':' + Param[i].toString();
                    }
                    MapControl1.CustomLayer.InsertMark(ParamsArray[last_shapdb],log,lat,16,16,html);
                }      
            }
            else
            {
                last_shapdb = -1;
                timer.stop();
            }      
        }); 
    }   
}

