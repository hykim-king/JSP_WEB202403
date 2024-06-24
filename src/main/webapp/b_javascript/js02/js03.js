/**
 * 
 */
 
//document.querySelector("#weather") 
const select = document.querySelector("#weather");//id로 선택
const para   = document.querySelector("p");//tag로 선택


//event감지
select.addEventListener("change", setWeather);


function setWeather(){
  console.log('setWeather');
  const choice = select.value;
  
  console.log('choice:'+choice);
  
  if("sunny" === choice){
    para.textContent ="오늘은 날씨가 맑고 화창합니다. 반바지를 입고 한강에 가서 아이스크림을 먹어요.";    
  }else if("rainy" === choice){
    para.textContent ="밖에 비가 오고 있습니다. 우산을 챙기 세요.";  
  }else if("snowing" === choice){
    para.textContent ="눈이 내리고  있습니다. 매우 춥습니다. 따뜻한 커피를 마시면 집에 있어요.";  
  }else if("overcast" === choice){
    para.textContent ="비는 오지 않지만 하늘이 회색 입니다. 비가 올지 모르니, 우비를 챙기세요.";  
  }else{
    para.textContent = "";
  }
  
  
  
}


