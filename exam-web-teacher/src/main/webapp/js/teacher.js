var teacher = {}
teacher.toTeacherQuery = function (pageNo){
    pageNo = pageNo ? pageNo : 1;
    var param = {
        pageNo : pageNo,
        tname : $('#tname').val()
    };

    $.post('teacher/pageTeacher.html',param,function (view){
        $('#pageTemplate').replaceWith(view);
    });

}

teacher.toClearTeacherQuery = function (){
    $('#tname').html('');
    teacher.toTeacherQuery();
}

teacher.toPageTeacherQuery = function(pageNo){
    alert(pageNo);
    teacher.toTeacherQuery(pageNo);
}

//方法是用来新建教师的
teacher.toAdd = function (){
    $.post('teacher/teacherAddTemplate.html',{},function (view){
        $('#teacher-myModal-label').html('添加教师信息');
        $('#teacher-modal-body').html(view);
        $('#teacher-modal-submit').click(function (){
            var param = {
                tname : $('#teacher-tname').val()
            }
            $.post('teacher/teacherAddTemplate',param,function (f){
                if(f == true){
                    alert('创建成功');
                //    点击创建成功后，关闭这个模态框
                    $('#teacher-myModal').modal('hide');
                //    这个时候需要再次刷新这个表格
                    teacher.toTeacherQuery();
                }else{
                    alert('名称重复');
                }
            })
        });
        //展示这个模态框
        $('#teacher-myModal').modal('show');
    })
}
//先在要处理的问题是
//当点击确定按钮的时候，前端不知道使用的是那个按钮，导致两个方法都被触动了
//创建的是后，即会修改，修改的时候也有创建

//方法是用来编辑教师信息的
teacher.toEdit = function (id){
    $.post('teacher/teacherAddTemplate.html',{id:id},function (view){
        $('#teacher-myModal-label').html('修改教师信息');
        $('#teacher-modal-body').html(view);
        $('#teacher-modal-submit').click(function () {
            var param = {
                id : id,
                tname : $('#teacher-tname').val()
            }
            $.post('teacher/teacherEditTemplate',param,function (f){
                if(f == true){
                    alert("修改成功");
                //    关闭模态框
                    $('#teacher-myModal').modal('hide');
                //    刷新表格
                    teacher.toTeacherQuery();
                }else{
                    alert("名称重复，修改失败");
                }
            })
        });
        //展示这个模态框
        $('#teacher-myModal').modal('show');
    })
}

//我们对这个最上面的复选框进行一下处理
teacher.toCheckAll = function (){
    var f = $('#teacherGrid thead :checkbox').prop('checked');
    $('#teacherGrid tbody :checkbox').prop('checked',f);
}

//方法是用来删除选中的用户信息的
teacher.toDelete = function (){
    if($('#teacherGrid tbody :checked') == 0){
        alert("请选择要删除的内容");
        return;
    }

    if(!confirm('是否确认删除选中的记录')){
        return;
    }

    var ids = '' ;
    $('#teacherGrid tbody :checked').each( function(i,element){
        var id = element.value ;
        ids += id+"," ;
    } );

    $.post('teacher/deleteAll',{ids:ids},function(){
        alert('删除成功') ;
        var pageNo = $('.pagination .active').text() ;
        teacher.toTeacherQuery(pageNo) ;
    });
}
