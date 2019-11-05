function post() {
    var articleId = $("#article_id").val()
    var content = $("#content").val();
    $.ajax({
        type : "post",
        url : "/comment",
        data: JSON.stringify({
            "articleId": articleId,
            "content": content,
            "type": 0
        }),
        success : function (result) {
            console.log(response);
            if(response.code != 200){
                alert(response.message)
            }
        },
        dataType:"json",
        contentType:"application/json"
    });
    console.log(articleId);
    console.log(content);
}