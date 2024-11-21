<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Linksy - 알림</title>
    <style>
        /* CSS는 동일 */
    </style>
</head>
<body>
<div class="container">
    <!-- Sidebar -->
    <div class="sidebar">
        <div class="logo_img">
            <img src="${pageContext.request.contextPath}/images/logo/blacklogo.png">
        </div>
        <div class="sidebar-menu">
            <a href="${pageContext.request.contextPath}/settings/profile">프로필 편집</a>
            <a href="${pageContext.request.contextPath}/settings/notifications">알림</a>
        </div>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <h1>알림 설정</h1>
        <form class="notifications-form" action="${pageContext.request.contextPath}/save-notification-settings" method="post">
            <div class="form-group">
                <label for="emailNotifications">이메일 알림</label>
                <label class="toggle">
                    <input type="checkbox" id="emailNotifications" name="emailNotifications">
                    <span class="slider"></span>
                </label>
            </div>
            <div class="form-group">
                <label for="pushNotifications">푸시 알림</label>
                <label class="toggle">
                    <input type="checkbox" id="pushNotifications" name="pushNotifications">
                    <span class="slider"></span>
                </label>
            </div>
            <div class="form-group">
                <label for="smsNotifications">SMS 알림</label>
                <label class="toggle">
                    <input type="checkbox" id="smsNotifications" name="smsNotifications">
                    <span class="slider"></span>
                </label>
            </div>
            <button type="submit">저장</button>
        </form>
    </div>

    <!-- Right Panel -->
    <div class="right-panel">
        FRIEND
    </div>
</div>
<script>
    // JavaScript는 동일
</script>
</body>
</html>
