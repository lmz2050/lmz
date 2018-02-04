<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="l" uri="/lmz-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商城首页-示例商城</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=8"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.js"></script>
<link  href="${pageContext.request.contextPath}/shop/css/common.css"  rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shop/css/index.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/tools/sutil.js" id="sutil" path="${pageContext.request.contextPath}"></script> 
 

<script>
$(function(){
	$('#nav_menu>ul>li').dropDownMenu({child:'.child'});
});
</script>
</head>
<body>
<div id="hreder">
	<div class="login_bar">
		<ul>
				<li><a href="${pageContext.request.contextPath}/member/login.jsp">登录 </a></li>
				<li><a href="${pageContext.request.contextPath}/member/reg.jsp">注册 </a></li>
		</ul>	
	</div>	
	<div class="util_bar">
		<ul>
			<!--购物车下拉菜单-->
			<li id="cart_bar_wrapper" class="first">
				<a href="cart.html">购物车(<span class="num">0</span>)件</a>
				<div class="content" style="display:none">
					<div class="border"></div>
					<div class="cart_list">
<img src="/version4/themes/default/images/ajax-loader.gif" class="loading">					</div>					
				</div>
			</li>
			<!--购物车下拉菜单结束-->
			<li><a href="searchorder.html">游客订单查询</a></li>
			<li><a href="help.html">帮助中心</a></li>
			<li class="phone">400-400-4000</li>
		</ul>
	</div>
	<div class="clear"></div>	
</div>
<!--页面logo、信息-->
<div id="banner">
	<!--logo-->
	<div class="logo">
<img src="http://demo.javamall.com.cn:80/version4/statics/images/logo.gif" title="logo">	</div>
	<!--logo结束-->
	<!--承诺、优惠-->
	<div class="promise">
<img src="/version4/themes/default/images/sycn.jpg" alt="承诺图片">	</div>
	<!--承诺、优惠结束-->
</div>
<!--页面logo、信息结束-->
<!--导航-->
<div id="nav_menu">
<!-- 创建菜单标签 -->
<!-- 调用菜单信息 -->
<ul>
		<li class="first">
			<em class="first"></em>
			<a href="/version4/index.html">商城首页</a>	 
			<span></span> 
			<em></em>
		</li>
		<li class=" haschild">
			<em></em>
			<a href="/version4/search-cat-4.html">进口食品</a>	 
			<span></span> 
			<em></em>
				<ul class="child">
					   <li><a href="/version4/search-cat-5.html" )="">进口乳制品</a></li>
					   <li><a href="/version4/search-cat-24.html" )="">进口饼干糕点</a></li>
					   <li><a href="/version4/search-cat-27.html" )="">进口咖啡/茶品</a></li>
					   <li class="last"><a href="/version4/search-cat-31.html" )="">进口休闲零食</a></li>
				</ul>
		</li>
		<li class=" haschild">
			<em></em>
			<a href="/version4/search-cat-38.html">美容化妆</a>	 
			<span></span> 
			<em></em>
				<ul class="child">
					   <li><a href="/version4/search-cat-39.html" )="">面部护理</a></li>
					   <li class="last"><a href="/version4/search-cat-47.html" )="">男士护理</a></li>
				</ul>
		</li>
		<li class=" haschild">
			<em></em>
			<a href="/version4/search-cat-1.html">食品饮料</a>	 
			<span></span> 
			<em></em>
				<ul class="child">
					   <li><a href="/version4/search-cat-2.html" )="">休闲零食</a></li>
					   <li><a href="/version4/search-cat-3.html" )="">糖果/巧克力</a></li>
					   <li><a href="/version4/search-cat-13.html" )="">饮料</a></li>
					   <li class="last"><a href="/version4/search-cat-18.html" )="">酒水</a></li>
				</ul>
		</li>
		<li class="">
			<em></em>
			<a href="/version4/search-cat-89.html">手机通讯</a>	 
			<span></span> 
			<em></em>
		</li>
		<li class=" haschild">
			<em></em>
			<a href="/version4/search-cat-86.html">服装鞋帽</a>	 
			<span></span> 
			<em></em>
				<ul class="child">
					   <li><a href="/version4/search-cat-92.html" )="">短裤</a></li>
					   <li class="last"><a href="/version4/search-cat-93.html" )="">裙子</a></li>
				</ul>
		</li>
		<li class="">
			<em></em>
			<a href="/version4/search-cat-85.html">营养保健</a>	 
			<span></span> 
			<em></em>
		</li>
		<li class="">
			<em></em>
			<a href="/version4/search-cat-88.html">办公用品</a>	 
			<span></span> 
			<em></em>
		</li>
		<li class="last">
			<em></em>
			<a href="/version4/search-cat-87.html">汽车维护</a>	 
			<span></span> 
			<em class="last"></em>
		</li>
</ul></div>
<!--导航结束-->
<!--搜索-->
<div id="search_bar">
<!--搜索板块-->
<div class="search">
	<input type="text" class="hunt" id="keyword" value="请输入产品关键字，如品牌、型号等" title="请输入产品关键字，如品牌、型号等">
	<input type="button" name="s_seek" class="s_seek" value="搜索" id="searchBtn">	
</div>
<!--搜索板块结束-->
<!--搜索导航-->
<div class="hot_keyword">
	<h1>热门搜索：</h1>
	<ul>
	<!-- 获取CMS数据搜索标签， -->
	  	<!-- 调用CMS数据搜索标签，并传递所在模型ID参数(es_hot_keyword表.)“3”表示出现三个关键词。 -->
			<li><a href="/version4/search-keyword-戴尔显示器.html">戴尔显示器</a></li>
			<li><a href="/version4/search-keyword-中粮.html">中粮</a></li>
			<li class="last"><a href="/version4/search-keyword-iphone手机.html">iphone手机</a></li>
	</ul>
