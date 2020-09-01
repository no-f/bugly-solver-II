$().ready(function(){
    var vm = new Vue({
        el: '#app',
        data: {
            time:"",
            tableData: [],
            total: 50,
            page_size: 5,
            current_page: 1
        },
        methods: {
            handleDetail: function(row) {
                layer.open({
                    type: 2,
                    title: '查看完整异常',
                    maxmin: true,
                    shadeClose: false,
                    area: ['800px', '700px'],
                    content: context + 'bugly/exception/detail_show?id='+row.id,
                    end: function () {
                        vm.getExceptionDatailList();
                    }
                });
            },

            handleSizeChange: function (val) {
                vm.page_size = val;
                this.searchdetail();
            },
            handleCurrentChange: function (val) {
                vm.current_page = val;
                this.searchdetail();
            },

            getExceptionDatailList: function () {
                            var exceptionTypeId = $('#exceptionTypeId').val();
                            var page = this.current_page;
                            var pageSize = this.page_size;
                            $.ajax({
                                url: context + 'exception/detail_list?exceptionTypeId='+exceptionTypeId+'&pageSize=' + pageSize
                                + '&page=' + page,
                                type: 'GET',
                                success: function (res) {
                                    vm.tableData = res.data.sysUserList;
                                    vm.total = res.data.total;
                                    vm.page_size = res.data.page_size;
                                    vm.current_page = res.data.page;
                                }
                            });
                        },

            searchdetail: function () {
                var searchBuglyDatail = {
                       'pageSize':this.page_size,
                       'page':this.current_page,
                       'currentCluster':bugly_detail_search.search_currentCluster.value,
                       'serviceName':bugly_detail_search.search_serviceName.value,
                       'errorMessage':bugly_detail_search.search_errorMessage.value,
                       'errorException':bugly_detail_search.search_errorException.value,
                       'startTime':this.time[0],
                       'endTime':this.time[1]
                       };

                $.ajax({
                    cache : true,
                    type : "POST",
                    url : context + 'exception/detail_search',
                    data :JSON.stringify(searchBuglyDatail),
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
            this.searchdetail();
        }
    });
});