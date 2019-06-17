<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<%
	pageContext.setAttribute("Path", request.getContextPath());
%>
<script src="${Path}/static/js/jquery.js"></script>
<link rel="stylesheet" href="${Path}/static/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${Path}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="${Path}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<title>农作物牵手</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<style>
	.myresult{
		   box-shadow: 0 4px 20px 0px rgba(0, 0, 0, 0.14), 0 7px 10px -5px rgba(0, 168, 255, 0.4);background-color: #00bfff;
		    color: #fff; width: 200px; height: 80px; font-size: 1.5em; padding: 25px; border-radius: 5px;
	}
	.mp{
	display: inline-flex;margin-top: 5px
	}
	.info-content {
		display: block;
		width: 100%;
		height: 50px;
		background-color: #fff;
		margin-bottom: 10px;
		box-shadow: 0 4px 20px 0px rgba(0, 0, 0, 0.14), 0 7px 10px -5px;
		font-size: 1.5em;
		font-weight: 700;
		padding: 0;
		border-radius: 5px;
	}
	.info-content p {
		padding-top: 10px;
	}
	
</style>
</head>
<body>
	<div class="list-group">
		<a href="${Path}/yrd.jsp"
			class="list-group-item list-group-item-action active"> 您当前所处温度带：<%=request.getAttribute("local")%>
		</a> <a href="#" class="list-group-item list-group-item-action">您当前所在经度：<%=request.getAttribute("longitude")%></a>
		<a href="#" class="list-group-item list-group-item-action">您当前所在纬度：<%=request.getAttribute("latitude")%></a>
		<a href="#" class="list-group-item list-group-item-action disabled"
			tabindex="-1" aria-disabled="true">请写入您的种植意愿</a>
	</div>



	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" id = "qs_modal">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content" style="transform: scale(.9);">
				<div class="modal-header" style="text-align: center">
					<h4 class="modal-title" >牵手</h4>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<br>
							<form class="form-horizontal" id="update_form">
						
								<div class="form-group">
									<label class="col-sm-2 control-label">您所处的地形</label>
									<div class="col-sm-10">
										<select class="form-control">
										  <option>平原</option>
										  <option>山地</option>
										  <option>丘陵</option>
										  <option>沙滩</option>
										  <option>林中</option>
										  <option>河谷</option>
										  <option>青石山区</option>
										  <option>沙石山区</option>
										  <option>便于排灌的块地</option>
										  <option>在花盆或者阳台种植</option>
										</select>
									</div>
								</div>
								<br>
								<div class="form-group">
									<label class="col-sm-2 control-label">您愿意投入照看的时间</label>
									<div class="col-sm-10">
										<label class="radio-inline"> <input type="radio"
											name="gender" id="update_gender1" value="4" >
											极多
										</label> 
										<label class="radio-inline"> <input type="radio"
											name="gender" id="update_gender1" value="3" >
											多
										</label> 
										<label class="radio-inline"> <input type="radio"
											name="gender" id="update_gender2" value="2" checked="checked"> 一般
										</label>
										<label class="radio-inline"> <input type="radio"
											name="gender" id="update_gender2" value="1">少
										</label>
										<label class="radio-inline"> <input type="radio"
											name="gender" id="update_gender2" value="0">极少
										</label>
									</div>
								</div>
								<br>
								<div class="form-group">
									<label class="col-sm-2 control-label">土壤PH值范围</label>
										<div class="col-sm-10">
											<div class="row">
												<div class="col-xs-4">
												   <input type="text" class="form-control" placeholder="最低值">
												 </div>
												 <div class="col-xs-4">
												   <input type="text" class="form-control" placeholder="最高值">
												 </div>
											</div>
										</div>
								</div>
								<br>
								<div class="form-group">
									<label class="col-sm-2 control-label">您对收益的要求</label>
									<div class="col-sm-4">
										<select class="form-control" name="dId" id="update_depts">
										  <option>无所谓，有趣就行</option>
										  <option>随便，但还是希望有一点</option>
										  <option>看重，能赚点钱</option>
										  <option>非常看重，希望能获得较大回报</option>
										</select>
									</div>
								</div>
								<br>
								<div class="form-group">
									<label class="col-sm-2 control-label">我们想听听您内心的想法</label>
									<div class="col-sm-4">
										<textarea class="form-control" rows="3" placeholder="后续将提供更个性的服务">
										</textarea>
									</div>
								</div>
							</form>
						</div>
					</div>
				   <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				        <button type="button" class="btn btn-primary" id  = "doit_button">确定</button>
     			  </div>
				</div>
			</div>
		</div>
	</div>              

	<div style="text-align: center">
		<button type="button" class="btn btn-primary" style = " padding: 20px; border-radius: 10px; font-size: 20px;" data-toggle="modal"
			data-target=".bs-example-modal-lg">
			<span class="glyphicon glyphicon-pencil"  aria-hidden="true"></span>
			牵手
		</button>
	</div>
	
	<br>
	<br>
	
	<div id = "resultnum" style=" text-align:center ">
		<!-- 结果数量显示 -->
  	</div>
  	<br>
	<div id = "resultlist">
		<!-- 结果显示 -->
	</div>
	
	<!-- 植物模态框 -->
     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
         <div class="modal-dialog">
             <div class="modal-content">
                 <div class="modal-header" style="text-align: center">
                     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                     <h4 class="modal-title" id="myModalLabel"><span id="modal-title-1" >植物名称</span> </h4>
                 </div>
                 <div class="modal-body">
                 	<div class="info">
                 		<div class="info-content text-center" ><p id = "startTem">最适温度  ℃</p></div>
                 		<div class="info-content text-center" ><p id = "endTem">最适温度  ℃</p></div>
                 		<div class="info-content text-center" ><p id = "local">温度带</p></div>
                 		<div class="info-content text-center" ><p id = "landform">地势</p></div>
                 	</div>	
                 </div>
                 <div class="modal-footer">
                     <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                 </div>
             </div>
             <!-- /.modal-content -->
         </div>
         <!-- /.modal -->
     </div>
	<br></br>
	
	
  	
  	<div id="my_result" >
  	

  	</div>
	<script>
		var list;

		//save click
		$("#doit_button").click(function(){
			$("#resultlist").empty();
			$("#resultnum").empty();
			$("#qs_modal").modal('hide')
			$.ajax({
				url:"${Path}/result",
				type:"Get",
				data:"local="+"亚热带",
				success:function(result){
					resultList = result;
					//成功插入记录后关闭模态框
					if(result.state == 100){
						list = result.map.ResultList.list;
						build_zw_table(result);

					}else{
						alert("faile");
					}

				}
			})
		});
		
		function build_zw_table(result){
			var list =result.map.ResultList.list;
			var zw_num = $("<td></td>").append(result.map.ResultList.total);
			$("<label></label>").append("为您匹配到:"+result.map.ResultList.total+"结果！").appendTo("#resultnum");
			var i = 1;
			$.each(list,function(index,item){
				var zw_name = $("<i></i>").addClass("fa fa-tree  myresult").append("<span></span>").addClass("mp").append(item.name);
				var zw_local = $("<td></td>").append(item.local);
				var zw_startTem = $("<td></td>").append(item.startTem);
				var zw_endTem = $("<td></td>").append(item.endTem);
				var zw_landform = $("<td></td>").append(item.landform);
				var name = "#"+i;
				$("<div></div>").addClass("text-center").attr("data-toggle","modal").attr("data-target","#myModal").attr("id",i++).append(zw_name).appendTo("#resultlist");
				$(name).on("click", function () {
					$("#modal-title-1").text(item.name);
					$("#startTem").text(item.startTem+" ℃");
					$("#endTem").text(item.endTem+" ℃");
					$("#local").text(item.local);
					$("#landform").text(item.landform);
				})
			});
		}


		</script>
		
		
</body>
</html>