const nameField = document.querySelector('#update-user-name');
const emailField = document.querySelector('#update-user-email');
const roleField = document.querySelector('#update-user-role');

export function setUpdateUserForm(userId) {
    fetch(`http://localhost:8080/UsersCrud_war_exploded/users?id=${userId}`)
        .then(response => response.json())
        .then(data => {
            nameField.value = data.name || '';
            emailField.value = data.email || '';
            roleField.value = data.role || '';
        })
}