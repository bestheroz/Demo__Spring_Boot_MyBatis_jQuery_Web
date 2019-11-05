<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html>
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">달력 (date time picker)
                    <small>사용하기</small>
                </h1>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. 날짜 선택(date picker) </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. 날짜 선택(from ~ to) </a>
                    <a href="#list3" class="list-group-item list-group-item-action list-group-item-light"> 3. 시간 선택(time picker) </a>
                    <a href="#list4" class="list-group-item list-group-item-action list-group-item-light"> 4. 시간 선택(from ~ to) </a>
                    <a href="#list5" class="list-group-item list-group-item-action list-group-item-light"> 5. 선택 불가능 날짜 설정(disable date) </a>
                    <a href="#list6" class="list-group-item list-group-item-action list-group-item-light"> 6. 날짜 선택 범위 설정(range date) </a>
                    <a href="#list7" class="list-group-item list-group-item-action list-group-item-light"> 7. 날짜 + 시간 선택(date&amp;time picker) </a>
                </div>
            </div>
            <div class="card" id="list1">
                <h3 class="panel-title text-center">1. 날짜 선택(date picker)</h3>
                <div class="card-body">
                    <h4>YYYY-MM-DD(기본형)</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">
MyDatetimePicker.makeDatetimepicker('#resultTry1_1');
MyDatetimePicker.makeDatetimepicker('#resultTry1_1', 'YYYY-MM-DD');
</pre>
                    <h4>YYYY-MM</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry1_2" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry1_2', 'YYYY-MM');</pre>
                    <h4>YYYY</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry1_3" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry1_2', 'YYYY');</pre>
                </div>
            </div>

            <div class="card" id="list2">
                <h3 class="panel-title text-center">2. 날짜 선택(from ~ to)</h3>
                <div class="card-body">
                    <h4>YYYY-MM-DD(기본형)</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry2_1_1" placeholder="result..." readonly/>
					</span>
                        <span class="col-2">
						<input type="text" class="form-control" id="resultTry2_1_2" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.setFromTo($('#from'), $('#to'), 'YYYY-MM-DD');</pre>
                    <h4>YYYY-MM</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry2_2_1" placeholder="result..." readonly/>
					</span>
                        <span class="col-2">
						<input type="text" class="form-control" id="resultTry2_2_2" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.setFromTo($('#from'), $('#to'), 'YYYY-MM');</pre>
                    <h4>YYYY</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry2_3_1" placeholder="result..." readonly/>
					</span>
                        <span class="col-2">
						<input type="text" class="form-control" id="resultTry2_3_2" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.setFromTo($('#from'), $('#to'), 'YYYY');</pre>
                </div>
            </div>

            <div class="card" id="list3">
                <h3 class="panel-title text-center">3. 시간 선택(time picker)</h3>
                <div class="card-body">
                    <h4>HH:mm(기본형)</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry3_1" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry3_1', 'HH:mm');</pre>
                    <h4>HH</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry3_2" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry3_2', 'HH');</pre>
                    <h4>HH:mm:ss</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry3_3" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry3_3', 'HH:mm:ss');</pre>
                </div>
            </div>

            <div class="card" id="list4">
                <h3 class="panel-title text-center">4. 시간 선택(from ~ to)</h3>
                <div class="card-body">
                    <h4>hh:mm(기본형)</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry4_1_1" placeholder="result..." readonly/>
					</span>
                        <span class="col-2">
						<input type="text" class="form-control" id="resultTry4_1_2" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.setFromTo($('#from'), $('#to'), 'HH:mm');</pre>
                </div>
            </div>

            <div class="card" id="list5">
                <h3 class="panel-title text-center">5. 선택 불가능 날짜 설정(disable date)</h3>
                <div class="card-body">
                    <h4>특정일 제외</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry5_1" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry5_1').datetimepicker('disabledDates', [moment().subtract(1, 'days'), moment().add(1, 'days')]);</pre>
                    <h4>공휴일 제외</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry5_2" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">
