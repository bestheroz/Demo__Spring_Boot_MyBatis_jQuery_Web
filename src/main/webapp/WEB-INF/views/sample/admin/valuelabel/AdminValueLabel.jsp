<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>
<my:html table="YES" modal="YES" validator="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <h1 class="display-4 text-center">코드관리
                <small>
                    For 관리자
                    <i class="fas fa-wrench"></i>
                </small>
            </h1>
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-6">
                            <div class="table-responsive">
                                <table id="table1" class="table table-bordered table-hover table-sm" data-order='[[ 3, "desc" ]]' data-page-length="10">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>
                                            <i class="fas fa-key"></i>
                                            &nbsp;그룹코드
                                        </th>
                                        <th>그룹코드명</th>
                                        <th>수정자</th>
                                        <th>수정일</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="table-responsive">
                                <table id="table2" class="table table-bordered table-hover table-sm" data-order='[[ 5, "desc" ]]' data-page-length="10">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th>
                                            <i class="fas fa-key"></i>
                                            &nbsp;코드
                                        </th>
                                        <th>코드명</th>
                                        <th>사용여부</th>
                                        <th>출력순서</th>
                                        <th>수정자</th>
                                        <th>수정일</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div id="modalValueLabel" class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">그룹코드 입력</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="form1" class="form-horizontal">
                            <div class="form-group row">
                                <label for="groupCode" class="col-3 col-form-label">그룹코드</label>
                                <div class="col-9">
                                    <input type="text" id="groupCode" class="form-control" title="그룹코드" maxlength="100"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="groupCodeName" class="col-3 col-form-label">그룹코드명</label>
                                <div class="col-9">
                                    <input type="text" id="groupCodeName" class="form-control" title="그룹코드명" maxlength="300"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="remark1" class="col-3 col-form-label">비고</label>
                                <div class="col-9">
                                    <input type="text" id="remark1" class="form-control" title="비고" maxlength="1000"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-outline-primary" onclick="MyModal.close($('#modalValueLabel'));">
                                <i class="far fa-window-close"></i>
                            </button>

                            <div class="btn-group" role="group">
                                <button id="btnGroupDrop1" type="button" class="btn btn-outline-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                                <div class="dropdown-menu color-danger" aria-labelledby="btnGroupDrop1">
                                    <a class="dropdown-item" href="javascript:void(0);" onclick="deleteValueLabel();">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </div>
                            </div>

                            <button type="button" class="btn btn-outline-success" onclick="saveValueLabel();">
                                <i class="far fa-save"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div id="modalValueLabelDet" class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">그룹코드-코드 입력</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="form2" class="form-horizontal">
                            <div class="form-group row">
                                <label for="groupCodeDet" class="col-3 col-form-label">그룹코드</label>
                                <div class="col-9">
                                    <input type="text" id="groupCodeDet" class="form-control" title="그룹코드" maxlength="100" readonly/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="code" class="col-3 col-form-label">코드</label>
                                <div class="col-9">
                                    <input type="text" id="code" class="form-control" title="코드" maxlength="100"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="codeName" class="col-3 col-form-label">코드명</label>
                                <div class="col-9">
                                    <input type="text" id="codeName" class="form-control" title="코드명" maxlength="300"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="useTf" class="col-3 col-form-label">사용여부</label>
                                <div class="col-9">
                                    <input type="checkbox" class="form-control" id="useTf">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="displayOrder" class="col-3 col-form-label">출력순서</label>
                                <div class="col-9">
                                    <input type="text" id="displayOrder" class="form-control" title="출력순서" maxlength="10" data-parsley-type="digits"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="remark1Det" class="col-3 col-form-label">비고</label>
                                <div class="col-9">
                                    <input type="text" id="remark1Det" class="form-control" title="비고" maxlength="1000"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-outline-primary" onclick="MyModal.close($('#modalValueLabelDet'));">
                                <i class="far fa-window-close"></i>
                            </button>

                            <div class="btn-group" role="group">
                                <button id="btnGroupDrop2" type="button" class="btn btn-outline-danger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                                <div class="dropdown-menu color-danger" aria-labelledby="btnGroupDrop2">
                                    <a class="dropdown-item" href="javascript:void(0);" onclick="deleteValueLabelDet();">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </div>
                            </div>

                            <button type="button" class="btn btn-outline-success" onclick="saveValueLabelDet();">
                                <i class="far fa-save"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal -->
    </main>
    <my:footer/>
    <script data-for="ready">
        jQuery(($) => {
            $.when(drawTable1(), drawTable2(), MyAjax.getSelectOptions($("#useTf"), "${CONTEXT_PATH}/common/valuelabel/getValueLabelVOList.json", {
                groupCode: "USE_TF"
            })).done(() => {
                selectList();
            });
            $('#table2_wrapper > div.dt-buttons > a').addClass('disabled');
            $('#table1').DataTable().on('select', (e, dt, type, indexes) => {
                const rowData = $('#table1').DataTable().rows(indexes).data().toArray()[0];
                $('#table2_wrapper > div.dt-buttons > a').removeClass('disabled');
                selectListDet(rowData.groupCode);
            });
            $('#table1').DataTable().on('deselect', (e, dt, type, indexes) => {
                $('#table2_wrapper > div.dt-buttons > a').addClass('disabled');
                $('#table2').DataTable().clear().draw();
            });
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
                        modalAddValueLabel();
                    }
                }, {
                    text: '<i class="fas fa-pencil-alt"></i>',
                    titleAttr: '수정',
                    action: (e, dt, node, config) => {
                        modalModifyValueLabel();
                    }
                }, {
                    text: '<i class="fas fa-trash-alt"></i>',
                    titleAttr: '삭제',
                    action: (e, dt, node, config) => {
                        deleteValueLabel();
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
                    title: '코드관리MST데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-file-excel"></i>',
                    titleAttr: 'EXCEL'
                }, {
                    extend: 'pdfHtml5',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '코드관리MST데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-file-pdf"></i>',
                    titleAttr: 'PDF'
                }, {
                    extend: 'print',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '코드관리MST데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-print"></i>',
                    titleAttr: '프린트'
                }],
                columnDefs: [{
                    targets: "_all",
                    defaultContent: ""
                }, {
                    targets: [2, 3],
                    visible: false
                }, {
                    targets: 2,
                    width: 100
                }, {
                    targets: 3,
                    width: 120,
                    className: "text-center",
                    render: (data, type, row) => {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }],
                columns: [{
                    "data": "groupCode"
                }, {
                    "data": "groupCodeName"
                }, {
                    "data": "updatedBy"
                }, {
                    "data": "updated"
                }]
            }).on('column-visibility.dt', function (e, indicator) {
                $('#table1').DataTable().columns.adjust().responsive.recalc();
            });
        }

        function drawTable2() {
            $('#table2').DataTable({
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
                        modalAddValueLabelDet();
                    }
                }, {
                    text: '<i class="fas fa-pencil-alt"></i>',
                    titleAttr: '수정',
                    action: (e, dt, node, config) => {
                        modalModifyValueLabelDet();
                    }
                }, {
                    text: '<i class="fas fa-trash-alt"></i>',
                    titleAttr: '삭제',
                    action: (e, dt, node, config) => {
                        deleteValueLabelDet();
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
                    title: '코드관리DET데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-file-excel"></i>',
                    titleAttr: 'EXCEL'
                }, {
                    extend: 'pdfHtml5',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '코드관리DET데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-file-pdf"></i>',
                    titleAttr: 'PDF'
                }, {
                    extend: 'print',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '코드관리DET데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fas fa-print"></i>',
                    titleAttr: '프린트'
                }],
                columnDefs: [{
                    targets: "_all",
                    defaultContent: ""
                }, {
                    targets: [4, 5],
                    visible: false
                }, {
                    targets: 2,
                    width: 80,
                    className: "text-center",
                    render: (data, type, row) => {
                        return data ? '<input type="checkbox" checked disabled />' :
                            '<input type="checkbox" disabled />'
                    }
                }, {
                    targets: 3,
                    width: 60,
                    className: "text-right"
                }, {
                    targets: 4,
                    width: 100
                }, {
                    targets: 5,
                    width: 120,
                    className: "text-center",
                    render: (data, type, row) => {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }],
                columns: [{
                    "data": "code"
                }, {
                    "data": "codeName"
                }, {
                    "data": "useTf"
                }, {
                    "data": "displayOrder"
                }, {
                    "data": "updatedBy"
                }, {
                    "data": "updated"
                }]
            }).on('column-visibility.dt', function (e, indicator) {
                $('#table2').DataTable().columns.adjust().responsive.recalc();
            });
        }
    </script>
    <script>
        async function selectList() {
            const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/valuelabel/getSampleCodeMstVOList.json', {});
            $('#table1').DataTable().clear().rows.add(response).draw();
        }

        async function selectListDet(groupCode) {
            const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/valuelabel/getSampleCodeDetVOList.json', {
                groupCode: groupCode
            });
            $('#table2').DataTable().clear().rows.add(response).draw();
        }

        function modalAddValueLabel() {
            $('#form1')[0].reset();
            $('div.modal-footer>div.btn-group>div.btn-group').hide();
            MyModal.open($('#modalValueLabel'));
            $('#groupCode').prop('readonly', false);
        }

        function modalModifyValueLabel() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("수정하실 항목을 선택해주세요.");
                return;
            }
            $('#form1')[0].reset();
            $('div.modal-footer>div.btn-group>div.btn-group').show();
            MyModal.open($('#modalValueLabel'));
            $('#groupCode').val(selectedRow.groupCode).prop('readonly', true);
            $('#groupCodeName').val(selectedRow.groupCodeName);
            $('#remark1').val(selectedRow.remark1);
        }

        async function saveValueLabel() {
            if (MyValidator.validate($('#form1'), true) !== null) {
                return;
            }
            let url;
            if ($('div.modal-footer:visible>div.btn-group>div.btn-group').is(':hidden')) {
                url = '${CONTEXT_PATH}/sample/admin/valuelabel/insertSampleCodeMst.json';
            } else {
                url = '${CONTEXT_PATH}/sample/admin/valuelabel/updateSampleCodeMst.json';
            }
            const response = await MyAjax.execute(url, {
                groupCode: $('#groupCode').val(),
                groupCodeName: $('#groupCodeName').val(),
                remark1: $('#remark1').val()
            }, {
                autoResultFunctionTF: true
            });
            if (_.startsWith(response.responseCode, 'S')) {
                MyModal.close($('#modalValueLabel'));
                await selectList();
                $('#table2').DataTable().clear().draw();
            }
        }

        async function deleteValueLabel() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("삭제하실 항목을 선택해주세요.");
                return;
            }
            if (confirm("정말 삭제하시겠습니까?")) {
                const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/valuelabel/deleteCOMM_CODE.json', {
                    groupCode: selectedRow.groupCode
                }, {
                    autoResultFunctionTF: true
                });
                if (_.startsWith(response.responseCode, 'S')) {
                    MyModal.close($('#modalValueLabel'));
                    await selectList();
                    $('#table2').DataTable().clear().draw();
                }
            }
        }

        function modalAddValueLabelDet() {
            const selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("추가하실 그룹코드 항목을 우선적으로 선택해주세요.");
                return;
            }
            $('#form2')[0].reset();
            $('div.modal-footer>div.btn-group>div.btn-group').hide();
            MyModal.open($('#modalValueLabelDet'));
            $('#code').prop('readonly', false);
            $('#groupCodeDet').val(selectedRow.groupCode);
        }

        function modalModifyValueLabelDet() {
            const selectedRow = $('#table2').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("수정하실 항목을 선택해주세요.");
                return;
            }
            const selectedRow1 = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow1)) {
                alert("추가하실 그룹코드 항목을 우선적으로 선택해주세요.");
                return;
            }
            $('#form2')[0].reset();
            $('div.modal-footer>div.btn-group>div.btn-group').show();
            MyModal.open($('#modalValueLabelDet'));
            $('#code').prop('readonly', true);
            $('#groupCodeDet').val(selectedRow1.groupCode);
            $('#code').val(selectedRow.code);
            $('#codeName').val(selectedRow.codeName);
            $('#useTf').prop('checked', selectedRow.useTf);
            $('#displayOrder').val(selectedRow.displayOrder);
            $('#remark1Det').val(selectedRow.remark1);
        }

        async function saveValueLabelDet() {
            if (MyValidator.validate($('#form2'), true) !== null) {
                return;
            }
            let url;
            if ($('div.modal-footer:visible>div.btn-group>div.btn-group').is(':hidden')) {
                url = '${CONTEXT_PATH}/sample/admin/valuelabel/insertSampleCodeDet.json';
            } else {
                url = '${CONTEXT_PATH}/sample/admin/valuelabel/updateSampleCodeDet.json';
            }
            const response = await MyAjax.execute(url, {
                groupCode: $('#groupCodeDet').val(),
                code: $('#code').val(),
                codeName: $('#codeName').val(),
                useTf: $('#useTf').prop('checked'),
                displayOrder: $('#displayOrder').val(),
                remark1: $('#remark1Det').val()
            }, {
                autoResultFunctionTF: true
            });
            if (_.startsWith(response.responseCode, 'S')) {
                MyModal.close($('#modalValueLabelDet'));
                await selectListDet($('#groupCodeDet').val());
            }
        }

        async function deleteValueLabelDet() {
            const selectedRow = $('#table2').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("삭제하실 항목을 선택해주세요.");
                return;
            }

            if (confirm("정말 삭제하시겠습니까?")) {
                const response = await MyAjax.execute('${CONTEXT_PATH}/sample/admin/valuelabel/deleteSampleCodeDet.json', {
                    groupCode: selectedRow.groupCode,
                    code: selectedRow.code
                }, {
                    autoResultFunctionTF: true
                });
                if (_.startsWith(response.responseCode, 'S')) {
                    MyModal.close($('#modalValueLabelDet'));
                    await selectListDet(selectedRow.groupCode);
                }
            }
        }
    </script>
</my:html>
