const nameField = document.querySelector('#update-user-name');
const emailField = document.querySelector('#update-user-email');
const roleField = document.querySelector('#update-user-role');

export function setUpdateUserForm(userId) {
    fetch(`http://localhost:8080/UsersCrud_war_exploded/users?id=${userId}`)
        .then(response => {
            if (response.ok) return response.json()
            console.error(response.status === 401 ? "Unauthorized!" : "Error!")
        })
        .then(data => {
            nameField.value = data.name || '';
            emailField.value = data.email || '';
            if (!roleField) roleField.value = data.role || '';
        })
}