</div>
<!--搜索导航结束-->

<script type="text/javascript">
var searchTipText = "请输入产品关键字，如品牌、型号等";
function search(){
	var keyword = $.trim( $("#keyword").val());
	if(keyword == searchTipText){
		keyword = '';
	}
	if(keyword.indexOf('\\')!=-1){
		alert("请输入正确的关键词");
		return false;
	}
	if(keyword==''){
		alert("请输入关键字");
	}else{
		keyword = keyword.replace(/\//g,"-xie-");
		var url =  encodeURIComponent("search-keyword-"+keyword+".html");
		location.href="/version4/"+url;
	}
}

$(function(){	
	$("#keyword").keypress(function(event){
	//	$("#keyword").attr("class","seek");
		if(event.keyCode=="13" ){
			search();
		}
	});
	$("#keyword").click(function(){
		if($(this).val() == searchTipText){
			$(this).val("");
		}
	});
	$("#keyword").blur(function(){
		if($(this).val() == ''){
			$(this).val(searchTipText);
		}
	});
	
	$("#searchBtn").click(function(){
		search();
	});
});
</script></div>
<script>
$(function(){
	$("#logout").click(function(){
		$.ajax({
			url:"api/shop/member!logout.do?ajax=yes",
			dataType:"json",
			cache: false,             //清楚缓存，暂时测试，如果产生冲突，请优先考虑是否是这条语句。
			success:function(result){
				if(result.result==1){
					$.alert(result.message);
					location.href="/version4/index.html";
					
				}else{
					$.alert(result.message);
				}	
				$.Loading.hide();
			},
			error:function(){
				$.Loading.hide();
				$.alert("出错了:(");
			}
		});
		return false;
	})	
})
</script>	

	



<script>
$(function(){
	$('#cat_wrapper>ul>li.haschild').dropDownMenu({child:'.child'});
});
</script>
<!--正文-->
<!--第一层、商品列表-->
<div id="floor_1">
	<!--商品分类列表-->
	<div id="cat_wrapper">
		<h1>全部商品列表</h1>
		<!-- 创建商品分类标签 -->
		<!-- 调用商品分类信息 -->
		<ul>
				<li class="haschild"><a href="/version4/search-cat-1.html" class="menu">箱包</a>
						<div class="child" style="display: none;">
							<div class="top"></div>
							<div class="con">
								<ul class="inner_con">
										<li>
											<h6> <a href="/version4/search-cat-2.html">女包:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-6.html">坚果</a></li>
														<li><a href="/version4/search-cat-7.html">蜜饯</a></li>
														<li><a href="/version4/search-cat-8.html">肉干</a></li>
														<li><a href="/version4/search-cat-9.html">薯片</a></li>
												</ul>
										</li>
										<li>
											<h6> <a href="/version4/search-cat-3.html">男包:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-10.html">巧克力</a></li>
														<li><a href="/version4/search-cat-11.html">糖果</a></li>
														<li><a href="/version4/search-cat-12.html">口香糖</a></li>
												</ul>
										</li>
										<li>
											<h6> <a href="/version4/search-cat-13.html">功能包:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-14.html">碳酸饮料</a></li>
														<li><a href="/version4/search-cat-15.html">茶饮料</a></li>
														<li><a href="/version4/search-cat-16.html">果汁</a></li>
														<li><a href="/version4/search-cat-17.html">水</a></li>
												</ul>
										</li>
										<li class="last">
											<h6> <a href="/version4/search-cat-18.html">酒水:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-19.html">白酒</a></li>
														<li><a href="/version4/search-cat-20.html">啤酒</a></li>
														<li><a href="/version4/search-cat-21.html">洋酒</a></li>
												</ul>
										</li>
								</ul>
							</div>
							<div class="bottom"></div>
						</div>
				</li>
				<li class="haschild"><a href="/version4/search-cat-4.html" class="menu">进口食品、进口牛奶</a>
						<div class="child" style="display: none;">
							<div class="top"></div>
							<div class="con">
								<ul class="inner_con">
										<li>
											<h6> <a href="/version4/search-cat-5.html">进口乳制品:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-22.html">进品牛奶</a></li>
														<li><a href="/version4/search-cat-23.html">进品酸奶</a></li>
												</ul>
										</li>
										<li>
											<h6> <a href="/version4/search-cat-24.html">进口饼干糕点:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-25.html">进口饼干</a></li>
														<li><a href="/version4/search-cat-26.html">进口糕点</a></li>
												</ul>
										</li>
										<li>
											<h6> <a href="/version4/search-cat-27.html">进口咖啡/茶品:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-28.html">进口红茶</a></li>
														<li><a href="/version4/search-cat-29.html">进口绿茶</a></li>
														<li><a href="/version4/search-cat-30.html">进口速溶咖啡</a></li>
												</ul>
										</li>
										<li class="last">
											<h6> <a href="/version4/search-cat-31.html">进口休闲零食:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-32.html">进口膨化</a></li>
														<li><a href="/version4/search-cat-33.html">进口海产品</a></li>
														<li><a href="/version4/search-cat-34.html">进口果冻/布丁</a></li>
												</ul>
										</li>
								</ul>
							</div>
							<div class="bottom"></div>
						</div>
				</li>
				<li class="haschild"><a href="/version4/search-cat-38.html" class="menu">美容化妆、个人护理</a>
						<div class="child" style="display: none;">
							<div class="top"></div>
							<div class="con">
								<ul class="inner_con">
										<li>
											<h6> <a href="/version4/search-cat-39.html">面部护肤:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-40.html">洁面</a></li>
														<li><a href="/version4/search-cat-41.html">柔肤水/爽肤水</a></li>
														<li><a href="/version4/search-cat-42.html">乳液</a></li>
												</ul>
										</li>
										<li>
											<h6> <a href="/version4/search-cat-43.html">眼部保养:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-44.html">眼部精华</a></li>
														<li><a href="/version4/search-cat-45.html">眼霜</a></li>
														<li><a href="/version4/search-cat-46.html">眼膜</a></li>
												</ul>
										</li>
										<li>
											<h6> <a href="/version4/search-cat-47.html">男士护理:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-48.html">男士洁面</a></li>
														<li><a href="/version4/search-cat-49.html">男士护肤</a></li>
														<li><a href="/version4/search-cat-50.html">须后水</a></li>
												</ul>
										</li>
										<li class="last">
											<h6> <a href="/version4/search-cat-51.html">洗发护发:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-52.html">去屑型洗发水</a></li>
														<li><a href="/version4/search-cat-53.html">柔顺型洗发水</a></li>
														<li><a href="/version4/search-cat-54.html">修复型洗发水</a></li>
														<li><a href="/version4/search-cat-55.html">滋润营养洗发水</a></li>
												</ul>
										</li>
								</ul>
							</div>
							<div class="bottom"></div>
						</div>
				</li>
				<li class="haschild"><a href="/version4/search-cat-56.html" class="menu">母婴、玩具音像图书</a>
						<div class="child">
							<div class="top"></div>
							<div class="con">
								<ul class="inner_con">
										<li>
											<h6> <a href="/version4/search-cat-57.html">奶粉系列:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-58.html">1段奶粉</a></li>
														<li><a href="/version4/search-cat-59.html">2段奶粉</a></li>
														<li><a href="/version4/search-cat-60.html">3段奶粉</a></li>
														<li><a href="/version4/search-cat-61.html">特殊配方奶粉</a></li>
												</ul>
										</li>
										<li>
											<h6> <a href="/version4/search-cat-62.html">防尿用品:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-63.html">纸尿裤</a></li>
														<li><a href="/version4/search-cat-64.html">婴儿湿巾</a></li>
														<li><a href="/version4/search-cat-65.html">尿布/尿垫</a></li>
												</ul>
										</li>
										<li>
											<h6> <a href="/version4/search-cat-66.html">辅食:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-67.html">米粉</a></li>
														<li><a href="/version4/search-cat-68.html">婴儿果汁</a></li>
														<li><a href="/version4/search-cat-69.html">面条</a></li>
														<li><a href="/version4/search-cat-70.html">饼干/磨牙棒</a></li>
												</ul>
										</li>
										<li>
											<h6> <a href="/version4/search-cat-71.html">清洁用品:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-72.html">澡盆/浴床</a></li>
														<li><a href="/version4/search-cat-73.html">奶瓶/餐具清洁</a></li>
														<li><a href="/version4/search-cat-74.html">幼儿口腔护理</a></li>
												</ul>
										</li>
										<li class="last">
											<h6> <a href="/version4/search-cat-75.html">纺织/用品:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-76.html">床单/枕头</a></li>
														<li><a href="/version4/search-cat-77.html">浴巾/毛巾</a></li>
														<li><a href="/version4/search-cat-78.html">抱被</a></li>
												</ul>
										</li>
								</ul>
							</div>
							<div class="bottom"></div>
						</div>
				</li>
				<li class="haschild"><a href="/version4/search-cat-79.html" class="menu">厨房用品、清洁用品</a>
						<div class="child" style="display: none;">
							<div class="top"></div>
							<div class="con">
								<ul class="inner_con">
										<li class="last">
											<h6> <a href="/version4/search-cat-80.html">锅具水壶:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-81.html">汤锅</a></li>
														<li><a href="/version4/search-cat-82.html">水壶</a></li>
														<li><a href="/version4/search-cat-83.html">炒锅</a></li>
														<li><a href="/version4/search-cat-84.html">奶锅</a></li>
												</ul>
										</li>
								</ul>
							</div>
							<div class="bottom"></div>
						</div>
				</li>
				<li class="haschild"><a href="/version4/search-cat-35.html" class="menu">珠宝钟表、饰品箱包</a>
						<div class="child" style="display: none;">
							<div class="top"></div>
							<div class="con">
								<ul class="inner_con">
										<li class="last">
											<h6> <a href="/version4/search-cat-36.html">女包:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-37.html">单肩包</a></li>
												</ul>
										</li>
								</ul>
							</div>
							<div class="bottom"></div>
						</div>
				</li>
				<li><a href="/version4/search-cat-85.html" class="menu">营养保健、健康器械</a>
				</li>
				<li class="haschild"><a href="/version4/search-cat-86.html" class="menu">服装鞋靴、内衣佩饰</a>
						<div class="child">
							<div class="top"></div>
							<div class="con">
								<ul class="inner_con">
										<li>
											<h6> <a href="/version4/search-cat-91.html">男装:</a></h6>
												<ul>
														<li><a href="/version4/search-cat-92.html">短裤</a></li>
												</ul>
										</li>
										<li class="last">
											<h6> <a href="/version4/search-cat-93.html">裙子:</a></h6>
										</li>
								</ul>
							</div>
							<div class="bottom"></div>
						</div>
				</li>
				<li><a href="/version4/search-cat-87.html" class="menu">家居运动、汽车维护</a>
				</li>
				<li class="haschild"><a href="/version4/search-cat-88.html" class="menu">电脑、软件、办公用品</a>
						<div class="child">
							<div class="top"></div>
							<div class="con">
								<ul class="inner_con">
										<li class="last">
											<h6> <a href="/version4/search-cat-90.html">U盘:</a></h6>
										</li>
								</ul>
							</div>
							<div class="bottom"></div>
						</div>
				</li>
				<li><a href="/version4/search-cat-89.html" class="menu">手机通讯、数码电器</a>
				</li>
		</ul>
	</div>
	<!--商品列表结束-->
	<!--首页第一层右侧-->
	<div class="right">
		<!--商品广告-->
		<div class="adv">
			<!-- 创建广告标签 -->
			<!-- 调用广告信息,传递参数acid:1 -->
			<!-- 获取广告列表 -->
				<!-- 获取广告图片宽度 -->
				<!-- 获取广告图片高度 -->
				<div name="__DT" class="slides" width="765px" height="296px">
					<ul class="slide-pic" width="765px" height="296px">
							<li class="" style="display: none;">
							<a target="_blank" title="潮人双肩包69元起" href="/version4/goods-30.html">
							<img src="http://demo.javamall.com.cn:80/version4/statics/attachment/adv/1.jpg" height="296px" alt="潮人双肩包69元起" width="765px" border="0"></a></li>
							<li class="cur" style="display: list-item;">
							<a target="_blank" title="男士POLO衫特价68元" href="/version4/goods-30.html">
							<img src="http://demo.javamall.com.cn:80/version4/statics/attachment/adv/2.jpg" height="296px" alt="男士POLO衫特价68元" width="765px" border="0"></a></li>
							<li class="" style="display: none;">
							<a target="_blank" title="真空压缩袋节省空间67%" href="/version4/goods-30.html">
							<img src="http://demo.javamall.com.cn:80/version4/statics/attachment/adv/3.jpg" height="296px" alt="真空压缩袋节省空间67%" width="765px" border="0"></a></li>
							<li class="" style="display: none;">
							<a target="_blank" title="早春运动针织长裤69元起" href="/version4/goods-30.html">
							<img src="http://demo.javamall.com.cn:80/version4/statics/attachment/adv/4.jpg" height="296px" alt="早春运动针织长裤69元起" width="765px" border="0"></a></li>
					</ul>
					<ul class="slide-li op">
					    	<li class=""></li>
					    	<li class="cur"></li>
					    	<li class=""></li>
					    	<li class=" last"></li>
					</ul>
					<ul class="slide-li slide-txt">
						    <li class=""><a target="_blank" href="/version4/goods-100.html">潮人双肩包69元起</a>
							</li>
						    <li class="cur"><a target="_blank" href="/version4/goods-100.html">男士POLO衫特价68元</a>
							</li>
						    <li class=""><a target="_blank" href="/version4/goods-100.html">真空压缩袋节省空间67%</a>
							</li>
						    <li class=" last"><a target="_blank" href="/version4/goods-100.html">早春运动针织长裤69元起</a>
							</li>
					</ul>
				</div>
		</div>
		<!--商品广告结束-->
		<!--第一层、特别推荐商品-->
		<div class="unusual">
			<!-- 创建广告标签 -->
			<!-- 调用广告信息,传递参数acid:8 -->
			<!-- 获取广告列表 -->
				<!-- 获取广告图片宽度 -->
				<!-- 获取广告图片高度 -->
				<ul>
						<li>
							<a href="/version4/goods-118.html"><img src="http://demo.javamall.com.cn:80/version4/statics/attachment/adv/201209301144199462.jpg" height="158px" alt="首页示例图片" width="2231"></a>
						</li>
						<li>
							<a href="/version4/goods-118.html"><img src="http://demo.javamall.com.cn:80/version4/statics/attachment/adv/201209301140181934.jpg" height="158px" alt="首页示例图片" width="2231"></a>
						</li>
						<li class="last">
							<a href="/version4/goods-118.html"><img src="http://demo.javamall.com.cn:80/version4/statics/attachment/adv/201209301141112985.jpg" height="158px" alt="首页示例图片" width="2231"></a>
						</li>
				</ul>
		</div>
		<!--第一层、特别推荐商品结束-->
	</div>
	<!--首页第一层右侧结束-->
</div>
<!--第一层结束-->
<!--第二层-->
<div id="floor_2">
	<!--第二层、左侧、热卖排行榜-->
	<div class="goods_list_box">
		<div class="index_hot">
			<h1><span></span>热卖排行榜</h1>
			<!-- 获取商品列表标签 -->
			<!-- 调用商品列表标签，并传递商品列表所在catid及循环次数 -->		
			<ul>
					<li class="first ">
						<a href="/version4/goods-123.html">
<img src="http://static.v4.javamall.com.cn/attachment/goods/201202231834296191_thumbnail.jpg" alt="相宜本草 防皱消黑眼贴膜6对/盒">						</a>
						<div class="h_intro">
							<div>
								<a href="goods-123.html">
相宜本草 防皱消黑眼贴膜6对/盒								</a>
							</div>
							<p>售价：<span>36</span></p>
						</div>
					</li> 
					<li class=" ">
						<a href="/version4/goods-128.html">
<img src="http://static.v4.javamall.com.cn/attachment/goods/201202231901239199_thumbnail.jpg" alt="资生堂吾诺黑炭强力劲爽洁面膏130g ">						</a>
						<div class="h_intro">
							<div>
								<a href="goods-128.html">
资生堂吾诺黑炭强力劲爽洁面膏13...								</a>
							</div>
							<p>售价：<span>43.9</span></p>
						</div>
					</li> 
					<li class=" ">
						<a href="/version4/goods-132.html">
<img src="http://static.v4.javamall.com.cn/attachment/goods/201202231913344067_thumbnail.jpg" alt="曼秀雷敦男士活力修护润肤乳50ml">						</a>
						<div class="h_intro">
							<div>
								<a href="goods-132.html">
曼秀雷敦男士活力修护润肤乳50ml...								</a>
							</div>
							<p>售价：<span>61.5</span></p>
						</div>
					</li> 
					<li class=" ">
						<a href="/version4/goods-148.html">
<img src="http://static.v4.javamall.com.cn/attachment/goods/201202231959343514_thumbnail.jpg" alt="潘婷乳液修复洗发露 200ml">						</a>
						<div class="h_intro">
							<div>
								<a href="goods-148.html">
潘婷乳液修复洗发露 200ml								</a>
							</div>
							<p>售价：<span>19.8</span></p>
						</div>
					</li> 
					<li class=" last">
						<a href="/version4/goods-116.html">
<img src="http://static.v4.javamall.com.cn/attachment/goods/201202231706544833_thumbnail.jpg" alt="玉兰油水感透白明眸走珠精华笔 6ml">						</a>
						<div class="h_intro">
							<div>
								<a href="goods-116.html">
玉兰油水感透白明眸走珠精华笔 6...								</a>
							</div>
							<p>售价：<span>136</span></p>
						</div>
					</li> 
			</ul>
		</div>
	</div>
	<!--第二层、左侧、热卖排行榜结束-->
	<!--第二层右侧热卖图片-->
	<div class="right">
		<!--第二层、右侧、上部分大图片-->
		<div class="top">
			<!-- 创建广告标签 -->
			<!-- 调用广告信息,传递参数acid:2 -->
			<!-- 获取广告列表 -->
				<!-- 获取广告图片宽度 -->
				<!-- 获取广告图片高度 -->
				<ul>
						<li><a href="/version4/search-cat-86.html"><img src="http://static.v4.javamall.com.cn/attachment/adv/201202281306593822.jpg" height="194px" width="765px"></a></li>
				</ul>
		</div>
		<!--第二层、右侧、上部分大图片结束-->
		<!--第二层、右侧、下部分小图片列表-->
		<div class="bottom">
		<!--创建"商品列表"标签-->
		<!--调用"商品列表"标签，并传递参数-->            
		<ul>
			<li>
				<a href="/version4/goods-131.html"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202231910375481_thumbnail.jpg"></a>
				<div class="indexshow">
					<a href="/version4/goods-131.html">曼秀雷敦男士冰爽活炭套装(冰爽活炭洁面乳150ml + 清爽沐浴露-天然活炭200ml)</a>
					<span><del>￥59.80</del></span>
					<span><strong>￥56.00</strong>元</span>
				</div>
			</li>
			<li>
				<a href="/version4/goods-136.html"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202231923155745_thumbnail.jpg"></a>
				<div class="indexshow">
					<a href="/version4/goods-136.html">佰草集男仕君衡须后水150ml</a>
					<span><del>￥150.00</del></span>
					<span><strong>￥150.00</strong>元</span>
				</div>
			</li>
			<li>
				<a href="/version4/goods-132.html"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202231913344067_thumbnail.jpg"></a>
				<div class="indexshow">
					<a href="/version4/goods-132.html">曼秀雷敦男士活力修护润肤乳50ml</a>
					<span><del>￥69.90</del></span>
					<span><strong>￥61.50</strong>元</span>
				</div>
			</li>
			<li class="last">
				<a href="/version4/goods-30.html"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202221648349842_thumbnail.jpg"></a>
				<div class="indexshow">
					<a href="/version4/goods-30.html">德芙 榛仁葡萄干巧克力碗装283.5g</a>
					<span><del>￥49.00</del></span>
					<span><strong>￥45.00</strong>元</span>
				</div>
			</li>
		</ul>
		</div>
		<!--第二层、右侧、下部分小图片列表结束-->
	</div>
	<!--第二层右侧热卖图片结束-->
</div>
<!--第二层结束-->
<!--第三层相同内容-->
<div class="same">
	<!--第三层相同内容标题-->
	<div class="title">
		<h1>家居用品</h1>
		<h2>Household Goods</h2>
		<span></span>
		<ul> 
			<li><a href="/version4/search-cat-76.html">床单</a></li>
			<li><a href="/version4/search-cat-77.html">浴巾</a></li>
			<li class="last"><a href="/version4/search-cat-78.html">抱被</a></li>
		</ul>
	</div>
	<!--第三层相同内容标题结束-->
	<!--相同内容图片-->
	<div class="frame">
		<!--第三层相同图片大幅图片-->
		<div class="f_top">
			<!-- 创建广告标签 -->
			<!-- 调用广告信息,传递参数acid:1 -->
			<!-- 获取广告列表 -->
				<!-- 获取广告图片宽度 -->
				<!-- 获取广告图片高度 -->
					<a href="/version4/search-cat-86.html"><img src="http://static.v4.javamall.com.cn/attachment/adv/201202281306167304.jpg" height="135px" alt="皮质帆船鞋经典至极" width="976px" border="0"></a>
		</div>
		<!--第三层相同图片大幅图片结束-->
		<!--第三层相同图片小图片列表-->
		<div class="f_bottom">
			<ul>
					<li>
						<a href="/version4/goods-238.html" title="小榕树 压花珊瑚绒抱被-蓝"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242248178490_thumbnail.jpg" alt="小榕树 压花珊瑚绒抱被-蓝"></a>
						<a href="/version4/goods-238.html" title="小榕树 压花珊瑚绒抱被-蓝">小榕树 压花珊瑚绒抱被-蓝</a>
						<p>市场价：<del>￥98.00</del></p>
						<h3>商城价：<span>￥68.00</span>元</h3>
					</li>
					<li>
						<a href="/version4/goods-226.html" title="liangliang 良良 护型保健枕 0—3岁加长珍珠枕"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242138071429_thumbnail.jpg" alt="liangliang 良良 护型保健枕 0—3岁加长珍珠枕"></a>
						<a href="/version4/goods-226.html" title="liangliang 良良 护型保健枕 0—3岁加长珍珠枕">liangliang 良良 护型保健...</a>
						<p>市场价：<del>￥312.00</del></p>
						<h3>商城价：<span>￥281.00</span>元</h3>
					</li>
					<li>
						<a href="/version4/goods-236.html" title="皇家宝贝 2011可加长可拆睡袋（50x70/105）613150008粉红色均码"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242244427353_thumbnail.jpg" alt="皇家宝贝 2011可加长可拆睡袋（50x70/105）613150008粉红色均码"></a>
						<a href="/version4/goods-236.html" title="皇家宝贝 2011可加长可拆睡袋（50x70/105）613150008粉红色均码">皇家宝贝 2011可加长可拆睡...</a>
						<p>市场价：<del>￥568.00</del></p>
						<h3>商城价：<span>￥568.00</span>元</h3>
					</li>
					<li>
						<a href="/version4/goods-230.html" title="喜亲宝 竹纤维抗菌大浴巾 米黄 70*140cm"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242228039258_thumbnail.jpg" alt="喜亲宝 竹纤维抗菌大浴巾 米黄 70*140cm"></a>
						<a href="/version4/goods-230.html" title="喜亲宝 竹纤维抗菌大浴巾 米黄 70*140cm">喜亲宝 竹纤维抗菌大浴巾 ...</a>
						<p>市场价：<del>￥128.00</del></p>
						<h3>商城价：<span>￥98.00</span>元</h3>
					</li>
					<li>
						<a href="/version4/goods-232.html" title="爱得利 纱布大浴巾 纱布 DT-302"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242233231654_thumbnail.jpg" alt="爱得利 纱布大浴巾 纱布 DT-302"></a>
						<a href="/version4/goods-232.html" title="爱得利 纱布大浴巾 纱布 DT-302">爱得利 纱布大浴巾 纱布 DT...</a>
						<p>市场价：<del>￥47.90</del></p>
						<h3>商城价：<span>￥40.70</span>元</h3>
					</li>
					<li>
						<a href="/version4/goods-233.html" title="liangliang 良良 麻棉抗菌两用浴巾 黄"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242235534151_thumbnail.jpg" alt="liangliang 良良 麻棉抗菌两用浴巾 黄"></a>
						<a href="/version4/goods-233.html" title="liangliang 良良 麻棉抗菌两用浴巾 黄">liangliang 良良 麻棉抗菌...</a>
						<p>市场价：<del>￥128.00</del></p>
						<h3>商城价：<span>￥112.00</span>元</h3>
					</li>
					<li>
						<a href="/version4/goods-224.html" title="喜亲宝竹香炭防螨定型枕36*24cm（天绿色）"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242132103413_thumbnail.jpg" alt="喜亲宝竹香炭防螨定型枕36*24cm（天绿色）"></a>
						<a href="/version4/goods-224.html" title="喜亲宝竹香炭防螨定型枕36*24cm（天绿色）">喜亲宝竹香炭防螨定型枕36*...</a>
						<p>市场价：<del>￥126.00</del></p>
						<h3>商城价：<span>￥99.00</span>元</h3>
					</li>
					<li>
						<a href="/version4/goods-229.html" title="喜亲宝 竹纤维抗菌毛巾 绿色 30*66cm"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242224314923_thumbnail.jpg" alt="喜亲宝 竹纤维抗菌毛巾 绿色 30*66cm"></a>
						<a href="/version4/goods-229.html" title="喜亲宝 竹纤维抗菌毛巾 绿色 30*66cm">喜亲宝 竹纤维抗菌毛巾 绿...</a>
						<p>市场价：<del>￥36.80</del></p>
						<h3>商城价：<span>￥28.80</span>元</h3>
					</li>
					<li>
						<a href="/version4/goods-225.html" title="liangliang 良良 婴幼儿防滑落护形枕 0-3岁听梦枕（标准）"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242135318133_thumbnail.jpg" alt="liangliang 良良 婴幼儿防滑落护形枕 0-3岁听梦枕（标准）"></a>
						<a href="/version4/goods-225.html" title="liangliang 良良 婴幼儿防滑落护形枕 0-3岁听梦枕（标准）">liangliang 良良 婴幼儿防...</a>
						<p>市场价：<del>￥139.00</del></p>
						<h3>商城价：<span>￥119.00</span>元</h3>
					</li>
					<li>
						<a href="/version4/goods-237.html" title="新皇家宝贝 2011婴童护肩护手睡袋 613150009天蓝色均码 "><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242246255739_thumbnail.jpg" alt="新皇家宝贝 2011婴童护肩护手睡袋 613150009天蓝色均码 "></a>
						<a href="/version4/goods-237.html" title="新皇家宝贝 2011婴童护肩护手睡袋 613150009天蓝色均码 ">新皇家宝贝 2011婴童护肩护...</a>
						<p>市场价：<del>￥338.00</del></p>
						<h3>商城价：<span>￥338.00</span>元</h3>
					</li>
			</ul>
		</div>
		<!--第三层相同图片小图片列表结束-->
	</div>
	<!--相同内容图片结束-->
</div>
<!--第三层相同内容结束-->
<!--第四层相同内容-->
<div class="same">
	<!--第四层相同内容标题-->
	<div class="title">
		<h1>厨房用品</h1>
		<h2>Kitchen Supplies</h2>
		<span></span>
		<ul>
			<li><a href="search-cat-81.html">汤锅</a></li>
			<li><a href="search-cat-82.html">水壶</a></li>
			<li><a href="search-cat-83.html">炒锅</a></li>
			<li class="last"><a href="search-cat-84.html">奶锅</a></li>
		</ul>
	</div>
	<!--第四层相同内容标题结束-->
	<!--相同内容图片-->
	<div class="frame">
		<!--第四层相同图片大幅图片-->
		<div class="f_top">
			<!-- 创建广告标签 -->
			<!-- 调用广告信息,传递参数acid:1 -->
			<!-- 获取广告列表 -->
				<!-- 获取广告图片宽度 -->
				<!-- 获取广告图片高度 -->
					<a href="/search-cat-86.html"><img src="http://static.v4.javamall.com.cn/attachment/adv/201202281435584831.jpg" height="135px" alt="换季女装畅销款" width="976px" border="0"></a>
		</div>
		<!--第四层相同图片大幅图片结束-->
		<!--第四层相同图片小图片列表-->
		<div class="f_bottom">
			<ul>
					<li>
						<a href="goods-242.html" title="爱仕达不锈钢汤锅Q1720 20cm"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242305260437_thumbnail.jpg" alt="爱仕达不锈钢汤锅Q1720 20cm"></a>
						<a href="goods-242.html" title="爱仕达不锈钢汤锅Q1720 20cm">爱仕达不锈钢汤锅Q1720 20c...</a>
						<p>市场价：<del>￥59.00</del></p>
						<h3>商城价：<span>￥49.00</span>元</h3>
					</li>
					<li>
						<a href="goods-247.html" title="兴财4.0L不锈钢鸣笛水壶 电磁炉煤气炉适用"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242322467221_thumbnail.jpg" alt="兴财4.0L不锈钢鸣笛水壶 电磁炉煤气炉适用"></a>
						<a href="goods-247.html" title="兴财4.0L不锈钢鸣笛水壶 电磁炉煤气炉适用">兴财4.0L不锈钢鸣笛水壶 电...</a>
						<p>市场价：<del>￥148.00</del></p>
						<h3>商城价：<span>￥118.00</span>元</h3>
					</li>
					<li>
						<a href="goods-248.html" title="张小泉 紫气东来3.0L橄榄鸣壶 烧水壶 不锈钢茶壶 S80320800 "><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242326203645_thumbnail.jpg" alt="张小泉 紫气东来3.0L橄榄鸣壶 烧水壶 不锈钢茶壶 S80320800 "></a>
						<a href="goods-248.html" title="张小泉 紫气东来3.0L橄榄鸣壶 烧水壶 不锈钢茶壶 S80320800 ">张小泉 紫气东来3.0L橄榄鸣...</a>
						<p>市场价：<del>￥245.00</del></p>
						<h3>商城价：<span>￥245.00</span>元</h3>
					</li>
					<li>
						<a href="goods-251.html" title="张小泉 30CM中国小炒不锈精铁炒锅 铁锅 防锈 玻璃C30020200"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202251453040449_thumbnail.jpg" alt="张小泉 30CM中国小炒不锈精铁炒锅 铁锅 防锈 玻璃C30020200"></a>
						<a href="goods-251.html" title="张小泉 30CM中国小炒不锈精铁炒锅 铁锅 防锈 玻璃C30020200">张小泉 30CM中国小炒不锈精...</a>
						<p>市场价：<del>￥218.00</del></p>
						<h3>商城价：<span>￥218.00</span>元</h3>
					</li>
					<li>
						<a href="goods-241.html" title="双立人TWIN Select系列锅具 深炖锅 18cm 40426-180"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242301581927_thumbnail.jpg" alt="双立人TWIN Select系列锅具 深炖锅 18cm 40426-180"></a>
						<a href="goods-241.html" title="双立人TWIN Select系列锅具 深炖锅 18cm 40426-180">双立人TWIN Select系列锅具...</a>
						<p>市场价：<del>￥1,168.00</del></p>
						<h3>商城价：<span>￥1,048.00</span>元</h3>
					</li>
					<li>
						<a href="goods-240.html" title="双立人TWIN I.V.I系列 深钝锅 炖锅 18cm 40416-181"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242259084319_thumbnail.jpg" alt="双立人TWIN I.V.I系列 深钝锅 炖锅 18cm 40416-181"></a>
						<a href="goods-240.html" title="双立人TWIN I.V.I系列 深钝锅 炖锅 18cm 40416-181">双立人TWIN I.V.I系列 深钝...</a>
						<p>市场价：<del>￥1,028.00</del></p>
						<h3>商城价：<span>￥898.00</span>元</h3>
					</li>
					<li>
						<a href="goods-266.html" title="a"><img src="http://demo.javamall.com.cn:80/version4/statics/attachment/goods/201411092303559138_thumbnail.jpg" alt="a"></a>
						<a href="goods-266.html" title="a">a</a>
						<p>市场价：<del>￥1.00</del></p>
						<h3>商城价：<span>￥0.00</span>元</h3>
					</li>
					<li>
						<a href="goods-239.html" title="双立人 TWIN I.V.I 系列 不锈钢深烧锅24cm 40413-241"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202242254184284_thumbnail.jpg" alt="双立人 TWIN I.V.I 系列 不锈钢深烧锅24cm 40413-241"></a>
						<a href="goods-239.html" title="双立人 TWIN I.V.I 系列 不锈钢深烧锅24cm 40413-241">双立人 TWIN I.V.I 系列 不...</a>
						<p>市场价：<del>￥1,898.00</del></p>
						<h3>商城价：<span>￥1,248.00</span>元</h3>
					</li>
					<li>
						<a href="goods-252.html" title="张小泉母爱飘香 32CM炫动力时尚精铁炒锅 铁锅 C30040100"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202251456202107_thumbnail.jpg" alt="张小泉母爱飘香 32CM炫动力时尚精铁炒锅 铁锅 C30040100"></a>
						<a href="goods-252.html" title="张小泉母爱飘香 32CM炫动力时尚精铁炒锅 铁锅 C30040100">张小泉母爱飘香 32CM炫动力...</a>
						<p>市场价：<del>￥140.00</del></p>
						<h3>商城价：<span>￥98.00</span>元</h3>
					</li>
					<li>
						<a href="goods-255.html" title="【ROICHEN】进口锅具 奶锅 不粘锅 18cm RNC-18S"><img src="http://static.v4.javamall.com.cn/attachment/goods/201202251519580553_thumbnail.jpg" alt="【ROICHEN】进口锅具 奶锅 不粘锅 18cm RNC-18S"></a>
						<a href="goods-255.html" title="【ROICHEN】进口锅具 奶锅 不粘锅 18cm RNC-18S">【ROICHEN】进口锅具 奶锅 ...</a>
						<p>市场价：<del>￥444.60</del></p>
						<h3>商城价：<span>￥342.00</span>元</h3>
					</li>
			</ul>
		</div>
		<!--第四层相同图片小图片列表结束-->
	</div>
	<!--相同内容图片结束-->
</div>
<!--第四层相同内容结束-->
<script>
var defaultOpts = { interval: 10000, fadeInTime: 300, fadeOutTime: 200 };
//Iterate over the current set of matched elements
	var _titles = $("ul.slide-txt li");
	var _titles_bg = $("ul.op li");
	var _bodies = $("ul.slide-pic li");
	var _count = _titles.length;
	var _current = 0;
	var _intervalID = null;
	var stop = function() { window.clearInterval(_intervalID); };
	var slide = function(opts) {
		if (opts) {
			_current = opts.current || 0;
		} else {
			_current = (_current >= (_count - 1)) ? 0 : (++_current);
        }
        _bodies.filter(":visible").fadeOut(defaultOpts.fadeOutTime, function() {
			_bodies.eq(_current).fadeIn(defaultOpts.fadeInTime);
			_bodies.removeClass("cur").eq(_current).addClass("cur");
		});
		_titles.removeClass("cur").eq(_current).addClass("cur");
		_titles_bg.removeClass("cur").eq(_current).addClass("cur");
	}; //endof slide
	var go = function() {
		stop();
		_intervalID = window.setInterval(function() { slide(); }, defaultOpts.interval);
	}; //endof go
	var itemMouseOver = function(target, items) {
		stop();
		var i = $.inArray(target, items);
		slide({ current: i });
	}; //endof itemMouseOver
	_titles.hover(function() { if($(this).attr('class')!='cur'){itemMouseOver(this, _titles); }else{stop();}}, go);
	//_titles_bg.hover(function() { itemMouseOver(this, _titles_bg); }, go);
	_bodies.hover(stop, go);
	//trigger the slidebox
	go();
	 
</script>
		<div id="service">
			<div class="inner_con">
				<div>
					<div class="box use">
						<span></span>
						<h1>新手上路</h1>
						<ul>
	<li><a href="../help-17-10.html">顾客必读</a></li>
	<li><a href="../help-17-13.html">积分规则</a></li>
</ul>
					</div>
					<div class="box pay">
						<span></span>
						<h1>支付</h1>
						<ul>
	<li><a href="../help-19-20.html">支付方式</a></li>
	<li><a href="../help-19-28.html">付款常见问题</a></li>
</ul>
					</div>
					<div class="box ship">
						<span></span>
						<h1>配送</h1>
						<ul>
	<li><a href="../help-22-21.html">配送方式</a></li>
	<li><a href="../help-22-23.html">收货与验货</a></li>
</ul>
					</div>
					<div class="box serve">
						<span></span>
						<h1>售后</h1>
						<ul>
	<li><a href="../help-23-16.html">售后服务</a></li>
	<li><a href="../help-23-14.html">退换货保障</a></li>
</ul>
					</div>
					<div class="box message">
						<span></span>
						<h1>帮助</h1>
						<ul>
	<li><a href="../help-18-15.html">游客购物通道</a></li>
	<li><a href="../help-18-19.html">购物流程</a></li>
</ul>
					</div>
					<div class="box about">
						<span></span>
						<h1>关于我们</h1>
						<ul>
	<li><a href="../help-21-7.html">关于我们</a></li>
	<li><a href="../help-21-8.html">联系我们</a></li>
</ul>
					</div>
				</div>
			</div>
		</div>
		<!--帮助结束-->

	<!--正文结束-->
	<!--页脚-->
	<div id="footer">
		<!--页脚友情连接-->

			<img src="/version4/themes/default/images/bottom_img_05.jpg"><img src="/version4/themes/default/images/bottom_img_09.jpg"><img src="/version4/themes/default/images/bottom_img_11.jpg">
		<!--页脚友情连接结束-->
		<!--页脚信息-->
		<p>Javashop版权所有</p>
		<!--页脚信息结束-->
	</div>



<div id="datepicker_div"></div><div class="" style="display: none; visibility: hidden; position: absolute;"><table class="ui_border"><tbody><tr><td class="ui_lt"></td><td class="ui_t"></td><td class="ui_rt"></td></tr><tr><td class="ui_l"></td><td class="ui_c"><div class="ui_inner"><table class="ui_dialog"><tbody><tr><td colspan="2"><div class="ui_title_bar"><div class="ui_title" unselectable="on" style="cursor: auto;"></div><div class="ui_title_buttons"><a class="ui_min" href="javascript:void(0);" title="最小化" style="display: none;"><b class="ui_min_b"></b></a><a class="ui_max" href="javascript:void(0);" title="最大化" style="display: none;"><b class="ui_max_b"></b></a><a class="ui_res" href="javascript:void(0);" title="还原"><b class="ui_res_b"></b><b class="ui_res_t"></b></a><a class="ui_close" href="javascript:void(0);" title="关闭(esc键)" style="display: inline-block;">×</a></div></div></td></tr><tr><td class="ui_icon" style="display: none;"><img src="" class="ui_icon_bg"></td><td class="ui_main" style="width: auto; height: auto;"><div class="ui_content" style="padding: 10px;"></div></td></tr><tr><td colspan="2"><div class="ui_buttons" style="display: none;"></div></td></tr></tbody></table></div></td><td class="ui_r"></td></tr><tr><td class="ui_lb"></td><td class="ui_b"></td><td class="ui_rb" style="cursor: auto;"></td></tr></tbody></table></div></body></html>