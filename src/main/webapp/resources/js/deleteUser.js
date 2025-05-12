export function setupDeleteUser() {
    const deleteUserForm = document.querySelector("#delete-user-form")

    deleteUserForm.onsubmit = (e) => {
        e.preventDefault()
        const form = new FormData(deleteUserForm)
        const id = form.get('id')
        fetch(`http://localhost:8080/UsersCrud_war_exploded/users?id=${id}`, {
            method: 'DELETE',
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