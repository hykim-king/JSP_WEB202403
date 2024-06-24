/**
 * 화살표 함수(Arrow Function):
화살표 함수(Arrow Function)는 ES6부터 도입된 새로운 함수 표현식 입니다. 
Arrow Function은 함수를 간결하게 작성할 수 있고, 
기존 함수 표현식과 비교하여 가독성이 높아지는 장접이 있습니다.
 */
 
 document.getElementsByClassName
//매개변수가 하나인 경우
let square = x => x * x;
console.log(square(3)); //9


//매개변수가 여러 개인 경우
let add = (x, y) => x+y;
console.log(add(13,15));//28

//함수 본문이 여러 줄인 경우
let greet = name => {
  console.log("Hello,"+name+ "!");
  
}

greet("이상무");//Hello,이상무!
/*
1. 함수 선언이 간결 합니다.
2. function키워드 대신 => 기호를 사용하여 정의 합니다.
3. 매개변수가 하나인 경우에는 괄호를 생략할 수 있습니다.
4. 함수 본문이 한 줄인 경우 중괄호와 return 키워드를 생략할 수 있습니다.
(단 이때는 자동으로 반환됩니다.)
*/
