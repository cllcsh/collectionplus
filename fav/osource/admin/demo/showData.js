  function showDetails(a,number){
       switch(a){
           case 1:showDetails1(number);
               break;
           case 2:showDetails2(number);
               break;
           case 3:showDetails3(number);
               break;
       }
       
          
    }
    function showDetails1(number){
        $tbody =$("#functionTable tbody");
        $tbody.html("");
        for(var i=1;i<number;i++){
            $tr=$("#template").find("tr").eq(0).clone();  
            $tr.children("td").eq(0).text(i);
            $tr.children("td").eq(1).text('沙滩');
            $tr.children("td").eq(2).text('风景');
            $tr.children("td").eq(3).text('2111-11-11');
            $tr.children("td").eq(4).text(123456);
            $tr.children("td").eq(5).text(2222);
            $tbody.append($tr);
        }
          
    }
    
 function showDetails2(number){
        $tbody =$("#functionTable tbody");
        $tbody.html("");
        for(var i=1;i<number;i++){
                
            $tr=$("#template").find("tr").eq(0).clone();  
            $tr.children("td").eq(0).text(i);
            $tr.children("td").eq(1).text('大米');
            $tr.children("td").eq(2).text('粮食');
            $tr.children("td").eq(3).text('2018-11-13');
            $tr.children("td").eq(4).text(1);
            $tr.children("td").eq(5).text(1);
            $tbody.append($tr);
        }
          
    }

    
    function showDetails3(number){
        $tbody =$("#functionTable tbody");
        $tbody.html("");
        for(var i=1;i<number;i++){
                
            $tr=$("#template").find("tr").eq(0).clone();  
            $tr.children("td").eq(0).text(i);
            $tr.children("td").eq(1).text('潜水艇');
            $tr.children("td").eq(2).text('装备');
            $tr.children("td").eq(3).text('2111-11-11');
            $tr.children("td").eq(4).text(123456);
            $tr.children("td").eq(5).text(2222);
            $tbody.append($tr);
        }
          
    }