let createButtonClicked = document.getElementById("createButtonClicked");
let createPost = document.getElementById("createPost");

createButtonClicked.addEventListener('click', function() {
    window.location.href = "/create"
});

createPost.addEventListener('click', function() {
    let title = document.getElementById("title");
    let content = document.getElementById("content");
    let data = {
        'title' : title,
        'content' : content
    }

    $.ajax({
        type: "post",
        url: "/api/post",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8"
    })
})