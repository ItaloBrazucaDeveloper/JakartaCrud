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
                <button class="bg-emerald-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 font-bold cursor-pointer shadow-sm">
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
                            <c:when test="${usuarios.length > 0}">
                                <c:forEach var="usuario" items="${usuarios}">
                                    <tr class="border-b border-neutral-300 *:p-5 hover:bg-neutral-400/5">
                                        <td>${usuario.id}</td>
                                        <td>${usuario.name}</td>
                                        <td>
                                            <c:if test="${usuario.role == 1}">Admin</c:if>
                                            <c:if test="${usuario.role == 1}">Common user</c:if>
                                        </td>
                                        <td>
                                            <button><i class="bi bi-pencil-square"></i></button>
                                            <button><i class="bi bi-eraser"></i></button>
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

        <dialog class="">
            <form class="" method="dialog">
                <div class="">
                    <label for="user-email-create">Insert a email</label>
                    <input required type="text" id="user-email-create" name="new-email" />
                </div>

                <div class="">
                    <label for="user-passwd-create">Insert a password</label>
                    <input required type="text" id="user-passwd-create" name="new-passwd" />
                </div>

                <div class="">
                    <label for="role-selected-create">Select a role</label>
                    <select class="" id="role-selected-create" name="role-selected">
                        <option value="2">Common user</option>
                        <option value="1">Super user</option>
                    </select>
                </div>

                <button>New user</button>
            </form>
        </dialog>
    </main>

    <script type="module" src="${pageContext.request.contextPath}/resources/js/index.js"></script>
</body>
</html>
