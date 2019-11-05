<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html handlebars="YES" numberFormatter="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">HTML 템플릿
                    <small>(handlebars js)이용하기</small>
                </h1>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 기본형 </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. 배열형 </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 3. 데이터처리(가공) </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 4. 조건처리(if문) </a>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. 기본형</h3>
                <div class="card-body">
                    <h4>required</h4>
                    <pre class="prettyprint lang-html">&lt;my:html handlebars="YES"&gt;</pre>
                    <h5 class="card-title">템플릿 선언</h5>
                    <pre class="prettyprint lang-html">
&lt;script id="template01" type="text/x-handlebars-template"&gt;
&lt;div&gt;
    &lt;span&gt;{{value1}}&lt;/span&gt;
    &lt;span&gt;{{value2}}&lt;/span&gt;
    &lt;span&gt;{{value3}}&lt;/span&gt;
&lt;/div&gt;
&lt;/script&gt;
</pre>
                    <h5 class="card-title">데이터 준비</h5>
                    <pre class="prettyprint lang-html">
{ no : 1004, name : "천사", value : "천사값" }
</pre>
                    <h5 class="card-title">결과 html</h5>
                    <pre class="prettyprint lang-html">
&lt;div&gt;
    &lt;span&gt;1004&lt;/span&gt;
    &lt;span&gt;천사&lt;/span&gt;
    &lt;span&gt;천사값&lt;/span&gt;
&lt;/div&gt;
</pre>
                    <h5 class="card-title">MyHandlebars.drawDynamicHtml() 이용</h5>
                    <pre class="prettyprint lang-html">
MyHandlebars.drawDynamicHtml(targetObj, mode, template, data);
</pre>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <span class="form-control" id="resultTry1_1" readonly></span>
                    </div>
                    <pre class="prettyprint lang-html">
let data = { no : 1004, name : "천사", value : "천사값" };
MyHandlebars.drawDynamicHtml($('#resultTry1_1'), 'html', 'template01', data);
</pre>
                </div>

                <div class="card" id="list2">
                    <h3 class="panel-title text-center">2. 배열형</h3>
                    <div class="card-body">
                        <h5 class="card-title">템플릿 선언</h5>
                        <pre class="prettyprint lang-html">
