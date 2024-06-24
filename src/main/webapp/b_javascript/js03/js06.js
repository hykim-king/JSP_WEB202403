/**
 * 콜백 함수(Callbacks): 가장 기본적인 비동기 처리 방식으로,
 작업이 완료되면 호출될 함수를 인자로 전달하는 방식입니다. 예를 들어, 
 setTimeout 함수를 사용한 코드가 이에 해당합니다.
 */
 
 
//비동기 작업을 수행하는 함수
function doSomethingAsync(callback){
  console.log('1.비동기 작업을 시작 합니다.');
  
  //setTimeout 함수를 사용하여 3초 후 콜백 함수 호출
  
  setTimeout( function(){
    console.log('2. 비동기 작업이 완료 되었습니다.');
    callback();
    },3000);

} 


//콜백 함수
function callback(){
  console.log('3. 콜백 함수 호출!');  
}


//doSomethingAsync호출
doSomethingAsync(callback);