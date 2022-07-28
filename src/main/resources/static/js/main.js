let createButtonClicked = document.getElementById("createButtonClicked");
let createPost = document.getElementById("createPost");
let updatePost = document.getElementById("updatePost");
let updateButtonClicked = document.getElementById("updateButtonClicked");
let deletePost = document.getElementById("deletePost");
let keyword = document.getElementById("keyword");
let createReply = document.getElementById("createReply");
let replyUpdate = document.getElementById("replyUpdate");
let main = document.getElementById("main");
let newAccount = document.getElementById("newAccount");
let login = document.getElementById("login");

if (login !== null) {
    login.addEventListener('click', function() {
        let username = document.getElementById("username");
        let password = document.getElementById("password");
        let data = {
            username: username.value,
            password: password.value
        };

        $.ajax({
            url: "/api/login",
            method: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function(e) {
            if (e === false) {
                alert("아이디 혹은 비밀번호가 틀립니다.");
            }
            else {
                window.location.href = "/";
            }
        }).fail(function(error) {
            alert(error);
        })
    })
}

if (newAccount !== null) {
    newAccount.addEventListener('click', function() {
        let username = document.getElementById("username");
        let password = document.getElementById("password");
        let data = {
            username: username.value,
            password: password.value
        };

        $.ajax({
            url: "/api/account",
            method: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function(e) {
            if (e === false) {
                alert("이미 존재하는 아이디입니다.");
            }
            else {
                window.location.href = "/login";
            }
        }).fail(function(error) {
            alert(error);
        })
    })
}


if (main !== null) {
    main.addEventListener('click', function() {
        window.location.href = "/";
    })
}

if (keyword !== null) {
    let searchClicked = document.getElementById("searchClicked");

    searchClicked.addEventListener('click', function() {
        let key = keyword.options[keyword.selectedIndex].value;
        let findContent = document.getElementById("findContent");
    
        if (key === 'titleSelected') {
            window.location.href = "/?title=" + findContent.value;
        }
        else {
            window.location.href = "/?content=" + findContent.value;
        }
    })
} 

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

if (createReply !== null) {
    createReply.addEventListener('click', function() {
        let replyWriter = document.getElementById("replyWriter");
        let replyContent = document.getElementById("replyContent");
        let postID = document.getElementById("postID");
        let data = {
            'writer' : replyWriter.value,
            'content' : replyContent.value
        }

        $.ajax({
            type: "POST",
            url: "/api/reply/" + postID.value,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8"
        }).done(function() {
            window.location.href = "/detail/" + postID.value;
        }).fail(function(error) {
            alert(error);
        })
    })
}

function replyUpdateClicked(replyID) {
    window.open("/update/detail/" + replyID, 'Child', 'width=400, height=400');
}

function replyDelete(replyID) {
    let postID = document.getElementById('postID');

    $.ajax({
        type: "DELETE",
        url: "/api/reply/" + replyID
    }).done(function() {
        window.location.href = "/detail/" + postID.value;
    }).fail(function(error) {
        alert(error);
    })
}

if (replyUpdate !== null) {
    replyUpdate.addEventListener('click', function() {
        let writer = document.getElementById("writer");
        let content = document.getElementById("content");
        let replyID = document.getElementById("replyID");
        let postID = document.getElementById("postID");

        let data = {
            writer: writer.value,
            content: content.value
        }

        $.ajax({
            type: "PUT",
            url: "/api/reply/" + replyID.value,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8"
        }).done(function() {
            window.opener.location.href = "/detail/" + postID.value;
            window.close();
        }).fail(function(error) {
            alert(error);
        })
    })
}