&lt;script id="template02" type="text/x-handlebars-template"&gt;
&lt;table&gt;
    &lt;thead&gt;
        &lt;tr&gt;
            &lt;th&gt;&lt;/th&gt;
            &lt;th&gt;&lt;/th&gt;
            &lt;th&gt;&lt;/th&gt;
        &lt;/tr&gt;
    &lt;/thead&gt;
    &lt;tbody&gt;
{{#each this}}
        &lt;tr&gt;
            &lt;td&gt;{{no}}&lt;/td&gt;
            &lt;td&gt;{{name}}&lt;/td&gt;
            &lt;td&gt;{{value}}&lt;/td&gt;
        &lt;/tr&gt;
{{/each}}
    &lt;/tbody&gt;
&lt;/table&gt;
&lt;/script&gt;
</pre>
                        <h5 class="card-title">데이터 준비</h5>
                        <pre class="prettyprint lang-js">
[{ no : 1, name : "가나다", value : "첫번째 값" }, { no : 2, name : "라마바", value : "두번째 값" }, { no : 3, name : "사아자", value : "세번째 값" }]
</pre>
                        <h5 class="card-title">결과 html</h5>
                        <pre class="prettyprint lang-html">
&lt;table&gt;
    &lt;thead&gt;
        &lt;tr&gt;
            &lt;th&gt;&lt;/th&gt;
            &lt;th&gt;&lt;/th&gt;
            &lt;th&gt;&lt;/th&gt;
        &lt;/tr&gt;
    &lt;/thead&gt;
    &lt;tbody&gt;
        &lt;tr&gt;
            &lt;td&gt;1&lt;/td&gt;
            &lt;td&gt;가나다&lt;/td&gt;
            &lt;td&gt;첫번째 값&lt;/td&gt;
        &lt;/tr&gt;
        &lt;tr&gt;
            &lt;td&gt;2&lt;/td&gt;
            &lt;td&gt;라마바&lt;/td&gt;
            &lt;td&gt;두번째 값&lt;/td&gt;
        &lt;/tr&gt;
        &lt;tr&gt;
            &lt;td&gt;3&lt;/td&gt;
            &lt;td&gt;사아자&lt;/td&gt;
            &lt;td&gt;세번째 값&lt;/td&gt;
        &lt;/tr&gt;
    &lt;/tbody&gt;
&lt;/table&gt;
</pre>
                        <h5 class="card-title">MyHandlebars.drawDynamicHtml() 이용</h5>
                        <pre class="prettyprint lang-js">
MyHandlebars.drawDynamicHtml(targetObj, mode, template, data);
</pre>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <button class="btn btn-outline-primary" type="button" style="height: 90px;" onclick="try2_1()">
                                    <i class="far fa-play-circle"></i>
                                    Demo
                                </button>
                            </div>
                            <span class="form-control" id="resultTry2_1" style="height: 90px;" readonly></span>
                        </div>
                        <pre class="prettyprint lang-js">
let data = [{ no : 1, name : "가나다", value : "첫번째 값" }, { no : 2, name : "라마바", value : "두번째 값" }, { no : 3, name : "사아자", value : "세번째 값" }];
MyHandlebars.drawDynamicHtml($('#resultTry2_1'), 'html', 'template02', data);
</pre>
                    </div>

                    <div class="card" id="list3">
                        <h3 class="panel-title text-center">3. 데이터처리(가공)</h3>
                        <div class="card-body">
                            <h5 class="card-title">템플릿 선언</h5>
                            <pre class="prettyprint lang-html">
&lt;script id="template03" type="text/x-handlebars-template"&gt;
&lt;div&gt;
    &lt;span&gt;{{getYYYYMMDD dateValue}}&lt;/span&gt;
    &lt;span&gt;{{formatDate dateValue 'YYYY.SMM.DD'}}&lt;/span&gt;
    &lt;span&gt;{{thousandComma won}}&lt;/span&gt;
    &lt;span&gt;{{replace name 'abc' 'zzz'}}&lt;/span&gt;
    &lt;span&gt;{{substring name 0 3}}&lt;/span&gt;
    &lt;span&gt;{{unescapeXss filterredHtml}}&lt;/span&gt;
&lt;/div&gt;
&lt;/script&gt;
</pre>
                            <h5 class="card-title">데이터 준비</h5>
                            <pre class="prettyprint lang-js">
{ dateValue : 1513745846373, won : 3200000, name : "abcdefghijk", filterredHtml : '&lt;div&gt;' }
</pre>
                            <h5 class="card-title">결과 html</h5>
                            <pre class="prettyprint lang-html">
&lt;script id="template03" type="text/x-handlebars-template"&gt;
&lt;div&gt;
    &lt;span&gt;2017-12-20&lt;/span&gt;
    &lt;span&gt;2017.12.20&lt;/span&gt;
    &lt;span&gt;3,200,000&lt;/span&gt;
    &lt;span&gt;zzzdefghijk&lt;/span&gt;
    &lt;span&gt;abc&lt;/span&gt;
    &lt;span&gt;&lt;div&gt;&lt;/span&gt;
&lt;/div&gt;
&lt;/script&gt;
</pre>
                            <h5 class="card-title">MyHandlebars.drawDynamicHtml() 이용</h5>
                            <pre class="prettyprint lang-js">
MyHandlebars.drawDynamicHtml(targetObj, mode, template, data);
</pre>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <button class="btn btn-outline-primary" type="button" style="height: 60px;" onclick="try3_1()">
                                        <i class="far fa-play-circle"></i>
                                        Demo
                                    </button>
                                </div>
                                <span class="form-control" id="resultTry3_1" style="height: 60px;" readonly></span>
                            </div>
                            <h5 class="card-title">데이터처리(가공)를 추가하려면 Handlebars.registerHelper(); 사용방법을 익혀서 추가하자.</h5>
                            <pre class="prettyprint lang-js">
let data = { dateValue : 1513745846373, won : 3200000, name : "abcdefghijk", filterredHtml : '&lt;div&gt;' };
MyHandlebars.drawDynamicHtml($('#resultTry3_1'), 'html', 'template03', data);
</pre>
                        </div>
                        <div class="card" id="list4">
                            <h3 class="panel-title text-center">4. 조건처리(if문)</h3>
                            <div class="card-body">
                                <h5 class="card-title">템플릿 선언</h5>
                                <pre class="prettyprint lang-html">
&lt;script id="template04" type="text/x-handlebars-template"&gt;
&lt;div&gt;
    {{#if_empty dateValue}}&lt;span&gt;{{dateValue}}&lt;/span&gt;{{/if_empty}}
    {{#if_not_empty dateValue}}&lt;span&gt;{{dateValue}}&lt;/span&gt;{{/if_not_empty}}
    {{#if_eq name 'abcdefghijk'}}&lt;span&gt;{{name}}&lt;/span&gt;{{/if_eq}}
&lt;/div&gt;
&lt;/script&gt;
</pre>
                                <h5 class="card-title">데이터 준비</h5>
                                <pre class="prettyprint lang-js">
{ dateValue : 1513745846373, name : "abcdefghijk" }
</pre>
                                <h5 class="card-title">결과 html</h5>
                                <pre class="prettyprint lang-html">
&lt;div&gt;
    &lt;span&gt;1513745846373&lt;/span&gt;
    &lt;span&gt;abcdefghijk&lt;/span&gt;
&lt;/div&gt;
</pre>
                                <h5 class="card-title">MyHandlebars.drawDynamicHtml() 이용</h5>
                                <pre class="prettyprint lang-js">
MyHandlebars.drawDynamicHtml(targetObj, mode, template, data);
</pre>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <button class="btn btn-outline-primary" type="button" onclick="try4_1()">
                                            <i class="far fa-play-circle"></i>
                                            Demo
                                        </button>
                                    </div>
                                    <span class="form-control" id="resultTry4_1" readonly></span>
                                </div>
                                <h5 class="card-title">조건처리(if)를 추가하려면 Handlebars.registerHelper(); 사용방법을 익혀서 추가하자.</h5>
                                <pre class="prettyprint lang-js">
let data = { dateValue : 1513745846373, name : "abcdefghijk" }
MyHandlebars.drawDynamicHtml($('#resultTry4_1'), 'html', 'template04', data);
</pre>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </main>
    <my:footer/>
    <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
    <script data-for="ready">
        jQuery(($) => {
        });
    </script>
    <script>
        function try1_1() {
            let data = {
                no: 1004,
                name: "천사",
                value: "천사값"
            };
            MyHandlebars.drawDynamicHtml($('#resultTry1_1'), 'text', 'template01', data);
        }

        function try2_1() {
            let data = [{
                no: 1,
                name: "가나다",
                value: "첫번째 값"
            }, {
                no: 2,
                name: "라마바",
                value: "두번째 값"
            }, {
                no: 3,
                name: "사아자",
                value: "세번째 값"
            }];
            MyHandlebars.drawDynamicHtml($('#resultTry2_1'), 'text', 'template02', data);
        }

        function try3_1() {
            let data = {
                dateValue: 1513745846373,
                won: 3200000,
                name: "abcdefghijk",
                filterredHtml: '&lt;div&gt;'
            };
            MyHandlebars.drawDynamicHtml($('#resultTry3_1'), 'text', 'template03', data);
        }

        function try4_1() {
            let data = {
                dateValue: 1513745846373,
                name: "abcdefghijk"
            };
            MyHandlebars.drawDynamicHtml($('#resultTry4_1'), 'text', 'template04', data);
        }
    </script>
    <script id="template01" type="text/x-handlebars-template">
        <div>
            <span>{{no}}</span>
            <span>{{name}}</span>
            <span>{{value}}</span>
        </div>
    </script>
    <script id="template02" type="text/x-handlebars-template">
        <table>
            <thead class="thead-dark">
            <tr>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            {{#each this}}
            <tr>
                <td>{{no}}</td>
                <td>{{name}}</td>
                <td>{{value}}</td>
            </tr>
            {{/each}}
            </tbody>
        </table>
    </script>
    <script id="template03" type="text/x-handlebars-template">
        <div>
            <span>{{getYYYYMMDD dateValue}}</span>
            <span>{{formatDate dateValue 'YYYY.SMM.DD'}}</span>
            <span>{{thousandComma won}}</span>
            <span>{{replace name 'abc' 'zzz'}}</span>
            <span>{{substring name 0 3}}</span>
            <span>{{unescapeXss filterredHtml}} <!--테스트화면에선 제대로 나오지 않음. --></span>
        </div>
    </script>
    <script id="template04" type="text/x-handlebars-template">
        <div>
            {{#if_empty dateValue}}<span>{{dateValue}}</span>{{/if_empty}}
            {{#if_not_empty dateValue}}<span>{{dateValue}}</span>{{/if_not_empty}}
            {{#if_eq name 'abcdefghijk'}}<span>{{name}}</span>{{/if_eq}}
        </div>
    </script>
</my:html>
