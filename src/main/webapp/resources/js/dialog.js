import { setUpdateUserForm } from "./setUpdateUserForm.js";

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
    if (createUserDialog) {
        openCreateUserDialog.onclick = () => {
            createUserDialog.showModal()
        }
        closeCreateUserDialog.onclick = () => {
            createUserDialog.close();
        }
    }

    if (updateUserDialog) {
        openUpdateUserDialog.forEach((openUpdateButton) => {
            openUpdateButton.onclick = () => {
                const userId = openUpdateButton.dataset.userId;
                document.querySelector('#update-user-id').value = userId
                setUpdateUserForm(userId)
                updateUserDialog.showModal()
            }
        })
        closeUpdateUserDialog.onclick = () => {
          updateUserDialog.close();
        }
    }


    if (deleteUserDialog) {
         openDeleteUserDialog.forEach((openDeleteButton) => {
            openDeleteButton.onclick = () => {
                document.querySelector('#delete-user-id').value
                    = openDeleteButton.dataset.userId;
                deleteUserDialog.showModal()
            }
        })
        closeDeleteUserDialog.onclick = () => {
            deleteUserDialog.close();
        }
    }
}