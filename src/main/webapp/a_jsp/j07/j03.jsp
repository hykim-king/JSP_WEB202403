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
        .pagination {
            display: flex;
            list-style: none;
            padding: 0;
        }
        .page-item {
            margin: 0 2px;
        }
        .page-link {
            display: block;
            padding: 8px 16px;
            text-decoration: none;
            background-color: #f2f2f2;
            border: 1px solid #ddd;
            color: #000;
        }
        .page-link.active {
            background-color: #007bff;
            color: #fff;
            border-color: #007bff;
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
                <th>Actions</th> <!-- 새 열 추가 -->
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    
    <div id="pagination"></div>
    
    <script>
        function renderTable(data) {
            const tbody = document.querySelector('#jsonTable tbody');
            tbody.innerHTML = ''; // 기존 내용을 초기화
            
            data.forEach(item => {
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
                
                // 새 셀 추가
                const actionCell = document.createElement('td');
                const div = document.createElement('div');
                const button = document.createElement('input');
                button.type = 'button';
                button.value = '수정';
                button.className = 'btn btn-outline-success btn-sm';
                button.setAttribute('data-hidden-info', item.seq); // seq 값을 data-hidden-info 속성에 설정

                div.appendChild(button);
                actionCell.appendChild(div);
                actionCell.classList.add('custom-cell');
                row.appendChild(actionCell);
                
                tbody.appendChild(row);
            });
        }
        
        function renderPagination(maxNum, currentPageNo, rowPerPage, bottomCount, renderFunction) {
            const paginationDiv = document.getElementById('pagination');
            paginationDiv.innerHTML = ''; // 기존 내용을 초기화
            
            const maxPageNo = Math.floor((maxNum - 1) / rowPerPage) + 1;
            const startPageNO = Math.floor((currentPageNo - 1) / bottomCount) * bottomCount + 1;
            const endPageNo = Math.floor((currentPageNo - 1) / bottomCount + 1) * bottomCount;
            
            const nowBlockNo = Math.floor((currentPageNo - 1) / bottomCount) + 1;
            const maxBlockNo = Math.floor((maxNum - 1) / bottomCount) + 1;
            
            const ul = document.createElement('ul');
            ul.classList.add('pagination');
            
            // <<
            if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
                const li = document.createElement('li');
                li.classList.add('page-item');
                const a = document.createElement('a');
                a.classList.add('page-link');
                a.href = 'javascript:void(0)';
                a.innerHTML = '&laquo;';
                a.onclick = () => renderFunction(1);
                li.appendChild(a);
                ul.appendChild(li);
            }
            
            // <
            if (startPageNO > bottomCount) {
                const li = document.createElement('li');
                li.classList.add('page-item');
                const a = document.createElement('a');
                a.classList.add('page-link');
                a.href = 'javascript:void(0)';
                a.innerHTML = '&lt;';
                a.onclick = () => renderFunction(startPageNO - bottomCount);
                li.appendChild(a);
                ul.appendChild(li);
            }
            
            // 1 2 3 ... 10
            for (let inx = startPageNO; inx <= maxPageNo && inx <= endPageNo; inx++) {
                const li = document.createElement('li');
                li.classList.add('page-item');
                const a = document.createElement('a');
                a.classList.add('page-link');
                a.href = 'javascript:void(0)';
                a.innerHTML = inx;
                if (inx == currentPageNo) {
                    a.classList.add('active');
                } else {
                    a.onclick = () => renderFunction(inx);
                }
                li.appendChild(a);
                ul.appendChild(li);
            }
            
            // >
            if (maxPageNo > endPageNo) {
                const li = document.createElement('li');
                li.classList.add('page-item');
                const a = document.createElement('a');
                a.classList.add('page-link');
                a.href = 'javascript:void(0)';
                a.innerHTML = '&gt;';
                a.onclick = () => renderFunction((nowBlockNo * bottomCount) + 1);
                li.appendChild(a);
                ul.appendChild(li);
            }
            
            // >>
            if (maxPageNo > endPageNo) {
                const li = document.createElement('li');
                li.classList.add('page-item');
                const a = document.createElement('a');
                a.classList.add('page-link');
                a.href = 'javascript:void(0)';
                a.innerHTML = '&raquo;';
                a.onclick = () => renderFunction(maxPageNo);
                li.appendChild(a);
                ul.appendChild(li);
            }
            
            paginationDiv.appendChild(ul);
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
        };
        
        const answerList = jsonData.answerList;

        function renderPage(pageNo) {
            const pageSize = jsonData.pageObject.pageSize;
            const totalCnt = jsonData.pageObject.totalCnt;
            const bottomCount = jsonData.pageObject.bottomCount;
            
            const startIndex = (pageNo - 1) * pageSize;
            const endIndex = Math.min(startIndex + pageSize, answerList.length);
            const pageData = answerList.slice(startIndex, endIndex);
            
            renderTable(pageData);
            renderPagination(totalCnt, pageNo, pageSize, bottomCount, renderPage);
        }
        
        // 초기 페이지 렌더링
        renderPage(1);
    </script>
</body>
</html>
