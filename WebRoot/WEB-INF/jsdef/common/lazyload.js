/**
 * 短暂的页面混乱
 * 页面比较复杂的时候，easyui对其渲染往往需要一个较长的过程，这时候，加载进来的html片段毫无布局可以，过一会自动会好，这时候easyui已经完成对它的渲染。
 * 如何避免这个混乱的过程呢，还得靠easyui的一个基础插件——解析器(Parser)
 */
function show(){  
    jQuery("#loading").fadeOut("normal", function(){  
        jQuery(this).remove();  
    });  
}      
var delayTime;  
jQuery.parser.onComplete = function(){  
    if(delayTime)   
        clearTimeout(delayTime);  
    delayTime = setTimeout(show,100);  
}  