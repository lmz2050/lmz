
top.api.progress={};
(function(p){
    p.cfg={};
    p.cfg.barbox=null;
    p.cfg.url=null;
    p.cfg.width="300px";
    p.cfg.height="60px";
    p.cfg.percent=0;
    p.init=function(cfg){
        p.cfg = $.extend(p.cfg,cfg||{});
        var html=p.getHtml();
        p.cfg.barbox.html(html);
    };
    p.getHtml=function(barbox){
        var html='';
        html+='<div id="probar" style="display: none">';
        html+='<div style="width:'+p.cfg.width+',height:'+p.cfg.height+'">';
        html+='<div id="probox" style="width:100%;height:16px;line-height:16px;border:1px solid #bbb;text-align:center;position: relative;">';
        html+='<span id="proper" style="text-align:center;"></span>';
        html+='<div id="p" style="width:0px;background:#ccc;height:100%;text-align:center;position: absolute;top:0px;left:0px;z-index: -1;"></div>';
        html+='</div></div></div>';
        return html;
    }

    p.show=function(percent){
        if(!percent) percent=0;
        p.cfg.percent=percent;
        p.cfg.barbox.find('#probar').show();
        p.cfg.barbox.find("#p").width(p.cfg.percent+"%");
        p.cfg.barbox.find("#proper").html(p.cfg.percent+"%");
        if(p.cfg.d){
            //alert(p.cfg.barbox.html());
            top.api.closeP();
            top.api.openP(p.cfg.title,p.cfg.barbox.html(),p.cfg.width,p.cfg.height);
        }
        p.cfg.tp = setTimeout(function(){p.getProgress(p.cfg.url);},500);
    };
    p.close=function(){
        if(p.cfg.tp){
            clearTimeout(p.cfg.tp);
            p.cfg.tp = null;
        }
        p.cfg.barbox.find('#probar').hide();
        top.api.closeP();
    };
    p.getProgress=function(url){
        p.cfg.url = url;
        $.ajax({
            type:"post",//请求方式
            url:url,//发送请求地址
            timeout:3000,//超时时间：30秒
            dataType:"json",//设置返回数据的格式
            //请求成功后的回调函数 data为json格式
            success:function(data){
                console.log(data);
                p.show(data.percent);
                if (data.isStarted){
                    if (data.completed){
                        p.close();
                    }
                }else{
                    console.log("文件还没有提交");
                    //p.tp = setTimeout(function(){p.getProgress(p.url);},500);
                }
            },
            //请求出错的处理
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest+"--1-"+textStatus+"---"+errorThrown);
                p.close();
            }
        });
    }
})(top.api.progress);