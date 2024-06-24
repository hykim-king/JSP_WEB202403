/**
 * JavaScript 데이터 타입
 * 문자열, 숫자, 불리언, null, undefined
 */
 
 //1. 문자열(string):
 //문자열은 텍스트 데이터를 나타내는 데 사용됩니다. 
 //따옴표(작은 따옴표나 큰 따옴표)로 묶어서 표현 합니다.
 
 let str = "Hello, World";
 let str2 = 'Hello, World';
 
 //typeof 변수의 type확인
 console.log(`str:${str}, ${typeof str}`);//str:Hello, World, string
 console.log(`str2:${str2}, ${typeof str2}`);//str2:Hello, World, string
 
 //2. 숫자 (number): 정수와 실수를 나타내는 데 사용 됩니다.
 let num1 = 13;//정수 
 let num2 = 15.5;//실수
 
 console.log(`num1:${num1}, type :${typeof num1}`);//num1:13, type :number
 console.log(`num2:${num2}, type :${typeof num2}`);
 
 //3. 불리언(Boolean):  true/false 값을 가지는 데이터
 
 let isTure = true;
 let isFalse = false;
 
 console.log(`isTure:${isTure}, type :${typeof isTure}`);
 console.log(`isFalse:${isFalse}, type :${typeof isFalse}`);//isFalse:false, type :boolean
 
 //4. null : 
 // null은 값이 존재하지 않음을 나타내는 데이터 입니다.
 // 변수를 초기화할 때 사용됩니다.
 let myVar = null;
 
 //myVar: null,  type : object
 console.log(`myVar: ${myVar},  type : ${typeof myVar}`);
 
 //5. undefined:
 // undefined 값이 할당되지 않은 상태를 나타내는 데이터 타입니다.
 // 변수가 선언되었지만 값을 가지지 않은 경우
 
 let youVar ;
 //yourVar:undefined, type:undefined
 console.log(`yourVar:${youVar}, type:${typeof yourVar}`);
 
 //6. function:
 //
 let nyFun = function pcw(){ 
  
 }
 
 console.log(`함수:${typeof nyFun}`);//함수:function
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 