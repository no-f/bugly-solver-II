$().ready(function(){
    var vm = new Vue({
        el: '#app',
        data: {
            tableData: [],
            total: 50,
            page_size: 5,
            current_page: 1
        },
        methods: {
            handleEdit: function(row) {
                layer.open({
                    type: 2,
                    title: '状态变更',
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    area: ['800px', '700px'],
                    content: context + 'bugly/exception/update?id='+row.id,
                    end: function () {
                        vm.getDifferentExceptionList();
                    }
                });
            },

            handleDetail:function(row) {
                layer.confirm("您确定要删除吗？", function (index) {
                    $.ajax({
                        url: context + 'user/deleteUser?id=' + row.id,
                        type: 'GET',
                        success: function (res) {
                            if (res.code === 200){
                               if (res.data.code === 200){
                                   layer.msg("操作成功");
                                   vm.getUserList();
                               } else if(res.data.code === 500){
                                   layer.msg("操作失败");
                               }
                            }
                        }
                    });
                });
            },

            handleSizeChange: function (val) {
                vm.page_size = val;
                this.getDifferentExceptionList();
            },
            handleCurrentChange: function (val) {
                vm.current_page = val;
                this.getDifferentExceptionList();
            },

            getDifferentExceptionList: function () {
                            $.ajax({
                                url: context + 'exception/findAll',
                                type: 'GET',
                                success: function (res) {
                                    vm.tableData = res.data.sysUserList;
                                    vm.total = res.data.total;
                                    vm.page_size = res.data.page_size;
                                    vm.current_page = res.data.page;
                                }
                            });
                        }

        },
        mounted: function () {
            this.getDifferentExceptionList();
        }
    });
});