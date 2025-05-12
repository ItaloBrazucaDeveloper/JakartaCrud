const createUserForm = document.querySelector("#create-user-form")

export function setupCreateUser() {
    createUserForm.onsubmit = (e) => {
        e.preventDefault()
        const form = new FormData(createUserForm)
        const fieldsAsSearchParams = new URLSearchParams(form);

        fetch("http://localhost:8080/UsersCrud_war_exploded/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: fieldsAsSearchParams
        })
        .then(response => response.json())
        .then(data => {
            sessionStorage.setItem('flashMessage', JSON.stringify({
                type: data.type,
                message: data.message
            }))
            window.location.reload()
        })
    }
}