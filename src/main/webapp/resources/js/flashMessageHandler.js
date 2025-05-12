// Tailwind classes para flash messages
const baseClasses = [
    'flash-message',
    'fixed', 'top-6', 'left-1/2', 'transform', '-translate-x-1/2',
    'z-50', 'px-6', 'py-4', 'rounded-lg', 'shadow-md',
    'flex', 'items-center', 'gap-4', 'min-w-[260px]', 'max-w-full'
]

const successClasses = ['bg-emerald-500', 'text-zinc-50', 'border', 'border-emerald-600']
const errorClasses   = ['bg-rose-500', 'text-zinc-50', 'border', 'border-rose-600']

export function showFlashMessage(type, message) {
    // Remove mensagens existentes (opcional)
    document.querySelectorAll('.flash-message').forEach(el => el.remove())

    const isSuccess = type === 'success'

    const container = document.createElement('div')
    container.className = baseClasses.concat(isSuccess ? successClasses : errorClasses).join(' ')
    container.classList.add('flash-message-animation')

    const span = document.createElement('span')
    span.textContent = message
    span.className = 'font-semibold flex-1'

    container.appendChild(span)
    document.body.prepend(container)

    setTimeout(() => container.remove(), 4000)
}