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

const ANIMATION_DURATION = 300

export function setupDialogs() {
    if (createUserDialog) {
        openCreateUserDialog.onclick = () => {
            createUserDialog.showModal()
            createUserDialog.classList.remove('pop-out')
            createUserDialog.classList.add('pop-in')
        }
        closeCreateUserDialog.onclick = () => {
            createUserDialog.classList.remove('pop-in')
            createUserDialog.classList.add('pop-out')
            setTimeout(() => {
                createUserDialog.close()
            }, ANIMATION_DURATION)
        }
    }

    if (updateUserDialog) {
        openUpdateUserDialog.forEach((openUpdateButton) => {
            openUpdateButton.onclick = () => {
                const userId = openUpdateButton.dataset.userId;
                document.querySelector('#update-user-id').value = userId
                setUpdateUserForm(userId)

                updateUserDialog.showModal()
                updateUserDialog.classList.remove('pop-out')
                updateUserDialog.classList.add('pop-in')
            }
        })
        closeUpdateUserDialog.onclick = () => {
            updateUserDialog.classList.remove('pop-in')
            updateUserDialog.classList.add('pop-out')
            setTimeout(() => {
                updateUserDialog.close()
            }, ANIMATION_DURATION)
        }
    }


    if (deleteUserDialog) {
         openDeleteUserDialog.forEach((openDeleteButton) => {
            openDeleteButton.onclick = () => {
                document.querySelector('#delete-user-id').value = openDeleteButton.dataset.userId

                deleteUserDialog.showModal()
                deleteUserDialog.classList.remove('pop-out')
                deleteUserDialog.classList.add('pop-in')
            }
        })
        closeDeleteUserDialog.onclick = () => {
            deleteUserDialog.classList.remove('pop-in')
            deleteUserDialog.classList.add('pop-out')
            setTimeout(() => {
                deleteUserDialog.close()
            }, ANIMATION_DURATION)
        }
    }
}