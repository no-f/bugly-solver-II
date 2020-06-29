$().ready(function(){

     var serviceException = $("#serviceException").val();
     $("#exception").text(serviceException);

//     关键字变红色
//     var s = "Caused by";
//     var reg = new RegExp("(" + s + ")", "g");
//     var newstr = str.replace(serviceException, "<font color=red>$1</font>");
//     $("#exception").html(newstr);

//      var searchText = "Caused by"；
//      var regExp = new RegExp(searchText, "g");
//      $("#exception").each(function()
//             {
//                 var html = $(this).html();
//                 var newHtml = html.replace(regExp, "<span style='color:#ffd421' >"+searchText+"</span>");
//                 $(this).html(newHtml);
//             });

    var vm = new Vue({
        el: '#app',
        data: {
            tableData: [],
            total: 50,
            page_size: 5,
            current_page: 1
        }
    });
});