/**
 * 함수 표현식(Function Expression):

함수 표현식(Function Expression)은 JavaScript에서 함수를 변수에 할당하는 
방법 중 하나입니다. 함수 표현식은 함수를 정의하고 변수에 할당하는 과정을 통해 
함수를 만듭니다. 
이렇게 만들어진 함수는 변수를 통해 참조할 수 있습니다.
 */

//익명 함수 표현식
let greet = function(name) {
  console.log("Hello, " + name + "!");
}

greet("Alice");//Hello, Alice!

//기명 함수 표현식
let add = function sum(x, y) {
  return x + y;
}

console.log(add(13,15));//28