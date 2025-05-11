<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html lang="pt-br">
<head>
    <title>Users Crud</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/global.css">
</head>

<body>
    <main class="flex flex-col items-center h-screen w-full bg-amber-100">
        <div class="flex flex-col justify-center gap-5 mt-25 w-9/10 md:w-1/2">
            <div class="flex justify-end items-end w-full">
                <button id="open-create-user-dialog" class="bg-emerald-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 font-bold cursor-pointer shadow-sm">
                    <i class="bi bi-plus-lg stroke-2 size-10"></i>
                    <span>Add user</span>
                </button>
            </div>

            <div class="border border-neutral-200/60 outline-3 outline-neutral-100 rounded-lg overflow-hidden shadow-md w-full">
                <table class="table-auto bg-amber-50 w-full">
                    <thead class="text-amber-800">
                        <tr class="text-left border-b border-neutral-300 *:p-4 *:font-medium">
                            <th>#</th>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody class="text-left">
                        <c:choose>
                            <c:when test="${not empty users}">
                                <c:forEach var="user" items="${users}">
                                    <tr class="border-b border-neutral-300 *:p-5 hover:bg-neutral-400/5">
                                        <td>${user.getId()}</td>
                                        <td>${user.getName()}</td>
                                        <td>
                                            <c:if test="${user.getRole() == 1}">Admin</c:if>
                                            <c:if test="${user.getRole() == 2}">Common user</c:if>
                                        </td>
                                        <td>
                                            <button id="open-update-user-dialog"><i class="bi bi-pencil-square"></i></button>
                                            <button id="close-delete-user-dialog"><i class="bi bi-eraser"></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="4" class="text-center p-5">
                                        Cannot found users <i class="bi bi-emoji-frown stroke-2 size-10"></i>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>

        <dialog id="create-user-dialog" class="p-4 rounded-lg m-auto z-30 overflow-y-auto">
            <header class="flex justify-between items-center border-b-1 border-b-zinc-200 pt-1 pb-3">
                <h2 class="text-lg font-medium">Add a new user</h2>
                <button id="close-create-user-dialog" class="cursor-pointer p-1 outline-1 outline-rose-600 rounded-full">
                    <i class="bi bi-x text-rose-500"></i>
                </button>
            </header>
            <form class="grid gap-6 mt-4 min-w-100" method="post">
                <div class="grid gap-1">
                    <label for="user-name" class="text-neutral-600 font-medium">Insert a name</label>
                    <input
                            required
                            type="text"
                            id="user-name"
                            name="name"
                            class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <div class="grid gap-1">
                    <label for="user-email" class="text-neutral-600 font-medium">Insert a email</label>
                    <input
                            required
                            type="text"
                            id="user-email"
                            name="email"
                            class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <div class="grid gap-1">
                    <label for="user-passwd" class="text-neutral-600 font-medium">Insert a password</label>
                    <input
                            required
                            type="password"
                            id="user-passwd"
                            name="password"
                            class="outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <div class="grid gap-1">
                    <label for="role-selected-create" class="text-neutral-600 font-medium">Select a role</label>
                    <select
                            class="block w-full rounded-md outline-2 outline-stone-300 focus:outline-stone-400 p-2 rounded-md p-2 shadow-sm"
                            id="role-selected-create"
                            name="role-selected"
                            required
                    >
                        <option value="1">Common user</option>
                        <option value="2">Super user</option>
                    </select>
                </div>


                <button type="submit" class="mt-2 bg-emerald-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 w-full font-bold cursor-pointer shadow-sm">
                    New user
                </button>
            </form>
        </dialog>

        <dialog id="update-user-dialog" class="p-4 rounded-lg m-auto z-30 overflow-y-auto">
            <header class="flex justify-between items-center border-b-1 border-b-zinc-200 pt-1 pb-3">
                <h2 class="text-lg font-medium">Update User</h2>
                <button id="close-update-user-dialog" class="cursor-pointer p-1 outline-1 outline-rose-600 rounded-full">
                    <i class="bi bi-x text-rose-500"></i>
                </button>
            </header>
            <form class="grid gap-6 mt-4 min-w-100" method="post">
                <input type="hidden" id="user-id-update" name="id" />
                <div class="grid gap-1">
                    <label for="user-name-update" class="text-neutral-600 font-medium">Update name</label>
                    <input
                            required
                            type="text"
                            id="user-name-update"
                            name="name"
                            class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <div class="grid gap-1">
                    <label for="user-email-update" class="text-neutral-600 font-medium">Update email</label>
                    <input
                            required
                            type="text"
                            id="user-email-update"
                            name="email"
                            class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <div class="grid gap-1">
                    <label for="role-selected-update" class="text-neutral-600 font-medium">Select a role</label>
                    <select
                            class="block w-full rounded-md outline-2 outline-stone-300 focus:outline-stone-400 p-2 shadow-sm"
                            id="role-selected-update"
                            name="role-selected"
                            required
                    >
                        <option value="1">Common user</option>
                        <option value="2">Super user</option>
                    </select>
                </div>

                <button type="submit" class="mt-2 bg-emerald-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 w-full font-bold cursor-pointer shadow-sm">
                    Update user
                </button>
            </form>
        </dialog>

        <dialog id="delete-user-dialog" class="p-4 rounded-lg m-auto z-30 overflow-y-auto">
            <header class="flex justify-between items-center border-b-1 border-b-zinc-200 pt-1 pb-3">
                <h2 class="text-lg font-medium">Delete User</h2>
                <button id="close-delete-user-dialog" class="cursor-pointer p-1 outline-1 outline-rose-600 rounded-full">
                    <i class="bi bi-x text-rose-500"></i>
                </button>
            </header>
            <form class="grid gap-6 mt-4 min-w-100" method="post">
                <input type="hidden" id="user-id-delete" name="id" />
                <div class="text-neutral-700 mb-4">
                    Are you sure you want to <b>delete</b> this user? This action cannot be undone.
                </div>
                <button type="submit" class="mt-2 bg-rose-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 w-full font-bold cursor-pointer shadow-sm">
                    Delete user
                </button>
            </form>
        </dialog>
    </main>

    <script src="${pageContext.request.contextPath}/resources/js/dialog.js"></script>
</body>
</html>
