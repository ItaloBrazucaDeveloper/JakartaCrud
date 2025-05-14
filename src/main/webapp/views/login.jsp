<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Users Crud</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css">
</head>

<body>
    <main class="h-screen w-full grid place-items-center bg-amber-100">
        <c:if test="${not empty errorMessage}">
            <div class="flash-message-animation flash-message absolute top-0 left-0 z-50 m-5 bg-rose-600 text-zinc-50 min-w-[260px] text-center shadow-md border border-rose-700 rounded-md py-4 px-6">
                <c:out value="${errorMessage}" />
            </div>
        </c:if>

        <div class="border border-neutral-200/60 outline-3 outline-neutral-100 bg-amber-50 shadow-md rounded-md p-5">
            <h1 class="text-lg font-bold text-amber-900">
                Welcome back!
            </h1>

            <form class="grid gap-6 mt-4 min-w-100" method="post" action="${pageContext.request.contextPath}/login">
                <div class="grid gap-1">
                    <label for="user-email" class="text-neutral-600 font-medium">Your email</label>
                    <input
                            required
                            type="text"
                            id="user-email"
                            name="email"
                            class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <div class="grid gap-1">
                    <label for="user-passwd" class="text-neutral-600 font-medium">Your password</label>
                    <input
                            required
                            type="password"
                            id="user-passwd"
                            name="password"
                            class="outline outline-2 outline-stone-300 focus:outline-stone-400 rounded-md p-2 shadow-sm"
                    />
                </div>

                <button type="submit" class="mt-2 bg-emerald-600 hover:opacity-90 text-amber-50 rounded-xl px-4 py-3 w-full font-bold cursor-pointer shadow-sm">
                    <i class="bi bi-box-arrow-in-right mr-1 stroke-2 size-10"></i> Sign In
                </button>
            </form>
        </div>
    </main>
</body>
</html>
