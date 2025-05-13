const deleteUserForm = document.querySelector("#delete-user-form")

export function setupDeleteUser() {
    if (!deleteUserForm) return
    deleteUserForm.onsubmit = (e) => {
        e.preventDefault()
        const form = new FormData(deleteUserForm)
        const id = form.get('delete-user-id')

        fetch(`http://localhost:8080/UsersCrud_war_exploded/users?id=${id}`, {
            method: 'DELETE',
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