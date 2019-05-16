//员工状态格式化
function employeeStateFormat(value,row,index){
    if(value==0){
        return "<span style='color: green;'>正常</span>"
    }else if(value==-1){
        return "<span style='color: grey;text-decoration:line-through '>离职</span>"
    }else {
        return "";
    }
}
//对象格式化
function objFormat(value,row,index){
    return value?value.name||value.realname||value.username:"";

}

$(function(){

    //登录输入框效果
    $('.form_text_ipt input').focus(function(){
        $(this).parent().css({
            'box-shadow':'0 0 3px #bbb',
        });
    });
    $('.form_text_ipt input').blur(function(){
        $(this).parent().css({
            'box-shadow':'none',
        });
        //$(this).parent().next().hide();
    });

    //表单验证
    $('.form_text_ipt input').bind('input propertychange',function(){
        if($(this).val()==""){
            $(this).css({
                'color':'red',
            });
            $(this).parent().css({
                'border':'solid 1px red',
            });
            //$(this).parent().next().find('span').html('helow');
            $(this).parent().next().show();
        }else{
            $(this).css({
                'color':'#ccc',
            });
            $(this).parent().css({
                'border':'solid 1px #ccc',
            });
            $(this).parent().next().hide();
        }
    });
});


