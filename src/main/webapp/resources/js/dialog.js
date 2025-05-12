const createUserDialog = document.querySelector('#create-user-dialog')
const openCreateUserDialog = document.querySelector('#open-create-user-dialog')
const closeCreateUserDialog = document.querySelector('#close-create-user-dialog')

const updateUserDialog = document.querySelector('#update-user-dialog')
const openUpdateUserDialog = document.querySelectorAll('.open-update-user-dialog')
const closeUpdateUserDialog = document.querySelector('#close-update-user-dialog')

const deleteUserDialog = document.querySelector('#delete-user-dialog')
const openDeleteUserDialog = document.querySelectorAll('.open-delete-user-dialog')
const closeDeleteUserDialog = document.querySelector('#close-delete-user-dialog')


export function setupDialogs() {
    openCreateUserDialog.onclick = () => {
        createUserDialog.showModal()
    }

    openUpdateUserDialog.forEach((openUpdateButton) => {
        openUpdateButton.onclick = () => {
            updateUserDialog.showModal()
        }
    })

    openDeleteUserDialog.forEach((openDeleteButton) => {
        openDeleteButton.onclick = () => {
            document.querySelector('#delete-user-id').value
                = openDeleteButton.dataset.userId;
            deleteUserDialog.showModal()
        }
    })

    closeCreateUserDialog.onclick = () => {
        createUserDialog.close();
    };

    closeUpdateUserDialog.onclick = () => {
        updateUserDialog.close();
    }


    closeDeleteUserDialog.onclick = () => {
        deleteUserDialog.close();
    }
}