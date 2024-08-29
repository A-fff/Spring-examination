var main={};

main.exit = function (){
//    弹出一个提示框
    if(!confirm('是否确认退出'))
        return ;
    location.href='common/exit';
}