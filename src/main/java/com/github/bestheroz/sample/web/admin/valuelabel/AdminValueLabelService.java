package com.github.bestheroz.sample.web.admin.valuelabel;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.sample.web.tablevo.samplecodedet.TableSampleCodeDetDAO;
import com.github.bestheroz.sample.web.tablevo.samplecodedet.TableSampleCodeDetVO;
import com.github.bestheroz.sample.web.tablevo.samplecodemst.TableSampleCodeMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplecodemst.TableSampleCodeMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminValueLabelService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Autowired
    private HttpSession session;
    @Autowired
    private TableSampleCodeMstDAO tableSampleCodeMstDAO;
    @Autowired
    private TableSampleCodeDetDAO tableSampleCodeDetDAO;

    public List<AdminValueLabelVO> getSampleCodeMstVOList(final AdminValueLabelVO vo) throws CommonException {
        final TableSampleCodeMstVO tableSampleCodeMstVO = MyMapperUtils.writeObjectAsObject(vo, TableSampleCodeMstVO.class);
        final Set<String> whereKeys = new HashSet<>();
        if (StringUtils.isNotEmpty(vo.getGroupCode())) {
            whereKeys.add("groupCode");
        }
        return MyMapperUtils.writeObjectAsArrayList(this.tableSampleCodeMstDAO.getList(tableSampleCodeMstVO, whereKeys, "UPDATED DESC"), AdminValueLabelVO.class);
    }

    public void insertSampleCodeMst(final TableSampleCodeMstVO vo, final LoginVO loginVO) throws CommonException {
        vo.setCreatedBy(loginVO.getMemberId());
        vo.setUpdatedBy(loginVO.getMemberId());
        this.tableSampleCodeMstDAO.insert(vo);
        this.session.removeAttribute("ValueLabel." + vo.getGroupCode());
    }

    public void updateSampleCodeMst(final TableSampleCodeMstVO vo, final LoginVO loginVO) throws CommonException {
        vo.setUpdatedBy(loginVO.getMemberId());
        this.tableSampleCodeMstDAO.update(vo, Collections.singleton("groupCode"), null);
        this.session.removeAttribute("ValueLabel." + vo.getGroupCode());
    }

    // @Transactional
    public void deleteCOMM_CODE(final TableSampleCodeMstVO vo) throws CommonException {
        /*
          <pre>
          아래 PlatformTransactionManager 를 이용하면 메소드 내에 특정 부분만을 트랜잭션 처리할 수 있다.
          일반적으로는 상단에 주석되어 있는 @Transactional 을 사용하여 메소드 전체를 트랜잭션 처리한다.
          </pre>
         */
        final DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        final TransactionStatus status = this.platformTransactionManager.getTransaction(defaultTransactionDefinition);

        try {
            final TableSampleCodeDetVO tableSampleCodeDetVO = MyMapperUtils.writeObjectAsObject(vo, TableSampleCodeDetVO.class);
            try {
                this.tableSampleCodeDetDAO.delete(tableSampleCodeDetVO, Collections.singleton("groupCode"));
            } catch (final CommonException e) {
                if (!e.isExceptionNoDataSuccesss()) {
                    this.logger.warn(ExceptionUtils.getStackTrace(e));
                    throw e;
                }
            }
            final TableSampleCodeMstVO tableSampleCodeMstVO = MyMapperUtils.writeObjectAsObject(vo, TableSampleCodeMstVO.class);
            this.tableSampleCodeMstDAO.delete(tableSampleCodeMstVO, Collections.singleton("groupCode"));
            this.session.removeAttribute("ValueLabel." + tableSampleCodeMstVO.getGroupCode());
            this.platformTransactionManager.commit(status);
        } catch (final CommonException e) {
            if (!status.isCompleted()) {
                this.platformTransactionManager.rollback(status);
            }
            this.logger.warn(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    // DETAIL PART
    public List<AdminValueLabelVO> getSampleCodeDetVOList(final AdminValueLabelVO vo) throws CommonException {
        final Set<String> whereKeys = new HashSet<>();
        whereKeys.add("groupCode");
        if (StringUtils.isNotEmpty(vo.getCode())) {
            whereKeys.add("code");
        }
        return MyMapperUtils.writeObjectAsArrayList(this.tableSampleCodeDetDAO.getList(MyMapperUtils.writeObjectAsObject(vo, TableSampleCodeDetVO.class), whereKeys, "UPDATED DESC"),
                AdminValueLabelVO.class);
    }

    public void insertSampleCodeDet(final TableSampleCodeDetVO vo, final LoginVO loginVO) throws CommonException {
        vo.setCreatedBy(loginVO.getMemberId());
        vo.setUpdatedBy(loginVO.getMemberId());
        this.tableSampleCodeDetDAO.insert(vo);
        this.session.removeAttribute("ValueLabel." + vo.getGroupCode());
    }

    public void updateSampleCodeDet(final TableSampleCodeDetVO vo, final LoginVO loginVO) throws CommonException {
        vo.setUpdatedBy(loginVO.getMemberId());
        this.tableSampleCodeDetDAO.update(vo, Sets.newHashSet("groupCode", "code"), null);
        this.session.removeAttribute("ValueLabel." + vo.getGroupCode());
    }

    public void deleteSampleCodeDet(final TableSampleCodeDetVO vo) throws CommonException {
        this.tableSampleCodeDetDAO.delete(vo, Sets.newHashSet("groupCode", "code"));
        this.session.removeAttribute("ValueLabel." + vo.getGroupCode());
    }

}
