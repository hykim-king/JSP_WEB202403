try {
    // 예외 발생
    throw new Error('예외 발생!');
} catch (error) {
    // 오류 객체의 속성 출력
    console.log('오류 이름:', error.name);
    console.log('오류 메시지:', error.message);
    console.log('스택 추적 정보:', error.stack);

    // toString() 메서드를 통해 문자열로 변환하여 출력
    console.log('오류 객체:', error.toString());
}