/**
 * 프로미스(Promises): ES6부터 도입된 프로미스는 비동기 작업의 결과를 다루기
 위한 객체입니다. 비동기 작업의 성공 또는 실패에 따라 then과 catch 메서드를
 사용하여 처리합니다.
 */
 
 
//비동기 작업을 수행하는  함수
function asyncTask(){
  return new Promise( (resolve, reject) => {
    
    //3초 후에 작업을 완료하고 성공 또는 실패 상태 
    
    setTimeout( () => { 
      const randomNumber = Math.random();
      console.log(`randomNumber:${randomNumber}`);
      
      if(randomNumber < 0.5){
        resolve('2.작업이 성공적으로 처리 되었습니다.');
      }else{
        reject('2.작업이 실패했습니다.');
      }
      
    },3000);//3초후에 작업 완료
        
  });
  
}

//Promise사용 비동기 작업
console.log('1 작업 시작')
asyncTask()
  .then((result) => {
     console.log(result); //작업이 성공한 경우
  })
  .catch( (error) => {
      console.log(error); //작업이 실패한 경우
  });





