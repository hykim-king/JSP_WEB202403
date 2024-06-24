/**
 * 일급 함수(First-Class Function):

일급 함수(First-Class Function)는 함수를 일반 값과 같이 취급하는 언어의 특성을 말합니다.
JavaScript는 일급 함수를 지원하기 때문에  함수를 변수에 할당하고, 
함수를 다른 함수의 인자로 전달하거나 함수에서 함수를 반환할 수 있습니다.
 */
 
//함수를 변수에 할당
const greet=function(name){
  console.log("Hello, "+name+"!");
}

//함수를 다른 함수의 인자(파라메터)로 전달
function sayHello(callback){
  callback("이상무");
}


sayHello(greet);//Hello, 이상무!


//함수에 함수를 반환(return)

function  createMultiplier(factor){
  
  return function(x){
    return x * factor;
  }
  
}

const double=createMultiplier(2);

console.log(double(5));//10

