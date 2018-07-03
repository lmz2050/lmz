var api={};
(function(lmz){

    lmz.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    };
    lmz.post = function(url,data,fn,async){
        console.log("页面刷新："+url);
        $.ajax({
            url:url,
            type:"POST",
            datType: "JSON",
            data:data,
            async: async||true,
            success: function (d) {
                console.log("接口返回："+JSON.stringify(d));
                if(d.code!='0'){
                    alert(d.msg);
                }else{
                    fn(d.data);
                }
            },
            error:function(jqXHR){
                //alert("请求异常"+url+":"+jqXHR.status);
                console.error(jqXHR.responseText+"-"+jqXHR.status+"-"+jqXHR.readyState+"-"+jqXHR.statusText);
            }
        });
    };






})(api);
