// 댓글 펼치기
function showComments(spanElement) {
    const commentsView = spanElement.closest('.comments-view');
    const commentsList = commentsView.querySelector('.comments-list');
    const feed = commentsView.closest('.feeds-container');
    
    // feedId를 'feeds-container'의 data-feed-id에서 가져옴
    const feedId = feed.getAttribute('data-feed-id');
    console.log(feedId);

    commentsList.innerHTML = ''; // 댓글 리스트 초기화

    // 서버에서 댓글을 가져오는 AJAX 요청
    fetch(`/linksy/comments?feedId=${feedId}`)  // 댓글을 가져올 서버 엔드포인트
        .then(response => response.json())  // 서버로부터 JSON 응답을 받음
        .then(comments => {
            // 댓글 항목 생성
            comments.forEach(comment => {
                const commentElement = document.createElement('div');
                commentElement.className = 'comment-item';
                commentElement.innerHTML = `<strong>${comment.member.userNickname}</strong>: <span>${comment.commentContent}</span>`;
                commentsList.appendChild(commentElement);
            });

            // 댓글 영역을 확장
            commentsList.classList.toggle('show');

            // 버튼 텍스트 변경
            const isExpanded = commentsList.classList.contains('show');
            spanElement.innerText = isExpanded ? "댓글 숨기기" : `댓글 ${comments.length}개 보기`;

            // 피드 높이 변경하지 않고 댓글 영역만 확장
            commentsList.style.maxHeight = isExpanded ? `${commentsList.scrollHeight}px` : '0';

            if (isExpanded) {
                commentsList.scrollTop = 0; // 스크롤 초기화
            }
        })
        .catch(error => {
            console.error('댓글을 가져오는 데 실패했습니다:', error);
        });
}


// 더보기 기능
function toggleCaption(readMoreElement) {
    const caption = readMoreElement.previousElementSibling;
    const fullText = caption.getAttribute("data-full-text");

    if (fullText.length > 15) {
        if (readMoreElement.innerText === "더보기") {
            caption.innerText = fullText;
            readMoreElement.innerText = "숨기기";
        } else {
            const truncatedText = fullText.slice(0, 15) + "...";
            caption.innerText = truncatedText;
            readMoreElement.innerText = "더보기";
        }
    } else {
        readMoreElement.style.display = 'none'; // 15글자 이하일 때 버튼 숨기기
    }
}