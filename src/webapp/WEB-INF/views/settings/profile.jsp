<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Linksy - 프로필 편집</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/styles.css">
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
        <h1>프로필 편집</h1>
        <div class="profile-edit">
            <div class="profile-section">
                <form id="profileForm" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/save-profile">
                    <div class="profile-image-wrapper" id="profileImageWrapper">
                        <img src="${pageContext.request.contextPath}/images/profile/default-profile.png" id="profileImage">
                    </div>
                </form>
                <div class="file-input">
                    <label for="profileImageUpload">사진 변경</label>
                    <input type="file" id="profileImageUpload" name="profileImage" accept="image/*" onchange="previewImage(event)">
                    <button type="button" onclick="deleteProfileImage()">사진 삭제</button>
                </div>
            </div>
            <div class="bio-section">
                <label for="bio">소개글</label>
                <textarea id="bio" name="bio" placeholder="자기소개를 작성하세요.(200자 이내)" maxlength="200" oninput="updateCharacterCount()"></textarea>
                <p id="charCount">0/200</p> <!-- 글자수 표시 -->
            </div>
            <button onclick="saveProfile()">저장</button>
            <p id="saveMessage" style="color: green; display: none;">저장되었습니다!</p>
            <button onclick="openModal('nicknameModal')">닉네임 변경</button>
            <button onclick="openModal('passwordModal')">비밀번호 변경</button>
        </div>
    </div>

    <!-- Right Panel -->
    <div class="right-panel">
        FRIEND
    </div>
</div>

<!-- 닉네임 변경 팝업 -->
<div id="nicknameModal" class="modal">
    <div class="modal-content">
        <h2>닉네임 변경</h2>
        <label for="newNickname">새 닉네임</label>
        <input type="text" id="newNickname" name="newNickname" placeholder="새 닉네임을 입력하세요">
        <button onclick="saveNickname()">저장</button>
        <button onclick="closeModal('nicknameModal')">취소</button>
    </div>
</div>

<!-- 비밀번호 변경 팝업 -->
<div id="passwordModal" class="modal">
    <div class="modal-content">
        <h2>비밀번호 변경</h2>
        <label for="currentPassword">현재 비밀번호</label> <br>
        <input type="password" id="currentPassword" name="currentPassword" placeholder="현재 비밀번호 입력"><br>

        <label for="newPassword">새 비밀번호</label><br>
        <input type="password" id="newPassword" name="newPassword" placeholder="새 비밀번호 입력"><br>

        <label for="confirmPassword">비밀번호 확인</label><br>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인"><br>

        <button onclick="savePassword()">저장</button>
        <button onclick="closeModal('passwordModal')">취소</button>
    </div>
</div>

<script>
    // JavaScript는 동일
</script>
</body>
</html>
