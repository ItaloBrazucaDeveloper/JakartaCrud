import { setupDeleteUser } from './deleteUser.js'
import { setupEditUser } from './editUser.js'
import { setupCreateUser } from './createUser.js';
import { setupDialogs } from './dialog.js'
import { showFlashMessage } from './flashMessageHandler.js'

document.addEventListener('DOMContentLoaded', () => {
    const flash = sessionStorage.getItem('flashMessage')
    if (flash) {
        const { type, message } = JSON.parse(flash)
        showFlashMessage(type, message)
        sessionStorage.removeItem('flashMessage')
    }
    setupDeleteUser()
    setupEditUser()
    setupCreateUser()
    setupDialogs()
})
