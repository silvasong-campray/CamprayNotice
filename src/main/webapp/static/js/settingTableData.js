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
var SettingTable = function () {
	
	var initEditables = function() {

		// set editable mode based on URL parameter
		if (Metronic.getURLParameter('mode') == 'inline') {
			$.fn.editable.defaults.mode = 'inline';
			$('#inline').attr("checked", true);
			jQuery.uniform.update('#inline');
		} else {
			$('#inline').attr("checked", false);
			jQuery.uniform.update('#inline');
		}

		// global settings
		$.fn.editable.defaults.inputclass = 'form-control';
		$.fn.editable.defaults.url = rootURI + "setting/editsetting?rand=" + Math.random();

		$('#title').editable({
			url : rootURI + "setting/editsetting?rand=" + Math.random(),
			type : 'text',
			pk : 1,
			disabled:true,
			name : 'title',
			title : '标题',
			success : function(data) {
				var Obj=$.parseJSON(data);
				if(Obj.status){
					handleAlerts(Obj.info,"success","");
				}else{
					handleAlerts(Obj.info,"danger","");
				}
				
			}
		});

		

		
		
		
	
	}	

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
        	// init editable elements
			initEditables();

			// init editable toggler
			$('#enable').click(function() {
				$('#setting .editable').editable('toggleDisabled');
			});

			// handle editable elements on hidden event fired
			$('#setting .editable').on('hidden', function(e, reason) {
				if (reason === 'save' || reason === 'nochange') {
					var $next = $(this).closest('tr').next().find('.editable');
					if ($('#autoopen').is(':checked')) {
						setTimeout(function() {
							$next.editable('show');
						}, 300);
					} else {
						$next.focus();
					}
				}
			});
        }

    };

}();