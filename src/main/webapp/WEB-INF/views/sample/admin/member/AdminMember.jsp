<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html table="YES" modal="YES" validator="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <h1 class="display-4 text-center">회원관리
                <small>
                    For 관리자
                    <i class="fas fa-wrench"></i>
                </small>
            </h1>
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table id="table1" class="table table-bordered table-hover table-sm" data-order='[[ 7, "desc" ]]' data-page-length="10">
                            <thead class="thead-dark">
                            <tr>
                                <th>
                                    <i class="fas fa-key"></i>
                                    &nbsp;회원ID
                                </th>
                                <th>회원명</th>
                                <th>회원종류</th>
                                <th>로그인실패횟수</th>
                                <th>계정잠금여부</th>
                                <th>
                                    <i class="fas fa-user-lock"></i>
                                    &nbsp;계정만료일
                                </th>
                                <th>수정자</th>
                                <th>수정일</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div id="modalMember" class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">회원 입력</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group row">
                                <label for="memberId" class="col-3 col-form-label">회원ID</label>
                                <div class="col-9">
                                    <input type="text" id="memberId" class="form-control" title="회원" maxlength="20"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="memberName" class="col-3 col-form-label">회원명</label>
                                <div class="col-9">
                                    <input type="text" id="memberName" class="form-control" title="회원명" maxlength="30"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="memberPw" class="col-3 col-form-label">비밀번호</label>
                                <div class="col-9">
                                    <input type="password" id="memberPw" class="form-control" title="비밀번호" data-parsley-minlength="5" maxlength="20"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="memberPw2" class="col-3 col-form-label">비밀번호(확인용)</label>
                                <div class="col-9">
                                    <input type="password" id="memberPw2" class="form-control" title="비밀번호(확인용)" data-parsley-minlength="5" maxlength="20"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="memberType" class="col-3 col-form-label">회원종류</label>
                                <div class="col-9">
                                    <select id="memberType" class="form-control" title="회원종류" style="width: 70%" required></select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="loginFailCnt" class="col-3 col-form-label">로그인 실패 횟수</label>
                                <div class="col-9">
                                    <input type="number" id="loginFailCnt" class="form-control" title="비밀번호(확인용)" data-parsley-minlength="1" maxlength="10"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="closeTf" class="col-3 col-form-label">계정잠금여부</label>
                                <div class="col-9">
                                    <input type="checkbox" class="form-control" id="closeTf">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="expired" class="col-3 col-form-label">계정사용 만기일</label>
                                <div class="col-9">
                                    <input type="text" id="expired" class="form-control" title="계정사용 만기일"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-outline-primary" onclick="MyModal.close($('#modalMember'));">
                                <i class="far fa-window-close"></i>
                            </button>
                            <div class="btn-group" role="group">
                                <button id="btnGroupDrop1" type="button" class="btn btn-outline-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                                <div class="dropdown-menu color-danger" aria-labelledby="btnGroupDrop1">
                                    <a class="dropdown-item" href="javascript:void(0);" onclick="deleteMember();">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </div>
                            </div>

                            <button type="button" class="btn btn-outline-success" onclick="saveMember();">
                                <i class="far fa-save"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <!-- /.modal -->
    <my:footer/>
    <script data-for="ready">
        jQuery(($) => {
            $.when(drawTable1(), MyAjax.getSelectOptions($("#memberType"), "${CONTEXT_PATH}/common/valuelabel/getValueLabelVOList.json", {
                groupCode: "MEMBER_TYPE"
            })).done(() => {
                selectList();
            });
            MyDatetimePicker.makeDatetimepicker('#expired', 'YYYY-MM-DD HH:mm');
        });
    </script>
    <script data-for="define-table">
        function drawTable1() {
            $('#table1').DataTable({
                dom: 'Bfrtip',
                lengthChange: false,
                language: MyDataTables.language,
                pagingType: "full_numbers",
                select: true,
                responsive: true,
                scrollY: '400px',
                fixedColumns: {
                    leftColumns: 2
                    // 	            ,rightColumns: 1
                },
                buttons: [{
                    text: '<i class="fas fa-plus"></i>',
                    titleAttr: '추가',
                    action: (e, dt, node, config) => {
                        modalAddMember();
                    }
                }, {
                    text: '<i class="fas fa-pencil-alt"></i>',
                    titleAttr: '수정',
                    action: (e, dt, node, config) => {
                        modalModifyMember();
                    }
                }, {
                    text: '<i class="fas fa-trash-alt"></i>',
                    titleAttr: '삭제',
                    action: (e, dt, node, config) => {
                        deleteMember();
                    }
                }, {
                    extend: 'colvis',
                    text: '<i class="fas fa-eye"></i>',
                    titleAttr: '컬럼선택'
                }, {
                    extend: 'excelHtml5',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '회원관리데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-file-excel"></i>',
                    titleAttr: 'EXCEL'
                }, {
                    extend: 'print',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '회원관리데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-print"></i>',
                    titleAttr: '프린트'
                }],
                columnDefs: [{
                    targets: "_all",
                    defaultContent: ""
                }, {
                    targets: [6, 7],
                    visible: false
                }, {
                    targets: 2,
                    width: 100,
                    className: "text-center",
                    render: (data, type, row) => {
                        return MyCommon.getLabelFromSelectTag($('#memberType'), data);
                    }
                }, {
                    targets: 3,
                    width: 110,
                    className: "text-right"
                }, {
                    targets: 4,
                    width: 100,
                    className: "text-center",
                    render: (data, type, row) => {
                        return data ? '<input type="checkbox" checked disabled />' :
                            '<input type="checkbox" disabled />'
                    }
                }, {
                    targets: 5,
                    width: 120,
                    className: "text-center",
                    render: (data, type, row) => {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }, {
                    targets: 6,
                    width: 100
                }, {
                    targets: 7,
                    width: 120,
                    className: "text-center",
                    render: (data, type, row) => {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }],
                columns: [{
                    "data": "memberId"
                }, {
                    "data": "memberName"
                }, {
                    "data": "memberType"
                }, {
                    "data": "loginFailCnt"
                }, {
                    "data": "closeTf"
                }, {
                    "data": "expired"
                }, {
                    "data": "updatedBy"
                }, {
                    "data": "updated"
                }]
            }).on('column-visibility.dt', function (e, indicator) {
                $('#table1').DataTable().columns.adjust().responsive.recalc();
            });
        }
    </script>
    <script>
        async function selectList() {
            const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/member/getSampleMemberMstVOList.json', {});
            $('#table1').DataTable().clear().rows.add(response).draw();
        }

        async function saveMember() {
            if (MyValidator.validate($('form.form-horizontal'), true) !== null) {
                return;
            }
            if (!_.isEqual($('#memberPw').val(), $('#memberPw2').val())) {
                alert("입력하신 비밀번호와 비밀번호(확인용)이 서로 같지 않습니다.");
                return;
            }
            let memberPw;
            if (MyCommon.isEmpty(_.trim($('#memberPw').val()))) {
                memberPw = null;
            } else {
                memberPw = CryptoJS.SHA512($('#memberPw').val()).toString();
            }
            let url;
            if ($('div.modal-footer:visible>div.btn-group>div.btn-group').is(':hidden')) {
                url = '${CONTEXT_PATH}/sample/admin/member/insertSampleMemberMst.json';
            } else {
                url = '${CONTEXT_PATH}/sample/admin/member/updateSampleMemberMst.json';
            }
            const response = await MyAjax.execute(url, {
                memberId: $('#memberId').val(),
                memberName: $('#memberName').val(),
                memberPw: memberPw,
                memberType: $('#memberType option:selected').val(),
                loginFailCnt: $('#loginFailCnt').val(),
                closeTf: $('#closeTf').prop('checked'),
                expired: $('#expired').datetimepicker('viewDate').valueOf()
            }, {
                autoResultFunctionTF: true
            });
            if (_.startsWith(response.responseCode, 'S')) {
                MyModal.close($('#modalMember'));
                await selectList();
            }
        }

        async function deleteMember() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("삭제하실 항목을 선택해주세요.");
                return;
            }
            if (confirm("정말 삭제하시겠습니까?")) {
                const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/member/deleteSampleMemberMst.json', {
                    memberId: selectedRow.memberId
                }, {
                    autoResultFunctionTF: true
                });
                if (_.startsWith(response.responseCode, 'S')) {
                    MyModal.close($('#modalMember'));
                    await selectList();
                }
            }
        }

        function modalAddMember() {
            $('form.form-horizontal')[0].reset();
            $('div.modal-footer>div.btn-group>div.btn-group').hide();
            MyModal.open($('#modalMember'));
            $('#memberId').prop('disabled', false);
            $('#loginFailCnt').val(0);
            $('#expired').val(moment().add(1, 'years').format('YYYY-MM-DD 23:59:59')).trigger('change.datetimepicker');
        }

        function modalModifyMember() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("수정하실 항목을 선택해주세요.");
                return;
            }
            $('form.form-horizontal')[0].reset();
            $('div.modal-footer>div.btn-group>div.btn-group').show();
            MyModal.open($('#modalMember'));
            $('#memberId').val(selectedRow.memberId).prop('disabled', true);
            $('#memberName').val(selectedRow.memberName);
            $('#memberType').val(selectedRow.memberType);
            $('#loginFailCnt').val(selectedRow.loginFailCnt);
            $('#closeTf').prop('checked', selectedRow.closeTf);
            $('#expired').val(moment(selectedRow.expired).format('YYYY-MM-DD HH:mm')).trigger('change.datetimepicker');
        }
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js"></script>
</my:html>
