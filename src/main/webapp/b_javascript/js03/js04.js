/**
 * 
 */

function convertToNumber(str) {
  try {
    //문자열을 숫자로 변환
    const number = parseInt(str);

    //만약 NaN(Not a Number)이면 예외 발생
    if (isNaN(number)) {
      throw new Error('입력값이 올랍른 숫자 형식이 아닙니다.')
    }

    console.log(number);

    //변환된 숫자를 반환
    return number;

  } catch (error) {
    //예외가 발생했을 때 실행되는 코드
    console.error("예외", error.message);
  } finally {
    //예외발생 여부에 관계없이 항상 실행
    console.log("finally 무조건 수행")
  }

  console.log("convertToNumber() 종료")
}

//사용자로 부터 숫자 입력
const userInput = prompt('숫자로 변환할 값을 입력 하세요.');

const result = convertToNumber(userInput); 

console.log("result:",result);