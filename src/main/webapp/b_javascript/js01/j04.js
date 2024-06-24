
/**
 * console.log()
 * JavaScript에서 가장 기본적으로 사용되는 함수 중 하나로, 콘솔에 메시지를
 * 출력하는 데에 사용됩니다. 
 * 주로 디버깅 목적으로 사용되며, 코드 실행 중 중요한 값이나 상태를 확인 하는 데
 * 유용 합니다.
 */
 
 
 //1. 메시지 출력
 console.log("곧 점심 입니다.");
 
 //2. 변수값 출력
 let age = 22;
 console.log("age="+age);
 
 //3. 여러값 출력
 let name = "이상무";
 console.log("age:",age, "name:",name);
 
 //4. 템플릿 리터럴 : 
 //템플릿 리터럴(Template Literal)은 문자열을 편리하게 작성할 수 있는 JavaScript의 기능
 console.log(`name: ${name}`);
 
 //5. 배열 출력
 let numbers = [1,2,3,4,5];
 console.log(`numbers:${numbers}`);
 
 
 //6. 함수 호출 결과 출력
  function add(x, y){
    return x+y;  
  }
 console.log(`add(13, 15):${add(13, 15)}`);
 
 
 