const createUserForm = document.querySelector("#create-user-form")

export function setupCreateUser() {
    if (!createUserForm) return
    createUserForm.onsubmit = (e) => {
        e.preventDefault()
        createUserForm.querySelector('button[type="submit"]').disabled = true

        const form = new FormData(createUserForm)
        const fieldsAsSearchParams = new URLSearchParams(form);

        fetch("http://localhost:8080/UsersCrud_war_exploded/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: fieldsAsSearchParams
        })
        .then(response => {
            if (response.ok) return response.json()
            console.error(response.status === 401 ? "Unauthorized!" : "Error!")
        })
        .then(data => {
            sessionStorage.setItem('flashMessage', JSON.stringify({
                type: data.type,
                message: data.message
            }))
            window.location.reload()
        })
    }
}