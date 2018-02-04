	        
function timee(counter,tts,now,fne) {
    var c = (new Date()).getTime();
    var a = parseInt(tts) * 1000;
    var ls = a + now - c;
	
    if (ls > 0) {
        var dayInt = parseInt(ls / 86400000);
        var ld = parseInt(ls / 86400000).toString();
        ls = ls % 86400000;

        var hInt = parseInt(ls / 3600000);
        var lh = parseInt(ls / 3600000).toString();
        lh = lh.length > 1 ? lh : '0' + lh;
        ls = ls % 3600000;

        var mInt = parseInt(ls / 60000);
        var lm = parseInt(ls / 60000).toString();
        lm = lm.length > 1 ? lm : '0' + lm;

        var lms = parseInt((ls % 60000) / 100);
        ls = parseInt(lms / 10);
        var tms = lms - ls * 10;

        if (dayInt == 0 && hInt == 0 && mInt == 0 && ls == 0 && tms <= 1)
            location.href = location.href;

        ls = ls.toString().length > 1 ? ls : '0' + ls;

        var html = "";
        if (dayInt > 0)
            html += '' + ld + '天&nbsp;';
        html += '' + lh + ':' + lm + ':' + ls + '.' + tms + '';
        //html += '<span class="z">00</span>:<span class="z">' + lm + '</span>:<span class="z">' + ls + '.' + tms + '</span>';


        counter.html(html);
    }
    else {
        counter.stopTime('timer');
        counter.html('已结束');
        counter.css({'color':'#ccc'});
        
        fne();
    }
}
jQuery.fn.lmztime=function(){
	$(this).each(function(){
		var tt=$(this);
		var s=$(this).attr('s');
		var e=$(this).attr('e');
		var now = (new Date()).getTime();
		
		var stime=s;
		tt.css({'color':'#ccc'});
		if(s<=0&&e>0){
        	stime = e;
        	tt.css({'color':'#f00'});
        }else if(s>0){
        	stime = s;
        	tt.css({'color':'#00f'});
        }
		
		tt.everyTime(100, 'timer', function(){
			timee(tt,stime,now,function(){});
		});
		
		
	});
};

$(function(){
	$(".lmztime").lmztime();
});

