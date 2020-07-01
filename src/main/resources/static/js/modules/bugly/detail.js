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
                    content: context + 'bugly/exception/detail_show?exception='+row.errorException,
                    end: function () {
                        vm.getExceptionDatailList();
                    }
                });
            },

            handleSizeChange: function (val) {
                vm.page_size = val;
                this.getExceptionDatailList();
            },
            handleCurrentChange: function (val) {
                vm.current_page = val;
                this.getExceptionDatailList();
            },

            getExceptionDatailList: function () {
                            var exceptionTypeId = $('#exceptionTypeId').val();
                            console.log(exceptionTypeId)
                            $.ajax({
                                url: context + 'exception/detail_list?exceptionTypeId='+exceptionTypeId,
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
                       'machinneAddress':bugly_detail_search.search_machinneAddress.value,
                       'threadId':bugly_detail_search.search_threadId.value,
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
            this.getExceptionDatailList();
        }
    });
});