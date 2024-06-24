/**
 * 이름을 넣으면 전화번호 출력
 */

const contacts = [
  "이상무:01012345678",
  "홍길동:01012345699",
  "이순이:01012345600"
];

const para = document.querySelector("p");
const btn = document.querySelector("button");
const input = document.querySelector("#search");


//event감지
btn.addEventListener("click" ,function(){
  console.log('btn click');
  
  let searchName = input.value;
  console.log(`searchName:${searchName}`);
  
  input.value = "";//입력 지우기
  input.focus();
  
  //배열의 길이
  console.log(`배열의 길이:${contacts.length}`);
  
  for(let i=0;i < contacts.length ; i++){
     //console.log(contacts[i]) 
     let splictContact = contacts[i].split(":");
     //splictContact[0] :이름, splictContact[1]: 전화번호
     //console.log(splictContact[0]+","+splictContact[1]);
     
     if(splictContact[0] === searchName ){
        para.textContent = splictContact[0] +"의 전화 번호는 "+splictContact[1] +" 입니다.";
        break;
     }else{
        para.textContent = "연락처가 없습니다.";
        
     }// --if end
     
  }//--for end
  
});

