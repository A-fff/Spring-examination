var main={};

main.exit = function (){
//    弹出一个提示框
    if(!confirm('是否确认退出'))
        return ;
    location.href='common/exit';
}

main.toUpdatePass = function (){
    //先发送一个请求获取这个修改面的body部分
    $.post('common/toUpdatePass.html',{},function(view){
        //获取完body中的内容后，转入进去
        $('#modal-body').html(view);
        //展示模态框
        $('#myModal').modal('show');
    });
}

main.UpdatePass = function (){
    var oldPass = $('#old-pass').val();
    var newPass = $('#new-pass').val();
    var affirmPass = $('#affirm-pass').val();
    //判断新密码是否和久密码相同
    if(newPass == oldPass){
        alert("新密码不能和久密码相同");
        return ;
    }
    //判断两次输入的新密码是否一直
    if(newPass != affirmPass){
        alert("两次输入的新密码不相同");
        return ;
    }

    var param = {
        oldPass : oldPass,
        newPass : newPass
    }
    $.post('common/UpdatePass',param,function (f){

        if(f == true){
            //提示密码修改成功，返回登录页面
            alert("修改密码成功");
            location.href='common/login.html';
        }else{
            //提示修改密码失败
            alert("修改密码失败");
        }
    })
}