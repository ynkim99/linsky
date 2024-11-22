function showLikesPopup(feedId) {
    const popup = document.getElementById('likesPopup');
    const likesList = popup.querySelector('.likes-list');
    
    // 좋아요 목록 가져오기
    fetch(`/linksy/feed/${feedId}/likes`)
        .then(response => response.json())
        .then(likes => {
            likesList.innerHTML = ''; // 기존 목록 초기화
            
            likes.forEach(like => {
                const likeItem = document.createElement('div');
                likeItem.className = 'like-item';
                likeItem.innerHTML = `
                    <img src="/images/profile/${like.userImg}" alt="프로필 사진">
                    <span>${like.userNickname}</span>
                `;
                likesList.appendChild(likeItem);
            });
            
            popup.style.display = 'block';
        })
        .catch(error => console.error('Error:', error));
}

function closeLikesPopup() {
    const popup = document.getElementById('likesPopup');
    popup.style.display = 'none';
}

// ESC 키로 팝업 닫기
document.addEventListener('keydown', function(event) {
    if (event.key === 'Escape') {
        closeLikesPopup();
    }
});