MyDatetimePicker.makeDatetimepicker('#resultTry5_2').datetimepicker('disabledDates', ["20190101", "20190301", "20190505", "20190606", "20190815", "20191003", "20191009", "20191225", "20191004", "20191005", "20191006"]);</pre>
                    <h4>토, 일요일 제외</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry5_3" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry5_3').datetimepicker('daysOfWeekDisabled', [0, 6]);</pre>
                </div>
            </div>

            <div class="card" id="list6">
                <h3 class="panel-title text-center">6. 날짜 선택 범위 설정(range date)</h3>
                <div class="card-body">
                    <h4>범위 지정</h4>
                    <div class="row">
					<span class="col-2">
						<input type="text" class="form-control" id="resultTry6_1" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry6_1').datetimepicker('minDate', moment().add(3, 'days')).datetimepicker('maxDate', moment().add(7, 'days'));</pre>
                </div>
            </div>

            <div class="card" id="list7">
                <h3 class="panel-title text-center">7. 날짜 + 시간 선택(date&amp;time picker)</h3>
                <div class="card-body">
                    <h4>YYYY-MM-DD HH:mm(기본형)</h4>
                    <div class="row">
					<span class="col-3">
						<input type="text" class="form-control" id="resultTry7_1" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry7_1', 'YYYY-MM-DD HH:mm');</pre>
                    <h4>YYYY-MM-DD HH</h4>
                    <div class="row">
					<span class="col-3">
						<input type="text" class="form-control" id="resultTry7_2" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry7_2', 'YYYY-MM-DD HH');</pre>
                    <h4>YYYY-MM-DD HH:mm:ss</h4>
                    <div class="row">
					<span class="col-3">
						<input type="text" class="form-control" id="resultTry7_3" placeholder="result..." readonly/>
					</span>
                    </div>
                    <pre class="prettyprint lang-js">MyDatetimePicker.makeDatetimepicker('#resultTry7_3', 'YYYY-MM-DD HH:mm:ss');</pre>

                </div>
            </div>
        </div>
    </main>
    <my:footer/>
    <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
    <script data-for="ready">
        jQuery(($) => {
            MyDatetimePicker.makeDatetimepicker('#resultTry1_1');
            MyDatetimePicker.makeDatetimepicker('#resultTry1_2', 'YYYY-MM');
            MyDatetimePicker.makeDatetimepicker('#resultTry1_3', 'YYYY');
            MyDatetimePicker.setFromTo($('#resultTry2_1_1'), $('#resultTry2_1_2'), 'YYYY-MM-DD');
            MyDatetimePicker.setFromTo($('#resultTry2_2_1'), $('#resultTry2_2_2'), 'YYYY-MM');
            MyDatetimePicker.setFromTo($('#resultTry2_3_1'), $('#resultTry2_3_2'), 'YYYY');
            MyDatetimePicker.makeDatetimepicker('#resultTry3_1', 'HH:mm');
            MyDatetimePicker.makeDatetimepicker('#resultTry3_2', 'HH');
            MyDatetimePicker.makeDatetimepicker('#resultTry3_3', 'HH:mm:ss');
            MyDatetimePicker.setFromTo($('#resultTry4_1_1'), $('#resultTry4_1_2'), 'HH:mm');
            MyDatetimePicker.makeDatetimepicker('#resultTry5_1').datetimepicker('disabledDates', [moment().subtract(1, 'days'), moment().add(1, 'days')]);
            MyDatetimePicker.makeDatetimepicker('#resultTry5_2').datetimepicker('disabledDates', ["20190101", "20190301", "20190505", "20190606", "20190815", "20191003", "20191009", "20191225", "20191004", "20191005", "20191006"]);
            MyDatetimePicker.makeDatetimepicker('#resultTry5_3').datetimepicker('daysOfWeekDisabled', [0, 6]);
            MyDatetimePicker.makeDatetimepicker('#resultTry6_1').datetimepicker('minDate', moment().add(3, 'days'));
            $('#resultTry6_1').datetimepicker('maxDate', moment().add(7, 'days'));
            MyDatetimePicker.makeDatetimepicker('#resultTry7_1', 'YYYY-MM-DD HH:mm');
            MyDatetimePicker.makeDatetimepicker('#resultTry7_2', 'YYYY-MM-DD HH');
            MyDatetimePicker.makeDatetimepicker('#resultTry7_3', 'YYYY-MM-DD HH:mm:ss');
        });
    </script>
</my:html>
