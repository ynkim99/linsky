document.getElementById('notificationToggle').addEventListener('change', function() {
    const isChecked = this.checked;
    console.log('알림 상태:', isChecked ? '켜짐' : '꺼짐');
});
