const updateUserForm = document.querySelector("#update-user-form")

export function setupEditUser() {
    updateUserForm.onsubmit = (e) => {
        e.preventDefault()
        const form = new FormData(createUserForm)
        const fieldsAsSearchParams = new URLSearchParams(form);

        fetch('http://localhost:8080/UsersCrud_war_exploded/users', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
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