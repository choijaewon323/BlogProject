let createButtonClicked = document.getElementById("createButtonClicked");
let createPost = document.getElementById("createPost");
let updatePost = document.getElementById("updatePost");
let updateButtonClicked = document.getElementById("updateButtonClicked");
let deletePost = document.getElementById("deletePost");

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

if (updatePost !== null) {
    updatePost.addEventListener('click', function() {
        let title = document.getElementById("title");
        let content = document.getElementById("content");
        let data = {
            'title' : title.value,
            'content' : content.value
        }
        let postID = document.getElementById('postID');
    
        $.ajax({
            type: "PUT",
            url: "/api/post/" + postID.value,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8"
        }).done(function() {
            window.location.href = "/detail/" + postID.value;
        }).fail(function(error) {
            alert(error)
        })
    })
}

if (updateButtonClicked !== null) {
    updateButtonClicked.addEventListener('click', function() {
        let postID = document.getElementById("postID");
    
        window.location.href = "/update/" + postID.value;
    })
}

if (deletePost !== null) {
    deletePost.addEventListener('click', function() {
        let postID = document.getElementById('postID');
        
        $.ajax({
            type: "DELETE",
            url: "/api/post/" + postID.value
        }).done(function() {
            window.location.href = "/"
        }).fail(function(error) {
            alert(error)
        })
    })
}