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
        // JSON 데이터
        const jsonData = [
            {
                "seq": 1130,
                "contents": "내용81",
                "boardSeq": 900318,
                "modId": "ADMIN",
                "modDt": "2024/06/19",
                "flag": 0,
                "num": 81,
                "totalCnt": 90000,
                "bottomCount": 10,
                "pageNo": 9,
                "pageSize": 10
            },
            {
                "seq": 1131,
                "contents": "내용82",
                "boardSeq": 900318,
                "modId": "ADMIN",
                "modDt": "2024/06/19",
                "flag": 0,
                "num": 82,
                "totalCnt": 90000,
                "bottomCount": 10,
                "pageNo": 1,
                "pageSize": 10
            }
        ];

        // 테이블의 tbody 선택
        const tbody = document.querySelector('#jsonTable tbody');

        // JSON 데이터를 기반으로 테이블 행 생성
        jsonData.forEach(item => {
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
