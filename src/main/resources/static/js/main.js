let createButtonClicked = document.getElementById("createButtonClicked");
let createPost = document.getElementById("createPost");

if (createButtonClicked !== null) {
    createButtonClicked.addEventListener('click', function() {
        window.location.href = "/create"
    });
}

if (createPost !== null) {
    createPost.addEventListener('click', function() {
        let title = document.getElementById("title");
        let content = document.getElementById("content");
        let data = {
            'title' : title.value,
            'content' : content.value
        }
    
        $.ajax({
            type: "POST",
            url: "/api/post",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8"
        }).done(function() {
            window.location.href = "/"
        }).fail(function(error) {
            alert(error)
        })
    })

}