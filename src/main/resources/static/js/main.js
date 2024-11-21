// 더미 유저 계정 데이터
const users = [
    { username: "user1", comment: "아주 멋진 게시물이에요!" },
    { username: "user2", comment: "정말 유익한 정보네요." },
    { username: "user3", comment: "좋은 게시물 감사합니다." },
    { username: "user4", comment: "저도 한번 해볼게요!" },
    { username: "user5", comment: "완전 좋아요!" },
    { username: "user6", comment: "좋은 게시물 감사합니다." },
    { username: "user7", comment: "저도 한번 해볼게요!" },
    { username: "user8", comment: "완전 좋아요!" },
    { username: "user9", comment: "좋은 게시물 감사합니다." },
    { username: "user10", comment: "저도 한번 해볼게요!" },
    { username: "user11", comment: "완전 좋아요!" }
];

// 댓글 펼치기
function showComments(spanElement) {
    const commentsView = spanElement.closest('.comments-view');
    const commentsList = commentsView.querySelector('.comments-list');
    const feed = commentsView.closest('.feed');

    commentsList.innerHTML = ''; // 초기화

    // 더미 데이터로 댓글 항목 생성
    users.forEach(user => {
        const commentElement = document.createElement('div');
        commentElement.className = 'comment-item';
        commentElement.innerHTML = `<strong>${user.username}</strong>: <span>${user.comment}</span>`;
        commentsList.appendChild(commentElement);
    });

    // 댓글 영역을 확장
    commentsList.classList.toggle('show');

    // 버튼 텍스트 변경
    const isExpanded = commentsList.classList.contains('show');
    spanElement.innerText = isExpanded ? "댓글 숨기기" : "댓글 n개 보기";

    if (isExpanded) {
        commentsList.scrollTop = 0; // 스크롤 위치 초기화
        feed.style.height = `${commentsList.scrollHeight + 150}px`;

    } else {
        feed.style.height = '';
    }
}

function toggleLike(element, feedId) {
    const isLiked = element.classList.contains("bi-heart-fill");
    const url = isLiked ? "/linksy/feed/unlike" : "/linksy/feed/like";
    const userId = "testUser"; // 현재 로그인된 사용자 ID를 가져오세요.

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `feedId=${feedId}&userId=${userId}`,
    })
        .then(response => response.json())
        .then(likeAmount => {
            // 좋아요 상태 업데이트
            if (isLiked) {
                element.classList.remove("bi-heart-fill");
                element.classList.add("bi-heart");
            } else {
                element.classList.remove("bi-heart");
                element.classList.add("bi-heart-fill");
            }
            // 좋아요 개수 업데이트
            const likesElement = element.closest(".feed-footer").querySelector(".likes");
            likesElement.textContent = `좋아요 ${likeAmount}개`;
        })
        .catch(error => console.error("Error:", error));
}

// 더보기 기능
function toggleCaption(readMoreElement) {
    const caption = readMoreElement.previousElementSibling;
    const fullText = caption.getAttribute("data-full-text");

    if (readMoreElement.innerText === "더보기") {
        caption.innerText = fullText;
        readMoreElement.innerText = "숨기기";
    } else {
        const truncatedText = fullText.slice(0, 30) + "...";
        caption.innerText = truncatedText;
        readMoreElement.innerText = "더보기";
    }
}

