<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="isSuperUser" value="${not empty sessionScope.userRole and sessionScope.userRole == 1}" />

<html lang="pt-br">
<head>
    <title>Users Crud</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/global.css">
</head>

<body>
    <main class="flex flex-col justify-center items-center h-screen w-full bg-amber-100">
        <div class="flash-message"></div>

        <div class="flex flex-col justify-center gap-5 w-full md:w-2/3">
            <div class="flex justify-between items-end w-full">
                <div>
                    <span class="text-stone-600">Registered users: <c:out value="${users.size()}" /></span>
                    <h2 class="font-bold text-xl text-stone-700">Welcome back, ${sessionScope.userName}!</h2>
                </div>
                <div class="space-x-2">
                    <c:if test="${isSuperUser}">
                         <button id="open-create-user-dialog" class="bg-emerald-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 font-bold cursor-pointer shadow-sm">
                            <i class="bi bi-plus-lg stroke-2 size-10"></i>
                            <span>Add user</span>
                        </button>
                    </c:if>
                   <c:if test="${not isSuperUser}">
                       <button
                           data-user-id="${sessionScope.userId}"
                           class="bg-emerald-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 font-bold cursor-pointer shadow-sm space-x-1 open-update-user-dialog"
                       >
                           <i class="bi bi-pen"></i>
                           <span>Edit your profile</span>
                       </button>
                   </c:if>
                    <a
                        title="Encerrar sessão"
                        href="${pageContext.request.contextPath}/logout"
                        class="bg-rose-500 rounded-xl px-4 py-3 font-bold cursor-pointer shadow-sm"
                    >
                        <i class="bi bi-box-arrow-right text-zinc-50"></i>
                    </a>
                </div>
            </div>

            <div class="hidden-scroll border border-neutral-200/60 outline-3 outline-neutral-100 rounded-lg overflow-auto shadow-md w-full max-h-[600px]">
                <table class="table-auto bg-amber-50 w-full">
                    <thead class="text-amber-800 sticky top-0 bg-amber-50 z-5 shadow-sm shadow-neutral-300/60">
                        <tr class="text-left *:p-4 *:font-medium">
                            <th>#</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <c:if test="${isSuperUser}">
                                <th>Actions</th>
                            </c:if>
                        </tr>
                    </thead>
                    <tbody class="text-left">
                        <c:choose>
                            <c:when test="${not empty users}">
                                <c:forEach var="user" items="${users}" varStatus="status">
                                    <tr class="border-b border-neutral-300 *:p-5 hover:bg-neutral-400/5">
                                        <td>${status.index + 1}</td>
                                        <td>${user.getName()}</td>
                                        <td>${user.getEmail()}</td>
                                        <td>
                                            <c:if test="${user.getRole() == 1}">Admin</c:if>
                                            <c:if test="${user.getRole() == 2}">Common user</c:if>
                                        </td>
                                        <c:if test="${isSuperUser}">
                                            <td class="space-x-2">
                                                <button data-user-id="${user.getId()}" title="Edit" class="cursor-pointer open-update-user-dialog">
                                                    <i class="bi bi-pencil-square text-lg"></i>
                                                </button>
                                                <button data-user-id="${user.getId()}" title="Delete" class="cursor-pointer open-delete-user-dialog">
                                                    <i class="bi bi-eraser text-rose-600 text-lg"></i>
                                                </button>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="${isSuperUser ? 5 : 4}" class="text-center p-5">
                                        Cannot found users <i class="bi bi-emoji-frown stroke-2 size-10"></i>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>

        <c:if test="${isSuperUser}">
            <dialog id="create-user-dialog" class="p-4 rounded-lg m-auto z-30 overflow-y-auto bg-amber-50 shadow-md ring-2 ring-stone-300">
                <header class="flex justify-between items-center border-b-1 border-b-stone-800/20 pt-1 pb-3">
                    <h2 class="text-lg font-medium">Add a new user</h2>
                    <button id="close-create-user-dialog" class="cursor-pointer p-1 outline-1 outline-rose-600 rounded-full">
                        <i class="bi bi-x text-rose-500"></i>
                    </button>
                </header>
                <form class="grid gap-6 mt-4 min-w-100" method="post" id="create-user-form">
                    <div class="grid gap-1">
                        <label for="create-user-name" class="text-neutral-600 font-medium">Insert a name</label>
                        <input
                                required
                                type="text"
                                id="create-user-name"
                                name="create-user-name"
                                class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                        />
                    </div>

                    <div class="grid gap-1">
                        <label for="create-user-email" class="text-neutral-600 font-medium">Insert a email</label>
                        <input
                                required
                                type="text"
                                id="create-user-email"
                                name="create-user-email"
                                class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                        />
                    </div>

                    <div class="grid gap-1">
                        <label for="create-user-password" class="text-neutral-600 font-medium">Insert a password</label>
                        <input
                                required
                                type="password"
                                id="create-user-password"
                                name="create-user-password"
                                class="outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                        />
                    </div>

                    <div class="grid gap-1">
                        <label for="create-user-role" class="text-neutral-600 font-medium">Select a role</label>
                        <select
                                class="block w-full rounded-md outline-2 outline-stone-300 focus:outline-stone-400 p-2 rounded-md p-2 shadow-sm"
                                id="create-user-role"
                                name="create-user-role"
                                required
                        >
                            <option value="2">Common user</option>
                            <option value="1">Super user</option>
                        </select>
                    </div>

                    <button type="submit" class="mt-2 bg-emerald-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 w-full font-bold cursor-pointer shadow-md ring-2 ring-emerald-700/80">
                        New user
                    </button>
                </form>
            </dialog>

            <dialog id="delete-user-dialog" class="p-4 rounded-lg m-auto z-30 overflow-y-auto bg-amber-50 shadow-md ring-2 ring-stone-300">
                <header class="flex justify-between items-center border-b-1 border-b-zinc-200 pt-1 pb-3">
                    <h2 class="text-lg font-medium">Delete User</h2>
                    <button id="close-delete-user-dialog" class="cursor-pointer p-1 outline-1 outline-rose-600 rounded-full">
                        <i class="bi bi-x text-rose-500"></i>
                    </button>
                </header>
                <form class="grid gap-6 mt-4 min-w-100" method="dialog" id="delete-user-form">
                    <input type="hidden" id="delete-user-id" name="delete-user-id" />
                    <div class="text-neutral-700 mb-4">
                        Are you sure you want to <b>delete</b> this user? This action cannot be undone.
                    </div>
                    <button type="submit" class="mt-2 bg-rose-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 w-full font-bold cursor-pointer shadow-md ring-2 ring-rose-700/60">
                        Delete user
                    </button>
                </form>
            </dialog>
        </c:if>

        <dialog id="update-user-dialog" class="p-4 rounded-lg m-auto z-30 overflow-y-auto bg-amber-50 shadow-md ring-2 ring-stone-300">
            <header class="flex justify-between items-center border-b-1 border-b-zinc-200 pt-1 pb-3">
                <h2 class="text-lg font-medium">
                    <c:if test="${isSuperUser}">Update User</c:if>
                    <c:if test="${not isSuperUser}">Edit your profile</c:if>
                </h2>
                <button id="close-update-user-dialog" class="cursor-pointer p-1 outline-1 outline-rose-600 rounded-full">
                    <i class="bi bi-x text-rose-500"></i>
                </button>
            </header>
            <form class="grid gap-6 mt-4 min-w-100" method="post" id="update-user-form">
                <input type="hidden" id="update-user-id" name="update-user-id" />
                <div class="grid gap-1">
                    <label for="update-user-name" class="text-neutral-600 font-medium">Update name</label>
                    <input
                            type="text"
                            id="update-user-name"
                            name="update-user-name"
                            class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <div class="grid gap-1">
                    <label for="update-user-email" class="text-neutral-600 font-medium">Update email</label>
                    <input
                            type="text"
                            id="update-user-email"
                            name="update-user-email"
                            class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <div class="grid gap-1">
                    <label for="update-user-password" class="text-neutral-600 font-medium">Update password</label>
                    <input
                            type="password"
                            id="update-user-password"
                            name="update-user-password"
                            class="outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <c:if test="${isSuperUser}">
                    <div class="grid gap-1">
                        <label for="update-user-role" class="text-neutral-600 font-medium">Select a role</label>
                        <select
                            class="block w-full rounded-md outline-2 outline-stone-300 focus:outline-stone-400 p-2 shadow-sm"
                            id="update-user-role"
                            name="update-user-role"
                        >
                            <option value="2">Common user</option>
                            <option value="1">Super user</option>
                        </select>
                    </div>
                </c:if>

                <button type="submit" class="mt-2 bg-emerald-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 w-full font-bold cursor-pointer shadow-md ring-2 ring-emerald-700/80">
                    Update user
                </button>
            </form>
        </dialog>
    </main>

    <script type="module" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
</body>
</html>
