<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSON to Table</title>
    <style>
        table {
            width: 800px;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
        td {
            background-color: #f9f9f9;
            text-align: center;
            padding: 10px;
        }
    </style>
</head>
<body>

    <table id="jsonTable">
        <thead>
            <tr>
                <th>Seq</th>
                <th>Contents</th>
                <th>BoardSeq</th>
                <th>ModId</th>
                <th>ModDt</th>
                <th>Flag</th>
                <th>Num</th>
                <th>TotalCnt</th>
                <th>BottomCount</th>
                <th>PageNo</th>
                <th>PageSize</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    
    <script>
    function renderingPager(maxNum, currentPageNo, rowPerPage, bottomCount, url, scriptName) {
        let html = [];
        
        let maxPageNo = Math.floor((maxNum - 1) / rowPerPage) + 1;
        let startPageNO = Math.floor((currentPageNo - 1) / bottomCount) * bottomCount + 1;
        let endPageNo = Math.floor((currentPageNo - 1) / bottomCount + 1) * bottomCount;
        
        let nowBlockNo = Math.floor((currentPageNo - 1) / bottomCount) + 1;
        let maxBlockNo = Math.floor((maxNum - 1) / bottomCount) + 1;

        if (currentPageNo > maxPageNo) {
            return '';
        }

        html.push('<ul class="pagination">');
        
        // <<
        if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
            html.push('<li class="page-item">');
            html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\', 1);">');
            html.push('<span>&laquo;</span>');
            html.push('</a>');
            html.push('</li>');
        }
        
        // <
        if (startPageNO > bottomCount) {
            html.push('<li class="page-item">');
            html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + (startPageNO - bottomCount) + ');">');
            html.push('<span>&lt;</span>');
            html.push('</a>');
            html.push('</li>');
        }
        
        // 1 2 3 ... 10
        for (let inx = startPageNO; inx <= maxPageNo && inx <= endPageNo; inx++) {
            if (inx == currentPageNo) {
                html.push('<li class="page-item">');
                html.push('<a class="page-link active" href="#">' + inx + '</a>');
                html.push('</li>');
            } else {
                html.push('<li class="page-item">');
                html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + inx + ');">' + inx + '</a>');
                html.push('</li>');
            }
        }
        
        // >
        if (maxPageNo > endPageNo) {
            html.push('<li class="page-item">');
            html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + ((nowBlockNo * bottomCount) + 1) + ');">');
            html.push('<span>&gt;</span>');
            html.push('</a>');
            html.push('</li>');
        }
        
        // >>
        if (maxPageNo > endPageNo) {
            html.push('<li class="page-item">');
            html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + maxPageNo + ');">');
            html.push('<span>&raquo;</span>');
            html.push('</a>');
            html.push('</li>');
        }
        
        html.push('</ul>');

        return html.join('');
    }
    
        // JSON 데이터
        const jsonData = {
        		  "answerList": [
        			    {
        			      "seq": 1060,
        			      "contents": "내용11",
        			      "boardSeq": 900318,
        			      "modId": "ADMIN",
        			      "modDt": "07:25",
        			      "flag": 0,
        			      "num": 11,
        			      "totalCnt": 90000,
        			      "bottomCount": 10,
        			      "pageNo": 2,
        			      "pageSize": 10
        			    },
        			    {
        			      "seq": 1061,
        			      "contents": "내용12",
        			      "boardSeq": 900318,
        			      "modId": "ADMIN",
        			      "modDt": "07:01",
        			      "flag": 0,
        			      "num": 12,
        			      "totalCnt": 90000,
        			      "bottomCount": 10,
        			      "pageNo": 1,
        			      "pageSize": 10
        			    },
        			    {
        			      "seq": 1062,
        			      "contents": "내용13",
        			      "boardSeq": 900318,
        			      "modId": "ADMIN",
        			      "modDt": "06:37",
        			      "flag": 0,
        			      "num": 13,
        			      "totalCnt": 90000,
        			      "bottomCount": 10,
        			      "pageNo": 1,
        			      "pageSize": 10
        			    },
        			    {
        			      "seq": 1063,
        			      "contents": "내용14",
        			      "boardSeq": 900318,
        			      "modId": "ADMIN",
        			      "modDt": "06:13",
        			      "flag": 0,
        			      "num": 14,
        			      "totalCnt": 90000,
        			      "bottomCount": 10,
        			      "pageNo": 1,
        			      "pageSize": 10
        			    }
        			  ],
        			  "pageObject": {
        			    "seq": 1060,
        			    "contents": "내용11",
        			    "boardSeq": 900318,
        			    "modId": "ADMIN",
        			    "modDt": "07:25",
        			    "flag": 0,
        			    "num": 11,
        			    "totalCnt": 90000,
        			    "bottomCount": 10,
        			    "pageNo": 2,
        			    "pageSize": 10
        			  }
        			}


        // 테이블의 tbody 선택
        const tbody = document.querySelector('#jsonTable tbody');
        
        // 테이블 데이터
        if (!jsonData.answerList){
          throw new Error("Invalid data");
        }
        
        const answerList = jsonData.answerList;
        
        // JSON 데이터를 기반으로 테이블 행 생성
        answerList.forEach(item => {
            try {
              // 유효성 검사
              if (!item.seq || !item.contents || !item.boardSeq || !item.modId || !item.modDt || item.flag === undefined || !item.num || !item.totalCnt || !item.bottomCount || !item.pageNo || !item.pageSize) {
                  throw new Error("Invalid data");
              }
                  
              const row = document.createElement('tr');
  
              for (const key in item) {
                  const cell = document.createElement('td');
                  cell.textContent = item[key];
                  // 여기서 스타일을 설정합니다
                  cell.style.backgroundColor = '#f9f9f9';
                  cell.style.textAlign = 'center';
                  cell.style.padding = '10px';
                  row.appendChild(cell);
              }
  
              tbody.appendChild(row);
            } catch (error) {
                console.error("Error processing item:", item, error);
            }            
        });
    </script>
</body>
</html>
