<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>主页界面</title>
<%@ include file="common/common_header.jsp"%>
<%@ include file="common/contextMenu_header.jsp"%>
<%@ include file="common/gridster_header.jsp"%>

<style type="text/css"> 
.gridster * {
  margin:0;
  padding:0;
}




/*/
/* demo
/*/


body {
    font-size: 16px;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    color: #444;
    /* margin: 30px 40px; */
}

.controls {
    margin-bottom: 20px;
}
/*/
/* gridster
/*/

.gridster ul {
   background-color:#eee
}

.gridster li {
    font-size: 1em;
    font-weight: bold;
    text-align: center;
    line-height: 100%;
}


.gridster {
    margin: 0 auto;

    opacity: .8;

    -webkit-transition: opacity .6s;
    -moz-transition: opacity .6s;
    -o-transition: opacity .6s;
    -ms-transition: opacity .6s;
    transition: opacity .6s;
}

.gridster .gs-w {
    background: #DDD;
    cursor: pointer;
}

.gridster .player {
    background: #BBB;
}


.gridster .preview-holder {
    border: none!important;
    background: red!important;
}
 
</style>
<script type="text/javascript">
      var gridster;
      // same object than generated with gridster.serialize() method
      var serialization = [{"col":1,"row":1,"size_x":2,"size_y":2},{"col":4,"row":2,"size_x":1,"size_y":2},{"col":4,"row":1,"size_x":1,"size_y":1},{"col":1,"row":3,"size_x":1,"size_y":1},{"col":2,"row":4,"size_x":3,"size_y":1},{"col":1,"row":4,"size_x":1,"size_y":1},{"col":2,"row":5,"size_x":1,"size_y":1},{"col":2,"row":6,"size_x":1,"size_y":1}];


      // sort serialization
      serialization = Gridster.sort_by_row_and_col_asc(serialization);

      $(function(){
		var cWidth = document.documentElement.clientWidth*0.96;
		var cHeight = document.documentElement.clientHeight*0.94;
		//alert(cWidth+" " +cHeight);
        gridster = $(".gridster ul").gridster({
          max_cols:4,
          widget_base_dimensions: [cWidth/4, cHeight/3],
          widget_margins: [5, 5],

          serialize_params: function(w, wgd) {
        	  return {OPERATOR_ID:'${loginOperator.OPERATOR_ID}',HOME_MODULE_ID:w.attr('id'), DATA_COL: wgd.col, DATA_ROW: wgd.row, DATA_SIZEX: wgd.size_x, DATA_SIZEY: wgd.size_y } 
        	}
        }).data('gridster');
		//初始化后禁用
        gridster.disable();
		
       /*  $('.js-seralize').on('click', function() {
            gridster.remove_all_widgets();
            $.each(serialization, function(index,value) {
                gridster.add_widget('<li><span>'+index+'</span></li>', this.size_x, this.size_y, this.col, this.row);
            });
        });

        $('.js-seralizelog').on('click', function() {
            var s = gridster.serialize();
            $('#log').val(JSON.stringify(s));
        }) */
        
	    $(document).bind("contextmenu",function(e){  
	    	e.preventDefault();
			$('#contextMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			}); 
	    });
        
      });
      
      function changeHomePageModule(){
    	  var css = $('#collapseExample').attr('class');
    	  if(css=='collapse'){
    		  gridster.enable();
    	  }else{
    		  gridster.disable();
    	  }
    	  
  		  $('.gridster ul').css("background-color","#EFEFEF"); 
      }
      
      function nothing(){
    	  
      }
      
      function toggelModule(id,name,col,row,sizex,sizey){
    	  if($("#cb_"+id).prop("checked")==false){
    		  gridster.remove_widget( $('#'+id), nothing );
    		  //$('#'+id).remove();
    	  }else{
    		   var gridste = $(".gridster ul")
    		   //gridste.append('<li id="'+id+'" data-col="'+col+'" data-row="'+row+'" data-sizex="'+sizex+'" data-sizey="'+sizey+'" class="gs-w" style="display: list-item;">'+name+'</li>');
    		   gridster.add_widget( '<li id="'+id+'">'+name +'</li>', sizex, sizey, col, row )
    		  
    		  
/*     		  var widgets = [
    		                 ['<li id="'+id+'">'+name +'</li>', sizex, sizey]
    		             ];

    		             $.each(widgets, function(i, widget){
    		                 gridster.add_widget.apply(gridster, widget)
    		             }); */
    		  
    	  }

      }
      
      function saveDefModify(){
    	  var jsonDataPer = gridster.serialize();
    	  jsonDataPer= JSON.stringify(jsonDataPer);
    	  //jsonDataPer="["+jsonDataPer+"]";
    		var jsonData = {"dataJsonStr":jsonDataPer,"operatorId":"${loginOperator.OPERATOR_ID}"};
    	  defaultAjax("${ctx}/acHomeModule/batchInsertOperHomeModule.do",jsonData,homeModuleCallBack);
    	  
      }
      
      function homeModuleCallBack(data){
      	//var ret = jQuery.parseJSON(data);
      	BaseUtils.hideWaitMsg();
      	alert(data.msg);
      	if (data.flag) {
      		window.location.href="${ctx }/home.do";
      	}
      }
    </script>
</head>
<body>
	<div id="contextMenu" class="easyui-menu" style="width:120px;display:none;">
		<div id="createFuncGroup" data-options="iconCls:'icon-edit'" onclick="javascript:changeHomePageModule();" role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
<!-- 		   <a class="btn btn-xs" onclick="javascript:changeHomePageModule();" role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
 -->			首页定制
<!-- 			</a>
 -->		</div>
		
		<!-- <div class="menu-sep"></div> -->
	</div>

	<div class="collapse" id="collapseExample">
	  <div class="well well-sm" style="margin-bottom:1px">
	  	<button onclick="saveDefModify()" type="button" class="btn btn-primary btn-sm">保存定制</button>
	  	<c:forEach var="r" items="${allPermHomeModuleViewList}" varStatus="status">
	  		<label class="checkbox-inline">
			  <input onclick="toggelModule('${r.HOME_MODULE_ID }','${r.MODULE_NAME }','${r.DATA_COL }','${r.DATA_ROW }','${r.DATA_SIZEX }','${r.DATA_SIZEY }')" type="checkbox" <c:if test="${r.CHECKED =='true'}">checked</c:if> id="cb_${r.HOME_MODULE_ID }" value="${r.HOME_MODULE_ID }"> ${r.MODULE_NAME }
			</label>
	  	</c:forEach>
	  </div>
	</div>
   <div class="gridster ready"  style="width: 100%; height: 100%;margin:0">
   		<ul>
   			<c:forEach var="r" items="${operHomeModuleViewList}" varStatus="status">
				<li id="${r.HOME_MODULE_ID }" data-col="${r.DATA_COL }" data-row="${r.DATA_ROW }" data-sizex="${r.DATA_SIZEX }" data-sizey="${r.DATA_SIZEY }" class="gs-w player-revert" style="display: list-item; position: absolute;">
				
				<div class="panel panel-primary" style="width: 100%; height: 100%;">
				  <div class="panel-heading" style="text-align:left">
			        <h3 class="panel-title">${r.MODULE_NAME }</h3>
			      </div>
			      <div class="panel-body" style="width: 100%; height: 100%;">
					${r.HTML_CONTENT }
				  </div>
				</div>
				</li>
			</c:forEach>
   		</ul>
<!-- 	<ul>
			
			<li id="2" data-col="4" data-row="2" data-sizex="1" data-sizey="2"
				class="gs-w player-revert"
				style="display: list-item; position: absolute;"><span>2</span></li>
			<li id="3" data-col="4" data-row="1" data-sizex="1" data-sizey="1"
				class="gs-w" style="display: list-item;"><span>3</span></li>

			<li id="1" data-col="1" data-row="1" data-sizex="2" data-sizey="3"
				class="gs-w" style="display: list-item;">
					<div style="display:none">1</div><iframe id="orgRightFrame" style="width:100%;height:100%" name="orgRightFrame" src="http://localhost:8080/platform/acHomeModule/forQueryPage.do"   frameborder="0" ></iframe>
			</li>
		</ul> -->

	</div>
</body>
</html>