/**
 * 변수와 상수 : var, let, const 
 *  
 */
 
//1. var : var는 JavaScript에서 변수를 선언하는 데 사용되는 키워드 입니다.
//ES5 이전의 표준이었으며, 함수 스코프를 가집니다.
//변수가 선언되기 전에 사용될 수 있습니다.


//변수 호이스팅(Variable Hoisting)은 JavaScript의 고유한 동작 방식으로, 변수
//가 선언된 위치에 상관 없이 코드의 맨 위로 끌어 올려지는 것 처럼 동작.
//그러나 실제로 변수의 선언만 끌어 올려지고, 초기화는 끌어 올려지지 않는다.
console.log('x=', x);

var x = 10;
var name = "이상무";

console.log('x=', x);
console.log('name=', name);

console.log("-----------------------------");
//2. let은 ES6에서 도입된 새로운 변수 선언 키워드 입니다. let으로 선언된 변수를 
//블록 스코프를 가집니다. 또한 변수가 선언된 위치 이전에 접근 불가.

//console.log(`age:${age}`);//ReferenceError: Cannot access 'age' before initialization
let age = 23;
let message = "Hello, World";

age = 22;
console.log(`age:${age}`);
console.log(`message:${message}`);

console.log("-----------------------------");
//3. const :
//const는 상수를 선언하는 데 사용되는 키워드 입니다.
const PI = 3.141592;
const COMPANY_NAME = "PCW Coporation";

//TypeError: Assignment to constant variable.
//PI = 99;

console.log(`PI:${PI}`);
console.log(`COMPANY_NAME:${COMPANY_NAME}`);







