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
                    title: '配置修改',
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    area: ['800px', '700px'],
                    content: context + 'bugly/exception/updateAlarmConfig?id='+row.id,
                    end: function () {
                        vm.getDifferentExceptionList();
                    }
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
                            var page = this.current_page;
                             var pageSize = this.page_size;
                            $.ajax({
                                url: context + 'exception/findAllServiceType?pageSize='+pageSize+'&page=' + page,
                                type: 'GET',
                                success: function (res) {
                                    vm.tableData = res.data.sysUserList;
                                    vm.total = res.data.total;
                                    vm.page_size = res.data.page_size;
                                    vm.current_page = res.data.page;
                                }
                            });
                        },



            search: function () {
                    var searchBugly = {
                           'pageSize':this.page_size,
                           'page':this.current_page,
                           'serviceName':bugly_search.search_errorLocaltion.value
                           };

                $.ajax({
                    cache : true,
                    type : "POST",
                    url : context + 'exception/serviceSearch',
                    data :JSON.stringify(searchBugly),
                    dataType : 'json',
                    contentType:'application/json',
                    error : function(request) {
                        parent.layer.alert("Connection error");
                    },
                    success : function(res) {
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