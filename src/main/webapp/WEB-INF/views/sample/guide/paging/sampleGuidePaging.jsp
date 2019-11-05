<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html paging="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">페이징
                    <small>처리하기</small>
                </h1>
                <hr class="my-4">
                <h4>required</h4>
                <pre class="prettyprint lang-html">&lt;my:html paging="YES"&gt;</pre>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. &lt;button&gt; 페이징 처리 </a>
                        <%--                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. &lt;a&gt; 페이징 처리 </a>--%>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. &lt;button&gt; 페이징 처리</h3>
                <div class="card-body">
                    <nav>
                        <ul class="pagination">
                            <li>
                                <a href="javascript:void(0);" aria-label="First">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" aria-label="Previous">
                                    <span aria-hidden="true">&lt;</span>
                                </a>
                            </li>
                            <li class="active">
                                <a href="javascript:void(0);" data-page-number="true">1</a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" data-page-number="true" style="display: none;">2</a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" data-page-number="true" style="display: none;">3</a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" data-page-number="true" style="display: none;">4</a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" data-page-number="true" style="display: none;">5</a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" aria-label="Next">
                                    <span aria-hidden="true">&gt;</span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0);" aria-label="Last">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-primary" type="button" onclick="try1_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-html">
&lt;nav&gt;
    &lt;ul class="pagination"&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" aria-label="Previous"&gt;
                &lt;span aria-hidden="true"&gt;&laquo;&lt;/span&gt;
            &lt;/a&gt;
        &lt;/li&gt;
        &lt;li class="active"&gt;
            &lt;a href="javascript:void(0);"&gt;1&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" style="display: none;"&gt;2&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" style="display: none;"&gt;3&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" style="display: none;"&gt;4&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" style="display: none;"&gt;5&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" aria-label="Next"&gt;
                &lt;span aria-hidden="true"&gt;&raquo;&lt;/span&gt;
            &lt;/a&gt;
        &lt;/li&gt;
    &lt;/ul&gt;
&lt;/nav&gt;</pre>
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
            MyPaging.firstBtnObj = $('ul.pagination > li > a').first();
            MyPaging.prevBtnObj = $('ul.pagination > li > a').eq(1);
            MyPaging.nextBtnObj = $('ul.pagination > li > a').eq(7);
            MyPaging.lastBtnObj = $('ul.pagination > li > a').last();
            MyPaging.numberOfDisplayPages = 5;
            MyPaging.numberGroupObj = $('ul.pagination > li > a[data-page-number="true"]');
            MyPaging.numberSelectedClassName = 'active';
            MyPaging.positionSelectedClassName = 'parent';
            MyPaging.selectListFunctionName = selectList;
            MyPaging.init();
            selectList();
        }

        async function selectList() {
            const response = await MyAjax.execute('${CONTEXT_PATH }/sample/guide/paging/sampleGuidePaging.json', {});
            MyPaging.changeValue(MyPaging.getSelectedPageIndex(), response.totalItemCount, response.itemPerPage, response.nextPage);
        }
    </script>
</my:html>
