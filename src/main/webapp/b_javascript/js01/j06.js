/**
 * 연산자: 산술연산자, 할당연산자, 연결연산자, 비교연산자,논리 연산자
 */
 //1. 산술연산자: 계산에 사용되는 연산자.
 //+,-,*,/,%.++.--
 
 let x = 10;
 let y = 3;
 console.log(`x:${x}, type:${typeof x}`);//x:10, type:number
 console.log('덧셈: x+y :',x+y);
 console.log('뺄셈: x-y :',x-y);
 console.log('나눗셈: x/y :',x/y);
 console.log('나머지: x%y :',x%y);
 
 //2.할당연산자 : 할당 연산자는 오른쪽의 실행 결과를 왼쪽 변수에 할당
 // =, +=,-=,*=,/=,%=
 
 //3. 연결연산자 : 둘 이상의 문자열을 합쳐서 하나의 문자열로 만드는 연산자.
 //+
 console.log('-------------------------');
 //4. 비교연산자 : 비교연산자는 두 값을 비교하는 데 사용됩니다. 결과는 불리언
 // ==, : 두 피연산자가 서로 같으면 true
 // === : 두 피연산자가 서로 같고 그리고 자료형도 같은면 true, 3 === '3' -> false
 // !=  : 두 피연산자가 서로 같지 않으며 true 
 // !==  : 두 피연산자가 서로 같지 않거나 자료형도 같지않으면 true 3 !== '3' -> true 
 // >,>=,<,<=
 
 let a = 10;
 let b = 5;
 
 console.log(`a>b, ${a>b}`);
 console.log(`a<b, ${a<b}`);
 b = '10';
 
 console.log(`a===b, ${a===b}`);//a===b, false
 console.log(`a==b, ${a==b}`);//a==b, true
 console.log(`a!=b, ${a!=b}`);//a!=b, false
 console.log(`a!==b, ${a!==b}`);//a!==b, true
 
 console.log('-------------------------');
 //5. 논리 연산자: 논리 연산자는 불리언 값에 대한 연산을 수행
 //OR연산자 : || (피연산자 중 하나만 true여도 true)
 //AND연산자 : &&(피연산자 둘다 true인 경우만 true)
 //NOT연산자 : !(피연산자 true를 false로 false를 true):
 
 
 x = true;
 y = false;
 
 //&&
 console.log(`x:${x}, type:${typeof x}`);//x:true, type:boolean
 console.log(`x && y :${x && y}`);//x && y :false
 console.log(`x && !y :${x && !y}`);//x && !y :true
 
 
 x = 5;
 y = 10;
 if( x > 0 && y>5){
  console.log("두 조건 모두 만족!");//두 조건 모두 만족!
}
 
a = true;
b = false;
 
console.log(a || b);//true
console.log(b || !b);//true 
 
 
 
 
 
 