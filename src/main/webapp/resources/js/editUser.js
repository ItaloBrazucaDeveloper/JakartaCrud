const updateUserForm = document.querySelector("#update-user-form")

export function setupEditUser() {
    updateUserForm.onsubmit = (e) => {
        e.preventDefault()
        const form = new FormData(updateUserForm);
        const formObject = Object.fromEntries(form.entries());
        const fieldsAsJson = JSON.stringify(formObject);

        fetch('http://localhost:8080/UsersCrud_war_exploded/users', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: fieldsAsJson
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