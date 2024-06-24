/**
 * `JSON.parse()` : JSON문자열을 JavaScript 객체로 변환.

`JSON.stringify()`: JavaScript 객체를 문자열로 변환.
 */
 
 const jsonString = '{"name": "Alice","age":30}';
 
 console.log(`jsonString:${typeof jsonString}`);//jsonString:string

 
 const obj = JSON.parse(jsonString);
 console.log(`obj.name: ${obj.name}`);//obj.name: Alice
 console.log(`obj.age: ${obj.age}`);//obj.age: 30
 
 console.log(`-------------------------------`);
 
 const obj2 = {name: "Alice", age:30};
 
 const jString = JSON.stringify(obj2);//jString:string
 console.log(`jString:${typeof jString}`); 