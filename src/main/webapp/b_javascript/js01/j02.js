/**
 * 
 */
 
//1. 한 줄 주석
var x = 5; //변수 x에 값 5를 할당

console.log('x='+5); 

//2. 여러 줄 주석
/* 
여러 줄 주석 입니다.
여러 줄에 걸쳐서 설명을 적을 수 있어요.
*/


var y = 10;
console.log("x+y=",x+y);

//3. 문서화 주석
/**
 * 두 수를 더하는 함수
 * @param {number} x 첫 번째 숫자
 * @param {number} y 두 번째 숫자
 * @returns {number} 두 숫자의 합
 */
function add(x,y){
  return x+y;
}
 
console.log(add(15,13)); 
 