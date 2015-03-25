//jquery插件把表单序列化成json格式的数据start 
(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

var rootURI="/";
var Table = function () {
	var oTable;
	var selected = [];
    var handleTable = function () {				
		var table=$('#table');
		 oTable = table.dataTable({
			"lengthChange":false,
        	"filter":true,
        	"sort":false,
        	"info":true,
        	"processing":true,
        	"scrollX":"100%",
           	"scrollXInner":"100%",
            "displayLength": 10,
            "dom": "t<'row'<'col-md-6'i><'col-md-6'p>>",
            "columnDefs": [
                      {                    
                     'targets': 0,   
                      'render':function(data,type,row){
    	              return '<div class="checker"><span><input type="checkbox" class="checkboxes"/></span></div>';
                      },
    //'defaultContent':'<div class="checker"><span><input type="checkbox" class="checkboxes" value="1"/></span></div>'                    
                }
            ],
            "columns": [
               { title: ""},
               { title: "ID", data: "id"},
               { title: "标题", data: "title"},
               { title: "排序", data: "sort"},
	           { title: "内容",   data: "content" },
	           { title: "创建时间",    render: function(data,type,row){
	        	                  
	        	                  var date = new Date(row.createTime);
	        	                  return date;
	        	                  
	           } },
	           
	        ],
	        "serverSide": true,
	        "serverMethod": "GET",
	        "ajaxSource": rootURI+"/dashbord/info_list?rand="+Math.random(),
	        "fnDrawCallback":function(oSetting){
	        	selected=[];
	        },
	      
		});		
		 
		   //打开删除对话框前判断是否已选择要删除的行
			$("#delete").on("click",function(event){
					if(selected.length==0){
						handleAlerts("请至少选择一行.","warning","");				
						return false;
					}
				});
			 //打开编辑对话框前判断是否已选择要编辑的行
			$("#edit").on("click",function(event){
					if(selected.length==0){
						handleAlerts("请至少选择一行.","warning","");				
						return false;
					}else if(selected.length>1){
						handleAlerts("请选择一行.","warning","");				
						return false;
					}else{
						window.location.href="info_edit?id="+selected.join();
					}
				});
	        //单选
	        table.on('change', 'tbody tr .checkboxes', function () {
	            $(this).parents('tr').toggleClass("active");            
	            var data = oTable.api().row($(this).parents('tr')).data();
	            var id = data.id;
	            var index = $.inArray(id, selected);     
	            if ( index === -1 ) {
	                selected.push( id );
	                $(this).parents('span').addClass("checked");
	                $(this).attr("checked","checked");
	            } else {
	                selected.splice( index, 1 );
	                $(this).parents('span').removeClass("checked");
	                $(this).removeAttr("checked");
	            }
	        });
	        //确认删除
	        $("#deleteBtn").on("click",function(event){
				$.ajax({
					type:'post',
					dataType:'json',
					url:rootURI+'dashbord/info_delete/'+selected.join(),
				    success:function(data,status){
				    	if(status == "success"){
				    		//var obj = jQuery.parseJSON(data);
				    		if(data.status){
				    			selected = [];
				    			oTable.api().draw();
				    			oTable.$('th span').removeClass();
				    			handleAlerts("删除成功.","success","");	
				    		}else{
				    			handleAlerts("删除失败.","error","");	
				    		}
				    	}
				    },
				    error:function(XMLHttpRequest, textStatus, errorThrown){
		            	 alert(errorThrown);
		             }
				});
			});
		/* handle show/hide columns*/
        var tableColumnToggler = $('#column_toggler');		
		$('input[type="checkbox"]', tableColumnToggler).change(function () {
		    /* Get the DataTables object again - this is not a recreation, just a get of the object */
		    var iCol = parseInt($(this).attr("data-column"));
		    var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
		    oTable.fnSetColumnVis(iCol, (bVis ? false : true));
		});
        
		
        
	};
	
	//提示信息处理方法（是在页面中指定位置显示提示信息的方式）
	var handleAlerts = function(msg,msgType,position) {         
        Metronic.alert({
            container: position, // alerts parent container(by default placed after the page breadcrumbs)
            place: "prepent", // append or prepent in container 
            type: msgType,  // alert's type (success, danger, warning, info)
            message: msg,  // alert's message
            close: true, // make alert closable
            reset: true, // close all previouse alerts first
            focus: false, // auto scroll to the alert after shown
            closeInSeconds: 10, // auto close after defined seconds, 0 never close
            icon: "warning" // put icon before the message, use the font Awesone icon (fa-[*])
        });        

   
    


 
    
 
		
	}
 
    return {
        //main function to initiate the module
        init: function (rootPath) {
        	rootURI=rootPath;
        	handleTable();  
        		
        }

    };

}();
