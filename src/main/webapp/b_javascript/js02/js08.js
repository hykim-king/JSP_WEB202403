/**
 * 
 */

// 두 숫자를 더하는 함수

function add(x, y) {
  return x + y;
}


function multiply(x, y) {
  return x*y;
}

//두 숫자 중 더 큰 값을 반환하는 함수
function max(x,y){
  if(x>y){
    return x;
  }else{
    return y;
  }
  
}

//화면에 메시지 출력 함수
function greet(name){
  console.log("Hello, "+name +"!");
}

//함수 호출
let sum = add(13, 15);

//add(13,15): 28
console.log(`add(13,15): ${sum}`);

let product = multiply(5,2);
//multiply(5,2):10
console.log(`multiply(5,2):${product}`);

let biggerNumber = max(13,18);
console.log(`max(13,18):${biggerNumber}`);//max(13,18):18

//Hello, 이상무!
greet("이상무");
