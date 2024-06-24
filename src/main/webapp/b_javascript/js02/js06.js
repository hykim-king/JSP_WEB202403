/**
 * 구구단
 */

for (let i = 2; i<=9; i++) {
  document.write("<div>");
  document.write("<h3>"   +i+   "단</h3>");
  for (let j = 1; j<=9; j++) {
      document.write(i + " *" + j + "=" + (i * j) + "<br/>");
  }
  document.write("</div>");
}  