let usersTable = document.querySelector("#tbody")
function getUsers() {
    let res = fetch("/admin/users").then(res => res.json())
    let users = []
    res.then(data=>data.forEach(u=>users.push(u))).then(function () {
    let temp = ""
    for (let i = 0; i < users.length; i++) {
        temp += "<tr>" +
            "<td>" + users[i].id + "</td>" +
            "<td>" + users[i].name + "</td>" +
            "<td>" + users[i].lastname + "</td>" +
            "<td>" + users[i].email + "</td>" +
            "<td>" + users[i].stringRoles + "</td>" +
            '<td>' +
            '<button type="button" class="edit btn btn-info" data-bs-toggle="modal" data-bs-target="#editModal" id="' + users[i].id +'">Edit</button>' +
            '</td>' +
            '<td>' +
            '<button type="button" class="delete btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal" id="' + users[i].id +'">Delete</button>' +
            '</td>' +
            "</tr>"

    }
        getCurrentUser()
        usersTable.innerHTML = temp
        $(".edit").click(function () {
            let id = $(this).attr('id')
            fetch("/admin/users/" + id).then(res=>res.json()).then(function (u) {
                $("#edit-id").val(u.id)
                $("#edit-name").val(u.name)
                $("#edit-lastname").val(u.lastname)
                $("#edit-email").val(u.email)
                $("#edit-password").val(u.password)
            })
        })
        $(".delete").click(function () {
            let id = $(this).attr('id')
            fetch("/admin/users/" + id).then(res=>res.json()).then(function (u) {
                $("#delete-id").val(u.id)
                $("#delete-name").val(u.name)
                $("#delete-lastname").val(u.lastname)
                $("#delete-email").val(u.email)
                $("#delete-password").val(u.password)
            })
        })
    })
}

function getCurrentUser () {
    fetch("/admin/current").then(res=>res.json()).then(function (u) {
        $("#header").text(u.email + " with roles: " + u.stringRoles)
        $("#current-id").text(u.id)
        $("#current-name").text(u.name)
        $("#current-lastname").text(u.lastname)
        $("#current-email").text(u.email)
        $("#current-role").text(u.stringRoles)
    })
}

$("document").ready(getUsers())

$("#new-user").on("click", function () {
    document.querySelector("#table").style.display = "none"
    document.querySelector("#new").style.display = "block"
})

$("#users-table").click(function () {
    document.querySelector("#table").style.display = "block"
    document.querySelector("#new").style.display = "none"
    getUsers()
})

$("#user-link").click(function () {
    document.querySelector("#admin-panel").style.display = "none"
    document.querySelector("#user-panel").style.display = "block"
})

$("#admin-link").click(function () {
    document.querySelector("#admin-panel").style.display = "block"
    document.querySelector("#user-panel").style.display = "none"
})

$("#add-new-user").click(function () {
    let roles = []
    $("#new-roles option:selected").each(function () {
        roles.push($(this).text())
    })
    fetch("/admin/users", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            name: $("#new-name").val(),
            lastname: $("#new-lastname").val(),
            email: $("#new-email").val(),
            password: $("#new-password").val(),
            roles: roles})
    })
    $("#new-user-form").trigger('reset')
})

$("#edit-btn").click(function () {
    event.preventDefault()
    let roles = []
    $("#edit-roles option:selected").each(function () {
        roles.push($(this).text())
    })
    fetch("/admin/users", {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            id: $("#edit-id").val(),
            name: $("#edit-name").val(),
            lastname: $("#edit-lastname").val(),
            email: $("#edit-email").val(),
            password: $("#edit-password").val(),
            roles: roles})
    }).then(function () {
        getUsers()
    })
    $("#deleteModal").hide()
    $(".fade").hide()
})

$("#delete-btn").click(function () {
    event.preventDefault()
    let id = $("#delete-id").val()
    fetch("/admin/users/" + id, {
        method: "delete"
    }).then(function () {
        getUsers()
    })
    $("#deleteModal").hide()
    $(".fade").hide()
})