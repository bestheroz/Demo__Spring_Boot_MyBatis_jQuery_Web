<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html>
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">&lt;my:html&gt;
                    <small>사용하기</small>
                </h1>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 사용법 </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. 옵션종류 </a>
                </div>
            </div>
            <div class="card" id="list1">

                <h3 class="panel-title text-center">1. 사용법</h3>
                <div class="card-body">
				<pre class="prettyprint lang-html">
&lt;my:html title="페이지 타이틀" fileDownloader="YES" progressBar="NO"&gt;
&lt;body&gt;
&lt;/body&gt;
&lt;/my:html&gt;
</pre>
                </div>
            </div>

            <div class="card" id="list2">
                <h3 class="panel-title text-center">2. 옵션종류</h3>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover table-sm">
                            <colgroup>
                                <col style="width: 8%;"/>
                                <col style="width: 15%;"/>
                                <col style="width: 10%;"/>
                                <col style="width: 67%;"/>
                            </colgroup>
                            <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>옵션명</th>
                                <th>기본사용여부</th>
                                <th>설명</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td></td>
                                <td>title</td>
                                <td>N</td>
                                <td>페이지 타이틀을 설정할때 사용한다. 사용하지 않으면 CommonCode4WebContents.TITLE 값으면 출력된다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>cookie</td>
                                <td>N</td>
                                <td>쿠키 작업을 지원하는 JS 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>datetimePicker</td>
                                <td>Y</td>
                                <td>달력/시간 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>fileDownloader</td>
                                <td>N</td>
                                <td>ajax를 통한 파일 다운로드 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>handlebars</td>
                                <td>N</td>
                                <td>handlebars 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>icon</td>
                                <td>Y</td>
                                <td>아이콘을 제공해주는 font-awesome 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>maxLength</td>
                                <td>Y</td>
                                <td>input, textarea에 입력되는 자리수를 보여주는 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>modal</td>
                                <td>N</td>
                                <td>modal 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>numberFormatter</td>
                                <td>N</td>
                                <td>숫자 포매터(Number Formatter) 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>paging</td>
                                <td>N</td>
                                <td>(표준화 준비중)ajax를 통한 동적 UI 구성을 위한 페이지 처리 JS 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>progressBar</td>
                                <td>Y</td>
                                <td>액션 처리중일때 진행 상태를 알려주는 플러그인 포함 여부를 정한다</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>popup</td>
                                <td>Y</td>
                                <td>팝업 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>scrollUp</td>
                                <td>Y</td>
                                <td>"Scroll to top" 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>table</td>
                                <td>N</td>
                                <td>테이블 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>textEditor</td>
                                <td>N</td>
                                <td>textEditor관련 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>validator</td>
                                <td>N</td>
                                <td>엘리먼트 유효성 검증 플러그인 포함 여부를 정한다.</td>
                            </tr>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>#</th>
                                <th>옵션명</th>
                                <th>기본사용여부</th>
                                <th>설명</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <my:footer/>
    <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
    <script data-for="ready">
        jQuery(($) => {
            $('tbody > tr').each((index, value) => {
                $(this).children('td').eq(0).text(index + 1).addClass('text-center');
                $(this).children('td').eq(2).addClass('text-center');
            });
        });
    </script>
    <script>
        function try1_1() {
            MyCommon.goWindow('http://google.com');
        }

        function try1_2() {
            MyCommon.goWindow('http://google.com', {
                height: 800,
                width: 1200
            });
        }

        function try2_1() {
            MyCommon.goTab('http://google.com');
        }
    </script>
</my:html>
